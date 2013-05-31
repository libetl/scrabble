package org.toilelibre.libe.scrabble.applicationlayer.actions.party;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;

import org.toilelibre.libe.scrabble.applicationlayer.beans.GameBean;
import org.toilelibre.libe.scrabble.applicationlayer.beans.party.ChangeTurnBean;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.bounds.TurnSpecificAlign;
import org.toilelibre.libe.scrabble.s3d.util.scene.SceneStepper;
import org.toilelibre.libe.scrabble.sessions.ScrabbleSessionsHandler;
import org.toilelibre.libe.scrabble.sessions.Session;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.actions.ActionRedirect;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.timers.TimerHelper;

public final class ChangeTurnAction extends Action
{

  private double [][]            goTo;

  private Method                 methodToCallAtEnd;
  private ITransformGroup        stgToUse;

  public ChangeTurnAction ()
  {
  }
  
  public ActionRedirect changeTurn (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
  {
    final UserInteractions ui = this.getUiOwner ();
    final GameBean gb = (GameBean) ui.getBean ("gameBean");
    final Session session = ScrabbleSessionsHandler.get ();
    session.put (ChangeTurnBean.TURN, 
        new Integer ((((Integer) session.get (ChangeTurnBean.TURN))
        .intValue () + 1) % 
        ((Integer)session.get (ChangeTurnBean.NB_PLAYERS)).intValue ()));

    this.stgToUse = gb.getSc3d ().getCameraTransformGroup ();
    return this.playPosition (bean, listenerType, actionName, e);
  }

  /**
   * Choisir lettres
   * @param bean
   * @param listenerType
   * @param actionName
   * @param e
   * @return
   */
  public ActionRedirect deployLetters (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
  {
    final ChangeTurnBean ctb = (ChangeTurnBean) bean;
    final UserInteractions ui = this.getUiOwner ();
    final GameBean gb = (GameBean) ui.getBean (GameBean.ID_BEAN);
    final Session session = ScrabbleSessionsHandler.get ();
    final int turn = ((Integer) session.get (ChangeTurnBean.TURN)).intValue ();
    ILetterBranchGroup [] [] lbgs = ctb.getLetterBranchGroups ();
    if (lbgs == null)
    {
      lbgs = new ILetterBranchGroup 
               [((Integer)session.get (ChangeTurnBean.NB_PLAYERS)).intValue ()] 
               [ChangeTurnBean.TRAY_LENGTH];
    }
    final double [] initPosition = new double [ChangeTurnBean.TRAY_LENGTH];
    for (int i = 0; i < ChangeTurnBean.TRAY_LENGTH; i++)
    {
        final ILetterBranchGroup lbg = S3DHelper.cloneLetter ();
        final Character c = (Character) 
              ScrabbleBeansHelper.getComponent ("pickLetter")
                                 .execute (new Integer (turn), new Integer (i));
        if (c != null)
        {
          final int value = ((Integer) 
              ScrabbleBeansHelper.getComponent ("getLetterValue")
                                 .execute (c)).intValue ();
          lbg.affectLetter (c.charValue ());
          lbg.setIdTray (i);
          lbg.setPoints (value);
          lbgs [turn] [i] = lbg;
          lbg.setTransform (
            S3DHelper.newTransform (new Object [] {
                TurnSpecificAlign.PIOCHE_VIEWPOINTS[turn],}));
        }
      initPosition[i] = TurnSpecificAlign.INIT_POSITIONS [turn];
    }
    S3DHelper.cloneLetter ();
    for (int i = 0; i < ChangeTurnBean.TRAY_LENGTH; i++)
    {
      if (lbgs [turn] [i] != null){
        gb.getSc3d ().getScrabbleBranchGroup ().addBranchGraph (lbgs[turn] [i]);
      }
    }
    ctb.setLetterBranchGroups (lbgs);
    ctb.setLetterPositions (initPosition);
    this.getTimers ().get (ChangeTurnBean.MOVEMENT_LETTER).start ();
    return null;
  }

  public ActionRedirect movement (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
  throws ScrabbleException
  {
    if (!SceneStepper.stepToViewPoint (this.stgToUse, this.goTo))
    {
      this.getTimers ().get (ChangeTurnBean.MOVEMENT).stop ();
      try
      {
        this.methodToCallAtEnd.invoke (this, new Object [] {bean, listenerType,
            actionName, e, });
      } catch (IllegalArgumentException e1)
      {
        throw new ScrabbleException (e1);
      } catch (IllegalAccessException e1)
      {
        throw new ScrabbleException (e1);
      } catch (InvocationTargetException e1)
      {
        throw new ScrabbleException (e1);
      }
    }
    return null;
  }

  public ActionRedirect movementLetter (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
  {
    final ChangeTurnBean ctb = (ChangeTurnBean) bean;
    final Session session = ScrabbleSessionsHandler.get ();
    final int turn = ((Integer)session.get (ChangeTurnBean.TURN)).intValue ();
    final ILetterBranchGroup [] lbgs = ctb.getLetterBranchGroups () [turn];
    final double [] positions = ctb.getLetterPositions ();
    for (int i = 0; i < ChangeTurnBean.TRAY_LENGTH; i++)
    {
      if ((i == 0) || 
          TurnSpecificAlign.testTrigger (positions[i - 1], turn))
      {
        final double newPosition = positions [i] + 
            TurnSpecificAlign.LETTER_STEPPING [turn];
        positions[i] = newPosition;
        if (TurnSpecificAlign.testLimitStepping (
            lbgs [i].getTransformGroup (), newPosition,turn))
        {
          SceneStepper.stepWithFunction (
              lbgs[i].getTransformGroup (), newPosition,
              TurnSpecificAlign.function ("x", turn, i),
              TurnSpecificAlign.function ("y", turn, i), 
              TurnSpecificAlign.function ("z", turn, i));
        }
      }
    }
    if (TurnSpecificAlign.testStopStepping (
        ctb.getLetterPositions () [ChangeTurnBean.TRAY_LENGTH - 1], turn))
    {
      this.getTimers ().get (ChangeTurnBean.MOVEMENT_LETTER).stop ();
    }
    return null;
  }

  public ActionRedirect playPosition (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
      
  {
    final UserInteractions ui = this.getUiOwner ();
    final GameBean gb = (GameBean) ui.getBean (GameBean.ID_BEAN);
    final Session session = ScrabbleSessionsHandler.get ();

    this.stgToUse = gb.getSc3d ().getCameraTransformGroup ();
    this.goTo = 
      TurnSpecificAlign.READY_TO_PLAY_VIEWPOINTS 
                     [((Integer)session.get (ChangeTurnBean.TURN)).intValue ()];
    try
    {
      this.methodToCallAtEnd = this.getClass ().getMethod (
          "deployLetters",
          new Class [] {IUIBean.class, String.class, String.class,
              EventObject.class, });
    } catch (NoSuchMethodException e1)
    {
      e1.hashCode ();
    }
    TimerHelper.setTimeout (this, ChangeTurnBean.MOVEMENT, 
        ChangeTurnBean.INITIAL_DELAY_MOVEMENT);
    return null;
  }
}
