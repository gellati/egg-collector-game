package luad.eggs;

import luad.eggs.animals.Animal;

/**
 * Simple implementation of the {@link Egg} interface.
 */
public class SimpleEgg implements Egg
{
  /**
   * The time left until the egg hatches.
   */
  private int incubationTime;
  
  /**
   * The animal inside the egg.
   */
  private Animal contents;
  
  /**
   * Creates a new egg.
   * @param incubationTime The time the egg will take to hatch.
   * @param contents The animal inside the egg.
   */
  public SimpleEgg(int incubationTime, Animal contents)
  {
    this.contents = contents;
    this.incubationTime = incubationTime;
  }

  /**
   * Ages the egg by one step, reducing the time till hatching by 1.
   */
  @Override
  public void decrementIncubationTime()
  {
    if (incubationTime > 0) {
      --incubationTime;
    }
  }

  /**
   * @return true  If the egg is ready to hatch.
   *         false If the egg is not yet ready to hatch.
   */
  @Override
  public boolean isReadyToHatch()
  {
    return (incubationTime <= 0);
  }

  /**
   * @return The animal inside the egg.
   */
  @Override
  public Animal getContents()
  {
    return contents;
  }

}
