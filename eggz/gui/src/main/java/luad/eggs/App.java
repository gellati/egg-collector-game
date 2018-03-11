package luad.eggs;

/**
 * The main class that runs the game.
 */
public class App 
{
  /**
   * Sets up the game, using the provided map, and places a player on the map
   * at position (2, 3).  Starts the game.
   * @param args Command-line arguments, not used.
   */
  public static void main( String[] args )
  {
    GameMap map = new TextMap();
    System.out.println("main1");
//    map.readMap("src/main/resources/maps/map.txt");
    map.readMap("/maps/map.txt");
    System.out.println("main2");
    Player player = new HumanPlayer();
    player.setPosition(new Point2D(2, 3));
    OutputViewer viewer = new ConsoleOutputViewer();
    System.out.println("main3");
    GameModel gameModel = new StandardGameModel(viewer, map, player);
    System.out.println("main10");
    StreamInputController controller =
        new StreamInputController(System.in);
    System.out.println("main11");
    controller.addObserver(gameModel);
    new Thread(controller).start();
    System.out.println("main12");
  }
}
