package luad.eggs.animals;

/**
 * A duck.
 */
public class Duck implements Animal
{

  /**
   * @return "Duck"
   */
  @Override
  public String getName()
  {
    return "Duck";
  }

  /**
   * @return "Quack"
   */
  @Override
  public String getSound()
  {
    return "Quack";
  }

  /**
   * @return 'D'
   */
  @Override
  public char getCode()
  {
    return 'D';
  }

}
