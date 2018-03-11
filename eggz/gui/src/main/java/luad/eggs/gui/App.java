package luad.eggs.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import luad.eggs.GameMap;
import luad.eggs.GameModel;
import luad.eggs.HumanPlayer;
import luad.eggs.OutputViewer;
import luad.eggs.Player;
import luad.eggs.Point2D;
import luad.eggs.StandardGameModel;
import luad.eggs.TextMap;

public class App extends Application
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
    primaryStage.setTitle("Eggs");
    
    GameMap map = new TextMap();
//    map.readMap("src/main/resources/maps/bigMap.txt");
    map.readMap("/maps/bigMap.txt");
    Player player = new HumanPlayer();
    player.setPosition(new Point2D(2, 3));
    EggsGui root = new EggsGui();
    OutputViewer viewer = new GuiOutputViewer(root);
    GameModel gameModel = new StandardGameModel(viewer, map, player);
    gameModel.searchRadius(3);
    EggsListener listener = new EggsListener(root);
    listener.addObserver(gameModel);
    
    primaryStage.setScene(root.getContainingScene());

    primaryStage.show();
    
  }
}
