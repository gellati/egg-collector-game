package luad.eggs.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import luad.eggs.GameMap;
import luad.eggs.GameModel;
import luad.eggs.HumanPlayer;
import luad.eggs.OutputViewer;
import luad.eggs.Player;
import luad.eggs.Point2D;
import luad.eggs.StandardGameModel;
import luad.eggs.StreamInputController;
import luad.eggs.TextMap;

/**
 * A server running the Eggs game.  An eggs client can connect to this server
 * in order to play the game.
 */
public class EggsServer
{
  /**
   * Port number to connect to.
   */
  private static final int PORT_NUMBER = 9009;

  /**
   * Server socket to broadcast on.
   */
  private static ServerSocket serverSocket;

  /**
   * Sets up a client connection and I/O.
   * @param args Command-line arguments.
   */
  public static void main(String[] args)
  {
    try {
      serverSocket = new ServerSocket(PORT_NUMBER);
      Socket clientSocket;

      while((clientSocket = serverSocket.accept()) != null){
        // Set up the game.
        GameMap map = new TextMap();
        System.out.println("main4");
  //      map.readMap("src/main/resources/maps/bigMap.txt");
        map.readMap("/maps/bigMap.txt");
        Player player = new HumanPlayer();
        player.setPosition(new Point2D(2, 3));
        System.out.println("main5");
        OutputViewer viewer = new ServerHead(clientSocket);
        GameModel gameModel = new StandardGameModel(viewer, map, player);
        System.out.println("main6");

        // Set up the output back to the socket.
        StreamInputController controller;
        controller = new StreamInputController(clientSocket.getInputStream());
        controller.addObserver(gameModel);
        new Thread(controller).start();
      }

    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
