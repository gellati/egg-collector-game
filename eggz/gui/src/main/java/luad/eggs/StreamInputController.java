package luad.eggs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Observable;

/**
 * An input controller that reads user input from an input stream and passes it
 * to the class's observers.  This class should be started in its own thread.
 */
public class StreamInputController extends Observable implements Runnable
{
  private BufferedReader reader;

  public StreamInputController(InputStream in)
  {
    reader = new BufferedReader(new InputStreamReader(in));
  }
  
  /**
   * Read input from the input stream and pass it on to observers.
   */
  @Override
  public void run()
  {
    try {
      String userInput;
      while ((userInput = reader.readLine()) != null) {
        setChanged();
        notifyObservers(userInput);
      }
    } catch (IOException exception) {
      System.err.print("There was an error getting input from the stream.");
      exception.printStackTrace();
    }
  }
}
