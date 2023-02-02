package org.toilelibre.libe.scrabble.aop;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class ComponentBeforeLogging implements MethodBeforeAdvice
{

  private static final Logger LOG = LogManager.getLogger (ComponentBeforeLogging.class);

  @Override
  public void before (final Method arg0, final Object [] arg1, final Object arg2)
      throws Throwable
  {
    ComponentBeforeLogging.LOG.info ("->[C]"
        + arg2.getClass ().getSimpleName ()
        + " ; arguments : "
        + org.apache.commons.lang.builder.ReflectionToStringBuilder
            .toString (arg1));
  }

}
