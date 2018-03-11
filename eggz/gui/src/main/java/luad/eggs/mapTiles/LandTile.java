package luad.eggs.mapTiles;

/**
 * A piece of land on the map.
 */
public class LandTile extends MapTile
{
  /**
   * @return '.'
   */
  public char getToken()
  {
    return '.';
  }

  /**
   * @return true - land tiles are accessible.
   */
  public boolean isAccessible()
  {
    return true;
  }
  
  /**
   * @return "land"
   */
  @Override
  public String getCssClassName()
  {
    return "land";
  }
}
