package luad.eggs.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import luad.eggs.gui.layouts.GameMapPane;

/**
 * The graphical user interface for the Eggs game.  The interface consists of
 * a menu bar, a status bar, a control pane, a map grid and an inventory pane.
 */
public class EggsGui extends BorderPane
{
  /**
   * The pane containing the controls for moving the player.
   */
  private ControlPane controls = new ControlPane();
  
  /**
   * Pane containing the map grid.
   */
  private GameMapArea mapArea = 
      new GameMapArea(new GameMapPane(-3, -3, 3, 3));
  
  /**
   * Pane displaying the player's inventory.
   */
  private InventoryPane inventory = new InventoryPane();
  
  /**
   * Pane displaying the current status of the game.
   */
  private StatusText statusText = new StatusText();
  
  /**
   * Pane with menus.
   */
  private EggsMenuBar menuBar = new EggsMenuBar();
  
  /**
   * Scene to place the GUI pane on.
   */
  private Scene scene;
  
  /**
   * @return The pane with controls on it.
   */
  public ControlPane getControls()
  {
    return controls;
  }
  
  /**
   * @return The pane with the map grid on it.
   */
  public GameMapArea getMapArea()
  {
    return mapArea;
  }
  
  /**
   * @return The pane with the player's inventory on it.
   */
  public InventoryPane getInventory()
  {
    return inventory;
  }
  
  /**
   * @return The pane displaying game status.
   */
  public StatusText getStatusText()
  {
    return statusText;
  }
  
  /**
   * @return The menu bar.
   */
  public EggsMenuBar getMenuBar()
  {
    return menuBar;
  }
  
  /**
   * @return The scene containing the GUI pane.
   */
  public Scene getContainingScene()
  {
    return scene;
  }
  
  /**
   * Lays out the user interface and applies styles.
   */
  public EggsGui()
  {
    super();
    setLeft(controls);
    setCenter(mapArea);
    setRight(inventory);
    setBottom(statusText);
    setTop(menuBar);
    
    mapArea.setId("map-holder");
    
    addStylesheet(controls, "map-3d.css");
    addStylesheet(controls, "control-pane.css");
    addStylesheet(mapArea, "map-3d.css");
    addStylesheet(mapArea, "map.css");
    addStylesheet(inventory, "inventory-pane.css");
    addStylesheet(statusText, "status-text.css");
    
    scene = new Scene(this, 3000, 2500);
  }

  /**
   * Adds a stylesheet on to a GUI element.
   * @param element Element to add the stylesheet to.
   * @param path Path to the stylesheet (relative to the stylesheets
   *             directory).
   */
  private void addStylesheet(Parent element, String path)
  {
    element.getStylesheets()
      .add(getClass()
             .getResource("/stylesheets/" + path)
             .toExternalForm());
  }
}
