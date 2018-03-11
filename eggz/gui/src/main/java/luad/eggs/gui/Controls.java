package luad.eggs.gui;

import javafx.scene.Node;

public interface Controls
{
  /**
   * @return The button for moving north.
   */
  public Node getMoveNorthButton();
  
  /**
   * @return The button for moving south.
   */
  public Node getMoveSouthButton();
  
  /**
   * @return The button for moving east.
   */
  public Node getMoveEastButton();
  
  /**
   * @return The button for moving west.
   */
  public Node getMoveWestButton();
  
  /**
   * @return The button for picking up.
   */
  public Node getPickUpButton();
}
