package org.toilelibre.libe.scrabble.applicationlayer.beans;

import org.toilelibre.libe.scrabble.beans.ScrabbleBeansHelper;
import org.toilelibre.libe.scrabble.logging.ScrabbleConsoleAppender;
import org.toilelibre.libe.scrabble.s3d.model.ICanvas3D;
import org.toilelibre.libe.userinteractions.model.beans.AbstractUIBean;
import org.toilelibre.libe.userinteractions.util.BeansComponents;
import org.toilelibre.libe.userinteractions.util.ComboBoxModel;
import org.toilelibre.libe.userinteractions.util.TableModel;

public final class GameBean extends AbstractUIBean
{
  public static final String ID_BEAN  = "gameBean";
  public static final String CAMERA   = "Camera";
  public static final Object MOVEMENT = "movement";
  private Object             exitButton;
  private Object             newPartyButton;
  private ICanvas3D          sc3d;
  private Object             validateButton;
  private String             typeOfObjectMoved;
  private Object             moveObject;
  private Object             scoresTable;
  private Object             skinCbx;
  private Object             console;


  private Integer            buttonPressed;
  private Integer            mouseActualX;
  private Integer            mouseActualY;
  private Integer            mousePressX;
  private Integer            mousePressY;

  public GameBean ()
  {

  }
  
  /**
   * @return the console
   */
  public Object getConsole ()
  {
    return this.console;
  }

  /**
   * @param console1 the console to set
   */
  public void setConsole (final Object console1)
  {
    ScrabbleConsoleAppender.setImpl (console1);
    this.console = console1;
  }

  /**
   * @return the skinCbx
   */
  public Object getSkinCbx ()
  {
    return this.skinCbx;
  }

  /**
   * @return the skinCbx
   */
  public Object getSkinCbxValue ()
  {
    return BeansComponents.getComboBoxValue (this.skinCbx);
  }
  
  /**
   * @param skinCbx1 the skinCbx to set
   */
  public void setSkinCbx (final Object skinCbx1)
  {
    BeansComponents.setComboBoxModel (skinCbx1,
        (ComboBoxModel) ScrabbleBeansHelper.getBean ("skinListBean"));
    this.skinCbx = skinCbx1;
  }

  /**
   * @return scoresTable
   */
  public Object getScoresTable () {
    return this.scoresTable;
  }

  /**
   * @return scoresTableModel
   */
  public TableModel getScoresTableModel () {
    return BeansComponents.getTableModel (this.scoresTable);
  }

  /**
   * @param scoresTable1 scoresTable � d�finir
   */
  public void setScoresTable (final Object scoresTable1) {
    this.scoresTable = scoresTable1;
  }

  public Object getExitButton ()
  {
    return this.exitButton;
  }

  public Object getNewPartyButton ()
  {
    return this.newPartyButton;
  }

  public ICanvas3D getSc3d ()
  {
    return this.sc3d;
  }

  public Object getValidateButton ()
  {
    return this.validateButton;
  }

  public void setExitButton (final Object exitButton1)
  {
    this.exitButton = exitButton1;
  }

  public void setNewPartyButton (final Object newPartyButton1)
  {
    this.newPartyButton = newPartyButton1;
  }

  public void setSc3d (final ICanvas3D sc3d2)
  {
    this.sc3d = sc3d2;
  }

  public void setValidateButton (final Object validateButton1)
  {
    this.validateButton = validateButton1;
  }

  /**
   * @return the typeOfObjectMoved
   */
  public String getTypeOfObjectMoved ()
  {
    return this.typeOfObjectMoved;
  }

  /**
   * @param typeOfObjectMoved1
   *          the typeOfObjectMoved to set
   */
  public void setTypeOfObjectMoved (final String typeOfObjectMoved1)
  {
    this.typeOfObjectMoved = typeOfObjectMoved1;
  }

  /**
   * @return the moveObject
   */
  public Object getMoveObject ()
  {
    return this.moveObject;
  }

  /**
   * @param moveObject1
   *          the moveObject to set
   */
  public void setMoveObject (final Object moveObject1)
  {
    this.moveObject = moveObject1;
  }

  /**
   * @return the buttonPressed
   */
  public Integer getButtonPressed ()
  {
    return this.buttonPressed;
  }

  /**
   * @param buttonPressed1
   *          the buttonPressed to set
   */
  public void setButtonPressed (final Integer buttonPressed1)
  {
    this.buttonPressed = buttonPressed1;
  }

  /**
   * @return the mouseActualX
   */
  public Integer getMouseActualX ()
  {
    return this.mouseActualX;
  }

  /**
   * @param mouseActualX1
   *          the mouseActualX to set
   */
  public void setMouseActualX (final Integer mouseActualX1)
  {
    this.mouseActualX = mouseActualX1;
  }

  /**
   * @return the mouseActualY
   */
  public Integer getMouseActualY ()
  {
    return this.mouseActualY;
  }

  /**
   * @param mouseActualY1
   *          the mouseActualY to set
   */
  public void setMouseActualY (final Integer mouseActualY1)
  {
    this.mouseActualY = mouseActualY1;
  }

  /**
   * @return the mousePressX
   */
  public Integer getMousePressX ()
  {
    return this.mousePressX;
  }

  /**
   * @param mousePressX1
   *          the mousePressX to set
   */
  public void setMousePressX (final Integer mousePressX1)
  {
    this.mousePressX = mousePressX1;
  }

  /**
   * @return the mousePressY
   */
  public Integer getMousePressY ()
  {
    return this.mousePressY;
  }

  /**
   * @param mousePressY1
   *          the mousePressY to set
   */
  public void setMousePressY (final Integer mousePressY1)
  {
    this.mousePressY = mousePressY1;
  }

}
