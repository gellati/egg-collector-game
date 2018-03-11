package luad.eggs.animals;

/**
 * A representation of an animal.  Different animals have different
 * attributes, which correspond to the values returned by the methods
 * in this interface.
 */
public interface Animal
{
  /**
   * Each animal has a name.  
   * @return The name of the animal.
   */
  public String getName();
  
  /**
   * The animals all make different sounds.
   * @return The sound that the animal makes.  
   */
  public String getSound();
  
  /**
   * We use a one-character code to refer to each animal.
   * @return Code referring to the animal.
   */
  public char getCode();
}
