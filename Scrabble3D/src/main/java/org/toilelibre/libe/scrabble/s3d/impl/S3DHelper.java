package org.toilelibre.libe.scrabble.s3d.impl;

import java.lang.reflect.InvocationTargetException;

import org.toilelibre.libe.scrabble.s3d.model.AbstractLetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.ILetterBranchGroup;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.IUniverse;
import org.toilelibre.libe.scrabble.s3d.util.scene.IScenePicker;

public final class S3DHelper {

    private static S3DHelper    instance;

    private static final String MODEL_PACKAGE = ".model.";

    public static ILetterBranchGroup cloneLetter () {
        return AbstractLetterBranchGroup.cloneLetter ();
    }

    private static Class<?> [] getClasses (final Object [] objects) {
        final String scrabble = "scrabble";
        final Class<?> [] classes = new Class<?> [objects.length];
        for (int i = 0 ; i < objects.length ; i++) {
            if ( (objects [i].getClass ().getInterfaces ().length == 1)
                    && objects [i].getClass ().getInterfaces () [0].getName ()
                            .contains (scrabble)) {
                classes [i] = objects [i].getClass ().getInterfaces () [0];
            } else if (!Object.class.equals (objects [i].getClass ()
                    .getSuperclass ())
                    && objects [i].getClass ().getSuperclass ().getName ()
                            .contains (scrabble)) {
                classes [i] = objects [i].getClass ().getSuperclass ();
            } else {
                classes [i] = objects [i].getClass ();
            }
        }
        return classes;
    }

    /**
     * @return the instance
     */
    public static S3DHelper getInstance () {
        return S3DHelper.instance;
    }

    public static IScenePicker getScenePicker () {
        final Object [] objects2 = new Object [0];
        final Class<?> [] classes = new Class<?> [0];
        final String className = S3DHelper.instance.implPackage
                + ".util.scene." + S3DHelper.instance.implPrefix
                + "ScenePicker";
        return (IScenePicker) S3DHelper.invokeConstructor (className, classes,
                objects2);
    }

    private static Object invokeConstructor (final String className,
            final Class<?> [] classes, final Object [] objects2) {
        try {
            return Class.forName (className).getConstructor (classes)
                    .newInstance (objects2);
        } catch (final IllegalArgumentException e) {
            e.hashCode ();
        } catch (final SecurityException e) {
            e.hashCode ();
        } catch (final InstantiationException e) {
            e.hashCode ();
        } catch (final IllegalAccessException e) {
            e.hashCode ();
        } catch (final InvocationTargetException e) {
            e.hashCode ();
        } catch (final NoSuchMethodException e) {
            e.hashCode ();
        } catch (final ClassNotFoundException e) {
            e.hashCode ();
        }
        return null;
    }

    public static ILetterBranchGroup newLetterBranchGroup (
            final Object... objects) {
        Object [] objects2 = objects;
        if (objects2 == null) {
            objects2 = new Object [0];
        }
        final String className = S3DHelper.instance.implPackage
                + S3DHelper.MODEL_PACKAGE + S3DHelper.instance.implPrefix
                + "LetterBranchGroup";
        final Class<?> [] classes = new Class<?> [] { Object.class };
        return (ILetterBranchGroup) S3DHelper.invokeConstructor (className,
                classes, objects2);
    }

    public static IPoint3D newPoint3d (final Object... objects) {
        Object [] objects2 = objects;
        if (objects2 == null) {
            objects2 = new Object [0];
        }
        final String className = S3DHelper.instance.implPackage
                + S3DHelper.MODEL_PACKAGE + S3DHelper.instance.implPrefix
                + "Point3D";
        final Class<?> [] classes = S3DHelper.getClasses (objects2);
        return (IPoint3D) S3DHelper.invokeConstructor (className, classes,
                objects2);
    }

    public static ITransform newTransform (final Object... objects) {
        Object [] objects2 = objects;
        if (objects2 == null) {
            objects2 = new Object [0];
        }
        final String className = S3DHelper.instance.implPackage
                + S3DHelper.MODEL_PACKAGE + S3DHelper.instance.implPrefix
                + "Transform";
        final Class<?> [] classes = S3DHelper.getClasses (objects2);
        return (ITransform) S3DHelper.invokeConstructor (className, classes,
                objects2);
    }

    public static IUniverse newUniverse (final Object... objects) {
        Object [] objects2 = objects;
        if (objects2 == null) {
            objects2 = new Object [0];
        }
        final String className = S3DHelper.instance.implPackage
                + S3DHelper.MODEL_PACKAGE + S3DHelper.instance.implPrefix
                + "Universe";
        final Class<?> [] classes = S3DHelper.getClasses (objects2);
        return (IUniverse) S3DHelper.invokeConstructor (className, classes,
                objects2);
    }

    private String implPackage;

    private String implPrefix;

    public S3DHelper () {
        S3DHelper.instance = this;
    }

    /**
     * @return the implPackage
     */
    public String getImplPackage () {
        return this.implPackage;
    }

    /**
     * @return the implPrefix
     */
    public String getImplPrefix () {
        return this.implPrefix;
    }

    /**
     * @param implPackage
     *            the implPackage to set
     */
    public void setImplPackage (final String implPackage1) {
        this.implPackage = implPackage1;
    }

    /**
     * @param implPrefix
     *            the implPrefix to set
     */
    public void setImplPrefix (final String implPrefix1) {
        this.implPrefix = implPrefix1;
    }
}
