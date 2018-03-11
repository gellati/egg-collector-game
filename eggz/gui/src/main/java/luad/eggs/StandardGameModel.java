package luad.eggs;

import java.util.Observable;
import java.util.Set;

import luad.eggs.mapTiles.MapTile;

/**
 * A model of the game using text-based commands.
 */
public class StandardGameModel implements GameModel
{
  /**
   * Viewer for displaying the game to the user.
   */
  private OutputViewer viewer;
  
  /**
   * Map to play the game on.
   */
  private GameMap map;
  
  /**
   * The player on the map.
   */
  private Player player;
  
  /**
   * Creates an instance of the game model populated with the data it needs.
   * @param viewer The device used for displaying output.
   * @param map    The map that the player will be on.
   * @param player The player playing the game.
   */
  public StandardGameModel(OutputViewer viewer,
                           GameMap map,
                           Player player)
  {
    this.viewer = viewer;
    this.map = map;
    this.player = player;
  }

  /**
   * Takes in a command and executes the corresponding action.  See the
   * overridden method {@link GameModel.executeCommand} for details of the
   * valid commands.
   * 
   * @param command The command to execute.
   */
  @Override
  public void executeCommand(String command)
  {
    switch (command) {
      case "move N":
        move('N');
        break;
      case "move S":
        move('S');
        break;
      case "move E":
        move('E');
        break;
      case "move W":
        move('W');
        break;
      case "pick up":
        pickUp();
        break;
      case "search":
        searchRadius(3);
        break;
      case "inventory":
        displayInventory();
        break;
      default:
        viewer.displayMessage(command + " is not a valid command!");
        break;
    }
  }

  /**
   * Moves the player in the specified direction.
   * 
   * @param direction A character ({@code 'N'}, {@code 'S'},
   *                  {@code 'E'} or {@code 'W'}) denoting the direction to
   *                  move the player in.
   */
  @Override
  public void move(char direction)
  {
    Point2D currentPosition = player.getPosition();
    Point2D newPosition = null;
    
    switch (direction) {
      case 'N':
        newPosition = currentPosition.add(-1, 0);
        break;
      case 'S':
        newPosition = currentPosition.add(1, 0);
        break;
      case 'E':
        newPosition = currentPosition.add(0, 1);
        break;
      case 'W':
        newPosition = currentPosition.add(0, -1);
        break;
      default:
        // Method has been called incorrectly.
        System.err.println("Called 'move()' with incorrect parameter " + direction);
        System.err.println("Legal parameters are 'N', 'S', 'E', and 'W'");
        break;
    }
    
    MapTile newTile = map.getTile(newPosition);

    if (newTile != null) {
      if (newTile.isAccessible()) {
        player.setPosition(newPosition);
        player.incrementGameTime();
      } else {
        viewer.displayMessage("That tile is inaccessible!");
      }
    } else {
      viewer.displayMessage("That tile is outside the map!");
    }
  }

  /**
   * Tries to pick up eggs on the tile that the player is currently standing
   * on.  Adds the eggs to the player's inventory if successful.
   */
  @Override
  public void pickUp()
  {
    Point2D currentPosition = player.getPosition();
    Set<Egg> eggsOnTile = map.removeEggs(currentPosition);
    if (!(eggsOnTile.isEmpty())) {
      viewer.displayMessage("Found eggs on this tile!");
      for (Egg egg : eggsOnTile) {
        player.addEgg(egg);
      }
    } else {
      viewer.displayMessage("No eggs on this tile!");
    }
  }

  /**
   * Searches a circular radius around the player and displays the results to
   * the output viewer.
   * 
   * @param radius The radius to search.
   */
  @Override
  public void searchRadius(int radius)
  {
    Point2D currentPosition = player.getPosition();
    MapTile[][] searchResults = map.searchRadius(currentPosition, radius);
    viewer.displayMapTiles(searchResults);
  }

  /**
   * Displays the player's inventory (eggs and animals) to the output viewer.
   */
  @Override
  public void displayInventory()
  {
    viewer.displayInventory(player);
  }

  /**
   * Prints out a grid of tiles to the output viewer.
   * 
   * @param grid The grid of tiles to print.
   */
  @Override
  public void printMapGrid(MapTile[][] grid)
  {
    viewer.displayMapTiles(grid);
  }

  /**
   * Prints a message to the output viewer.
   * 
   * @param message The message to print.
   */
  @Override
  public void printMessage(String message)
  {
    viewer.displayMessage(message);
  }

  /**
   * When we receive a message from the input controller, execute that
   * message as a command.
   * 
   * @param observable The controller that sent the message (not used).
   * @param message    The command that was sent.
   */
  @Override
  public void update(Observable observable, Object message)
  {
    String command = (String) message;
    executeCommand(command);
  }
}
