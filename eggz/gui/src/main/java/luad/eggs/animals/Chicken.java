package luad.eggs.animals;

/**
 * A chicken
 */
public class Chicken implements Animal
{

  /**
   * @return "Chicken"
   */
  @Override
  public String getName()
  {
    return "Chicken";
  }

  /**
   * @return "Cluck"
   */
  @Override
  public String getSound()
  {
    return "Cluck";
  }

  /**
   * @return 'C'
   */
  @Override
  public char getCode()
  {
    return 'C';
  }

}
