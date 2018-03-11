package luad.eggs.mapTiles;

/**
 * A piece of sea on the map.
 */
public class SeaTile extends MapTile
{

  /**
   * @return '~'
   */
  @Override
  public char getToken()
  {
    return '~';
  }

  /**
   * @return false - sea tiles are inaccessible.
   */
  @Override
  public boolean isAccessible()
  {
    return false;
  }

  /**
   * @return "sea"
   */
  @Override
  public String getCssClassName()
  {
    return "sea";
  }
}
