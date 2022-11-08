/** relevant imports*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The PlayerData class
 */
public class PlayerData implements Serializable {
    /** Stores the health of the PlayerData object */
    public static final int MAXHEALTH = 500;

    /** Stores the attack power of the PlayerData object */
    private int attackPower;

    /** The current event */
    private int eventID;

    /** The username of the player */
    private String username;

    /** The inventory of the PlayerData stores the name of the item mapped to the ItemData object */
    private HashMap<String, ArrayList<ItemData>> inventory;

    /**
     * Constructor
     * @param username The username of the Player
     * @param eventID The current event
     */
    public PlayerData(String username, int eventID, int ap) {
        inventory = new HashMap<String, ArrayList<ItemData>>();
        this.eventID = eventID;
        this.username = username;
        this.attackPower = ap;
    }

    /**
     * Returns the value of the PlayerData object's Attack Power
     * @return attackPower
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Updates the PlayerData's attack power
     * @param attackPower the new value for attackPower
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Returns the eventID of the PlayerData object
     * @return eventID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Updates the eventID
     * @param eventID the new value of the eventID
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * Returns the username of the PlayerData object
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the PlayerData object's value.
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Adds an ItemData object to the PlayerData object's inventory.
     * Creates a new list of ItemData if there is not already one.
     * @param item The ItemData object that is added to the PlayerData's inventory.
     */
    public void addToInventory(ItemData item) {
        // if the item is not already in the map, create a list for it.
        if (!inventory.containsKey(item.getName())) {
            inventory.put(item.getName(), new ArrayList<ItemData>());
        }

        // add the item to the list in the hashmap.
        inventory.get(item.getName()).add(item);
    }

    /**
     * Removes an ItemData object from the PlayerData object's inventory.
     * @param item The ItemData object that is removed from the inventory.
     * @return removed Whether the ItemData object was removed.
     */
    public boolean removeFromInventory(ItemData item) {
        // boolean to determine if the value was returned successfuly.
        boolean removed = false;
        // assign a pointer to the list contained in the hashmap
        ArrayList<ItemData> pointer = inventory.get(item.getName());

        // removes the item if it is in the inventory
        removed = pointer.remove(item);

        // if the list is empty, we will entirely remove the entry from the hashmap
        if (pointer.isEmpty()) {
            inventory.remove(item.getName());
        }

        // returns whether this was successful
        return removed;
    }

    /**
     * Returns the number of items of a type in a list.
     * @param item The ItemData object that we count in the inventory.
     * @return the number of instances of the item type in the inventory.
     */
    public int itemCount(ItemData item) {
        return inventory.get(item.getName()).size();
    }
}
