package luad.eggs.animals;

/**
 * A triceratops
 */
public class Triceratops implements Animal
{

  /**
   * @return "Triceratops"
   */
  @Override
  public String getName()
  {
    return "Triceratops";
  }

  /**
   * @return "HRRRR!!"
   */
  @Override
  public String getSound()
  {
    return "HRRRR!!";
  }

  /**
   * @return '3'
   */
  @Override
  public char getCode()
  {
    return '3';
  }

}
