package org.toilelibre.libe.scrabble.s3d.util.bounds;

import org.toilelibre.libe.scrabble.s3d.impl.S3DHelper;
import org.toilelibre.libe.scrabble.s3d.model.IPoint3D;
import org.toilelibre.libe.scrabble.s3d.model.ITransform;
import org.toilelibre.libe.scrabble.s3d.model.ITransformGroup;
import org.toilelibre.libe.scrabble.s3d.util.scene.SceneHelper;

public final class TurnSpecificAlign
{
  public static final double [] CHEVALET_OVER_X1  = {-10, -25, -10, 21.5};
  public static final double [] CHEVALET_OVER_X2  = {10, -23, 10, 23.5};
  public static final double [] CHEVALET_OVER_Y1  = {-2, -2, -2, -2};
  public static final double [] CHEVALET_OVER_Y2  = {2.3, 2, 2.3, 2};
  public static final double [] CHEVALET_OVER_Z1  = {23, -8, -21.4, -7};
  public static final double [] CHEVALET_OVER_Z2  = {25, 8, -19.2, 10};

  public static final double [] LETTER_STEPPING   = 
                                                 {0.03, -0.005, -0.002, 0.05};
  public static final double [] TRIGGER_POSITION  = {-10, -22.5, -19.6, 2};
  public static final double [] LIMIT_STEPPING    = {24.2, -24.2, -20.4, 22.9};
  public static final double [] LIMIT_STEPPING1   = 
                                                 {24.19, -24.19, -20.39, 22.84};

  public static final double [] INIT_POSITIONS    = {-18.5, -20, -18.5, -20, };
  
  public static final double [][][] PIOCHE_VIEWPOINTS = new double [] [] [] {
      { {1.0, 0.0, 0.0, -20 }, {0.0, 1.0, 0.0, 9 }, {0.0, 0.0, 1.0, -18.5 },
        {0.0, 0.0, 0.0, 1.0, }, },
      { {0.0, 0.0, -1.0, -20 }, {0.0, 1.0, 0.0, 9 }, {1.0, 0.0, 0.0, -18.5 },
        {0.0, 0.0, 0.0, 1.0, },},
      { {-1.0, 0.0, 0.0, -20 }, {0.0, 1.0, 0.0, 9 }, {0.0, 0.0, -1.0, -18.5 },
        {0.0, 0.0, 0.0, 1.0, },},
      { {0.0, 0.0, 1.0, -20 }, {0.0, 1.0, 0.0, 9 }, {-1.0, 0.0, 0.0, -18.5 },
        {0.0, 0.0, 0.0, 1.0, },},};

  public static final double [][] []  READY_TO_PLAY_VIEWPOINTS = 
    new double [] [] [] {
      {{1.0, 0.0, 0.0, 0}, 
       {0.0, 0.707, 0.707, 31.7}, 
       {0.0, -0.707, 0.707, 41.3}, 
       {0.0, 0.0, 0.0, 1.0},},
 
      {{0.0, 0.661, -0.751, -46.0}, 
       {0.0, 0.751,  0.661, 33.5}, 
       {1.0, -0.0, - 0.0, -2.44}, 
       {0.0, 0.0, 0.0, 1.0},},
       
      {{-1.0, 0.0, 0.0, 0},
       {0.0, 0.707, 0.707, 38.222}, 
       {0.0, 0.707, -0.707, -40.532}, 
       {0.0, 0.0, 0.0, 1.0},},

      {{0.0, -0.733, 0.680, 46.0}, 
       {0.0, 0.680, 0.733, 37.5}, 
       {-1.0, 0.0, 0.0, 2.44}, 
       {0.0, 0.0, 0.0, 1.0},},};

  public static final
  Object []      FUNCTIONSX          = new Object [] { new String []{
        "0.325 * t - 13.984", "0.365 * t - 13.253", "0.400 * t - 12.600",
        "0.435 * t - 11.947", "0.471 * t - 11.294", "0.506 * t - 10.641",
        "0.541 * t - 09.988",}, TurnSpecificAlign.T, new String [] {
        "-166.053  - 7.895  * t", "-185.526 -  8.947 * t", 
        "-205.000  - 10.000 * t", "-224.474 - 11.053 * t", 
        "-243.947  - 12.105 * t", "-263.421 - 13.158 * t",
        "-282.895  - 14.211 * t",}, TurnSpecificAlign.T, };
  
  public static final
  Object []      FUNCTIONSY          = new Object [] {
        "    12.996 -   0.105 * t - 0.017 * t * t",
        "  -749.571 -  71.045 * t - 1.656 * t * t",
        " -3329.000 - 348.179 * t - 9.067 * t * t",
        "    12.372 -   0.154 * t - 0.016 * t * t",};
  
  public static final
  Object []      FUNCTIONSZ          = new Object [] {
        TurnSpecificAlign.T, new String [] {
        "-70.881  -  2.619 * t", "-80.405  - 3.095 * t", "-89.929  - 3.571 * t",
        "-99.452  -  4.048 * t", "-108.976 - 4.524 * t", "-118.500 - 5.000 * t",
        "-128.024 -  5.476 * t",}, TurnSpecificAlign.T, new String [] {
        "-12.206  +  0.315 * t", "-11.274  + 0.361 * t", "-10.341  + 0.408 * t",
        "-9.409   +  0.455 * t", "-8.477   + 0.501 * t", "-7.544   + 0.548 * t",
        "-6.612   +  0.594 * t",},};
  
  private static final String T = "t";
  
  
  private TurnSpecificAlign ()
  {
    
  }
  
  private static double alignRotX (final ITransform stLetter, final int turn){
    double newRotX = 0.0;
    final double unPointDeux = 1.2;
    if (stLetter.getRotX () <= -1.0)
    {
      newRotX = unPointDeux;
    }
    return newRotX;
  }
  
  private static double alignRotY (final ITransform stLetter, final int turn){
    double newRotY = 0.0;
    final int trois = 3;
    final double pointUn = .1;
    switch (turn)
    {
      case 1:
        if (stLetter.getRotY () == 1.0)
        {
          newRotY = -turn * Math.PI / 2;
        }
        break;
      case 2:
        if (stLetter.getRotY () < pointUn && stLetter.getRotY () > -pointUn)
        {
          newRotY = -turn * Math.PI / 2;
        }
        break;
      case trois:
        if (stLetter.getRotY () < pointUn && stLetter.getRotY () > -pointUn)
        {
          newRotY = -turn * Math.PI / 2;
        }
        break;
      default:
        break;
    }
    return newRotY;
  }
  
  public static void alignChevalet (final ITransformGroup stg, final int turn)
  {    

    final double moinsVingtQuatreQuatre = -24.4;
    final double moinsVingtPointQuatre = -20.4;
    final double vingtQuatreUn = 24.1;
    final double vingtDeuxNeuf = 22.9;
    final double zeroCinq = 0.5;
    final double zeroQuatre = 0.4;
    final int trois = 3;
    final ITransform stLetter = S3DHelper.newTransform ((Object [])null);
    stg.getTransform (stLetter);
    final double newRotX = TurnSpecificAlign.alignRotX (stLetter, turn);
    final double newRotY = TurnSpecificAlign.alignRotY (stLetter, turn);
    SceneHelper.rotate (stg, newRotX, 0, 0);
    SceneHelper.rotate (stg, 0, newRotY, 0);
    switch (turn)
    {
      case 0:
        SceneHelper.translate (stg, .0, zeroCinq - stLetter.getY (), 
            vingtQuatreUn - stLetter.getZ ());
        break;
      case 1:
        SceneHelper.translate (stg, moinsVingtQuatreQuatre - stLetter.getX (),
            -stLetter.getY (), .0);
        break;
      case 2:
        SceneHelper.translate (stg, .0, zeroQuatre - stLetter.getY (), 
            moinsVingtPointQuatre - stLetter.getZ ());
        break;
      case trois:
        SceneHelper.translate (stg, vingtDeuxNeuf - stLetter.getX (), 
            zeroQuatre - stLetter.getY (), .0);
        break;
      default:
        break;
    }
  }

  public static void swap (final ITransformGroup stg, 
      final ITransformGroup stgOtherLetter, final int turn)
  {
    final double zeroCinq = 0.5;
    final double moinsVingtQuatreQuatre = -24.4;
    final double moinsVingtPointQuatre = -20.4;
    final double vingtDeuxNeuf = -22.9;
    final double vingtQuatreUn = 24.1;
    final double zeroQuatre = 0.4;
    final ITransform stLetter = 
                                       S3DHelper.newTransform ((Object [])null);
    final ITransform stOtherLetter = 
                                       S3DHelper.newTransform ((Object [])null);
    stg.getTransform (stLetter);
    stgOtherLetter.getTransform (stOtherLetter);
    final double dx = stLetter.getX () - stOtherLetter.getX ();
    final double dz = stLetter.getZ () - stOtherLetter.getZ ();
    final int trois = 3;
    switch (turn)
    {
      case 0:
        SceneHelper.translate (stg, .0, zeroCinq - stLetter.getY (), 
            vingtQuatreUn - stLetter.getZ ());
        SceneHelper.translate (stg, -dx, .0, .0);
        SceneHelper.translate (stgOtherLetter, dx, .0, .0);
        break;
      case 1:
        SceneHelper.translate (stg, moinsVingtQuatreQuatre - stLetter.getX (),
            -stLetter.getY (), .0);
        SceneHelper.translate (stg, .0, .0, -dz);
        SceneHelper.translate (stgOtherLetter, .0, .0, dz);
        break;
      case 2:
        SceneHelper.translate (stg, .0, zeroQuatre - stLetter.getY (), 
            moinsVingtPointQuatre - stLetter.getZ ());
        SceneHelper.translate (stg, -dx, .0, .0);
        SceneHelper.translate (stgOtherLetter, dx, .0, .0);
        break;
      case trois:
        SceneHelper.translate (stg, vingtDeuxNeuf - stLetter.getX (), 
            zeroQuatre - stLetter.getY (), .0);
        SceneHelper.translate (stg, .0, .0, -dz);
        SceneHelper.translate (stgOtherLetter, .0, .0, dz);
        break;
      default:
        break;
    }
  }
  
  public static boolean testTrigger (final double position, final int turn){
    boolean trigger = false;
    final int trois = 3;
    switch (turn)
    {
      case 0:
        trigger = position > TurnSpecificAlign.TRIGGER_POSITION [turn];
        break;
      case 1:
        trigger = position < TurnSpecificAlign.TRIGGER_POSITION [turn];
        break;
      case 2:
        trigger = position < TurnSpecificAlign.TRIGGER_POSITION [turn];
        break;
      case trois:
        trigger = position > TurnSpecificAlign.TRIGGER_POSITION [turn];
        break;
      default:break;
    }
    return trigger;
  }

  public static boolean testStopStepping (
      final double lastPosition, final int turn){
    boolean inLimit = false;
    final int trois = 3;
    switch (turn)
    {
      case 0:
        inLimit = lastPosition >= TurnSpecificAlign.LIMIT_STEPPING [turn];
        break;
      case 1:
        inLimit = lastPosition < TurnSpecificAlign.LIMIT_STEPPING [turn];
        break;
      case 2:
        inLimit = lastPosition < TurnSpecificAlign.LIMIT_STEPPING [turn];
        break;
      case trois:
        inLimit = lastPosition >= TurnSpecificAlign.LIMIT_STEPPING [turn];
        break;
      default:break;
    }
    return inLimit;
  }
  
  public static boolean testLimitStepping (
      final ITransformGroup iScrabbleTransformGroup, 
      final double newPosition, final int turn){
    boolean inLimit = false;
    final int trois = 3;
    final ITransform st = S3DHelper.newTransform ((Object [])null);

    iScrabbleTransformGroup.getTransform (st);
    switch (turn)
    {
      case 0:
        inLimit = newPosition <= TurnSpecificAlign.LIMIT_STEPPING [turn] && 
        st.getZ () <= TurnSpecificAlign.LIMIT_STEPPING1 [turn];
        break;
      case 1:
        inLimit = newPosition >= TurnSpecificAlign.LIMIT_STEPPING [turn] && 
        st.getX () >= TurnSpecificAlign.LIMIT_STEPPING1 [turn];
        break;
      case 2:
        inLimit = newPosition >= TurnSpecificAlign.LIMIT_STEPPING [turn] && 
        st.getZ () >= TurnSpecificAlign.LIMIT_STEPPING1 [turn];
        break;
      case trois:
        inLimit = newPosition <= TurnSpecificAlign.LIMIT_STEPPING [turn] && 
        st.getX () <= TurnSpecificAlign.LIMIT_STEPPING1 [turn];
        break;      
      default:break;
    }
    return inLimit;
  }
  
  public static boolean isOverChevalet (final double x, final double y,
      final double z, final int turn)
  {
    final IPoint3D sp3d = 
      S3DHelper.newPoint3d (new Double (x), new Double (y), new Double (z));
    return TurnSpecificAlign.isOverChevalet (sp3d, turn);
  }

  public static boolean isOverChevalet (final IPoint3D sp3d, 
      final int turn)
  {
    if (sp3d == null){
      return false;
    }
    return
      (sp3d.getZ () >= TurnSpecificAlign.CHEVALET_OVER_Z1 [turn])
   && (sp3d.getZ () <= TurnSpecificAlign.CHEVALET_OVER_Z2 [turn])
   && (sp3d.getY () >= TurnSpecificAlign.CHEVALET_OVER_Y1 [turn])
   && (sp3d.getY () <= TurnSpecificAlign.CHEVALET_OVER_Y2 [turn])
   && (sp3d.getX () >= TurnSpecificAlign.CHEVALET_OVER_X1 [turn])
   && (sp3d.getX () <= TurnSpecificAlign.CHEVALET_OVER_X2 [turn]);
  }
  
  public static String function (final String type, final int turn,
      final int index){
    String res = TurnSpecificAlign.T;
    Object o = new Object ();
    if ("x".equals (type)){
      o = TurnSpecificAlign.FUNCTIONSX [turn];
    }else if ("y".equals (type)){
      o = TurnSpecificAlign.FUNCTIONSY [turn];
    }else if ("z".equals (type)){
      o = TurnSpecificAlign.FUNCTIONSZ [turn];      
    }
    if (o instanceof String){
      res = (String) o;
    }else {
      res = ((String[]) o) [index]; 
    }
    return res;
  }

  /**
   * @param stg1
   */
  public static void alignOverBoard (final ITransformGroup stg, final int turn)
  {

    final ITransform st = 
      S3DHelper.newTransform ((Object [])null);
    stg.getTransform (st);
    double newRotX = 0;
    double newRotY = 0;
    final int trois = 3;
    final double unPoint2 = 1.2;
    if (st.getRotX () == 0)
    {
      newRotX = -unPoint2;
    }
    if ((turn == 1 && st.getRotY () == -1.0) ||
        (turn == 2 && st.getRotY () == 1.0) ||
        (turn == trois && st.getRotY () == 1.0))
    {
      newRotY = turn * Math.PI / 2;
    }
    SceneHelper.rotate (stg, 0, newRotY, 0);
    SceneHelper.rotate (stg, newRotX, 0, 0);
  }
}
