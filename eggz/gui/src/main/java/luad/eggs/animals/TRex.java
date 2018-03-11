package luad.eggs.animals;

/**
 * A T-Rex.
 */
public class TRex implements Animal
{

  /**
   * @return "T-Rex"
   */
  @Override
  public String getName()
  {
    return "T-Rex";
  }

  /**
   * @return "ROAR"
   */
  @Override
  public String getSound()
  {
    return "ROAR";
  }

  /**
   * @return 'T'
   */
  @Override
  public char getCode()
  {
    return 'T';
  }

}
