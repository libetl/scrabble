package org.toilelibre.libe.scrabble.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.toilelibre.libe.scrabble.component.IComponent;

public final class ScrabbleBeansHelper
{
  private static Object                  bf;
  private static final String            COMPONENT = "Component";
  private static Map<String, IComponent> components;

  private ScrabbleBeansHelper ()
  {

  }

  public static Object getBean (final String name)
  {
    return ((org.springframework.beans.factory.BeanFactory) 
        ScrabbleBeansHelper.bf).getBean (name);
  }

  public static void modifyMethodParameters (final String name, 
      final Object [] attrs)
  {
    final Object o = ScrabbleBeansHelper.getBean (name);
    if (o instanceof org.springframework.util.MethodInvoker)
    {
      final org.springframework.util.MethodInvoker mi = 
          (org.springframework.util.MethodInvoker) o;
      mi.setArguments (attrs);
    }    
  }
  
  public static Object launchMethod (final String name)
  {
    final Object o = ScrabbleBeansHelper.getBean (name);
    if (o instanceof org.springframework.util.MethodInvoker)
    {
      final org.springframework.util.MethodInvoker mi = 
          (org.springframework.util.MethodInvoker) o;
      try
      {
        return mi.invoke ();
      } catch (InvocationTargetException e)
      {
        e.hashCode ();
      } catch (IllegalAccessException e)
      {
        e.hashCode ();
      }
    }
    return null;
  }

  private static void setComponents ()
  {
    ScrabbleBeansHelper.components = new HashMap<String, IComponent> ();
    final Map<?, ?> input = 
      ((org.springframework.beans.factory.ListableBeanFactory) 
          ScrabbleBeansHelper.bf)
        .getBeansOfType (IComponent.class);
    for (Object o : input.keySet ())
    {
      final String key = (String) o;
      if (key.endsWith (ScrabbleBeansHelper.COMPONENT))
      {
        final String key1 = key.substring (0, key.length ()
            - ScrabbleBeansHelper.COMPONENT.length ());
        ScrabbleBeansHelper.components.put (key1, (IComponent) input.get (key));
      }
    }
  }

  /**
   * @return the components
   */
  public static Map<String, IComponent> getComponents ()
  {
    if (ScrabbleBeansHelper.components == null)
    {
      ScrabbleBeansHelper.setComponents ();
    }
    return ScrabbleBeansHelper.components;
  }

  public static IComponent getComponent (final String name)
  {
    return
    (IComponent) ((org.springframework.beans.factory.ListableBeanFactory) 
        ScrabbleBeansHelper.bf)
        .getBeansOfType (IComponent.class).get (
            name + ScrabbleBeansHelper.COMPONENT);
  }

  /**
   * @param name
   *          bean name
   * @return clazz
   * @throws NoSuchBeanDefinitionException
   * @see org.springframework.beans.factory.BeanFactory#getType
   *      (java.lang.String)
   */
  public static Class<?> getType (final String name)
  {
    return ((org.springframework.beans.factory.BeanFactory) 
        ScrabbleBeansHelper.bf).getType (name);
  }


  /**
   * @param factory1
   */
  public static void setImpl (final Object factory1)
  {
    ScrabbleBeansHelper.bf = factory1;
  }
}
