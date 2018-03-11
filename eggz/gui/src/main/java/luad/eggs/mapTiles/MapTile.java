package luad.eggs.mapTiles;

import java.util.HashSet;
import java.util.Set;

import luad.eggs.Egg;

/**
 * Represents a tile on a map.
 */
public abstract class MapTile
{
  /**
   * The eggs stored on this tile.
   */
  private Set<Egg> eggsOnTile = new HashSet<>();

  /**
   * We want to be able to read and write a map from a file, so we need a
   *  unique string representation (a token) of the tile.
   * @return The unique string representation of this map tile.
   */
  public abstract char getToken();
  
  /**
   * Map tiles can be classed as either accessible or inaccessible.  If a
   * player tries to move on to an inaccessible tile, then they will get an
   * error message.
   * @return Whether or not the tile is accessible.
   */
  public abstract boolean isAccessible();
  
  /**
   * Adds a new egg to the tile.
   * @param egg the new egg to add.
   */
  public void addEgg(Egg egg)
  {
    eggsOnTile.add(egg);
  }
  
  /**
   * Empties the eggs from the tile, and returns the eggs collected.
   * @return The set of eggs that were found on the tile.
   */
  public Set<Egg> removeEggs()
  {
    Set<Egg> eggsFound = new HashSet<>(eggsOnTile);
    eggsOnTile.removeAll(eggsOnTile);
    return eggsFound;
  }
  
  /**
   * @return true  If the set of eggs on the tile is non-empty.
   *         false If the set of eggs on the tile is empty.
   */
  public boolean hasEggs()
  {
    return !(eggsOnTile.isEmpty());
  }
  
  /**
   * Returns the CSS class name for the tile.
   * @return The CSS class name for the tile (by default, this is the same as
   *         the token name, but may need to be overridden for special 
   *         characters.
   */
  public String getCssClassName()
  {
    return "" + getToken();
  }
}
