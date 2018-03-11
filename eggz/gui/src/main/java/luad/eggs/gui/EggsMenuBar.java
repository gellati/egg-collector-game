package luad.eggs.gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * The menu bar at the top of the Eggs GUI.
 */
public class EggsMenuBar extends MenuBar
{
  /**
   * The 'File' menu.
   */
  private Menu fileMenu = new Menu("File");
  
  /**
   * The 'Quit' menu item.
   */
  private MenuItem quitMenuItem = new MenuItem("Quit");
  
  /**
   * Lays out the menu bar and adds actions to menu items.
   */
  public EggsMenuBar()
  {
    super();
    quitMenuItem.setOnAction(event -> {
      Platform.exit();
      System.exit(0);
    });
    fileMenu.getItems().add(quitMenuItem);
    this.getMenus().add(fileMenu);
  }
}
