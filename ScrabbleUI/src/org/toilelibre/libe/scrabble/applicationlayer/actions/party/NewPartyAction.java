package org.toilelibre.libe.scrabble.applicationlayer.actions.party;

import java.util.EventObject;

import org.toilelibre.libe.scrabble.applicationlayer.beans.GameBean;
import org.toilelibre.libe.scrabble.applicationlayer.beans.party.ChangeTurnBean;
import org.toilelibre.libe.scrabble.applicationlayer.beans.party.NewPartyBean;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.s3d.util.scene.SceneStepper;
import org.toilelibre.libe.scrabble.sessions.ScrabbleSessionsHandler;
import org.toilelibre.libe.scrabble.sessions.Session;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.actions.ActionRedirect;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;
import org.toilelibre.libe.userinteractions.util.TableModel;

public class NewPartyAction extends Action
{

  public NewPartyAction ()
  {
    
  }
  
  /**
   * Créer nouveaux joueurs
   * @param bean
   * @param listenerType
   * @param actionName
   * @param e
   * @return
   */
  public final ActionRedirect execute (final IUIBean bean, 
      final String listenerType,final String actionName, final EventObject e)
  {
    final UserInteractions ui = this.getUiOwner ();
    final NewPartyBean npb = (NewPartyBean) bean;
    final GameBean gb = (GameBean) ui.getBean (GameBean.ID_BEAN);
    final Session session = ScrabbleSessionsHandler.get ();
    
    final String [] playerNames = new String [] { 
        npb.getJN1Text (), npb.getJN2Text (),
        npb.getJN3Text (), npb.getJN4Text (),};
    final Boolean [] computerPlayers = new Boolean [] { 
        npb.isJ1CSelected (), npb.isJ2CSelected (),
        npb.isJ3CSelected (), npb.isJ4CSelected (),};
    
    final Integer nbPlayers = (Integer)
    ScrabbleBeansHelper.getComponent ("newPlayer").execute (
        playerNames, computerPlayers);
    
    final TableModel tm = gb.getScoresTableModel ();
    for (int i = 0 ; i < nbPlayers.intValue () ; i++)
    {
      tm.addColumn (playerNames [i]);
    }
    
    session.put (ChangeTurnBean.TURN, new Integer (0));
    session.put (ChangeTurnBean.NB_PLAYERS, nbPlayers);
    this.getTimers ().get (NewPartyBean.MOVEMENT).start ();
    return new ActionRedirect (null, true);
  }

  public final ActionRedirect movement (final IUIBean bean,
      final String listenerType, final String actionName, final EventObject e)
  {
    final UserInteractions ui = this.getUiOwner ();
    final GameBean gb = (GameBean) ui.getBean (GameBean.ID_BEAN);
    final Session session = ScrabbleSessionsHandler.get ();

    if (!SceneStepper.stepToViewPoint (
        gb.getSc3d ().getCameraTransformGroup (), 
        NewPartyBean.VIEWPOINT_NEW_PARTY))
    {
      this.getTimers ().get (NewPartyBean.MOVEMENT).stop ();
      
      if (session.get (ChangeTurnBean.NB_PLAYERS) != null &&
          ((Integer)session.get (ChangeTurnBean.NB_PLAYERS)).intValue () > 0){
        return ((ChangeTurnAction) ui.getAction (ChangeTurnAction.class
            .getName ())).playPosition (ui.getBean ("changeTurnBean"),
            listenerType, actionName, e);
      }
      this.getTimers ().get (NewPartyBean.MOVEMENT).stop ();
    }

    return null;
  }
}
