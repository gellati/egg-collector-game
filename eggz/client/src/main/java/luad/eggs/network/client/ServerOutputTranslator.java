package luad.eggs.network.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import luad.eggs.HumanPlayer;
import luad.eggs.OutputViewer;
import luad.eggs.Player;
import luad.eggs.SimpleEgg;
import luad.eggs.animals.AnimalDecode;
import luad.eggs.mapTiles.MapTile;

/**
 * Translates textual input from the network and displays it to an output
 * viewer.
 */
public class ServerOutputTranslator implements Observer
{
  /**
   * Viewer to display the server output to once it has been translated.
   */
  private OutputViewer viewer;

  /**
   * Sets up the viewer.
   * @param viewer The viewer to display the server output once it has been 
   *               translated.
   */
  public ServerOutputTranslator(OutputViewer viewer)
  {
    this.viewer = viewer;
  }

  /**
   * When we receive a message from the server, parse it and display the
   * result on the output viewer.
   */
  @Override
  public void update(Observable observable, Object message)
  {
    @SuppressWarnings("unchecked")
    Queue<String> messages = (Queue<String>) message;
    String messageType = messages.poll();
    switch (messageType) {
      case "message":
        viewer.displayMessage(messages.poll());
        break;
      case "map":
        viewer.displayMapTiles(messagesToMapTiles(messages));
        break;
      case "inventory":
        viewer.displayInventory(createDummyPlayerFromInventoryCode(messages));
        break;
      default:
        viewer.displayMessage("Do not know what do with " + messageType);
        break;
    }
  }

  /**
   * Converts a queue of map-line strings into a grid of
   * {@link luad.eggs.mapTiles.MapTile} objects.
   * 
   * @param messages Queue of lines from the server, each representing a row
   *                 of the game map.
   * @return         A 2D array of {@code MapTile} objects corresponding to
   *                 the input from the server.
   */
  private static MapTile[][] messagesToMapTiles(Queue<String> messages)
  {
    List<MapTile[]> mapTiles = new ArrayList<>();
    messages.iterator()
            .forEachRemaining(mapLine -> {
              List<MapTile> currentLine = new ArrayList<>();
              char[] tileCodes = mapLine.toCharArray();
              for (int i = 0 ; i < tileCodes.length ; ++i) {
                currentLine.add(AnimalDecode.decodeTile(tileCodes[i]));
              }
              mapTiles.add(currentLine.toArray(new MapTile[0]));
            });
    
    return mapTiles.toArray(new MapTile[0][0]);
  }

  /**
   * Given messages from the server encoding a player's inventory, create a
   * dummy object of the {@link luad.eggs.Player} class with a matching
   * inventory.  The purpose of this object is so it can be passed to the 
   * {@link luad.eggs.OutputViewer.displayInventory} method, which expects a
   * {@code Player} object.
   * @param messages Messages from the server that encode the number of eggs
   *                 and the number of each type of animal that a player is
   *                 holding.
   * @return
   */
  private static Player createDummyPlayerFromInventoryCode(Queue<String> messages)
  {
    Player dummyPlayer = new HumanPlayer();
    int numberOfEggs = Integer.parseInt(messages.poll());
    for (int i = 0 ; i < numberOfEggs ; ++i) {
      // Add a dummy egg with nothing in it to the player's inventory.
      dummyPlayer.getCarriedEggs().add(new SimpleEgg(0, null));
    }
    String carriedAnimals = messages.poll();
    for (int i = 0 ; i < carriedAnimals.length() ; ++i) {
      dummyPlayer.getCarriedAnimals()
                 .add(AnimalDecode.decodeAnimal(carriedAnimals.charAt(i)));
    }
    return dummyPlayer;
  }
}
