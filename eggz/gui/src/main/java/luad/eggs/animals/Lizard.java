package luad.eggs.animals;

/**
 * A lizard.
 */
public class Lizard implements Animal
{

  /**
   * @return "Lizard"
   */
  @Override
  public String getName()
  {
    return "Lizard";
  }

  /**
   * @return "nothing"
   */
  @Override
  public String getSound()
  {
    return "nothing";
  }

  /**
   * @return 'L'
   */
  @Override
  public char getCode()
  {
    return 'L';
  }

}
