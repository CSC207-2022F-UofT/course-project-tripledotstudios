package entities;  // relevant imports

import java.util.ArrayList;

/**
 * PlayerInteractor class
 * Responsible for updating the entities.PlayerData class.
 */
@SuppressWarnings("all")
public class PlayerInteractor {

    /**
     * The entities.PlayerData object that this class will interact with.
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
     * Updates the entities.PlayerData object that this class interacts with.
     * @param p the new entities.PlayerData object.
     */
    public void updatePlayer(PlayerData p) {
        player = p;
    }

    /**
     * Updates the player's attack power.
     * @param attackPower the value to be added to the entities.PlayerData object.
     */
    public void updateAttackPower(int attackPower) {
        player.setAttackPower(player.getAttackPower() + attackPower);
    }

    /**
     * Returns the player's attack power.
     * @return the entities.PlayerData object's attack power.
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
     * Returns what event id is stored in the entities.PlayerData object.
     * @return The current event stored in the entities.PlayerData object.
     */
    public int getPlayerEventID() {
        return player.getEventID();
    }

    /**
     * Returns the username of the entities.PlayerData object.
     * @return The username of the entities.PlayerData object.
     */
    public String getPlayerUsername() {
        return player.getUsername();
    }

    /**
     * Adds an entities.ItemData object to the entities.PlayerData object's inventory.
     * Creates a new list of entities.ItemData if there is not already one.
     * @param item The entities.ItemData object that is added to the entities.PlayerData's inventory.
     */
    public void addToInventory(ItemData item) {
        player.addToInventory(item);
    }

    /**
     * Removes an entities.ItemData object from the entities.PlayerData object's inventory.
     * @param item The entities.ItemData object that is removed from the inventory.
     * @return removed Whether the entities.ItemData object was removed.
     */
    public boolean removeFromInventory(ItemData item) {
        return player.removeFromInventory(item);
    }

    /**
     * Returns the number of items of a type in a list.
     * @param item The entities.ItemData object that we count in the inventory.
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
