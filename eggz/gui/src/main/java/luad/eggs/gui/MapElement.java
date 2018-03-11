package luad.eggs.gui;

import java.awt.Point;

import javafx.scene.layout.Pane;
import luad.eggs.gui.layouts.GameMapPane;

/**
 * A map tile on the game board.  The image should be set using CSS.
 */
public class MapElement extends Pane
{
  /**
   * Creates a new map tile with no CSS class.
   */
  public MapElement()
  {
    super();
  }

  /**
   * Creates a new map tile with a given CSS class.
   * @param cssId The CSS ID for the new tile.
   */
  public MapElement(String cssId)
  {
    this();
    setId(cssId);
  }
  
  /**
   * Sets the position of the object.
   * @param position The position to put the map element in.
   */
  public void setPosition(Point position)
  {
    GameMapPane.setRowIndex(this, position.x);
    GameMapPane.setColumnIndex(this, position.y);
  }
}
