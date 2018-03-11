package luad.eggs;

import luad.eggs.mapTiles.MapTile;

public interface OutputViewer
{
  /**
   * Displays a message.
   * @param message The message to display.
   */
  public void displayMessage(String message);
  
  /**
   * Displays a grid of map tiles.
   * @param grid An array of map tiles to display.
   */
  public void displayMapTiles(MapTile[][] grid);
  
  /**
   * Displays a player's inventory.
   * @param player The player whose inventory to display.
   */
  public void displayInventory(Player player);
}
