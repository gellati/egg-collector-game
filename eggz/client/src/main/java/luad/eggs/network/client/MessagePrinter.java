package luad.eggs.network.client;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} class that writes a message to an output stream when it
 * updates.
 */
public class MessagePrinter extends PrintWriter implements Observer
{
  /**
   * Sets up the underlying {@PrintWriter} to send messages to an output
   * stream
   * @param outputStream The stream we want to send messages to.
   */
  public MessagePrinter(OutputStream outputStream)
  {
    super(outputStream);
  }
  
  /**
   * Sends a command to the output stream.
   * @param command The command to send.
   */
  public void sendCommand(Object command)
  {
    println(command);
    flush();
  }

  /**
   * When an observing object sends a message, updates by printing that
   * message to the output stream.
   */
  @Override
  public void update(Observable observable, Object command)
  {
    sendCommand(command);
  }
}
