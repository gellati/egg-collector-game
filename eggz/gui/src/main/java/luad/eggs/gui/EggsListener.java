package luad.eggs.gui;

import java.util.Arrays;
import java.util.Collection;
import java.util.Observable;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * {@link Observable} class designed to listen to the Eggs GUI and send
 * messages to its observers.
 */
public class EggsListener extends Observable
{
  /**
   * GUI instance to listen to.
   */
  private EggsGui gui;
  
  /**
   * Adds listeners to all the buttons on the GUI that cause them to send
   * messages to observers when they are pressed.  Adds keyboard shortcuts to
   * the buttons.
   * @param gui The GUI to listen to. 
   */
  public EggsListener(EggsGui gui)
  {
    this.gui = gui;
    Controls controls = gui.getControls();
    Node moveNorthButton = controls.getMoveNorthButton();
    Node moveSouthButton = controls.getMoveSouthButton();
    Node moveEastButton = controls.getMoveEastButton();
    Node moveWestButton = controls.getMoveWestButton();
    Node pickUpButton = controls.getPickUpButton();
    
    sendCommandsOnClick(moveNorthButton,
                        new String[] {
                            "move N", "search", "inventory"
                        });
    addKeyboardShortcuts(moveNorthButton, Arrays.asList(KeyCode.UP, KeyCode.W));
    
    sendCommandsOnClick(moveSouthButton,
                        new String[] {
                            "move S", "search", "inventory"
                        });
    addKeyboardShortcuts(moveSouthButton, Arrays.asList(KeyCode.DOWN, KeyCode.S));
                                     
    sendCommandsOnClick(moveEastButton,
                        new String[] {
                            "move E", "search", "inventory"
                        });
    addKeyboardShortcuts(moveEastButton, Arrays.asList(KeyCode.RIGHT, KeyCode.D));
                                     
    sendCommandsOnClick(moveWestButton,
                        new String[] {
                            "move W", "search", "inventory"
                        });
    addKeyboardShortcuts(moveWestButton, Arrays.asList(KeyCode.LEFT, KeyCode.A));
    
    sendCommandsOnClick(pickUpButton,
                        new String[] {
                            "pick up", "search", "inventory"
                        });
    addKeyboardShortcuts(pickUpButton, Arrays.asList(KeyCode.SPACE, KeyCode.P));
  }

  /**
   * Adds a listener to a button to cause it to send a list of commands when
   * it is clicked.
   * @param button The button to listen to.
   * @param commands Commands to send to observers when the button is clicked.
   */
  private void sendCommandsOnClick(Node button, String[] commands)
  {
    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event)
      {
        for (String s : commands) {
          sendCommand(s);
        }
      }
    });
  }
  
  /**
   * Adds a keyboard shortcut to a button so that pressing that key fires the
   * click event for the button.
   * @param button The button to add the keyboard shortcut to.
   * @param key The key that will press the button.
   */
  private void addKeyboardShortcut(Node button, KeyCode key)
  {
    Scene scene = gui.getContainingScene();
    scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event)
      {
        if (event.getCode().equals(key)) {
          button.fireEvent(createNewMouseEvent(MouseEvent.MOUSE_PRESSED, true));
        }
      }
    });

    scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event)
      {
        if (event.getCode().equals(key)) {
          button.fireEvent(createNewMouseEvent(MouseEvent.MOUSE_RELEASED, false));
          button.fireEvent(createNewMouseEvent(MouseEvent.MOUSE_CLICKED, false));
        }
      }
    });
  }
  
  /**
   * Adds a collection of keyboard shortcuts to a button so that if any of
   * the keys is pressed then the button's click event will be fired.
   * @param button The button to add the shortcuts to.
   * @param keys The list of keyboard shortcuts to add to the button.
   */
  private void addKeyboardShortcuts(Node button, Collection<KeyCode> keys)
  {
    for (KeyCode key : keys) {
      addKeyboardShortcut(button, key);
    }
  }
  
  /**
   * Factory method that creates a mouse event so we can simulate button
   * presses programmatically.
   * @param mouseEvent The type of mouse event; whether the mouse has been 
   * pressed, released or clicked.
   * @param isButtonDown Whether or not the button is down (true for 
   * mouse-down, false for mouse-up or mouse-click).
   * @return The mouse event we have created.
   */
  private static Event createNewMouseEvent(EventType<MouseEvent> mouseEvent,
                                      boolean isButtonDown)
  {
    return new MouseEvent(mouseEvent,
                          0, 0, 0, 0,
                          MouseButton.PRIMARY, 1, 
                          false, false, false, false, isButtonDown,
                          false, false, true, true, true,
                          null);
  }

  /**
   * Sends a command using to all {@link java.util.Observer}s, using the
   * {@link java.util.Observable.notifyObservers} method.
   * @param command The text command to send to observers.
   */
  protected void sendCommand(String command)
  {
    setChanged();
    notifyObservers(command);
  }
}
