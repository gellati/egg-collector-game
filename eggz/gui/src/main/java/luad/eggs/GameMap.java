package luad.eggs;

import java.util.Set;

import luad.eggs.mapTiles.MapTile;

public abstract class GameMap
{
  /**
   * Reads the map in from a file.
   * @param fileName The file to read the map from.
   */
  public abstract void readMap(String fileName);
  
  /**
   * Gets the value of the tile at a particular position.
   * @param position The position where the tile is.
   * @return The tile at that position.
   */
  public abstract MapTile getTile(Point2D position);
  
  /**
   * Gets the eggs lying at a particular position and removes them from the
   * map.
   * @param position The position to look for the eggs.
   * @return The set of eggs that are at that position.  
   */
  public abstract Set<Egg> removeEggs(Point2D position);
  
  /**
   * Searches a radius around a particular position in the map.
   * @param centre The centre of the circle to search.
   * @param radius The radius of the circle to search.
   * @return An array containing the search results.  
   */
  public MapTile[][] searchRadius(Point2D centre, int radius)
  {
    int width = (2 * radius) + 1;
    MapTile[][] reply = new MapTile[width][width];
    
    for (int i = 0 ; i < width ; ++i) {
      for (int j = 0 ; j < width ; ++j) {
        Point2D currentPoint =
            centre.add(i - radius, j - radius);
        if (centre.distanceSquaredFrom(currentPoint) <= (radius * radius)) {
          reply[i][j] = getTile(currentPoint);
        } else {
          reply[i][j] = null;
        }
      }
    }
    
    return reply;
  }
}
