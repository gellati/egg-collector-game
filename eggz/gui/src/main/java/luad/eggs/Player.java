package luad.eggs;

import java.util.Collection;

import luad.eggs.animals.Animal;

public interface Player
{
  /**
   * Each player has a position on the map.
   * @return The player's position on the map.
   */
  public Point2D getPosition();
  
  /**
   * This method allows us to move players to new positions on the map.
   * @param newPosition The new position to move the player to.
   */
  public void setPosition(Point2D newPosition);
  
  /**
   * Adds an egg to the player's inventory.
   * @param egg The egg to add.
   */
  public void addEgg(Egg egg);
  
  /**
   * Gets the player's inventory of eggs.
   * @return The set of eggs the player is carrying.
   */
  public Collection<Egg> getCarriedEggs();
  
  /**
   * Adds an animal to the player's inventory.
   * @param animal The animal to add.
   */
  public void addAnimal(Animal animal);
  
  /**
   * Get's the player's inventory of animals.
   * @return The set of animals the player is carrying.  
   */
  public Collection<Animal> getCarriedAnimals();
  
  /**
   * Increments the game time.  Concretely, this means doing the following:
   *  - Decrementing the incubation time of every egg in the inventory by 1.
   *  - Hatching all eggs that are ready to hatch, by removing the eggs from
   *      the egg inventory and placing their contents in the animal inventory.
   */
  public void incrementGameTime();
}
