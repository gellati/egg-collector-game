package luad.eggs.gui;

import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import luad.eggs.Player;
import luad.eggs.animals.Animal;

public class InventoryPane extends VBox implements InventoryDisplay
{
  /**
   * Pane at the top to display the title.
   */
  private Pane titleBox = new HBox();
  
  /**
   * Pane in the middle to display the eggs that have been collected.
   */
  private Pane eggsBox = new HBox();
  
  /**
   * Pane at the bottom to display the animals that have been collected.
   */
  private GridPane animalsPane = new GridPane();

  /**
   * Sets up the pane.
   */
  public InventoryPane()
  {
    super();
    getStyleClass().add("inventory-pane");
    ObservableList<Node> children = getChildren();
    titleBox.getStyleClass().add("title-box");
    setUpTitleBox();
    children.add(titleBox);

    eggsBox.getStyleClass().add("eggs-box");
    ScrollPane eggsScrollPane = new ScrollPane();
    eggsScrollPane.getStyleClass().add("eggs-scroll-pane");
    eggsScrollPane.setContent(eggsBox);
    children.add(eggsScrollPane);

    // Prevent the new scroll pane from receiving the focus, so that it
    // doesn't interfere with our hot-keys.
    ScrollPane animalsScrollPane = new ScrollPane() {
      @Override
      public void requestFocus() { }
    };
    animalsScrollPane.getStyleClass().add("animals-scroll-pane");
    animalsScrollPane.setContent(animalsPane);
    animalsPane.getStyleClass().add("animals-pane");
    children.add(animalsScrollPane);
  }
  
  private void setUpTitleBox()
  {
    Pane bagIcon = new InventoryIcon("bag-icon");
    Text inventoryTitle = new Text("Inventory");
    inventoryTitle.getStyleClass().add("inventory-title");
    titleBox.getChildren().addAll(bagIcon, inventoryTitle);
  }

  /**
   * Displays a player's inventory on the pane.
   * @param player The player whose inventory to display.
   */
  @Override
  public void displayInventory(Player player)
  {
    // Clear the existing eggs and animals from the inventory.
    eggsBox.getChildren().clear();
    animalsPane.getChildren().clear();
    
    // Add in the new information about the eggs and animals.
    int numberOfEggs = player.getCarriedEggs().size();
    Collection<Animal> carriedAnimals = player.getCarriedAnimals();
    displayEggs(numberOfEggs);
    displayAnimals(carriedAnimals);
  }

  private void displayAnimals(Collection<Animal> carriedAnimals)
  {
    // Loop over the animals, creating elements to display a picture and a
    // description of the animal.
    int row = 0;
    for (Animal animal : carriedAnimals) {
      String name = animal.getName();
      String sound = animal.getSound();
      Pane icon = new InventoryIcon(name.toLowerCase() + "-icon");
      Text description = new Text(name + " says " + sound);

      // Set row and column indices for the new elements.
      GridPane.setRowIndex(icon, row);
      GridPane.setColumnIndex(icon, 0);
      GridPane.setRowIndex(description, row);
      GridPane.setColumnIndex(description, 1);
      
      // Add the elements to the pane.
      animalsPane.getChildren().addAll(icon, description);
      ++row;
    }
  }

  private void displayEggs(int numberOfEggs)
  {
    for (int i = 0 ; i < numberOfEggs ; ++i) {
      eggsBox.getChildren().add(new InventoryIcon("egg-icon"));
    }
  }

}
