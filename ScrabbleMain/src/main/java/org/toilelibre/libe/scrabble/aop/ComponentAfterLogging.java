package org.toilelibre.libe.scrabble.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class ComponentAfterLogging implements AfterReturningAdvice
{

  private static final Logger LOG = Logger
                                      .getLogger (ComponentBeforeLogging.class);

  @Override
  public void afterReturning (final Object arg0, final Method arg1,
      final Object [] arg2, final Object arg3) throws Throwable
  {
    ComponentAfterLogging.LOG.info ("<-[C]" + arg3.getClass ().getSimpleName ()
        + " ; retour : " + arg0);
  }

}
