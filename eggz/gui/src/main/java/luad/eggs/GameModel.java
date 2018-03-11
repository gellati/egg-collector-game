package luad.eggs;

import java.util.Observable;
import java.util.Observer;

import luad.eggs.mapTiles.MapTile;

public interface GameModel extends Observer
{
  /**
   * Takes in a text command and executes that command for the given player.
   * The legal commands are:
   *  - "move N" / "move S" / "move E" / "move W"
   *    -- moves the player in the appropriate direction using the
   *      {@link move(char)} method below.
   *  - "pick up" -- picks up all the eggs lying on the current square
   *      and adds them to the player's inventory, using the
   *      {@link pickup()} method below.
   *  - "search" -- searches an area of radius 3 around the player, using
   *      the {@link searchRadius(int)} method below, and displays the 
   *      results.
   *  - "inventory" -- displays the contents of the player's inventory.
   * @param command The command to execute.
   * @param player The player to execute the command for.
   */
  public void executeCommand(String command);
  
  /**
   * Moves a player in a particular direction on the map.
   * @param direction The direction to move the player in - either
   *   'N', 'S', 'E' or 'W'.
   */
  public void move(char direction);
  
  /**
   * Given a player on the map, picks up all the eggs at the player's current
   * position and adds them to the player's inventory.
   */
  public void pickUp();
  
  /**
   * Searches a radius around the player and prints out the results using the
   * current output viewer.
   * @param radius
   */
  public void searchRadius(int radius);
  
  /**
   * Displays the contents of the player's inventory, using the current output
   * viewer.
   */
  public void displayInventory();
  
  /**
   * Prints out a section of the map, using the current output viewer.
   * @param grid The map grid to print out.
   */
  public void printMapGrid(MapTile[][] grid);

  /**
   * Prints out a message, using the current output viewer.
   * @param message The message to print out.
   */
  public void printMessage(String message);
  
  /**
   * Updates the game in respond to an input message.
   * @param observable The input controller that sent the message.
   * @param message The message that was sent.
   */
  public void update(Observable observable, Object message);
}
