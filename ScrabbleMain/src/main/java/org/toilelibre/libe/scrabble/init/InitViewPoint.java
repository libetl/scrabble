package org.toilelibre.libe.scrabble.init;

import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.userinteractions.model.UserInteractions;
import org.toilelibre.libe.userinteractions.model.interactions.Interaction;
import org.toilelibre.libe.userinteractions.timers.impl.IUITimer;

public final class InitViewPoint implements IScrabbleInit
{

  public InitViewPoint ()
  {

  }

  @Override
  public void init ()
  {
    final int threadSleep = 1000;
    final String newPartyAction = "org.toilelibre.libe.scrabble.applicationlayer.actions.party.NewPartyAction";
    final UserInteractions ui = (UserInteractions) ScrabbleBeansHelper
        .getBean ("userInteractions");
    Interaction interaction = ui.getInteraction (newPartyAction);
    while (ui.getInteraction (newPartyAction) == null)
    {
      interaction = ui.getInteraction (newPartyAction);
      try
      {
        Thread.sleep (threadSleep);
      } catch (final InterruptedException e)
      {
        e.hashCode ();
      }
    }
    IUITimer timer = interaction.getAction ().getTimers ().get ("movement");
    if (timer != null) timer.start ();
  }
}
