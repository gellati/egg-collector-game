package luad.eggs.gui;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import luad.eggs.gui.layouts.GameMapPane;

public class ControlPane extends VBox implements Controls
{
  /**
   * Area for laying out the controls.
   */
  private GameMapPane controlsArea;
  
  /**
   * Button for moving north.
   */
  private Node moveNorthButton = new ControlButton("north");
  
  /**
   * Button for moving south.
   */
  private Node moveSouthButton = new ControlButton("south");
  
  /**
   * Button for moving east.
   */
  private Node moveEastButton = new ControlButton("east");
  
  /**
   * Button for moving west.
   */
  private Node moveWestButton = new ControlButton("west");
  
  /**
   * Button for picking up.
   */
  private Node pickUpButton = new ControlButton("centre");

  @Override
  public Node getMoveNorthButton()
  {
    return moveNorthButton;
  }

  @Override
  public Node getMoveSouthButton()
  {
    return moveSouthButton;
  }

  @Override
  public Node getMoveEastButton()
  {
    return moveEastButton;
  }

  @Override
  public Node getMoveWestButton()
  {
    return moveWestButton;
  }

  @Override
  public Node getPickUpButton()
  {
    return pickUpButton;
  }
  
  /**
   * Sets up the pane.
   */
  public ControlPane()
  {
    controlsArea = new GameMapPane(-1, -1, 1, 1);
    controlsArea.getChildren()
                .addAll(getPickUpButton(),
                        getMoveNorthButton(),
                        getMoveSouthButton(),
                        getMoveEastButton(),
                        getMoveWestButton());
    getStyleClass().add("control-pane");
    controlsArea.getStyleClass()
                .add("controls-area");
    this.getChildren().add(controlsArea);
  }
}
