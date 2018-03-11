package luad.eggs.network.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import luad.eggs.ConsoleOutputViewer;
import luad.eggs.OutputViewer;
import luad.eggs.Player;
import luad.eggs.animals.Animal;
import luad.eggs.mapTiles.MapTile;

/**
 * Broadcasts game information to clients.
 */
public class ServerHead extends PrintWriter implements OutputViewer
{
  /**
   * Denotes the end of a message.
   */
  public static final String MESSAGE_END = "end";  

  /**
   * Sets up a stream to write to the socket.
   * @param clientSocket The socket to write to.
   * @throws IOException If there was a problem with IO connecting to the
   *                     socket.
   */
  public ServerHead(Socket clientSocket) throws IOException
  {
    super(clientSocket.getOutputStream());
  }
  
  /**
   * Prints a message to the stream.
   */
  @Override
  public void displayMessage(String message)
  {
    startMessage("message");
    println(message);
    endMessage();
  }

  /**
   * Prints a grid of map tiles to the stream, encoded as characters.
   */
  @Override
  public void displayMapTiles(MapTile[][] grid)
  {
    startMessage("map");
    println(ConsoleOutputViewer.tilesToString(grid));
    endMessage();
  }

  /**
   * Prints a code to the stream that encodes a player's inventory.
   */
  @Override
  public void displayInventory(Player player)
  {
    startMessage("inventory");

    // First send the number of eggs.
    println(Integer.toString(player.getCarriedEggs().size()));

    // Then send the list of all the animals collected.
    for (Animal animal : player.getCarriedAnimals()) {
      print(animal.getCode());
    }
    println();
    endMessage();
  }
  
  /**
   * Run this command before starting a message to give it a header.
   * @param messageType Message-type header to tell it apart from other
   *                    messages.
   */
  private void startMessage(String messageType)
  {
    println(messageType);
  }
  
  /**
   * Run this command to end and send a message.
   */
  private void endMessage()
  {
    println(MESSAGE_END);
    flush();
  }
}
