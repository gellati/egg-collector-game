package luad.eggs.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

//import luad.eggs.network.server.ServerHead;

/**
 * {@link Observable} class that listens for messages from a server socket
 * and sends them through to its {@link Observer}s.
 */
public class ServerListener extends Observable implements Runnable
{
  /**
   * Socket to listen to.
   */
  private Socket socket;
//  ServerHead.MESSAGE_END
  public static final String MESSAGE_END = "end";

  /**
   * Sets the socket to listen for messages on.
   * @param socket The socket to listen to.
   */
  public ServerListener(Socket socket)
  {
    this.socket = socket;
  }

  /**
   * Bundles lines of input from the server together into individual
   * messages, delimited by the special ending string
   * {@link luad.eggs.network.client.ServerHead.MESSAGE_END}, before sending
   * them to observers.
   */
  @Override
  public void run()
  {
    try {
      BufferedReader fromServer =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String serverLine;
      Queue<String> serverMessageQueue = new LinkedList<>();

      // Loop through the input lines from the server, adding them to a queue
      // of strings.  When we reach the "message end" string, send the queue
      // off to observers and clear it so we can start filling it up again.
      while ((serverLine = fromServer.readLine()) != null) {
        if (!(serverLine.equals(MESSAGE_END))) {
          serverMessageQueue.add(serverLine);
        } else {
          setChanged();
          notifyObservers(serverMessageQueue);
          serverMessageQueue.clear();
        }
      }
      socket.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
