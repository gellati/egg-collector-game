package luad.eggs;

import luad.eggs.animals.Animal;

public interface Egg
{
  /**
   * Each egg has an incubation time, which is set at the time the egg is
   * created, and is decremented each time this method is called.
   */
  public void decrementIncubationTime();
  
  /**
   * @return true  if the incubation time has reached 0.
   *         false if the incubation time is still greater than 0.
   */
  public boolean isReadyToHatch();
  
  /**
   * Gets the animal inside the egg.
   * @return The animal instance contained in the egg.  
   */
  public Animal getContents();

}
