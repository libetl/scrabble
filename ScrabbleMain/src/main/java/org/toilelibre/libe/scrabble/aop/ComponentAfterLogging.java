package org.toilelibre.libe.scrabble.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class ComponentAfterLogging implements AfterReturningAdvice
{

  private static final Logger LOG = LogManager.getLogger (ComponentBeforeLogging.class);

  @Override
  public void afterReturning (final Object arg0, final Method arg1,
      final Object [] arg2, final Object arg3) throws Throwable
  {
    if (arg0 != null && arg0.getClass ().isArray ())
    {
      ComponentAfterLogging.LOG.info ("<-[C]"
          + arg3.getClass ().getSimpleName () + " ; retour : "
          + ReflectionToStringBuilder.toString (arg0));
    } else
    {
      ComponentAfterLogging.LOG.info ("<-[C]"
          + arg3.getClass ().getSimpleName () + " ; retour : " + arg0);
    }
  }

}
