package luad.eggs.gui;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.layout.VBox;
import luad.eggs.gui.layouts.GameMapPane;
import luad.eggs.mapTiles.MapTile;

/**
 * The area of the Eggs GUI containing the game map.
 */
public class GameMapArea extends VBox implements MapView
{
  /**
   * Pane where the map tiles are located.
   */
  private GameMapPane mapPane;
  
  /**
   * Map storing all the tiles that have been added to the map.
   */
  private Map<Point, MapElement> addedTiles = new HashMap<>();
  
  /**
   * Set of all the egg and player tiles that have been added to the map.
   */
  private Set<MapElement> overlaidTiles = new HashSet<>();

  /**
   * Sets up the pane and registers it with CSS.
   */
  public GameMapArea(GameMapPane mapPane)
  {
    super();
    setMapPane(mapPane);
    getStyleClass().add("game-map-area");
    getChildren().add(getMapPane());
  }

  /**
   * @return the pane where the map tiles are located.
   */
  public GameMapPane getMapPane()
  {
    return mapPane;
  }

  /**
   * @param mapPane the new game map pane.
   */
  public void setMapPane(GameMapPane mapPane)
  {
    this.mapPane = mapPane;
  }

  /**
   * Displays the map grid on the map area.
   * @param mapGrid The map grid as an array of
   *  {@link luad.eggs.mapTiles.MapTile} objects.
   * @param minRow The smallest index of a row in the map.
   * @param minColumn The smallest index of a column in the map.
   */
  @Override
  public void displayMapGrid(MapTile[][] mapGrid,
                             int minRow,
                             int minColumn)
  {
    // Remove the eggs and player from the previous version of the map.
    clearOverlaidTiles();
    
    // Iterate over the map tiles, adding new tiles if necessary.
    for (int i = 0 ; i < mapGrid.length ; ++i) {
      for (int j = mapGrid[i].length - 1 ; j >= 0 ; --j) {
        MapTile mapTile = mapGrid[i][j];
        int row = minRow + i;
        int column = minColumn + j;
        MapElement tilePane = addedTiles.get(new Point(row, column));
        if (tilePane == null) {
          tilePane = new MapElement();
          tilePane.setPosition(new Point(row, column));
          tilePane.getStyleClass().add("map-tile");
          mapPane.getChildren().add(tilePane);
          addedTiles.put(new Point(row, column), tilePane);
        }

        // Set the style for the tiles, based on their 'tileClass' field.
        if (mapTile != null) {
          String tileClass = mapTile.getCssClassName();
          tilePane.setId("tile-" + tileClass);
          
          // Add an egg on the tile, if there are any.
          if (mapTile.hasEggs()) {
            MapElement eggTile = new MapElement("egg");
            eggTile.setPosition(new Point(row, column));
            mapPane.getChildren().add(eggTile);
            overlaidTiles.add(eggTile);
          }
        } else {
          // If the tile is null, make it invisible.
          tilePane.setId("invisible");
        }
      }
      
      // Lastly, add the player figure on to the map.
      MapElement playerTile = new MapElement("player");
      mapPane.getChildren().add(playerTile);
      overlaidTiles.add(playerTile);
    }
  }

  /**
   * Clears all the tiles from the map.
   */
  @Override
  public void clearMapTiles()
  {
    mapPane.getChildren().removeAll();
  }
  
  /**
   * Clears all the eggs and player tiles from the map.
   */
  private void clearOverlaidTiles()
  {
    mapPane.getChildren().removeAll(overlaidTiles);
  }
}
