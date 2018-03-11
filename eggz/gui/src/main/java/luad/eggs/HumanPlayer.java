package luad.eggs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import luad.eggs.animals.Animal;

/**
 * An implementation of the {@link Player} interfcae, using {@link HashSet}s
 * to store the player's data.
 */
public class HumanPlayer implements Player
{
  /**
   * The position the player is standing at.
   */
  private Point2D position;
  
  /**
   * The collection of eggs that the player is carrying.
   */
  private Collection<Egg> carriedEggs;
  
  /**
   * The collection of animals that the player is carrying.
   */
  private Collection<Animal> carriedAnimals;
  
  /**
   * Initializes the player's inventories using {@link HashSet}s.
   */
  public HumanPlayer()
  {
    carriedEggs = new ArrayList<>();
    carriedAnimals = new ArrayList<>();
  }

  /**
   * @return The player's position on the map.
   */
  @Override
  public Point2D getPosition()
  {
    return position;
  }

  /**
   * Sets the player's position.
   * @param newPosition The new position on the map.
   */
  @Override
  public void setPosition(Point2D newPosition)
  {
    this.position = newPosition;
  }

  /**
   * Adds an egg to the player's inventory.
   * 
   * @param egg The egg to add.
   */
  @Override
  public void addEgg(Egg egg)
  {
    carriedEggs.add(egg);
  }

  /**
   * @return The collection of eggs that the player is carrying.
   */
  @Override
  public Collection<Egg> getCarriedEggs()
  {
    return carriedEggs;
  }

  /**
   * Adds an animal to the player's inventory.
   * 
   * @param animal The animal to add.
   */
  @Override
  public void addAnimal(Animal animal)
  {
    carriedAnimals.add(animal);
  }

  /**
   * @return The collection of animals that the player is holding.
   */
  @Override
  public Collection<Animal> getCarriedAnimals()
  {
    return carriedAnimals;
  }

  /**
   * Ages the player by one turn, decrementing the incubation time of all the
   * eggs that the player is holding and hatching any eggs that are ready to
   * hatch.
   */
  @Override
  public synchronized void incrementGameTime()
  {
    // Loop over the eggs in the inventory.  For each egg, decrement the
    // incubation time.  If the egg is ready to hatch, remove it from the 
    // egg inventory and place the contents in the animal inventory.
    Collection<Egg> eggsToRemove = new ArrayList<Egg>();
    for (Egg egg : carriedEggs) {
      egg.decrementIncubationTime();
      if (egg.isReadyToHatch()) {
        eggsToRemove.add(egg);
        carriedAnimals.add(egg.getContents());
      }
    }
    carriedEggs.removeAll(eggsToRemove);
  }
}
