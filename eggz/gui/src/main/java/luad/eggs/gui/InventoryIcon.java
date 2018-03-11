package luad.eggs.gui;

import javafx.scene.layout.Pane;

/**
 * An icon for display in the inventory pane of the GUI.
 */
public class InventoryIcon extends Pane
{
  /**
   * Creates the icon and registers it to the appropriate CSS class.
   * @param CSS class to register the icon to.
   */
  public InventoryIcon(String cssClass)
  {
    this();
    getStyleClass().add(cssClass);
  }
  
  /**
   * Creates the icon and registers it to the "inventory-icon" CSS class.
   */
  public InventoryIcon()
  {
    super();
    getStyleClass().add("inventory-icon");
  }
}
