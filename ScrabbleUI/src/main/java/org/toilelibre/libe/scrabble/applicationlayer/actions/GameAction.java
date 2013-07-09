package org.toilelibre.libe.scrabble.applicationlayer.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;
import java.util.Map;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.Scrabble;
import org.toilelibre.libe.scrabble.applicationlayer.actions.party.ChangeTurnAction;
import org.toilelibre.libe.scrabble.applicationlayer.beans.GameBean;
import org.toilelibre.libe.scrabble.applicationlayer.beans.party.ChangeTurnBean;
import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.component.IComponent;
import org.toilelibre.libe.scrabble.exception.ScrabbleException;
import org.toilelibre.libe.scrabble.init.InitAppearance;
import org.toilelibre.libe.scrabble.properties.ScrabbleMessages;
import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.util.bounds.BoundsChecker;
import org.toilelibre.libe.scrabble.s3d.util.move.MoveObjectHelper;
import org.toilelibre.libe.scrabble.sessions.ScrabbleSessionsHandler;
import org.toilelibre.libe.scrabble.sessions.Session;
import org.toilelibre.libe.userinteractions.listeners.EventMethods;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.actions.Action;
import org.toilelibre.libe.userinteractions.model.actions.ActionRedirect;
import org.toilelibre.libe.userinteractions.model.beans.IUIBean;

public final class GameAction extends Action {

    private static final Logger LOG = Logger.getLogger (GameAction.class);

    public GameAction () {

    }

    public ActionRedirect changeSkin (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        final GameBean gb = (GameBean) bean;
        InitAppearance.changeLaf60 ((Class<?>) gb.getSkinCbxValue ());
        return null;
    }

    public ActionRedirect exit (final IUIBean bean, final String listenerType,
            final String actionName, final EventObject e) {
        Scrabble.exit (0);
        return new ActionRedirect (null, true);
    }

    public ActionRedirect forwardBackward (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        final GameBean gb = (GameBean) bean;
        MoveObjectHelper.move (gb.getSc3d (), GameBean.CAMERA, gb.getSc3d ()
                .getCameraTransformGroup (), 2, EventMethods
                .getWheelRotation (e), 0, EventMethods.getScrollAmount (e), 0,
                0);
        return null;
    }

    public ActionRedirect movement (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        final GameBean gb = (GameBean) bean;
        final Session session = ScrabbleSessionsHandler.get ();
        final Integer turn = (Integer) session.get (ChangeTurnBean.TURN);
        int turnInt = 0;
        if (turn != null) {
            turnInt = turn.intValue ();
        }

        MoveObjectHelper.move (gb.getSc3d (), gb.getTypeOfObjectMoved (), gb
                .getMoveObject (), gb.getButtonPressed ().intValue (), gb
                .getMouseActualX ().intValue (), gb.getMousePressX ()
                .intValue (), gb.getMouseActualY ().intValue (), gb
                .getMousePressY ().intValue (), turnInt);
        return null;
    }

    public ActionRedirect newParty (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        return new ActionRedirect ("newPartyBean",
                ScrabbleMessages.getMessage ("locations.xul.newParty"));
    }

    private void setPressedAttributes (final GameBean gb,
            final Session session, final EventObject e) {
        gb.setMousePressX (new Integer (EventMethods.getX (e)));
        gb.setMousePressY (new Integer (EventMethods.getY (e)));
        gb.setButtonPressed (new Integer (EventMethods.getButton (e)));

        Object stg = S3DHelper.getScenePicker ().getTransformGroupAt (
                gb.getSc3d (), "LetterBranchGroup", EventMethods.getX (e),
                EventMethods.getY (e));
        if (stg == null) {
            stg = gb.getSc3d ().getCameraTransformGroup ();
            gb.setTypeOfObjectMoved (GameBean.CAMERA);
        } else {
            gb.setTypeOfObjectMoved ("Letter");
        }

        gb.setMoveObject (stg);
    }

    public ActionRedirect startStopMove (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        final Session session = ScrabbleSessionsHandler.get ();
        final GameBean gb = (GameBean) bean;
        if ("pressed".equals (actionName)) {
            this.setPressedAttributes (gb, session, e);
            this.getTimers ().get (GameBean.MOVEMENT).start ();
        } else if ("released".equals (actionName)) {
            this.getTimers ().get (GameBean.MOVEMENT).stop ();
        }
        return null;
    }

    public ActionRedirect updateMousePosition (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) {
        final GameBean gb = (GameBean) bean;
        gb.setMouseActualX (new Integer (EventMethods.getX (e)));
        gb.setMouseActualY (new Integer (EventMethods.getY (e)));
        return null;
    }

    /**
     * Construire insertion Vérifier insertion Compter score Enregistrer
     * 
     * @param bean
     * @param listenerType
     * @param actionName
     * @param e
     * @return
     * @throws NoSuchMethodException
     * @throws ScrabbleException
     */
    public ActionRedirect validate (final IUIBean bean,
            final String listenerType, final String actionName,
            final EventObject e) throws ScrabbleException {
        GameAction.LOG.info ("Appui sur le bouton 'Valider'");
        final GameBean gb = (GameBean) bean;
        final Map<String, IComponent> c = ScrabbleBeansHelper.getComponents ();
        final UserInteractions ui = this.getUiOwner ();
        final ILetterBranchGroup [][] lbgs = ((ChangeTurnBean) ui
                .getBean (ChangeTurnBean.ID_BEAN)).getLetterBranchGroups ();
        if (lbgs == null) {
            throw new ScrabbleException ("Pas de jeu en cours");
        }
        final int turn = ((Integer) ScrabbleSessionsHandler.get ().get (
                ChangeTurnBean.TURN)).intValue ();
        final int [][] alignment = new int [lbgs [turn].length] [2];
        final char [] letters = new char [lbgs [turn].length];

        c.get ("getAligns").execute (lbgs [turn], alignment, letters);

        final Object i = c.get ("createInsertion").execute (alignment, letters);

        String [] words = null;
        try {
            words = (String []) c.get ("validateInsertion").executeAndThrow (i);
        } catch (final InvocationTargetException e1) {
            throw new ScrabbleException (e1.getTargetException ().getMessage ());
        }
        c.get ("insert").execute (i);

        final int score = ((Integer) c.get ("countScore").execute (i))
                .intValue ();

        c.get ("storeScore").execute (new Integer (turn), words,
                new Integer (score));

        if (turn == 0) {
            gb.getScoresTableModel ().addRow ();
        }
        String text = "";
        for (final String word : words) {
            text += word + "\n";
        }
        if (text.length () > 0) {
            text = text.substring (0, text.length () - 1);
        }
        gb.getScoresTableModel ().setValueAt (text + "\t" + score, -1, turn);

        for (final ILetterBranchGroup lbg : lbgs [turn]) {
            if (BoundsChecker.isOverBoard (lbg)) {
                lbg.setOld ();
                c.get ("setOld").execute (new Integer (turn),
                        new Integer (lbg.getIdTray ()));
            }
        }
        return ((ChangeTurnAction) this.getUiOwner ().getAction (
                ChangeTurnAction.class.getName ())).changeTurn (
                ui.getBean (ChangeTurnBean.ID_BEAN), listenerType, actionName,
                e);
    }
}
