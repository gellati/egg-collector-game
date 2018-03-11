package luad.eggs.gui;

import luad.eggs.mapTiles.MapTile;

public interface MapView
{
  /**
   * Displays a grid of tiles on the map.  Any tiles already on the map will
   * remain on there, though their appearance may change.
   * @param mapGrid The grid of tiles to display.
   * @param minRow The index of the first row in the map.
   * @param minColumn The index of the first column in the map.
   */
  public void displayMapGrid(MapTile[][] mapGrid, int minRow, int minColumn);
  
  /**
   * Clears the map of tiles.
   */
  public void clearMapTiles();
}
