package luad.eggs.gui;

import javafx.application.Platform;
import luad.eggs.OutputViewer;
import luad.eggs.Player;
import luad.eggs.mapTiles.MapTile;

/**
 * An implementation of the {@link luad.eggs.OutputViewer} interface that
 * displays the information on the Eggs GUI.
 */
public class GuiOutputViewer implements OutputViewer
{
  /**
   * GUI to use for displaying information.
   */
  private EggsGui gui;
  
  /**
   * Sets up the output viewer with an instance of the GUI.
   * @param gui GUI to display the information on.
   */
  public GuiOutputViewer(EggsGui gui)
  {
    this.gui = gui;
  }

  /**
   * Displays a message in the status bar at the bottom of the GUI.
   */
  @Override
  public void displayMessage(String message)
  {
    Platform.runLater(() -> {
      gui.getStatusText().scheduleMessage(message, 2000.0);
    });
  }

  /**
   * Displays map tiles in the map area of the GUI.
   */
  @Override
  public void displayMapTiles(MapTile[][] grid)
  {
    Platform.runLater(() -> {
      MapView mapArea = gui.getMapArea();
      mapArea.displayMapGrid(grid, -3, -3);
    });
  }

  /**
   * Presents the player's inventory in the inventory area of the GUI.
   */
  @Override
  public void displayInventory(Player player)
  {
    Platform.runLater(() -> {
      InventoryDisplay inventory = gui.getInventory();
      inventory.displayInventory(player);
    });
  }
}
