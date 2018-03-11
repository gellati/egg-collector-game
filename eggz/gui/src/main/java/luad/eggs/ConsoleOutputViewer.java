package luad.eggs;

import luad.eggs.animals.Animal;
import luad.eggs.mapTiles.MapTile;

/**
 * An output viewer that prints output to the console.
 */
public class ConsoleOutputViewer implements OutputViewer
{
  /**
   * The character displayed for tiles that return as null (e.g., tiles
   * outside the player's viewing area).
   */
  private static final char nullCharacter = ' ';
  
  /**
   * The character displayed for the player.
   */
  private static final char playerCharacter = '☺';

  /**
   * Displays a message by printing it to the console.
   * 
   * @param message The message to print.
   */
  @Override
  public void displayMessage(String message)
  {
    System.out.println(message);
  }

  /**
   * Displays a grid of map tiles to the console in a grid shape.
   * 
   * @param grid The grid of map tiles to display.
   */
  @Override
  public void displayMapTiles(MapTile[][] grid)
  {
    displayMessage(tilesToString(grid));
  }
  
  /**
   * Converts an array of {@link MapTile} objects into a human-readable grid
   * for printing.
   * @param grid The array of tiles to convert.
   * @return A human-readable String representation of the map grid.
   */
  public static String tilesToString(MapTile[][] grid)
  {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0 ; i < grid.length ; ++i) {
      for (int j = 0 ; j < grid[0].length ; ++j) {
        // If we are at the centre of the grid, we print a player character
        // to show where the player is.
        if ((i == (grid.length - 1) / 2) &&
            (j == (grid[0].length - 1) / 2)) {
          stringBuilder.append(playerCharacter);
          continue;
        }
        MapTile currentTile = grid[i][j];
        if (currentTile != null) {
          if (currentTile.hasEggs()) {
            stringBuilder.append('E');
          } else {
            stringBuilder.append(currentTile.getToken());
          }
        } else {
          stringBuilder.append(nullCharacter);
        }
      }
      if (i != grid.length - 1) {
        stringBuilder.append("\n");
      }
    }
    return (stringBuilder.toString());
  }

  /**
   * Displays a player's inventory to the console.
   * 
   * @param player The player whose inventory to display.
   */
  @Override
  public void displayInventory(Player player)
  {
    displayMessage("EGGS:");
    int numberOfEggs = player.getCarriedEggs().size();
    displayMessage("Carrying " + numberOfEggs + " mystery eggs.\n");
    displayMessage("ANIMALS:");
    int numberOfAnimals = player.getCarriedAnimals().size();
    displayMessage("Carrying " + numberOfAnimals + " animal(s).");
    for (Animal animal : player.getCarriedAnimals()) {
      displayMessage(animal.getName() + " says " + animal.getSound());
    }
    displayMessage("");
  }
}
