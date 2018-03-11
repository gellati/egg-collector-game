package luad.eggs.animals;

import luad.eggs.SimpleEgg;
import luad.eggs.mapTiles.*;

/**
 * Contains static methods that can decode characters corresponding to
 * different sorts of map tiles and animals
 */
public class AnimalDecode
{
  /**
   * Decodes a map tile character and returns a matching
   * {@link luad.eggs.mapTiles.MapTile} object.
   * @param tileCode One-character code denoting the tile.
   * @return A {@code MapTile} object matching that code.
   */
  public static MapTile decodeTile(char tileCode)
  {
    MapTile newTile;
    
    // If there is a space, return null.
    if (tileCode == ' ') {
      return null;
    }
    
    // Decide whether the tile should be a land tile or a sea tile.
    if (tileCode == '~') {
      newTile = new SeaTile();
    } else {
      newTile = new LandTile();
    }

    // Put any eggs on to the new tile.
    if (tileCode == 'E') {
      // Put a dummy egg on the tile.
      newTile.addEgg(new SimpleEgg(0, null));
    } else {
      Animal animalOnTile = decodeAnimal(tileCode);
      if (animalOnTile != null) {
        int hatchingTime = getHatchingTime(tileCode);
        newTile.addEgg(new SimpleEgg(hatchingTime, animalOnTile));
      }
    }

    return newTile;
  }
  
  /**
   * Given a one-character code representing an animal, return that animal.
   * @param animalCode The code representing the animal.
   * @return A new instance of the appropriate {@link Animal} subclass.
   */
  public static Animal decodeAnimal(char animalCode)
  {
    Animal decodedAnimal;
    
    switch (animalCode) {
      case 'C':
        decodedAnimal = new Chicken();
        break;
      case 'D':
        decodedAnimal = new Duck();
        break;
      case 'S':
        decodedAnimal = new Snake();
        break;
      case 'L':
        decodedAnimal = new Lizard();
        break;
      case 'T':
        decodedAnimal = new TRex();
        break;
      case '3':
        decodedAnimal = new Triceratops();
        break;
      default:
        decodedAnimal = null;
        break;
    }

    return(decodedAnimal);
  }
  
  /**
   * Given a one-character code representing an animal, return how long it
   * should take an egg containing that animal to hatch.
   * @param animalCode Code representing an animal.
   * @return How long it takes one of their eggs to hatch.
   */
  public static int getHatchingTime(char animalCode)
  {
    switch (animalCode) {
      case 'C':
        return 3;
      case 'D':
        return 5;
      case 'S':
        return 5;
      case 'L':
        return 7;
      case 'T':
        return 10;
      case '3':
        return 10;
      default:
        return 0;
    }
  }
}
