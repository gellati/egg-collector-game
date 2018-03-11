package luad.eggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.InputStream;
import java.io.InputStreamReader;



import luad.eggs.animals.AnimalDecode;
import luad.eggs.mapTiles.MapTile;

public class TextMap extends GameMap
{
  /**
   * 2D array of tiles representing the map grid.
   */
  private MapTile[][] mapGrid;
  
  /**
   * Reads the map in from a text representation in a file.
   * 
   * @param fileName Path to the file where the map is stored.
   */
  @Override
  public void readMap(String fileName)
  {
    // Load the text representation of the map into a 2D array of characters.
    // Get the width and height of the map.
    char[][] mapRepresentation = null;
    try {
      mapRepresentation = loadTiles(fileName);
    } catch (IOException exception) {
      System.err.println("Error reading file.");
      exception.printStackTrace();
      System.exit(1);
    }

    int mapWidth = mapRepresentation.length;
    int mapHeight = mapRepresentation[0].length;
    
    // Set up the array of 'MapTile' objects.  Each character in the file
    // refers to a different sort of map tile.
    mapGrid = new MapTile[mapWidth][mapHeight];
    
    for (int i = 0 ; i < mapWidth ; ++i) {
      for (int j = 0 ; j < mapHeight ; ++j) {
        char currentTile = mapRepresentation[i][j];
        mapGrid[i][j] = AnimalDecode.decodeTile(currentTile);
      }
    }
  }
  
  /**
   * Loads the map tiles from a file and puts them into a 2D {@code char}
   * array.
   * @param fileName Path to the file where the map tiles are stored.
   * @return Map tiles as a 2D {@code char} array.
   * @throws IOException If there is a problem reading the file.
   */
  private char[][] loadTiles(String fileName) throws IOException
  {
    List<char[]> tilesList = new ArrayList<char[]>();
    InputStream is = getClass().getResourceAsStream(fileName);
    if(getClass().getResourceAsStream(fileName) == null){
      System.out.println("is null");
    }
//    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String line;
    while ((line = reader.readLine()) != null) {
      tilesList.add(line.toCharArray());
      System.out.println(line);
    }
    is.close();
    reader.close();

    return (tilesList.toArray(new char[0][0]));
  }

  /**
   * Gets the tile at a given position.
   * 
   * @param position The position to get the tile from.
   */
  @Override
  public MapTile getTile(Point2D position)
  {
    int x = position.getX();
    int y = position.getY();
    if ((0 <= x) && (x < mapGrid.length)
        && (0 <= y) && (y < mapGrid[0].length)) {
      return mapGrid[position.getX()][position.getY()];
    } else {
      return null;
    }
  }

  /**
   * Removes the eggs from a position on the map and returns them.
   * 
   * @param position The position to remove the eggs from.
   * @return The eggs removed from the position.
   */
  @Override
  public Set<Egg> removeEggs(Point2D position)
  {
    return getTile(position).removeEggs();
  }
}
