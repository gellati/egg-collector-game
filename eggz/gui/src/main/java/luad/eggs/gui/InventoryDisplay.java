package luad.eggs.gui;

import luad.eggs.Player;

public interface InventoryDisplay
{
  /**
   * Displays a player's inventory.
   * @param player The player whose inventory to display.
   */
  void displayInventory(Player player);
}