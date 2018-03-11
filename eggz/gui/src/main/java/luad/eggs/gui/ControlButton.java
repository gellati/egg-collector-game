package luad.eggs.gui;

import javafx.scene.layout.Pane;
import luad.eggs.gui.layouts.GameMapPane;

public class ControlButton extends Pane
{
  /**
   * Sets up the button with information about 
   * @param direction The direction that the button should be pointing.
   */
  public ControlButton(String direction)
  {
    super();
    getStyleClass().addAll("control-button",
                           "control-button-" + direction);
    setCoordinates(direction);
  }

  /**
   * Sets the coordinates of a control button, based on the direction the
   * button should be pointing in.
   * @param The direction the button should be pointing in.
   */
  private void setCoordinates(String direction)
  {
    int row = 0, column = 0;
    switch (direction) {
      case "north":
        row = -1;
        column = 0;
        break;
      case "south":
        row = 1;
        column = 0;
        break;
      case "east":
        row = 0;
        column = 1;
        break;
      case "west":
        row = 0;
        column = -1;
        break;
      case "centre":
        row = 0;
        column = 0;
        break;
    }
    GameMapPane.setRowIndex(this, row);
    GameMapPane.setColumnIndex(this, column);
  }
}
