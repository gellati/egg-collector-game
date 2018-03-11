package luad.eggs.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Area to display status messages (usually at the bottom of the GUI).
 */
public class StatusText extends HBox
{
  /**
   * Area for displaying messages.
   */
  private Text messageDisplay = new Text();
  
  /**
   * Timeline that removes all text after a specified length of time.
   */
  private Timeline removeText = new Timeline();
  
  /**
   * Lays out the status bar and applies styles.
   */
  public StatusText()
  {
    super();
    getStyleClass().add("status-text-holder");
    messageDisplay.getStyleClass().add("status-text");
    getChildren().add(messageDisplay);
    removeText.setCycleCount(1);
  }

  /**
   * Schedules a message to appear on the status bar and then disappear after a
   * certain period of time.
   * 
   * @param messageTThe message to display.
   * @param ms      Time in milliseconds to keep the message up before 
   *                removing it.
   */
  public void scheduleMessage(String message, double ms)
  {
    removeText.stop();
    messageDisplay.setText(message);
    removeText.getKeyFrames().clear();
    removeText.getKeyFrames()
              .add(new KeyFrame(Duration.millis(ms),
                   event -> messageDisplay.setText("")));
    removeText.play();
  }
}
