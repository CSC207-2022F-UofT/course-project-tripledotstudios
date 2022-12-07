package entities.player;  // relevant imports

import entities.items.ItemData;

import java.util.ArrayList;

/**
 * PlayerInteractor class
 * Responsible for updating the entities.player.PlayerData class.
 */
@SuppressWarnings("all")
public class PlayerInteractor {

    /**
     * The entities.player.PlayerData object that this class will interact with.
     */
    PlayerData player;

    /**
     * Creates a PlaterInteractor object.
     * @param player The Player that this object will interact with.
     */
    public PlayerInteractor(PlayerData player) {
        this.player = player;
    }

    /**
     * Updates the entities.player.PlayerData object that this class interacts with.
     * @param p the new entities.player.PlayerData object.
     */
    public void updatePlayer(PlayerData p) {
        player = p;
    }

    /**
     * Updates the player's attack power.
     * @param attackPower the value to be added to the entities.player.PlayerData object.
     */
    public void updateAttackPower(int attackPower) {
        player.setAttackPower(player.getAttackPower() + attackPower);
    }

    /**
     * Returns the player's attack power.
     * @return the entities.player.PlayerData object's attack power.
     */
    public int getAttackPower() {
        return player.getAttackPower();
    }
    /**
     * Updates the event the player is on in the game.
     * @param eventID The UUID of the event.
     */
    public void updateEvent(int eventID) {
        player.setEventID(eventID);
    }

    /**
     * Returns what event id is stored in the entities.player.PlayerData object.
     * @return The current event stored in the entities.player.PlayerData object.
     */
    public int getPlayerEventID() {
        return player.getEventID();
    }

    /**
     * Returns the username of the entities.player.PlayerData object.
     * @return The username of the entities.player.PlayerData object.
     */
    public String getPlayerUsername() {
        return player.getUsername();
    }

    /**
     * Adds an entities.items.ItemData object to the entities.player.PlayerData object's inventory.
     * Creates a new list of entities.items.ItemData if there is not already one.
     * @param item The entities.items.ItemData object that is added to the entities.player.PlayerData's inventory.
     */
    public void addToInventory(ItemData item) {
        player.addToInventory(item);
    }

    /**
     * Removes an entities.items.ItemData object from the entities.player.PlayerData object's inventory.
     * @param item The entities.items.ItemData object that is removed from the inventory.
     * @return removed Whether the entities.items.ItemData object was removed.
     */
    public boolean removeFromInventory(ItemData item) {
        return player.removeFromInventory(item);
    }

    /**
     * Returns the number of items of a type in a list.
     * @param item The entities.items.ItemData object that we count in the inventory.
     * @return the number of instances of the item type in the inventory.
     */
    public int itemCount(ItemData item) {
        return player.itemCount(item);
    }

    /**
     * Returns a list of the ItemData objects using the PLayerData class's method.
     * @return items
     */
    public ArrayList<ItemData> getInventoryItems() {
        return player.getInventoryItems();
    }

    public PlayerData getReference() {
        return player;
    }

}
