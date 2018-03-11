package luad.eggs.network.client;

import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;
import luad.eggs.gui.EggsGui;
import luad.eggs.gui.EggsListener;
import luad.eggs.gui.GuiOutputViewer;

/**
 * Client for the Eggs game.  Connects to the server and displays the game on
 * a GUI.
 */
public class EggsClient extends Application
{
  
  /**
   * Entry point to the program.
   * @param args Command line arguments/
   */
  public static void main(String[] args)
  {
    launch(args);
  }

  /**
   * Sets the game up and starts it.
   * @param primaryStage The primary stage to set the scene on.
   */
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    // Set up the GUI.
    EggsGui gui = new EggsGui();
    primaryStage.setScene(gui.getContainingScene());
    primaryStage.show();
    
    // Set up networking.
    Socket socket = new Socket("localhost", 9009);
    MessagePrinter clientHead = new MessagePrinter(socket.getOutputStream());
    ServerListener serverListener = new ServerListener(socket);
    ServerOutputTranslator outputTranslator =
        new ServerOutputTranslator(new GuiOutputViewer(gui));
    serverListener.addObserver(outputTranslator);
    new Thread(serverListener).start();
    
    // Set the network up to listen to the GUI.
    EggsListener guiListener = new EggsListener(gui);
    guiListener.addObserver(clientHead);
    
    // Send an initial 'search' command so that the game map displays when
    // we start the program.
    clientHead.sendCommand("search");
  }
}
