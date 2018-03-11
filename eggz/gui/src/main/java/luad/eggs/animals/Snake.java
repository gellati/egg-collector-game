package luad.eggs.animals;

/**
 * A snake.
 */
public class Snake implements Animal
{

  /**
   * @return "Snake"
   */
  @Override
  public String getName()
  {
    return "Snake";
  }

  /**
   * @return "Hiss"
   */
  @Override
  public String getSound()
  {
    return "Hiss";
  }

  /**
   * @return 'S'
   */
  @Override
  public char getCode()
  {
    return 'S';
  }

}
