/** relevant imports */
import java.util.ArrayList;

/**
 * PlayerInteractor class
 * Responsible for updating the PlayerData class.
 */
public class PlayerInteractor {

    /**
     * The PlayerData object that this class will interact with.
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
     * Updates the PlayerData object that this class interacts with.
     * @param p the new PlayerData object.
     */
    public void updatePlayer(PlayerData p) {
        player = p;
    }

    /**
     * Updates the player's attack power.
     * @param attackPower the value to be added to the PlayerData object.
     */
    public void updateAttackPower(int attackPower) {
        player.setAttackPower(player.getAttackPower() + attackPower);
    }

    /**
     * Returns the player's attack power.
     * @return the PlayerData object's attack power.
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
     * Returns what event id is stored in the PlayerData object.
     * @return The current event stored in the PlayerData object.
     */
    public int getPlayerEventID() {
        return player.getEventID();
    }

    /**
     * Returns the username of the PlayerData object.
     * @return The username of the PlayerData object.
     */
    public String getPlayerUsername() {
        return player.getUsername();
    }

    /**
     * Adds an ItemData object to the PlayerData object's inventory.
     * Creates a new list of ItemData if there is not already one.
     * @param item The ItemData object that is added to the PlayerData's inventory.
     */
    public void addToInventory(ItemData item) {
        player.addToInventory(item);
    }

    /**
     * Removes an ItemData object from the PlayerData object's inventory.
     * @param item The ItemData object that is removed from the inventory.
     * @return removed Whether the ItemData object was removed.
     */
    public boolean removeFromInventory(ItemData item) {
        return player.removeFromInventory(item);
    }

    /**
     * Returns the number of items of a type in a list.
     * @param item The ItemData object that we count in the inventory.
     * @return the number of instances of the item type in the inventory.
     */
    public int itemCount(ItemData item) {
        return player.itemCount(item);
    }

}
