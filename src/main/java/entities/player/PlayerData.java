package entities.player;  // relevant imports

import entities.items.ItemData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The PlayerData class
 */
public class PlayerData implements Serializable {
    /* Stores the health of the entities.player.PlayerData object */
    // public static final int MAX_HEALTH = 500;

    // Stores the attack power of the entities.player.PlayerData object
    private int attackPower;

    // The current event
    private int eventID;

    // The username of the player
    private final String username;

    // The inventory of the entities.player.PlayerData stores the
    // name of the item mapped to the entities.items.ItemData object
    @SuppressWarnings("all")
    private HashMap<String, ArrayList<ItemData>> inventory;

    /**
     * Constructor
     * @param username The username of the Player
     * @param eventID The current event
     */
    public PlayerData(String username, int eventID, int ap, HashMap<String, ArrayList<ItemData>> inventory) {
        this.inventory = inventory;
        this.eventID = eventID;
        this.username = username;
        this.attackPower = ap;
    }

    /**
     * Returns the value of the entities.player.PlayerData object's Attack Power
     * @return attackPower
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Updates the entities.player.PlayerData's attack power
     * @param attackPower the new value for attackPower
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Returns the eventID of the entities.player.PlayerData object
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
     * Returns the username of the entities.player.PlayerData object
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Adds an entities.items.ItemData object to the entities.player.PlayerData object's inventory.
     * Creates a new list of entities.items.ItemData if there is not already one.
     * @param item The entities.items.ItemData object that is added to the entities.player.PlayerData's inventory.
     */
    @SuppressWarnings("all")
    public void addToInventory(ItemData item) {
        // if the item is not already in the map, create a list for it.
        if (!inventory.containsKey(item.getName())) {
            inventory.put(item.getName(), new ArrayList<ItemData>());
        }

        // add the item to the list in the hashmap.
        inventory.get(item.getName()).add(item);
    }

    /**
     * Removes an entities.items.ItemData object from the entities.player.PlayerData object's inventory.
     * @param item The entities.items.ItemData object that is removed from the inventory.
     * @return removed Whether the entities.items.ItemData object was removed.
     */
    public boolean removeFromInventory(ItemData item) {
        // boolean to determine if the value was returned successfully.
        boolean removed;
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
     * @param item The entities.items.ItemData object that we count in the inventory.
     * @return the number of instances of the item type in the inventory.
     */
    public int itemCount(ItemData item) {
        return inventory.get(item.getName()).size();
    }

    /**
     * Returns a list of the ItemData objects from the inventory.
     * @return items
     */
    public ArrayList<ItemData> getInventoryItems() {
        // the list of ItemData objects
        ArrayList<ItemData> items = new ArrayList<>();

        // looping through the values of the hashmap
        for (ArrayList<ItemData> itemList : inventory.values()) {
            // looping through the values in each list
            items.addAll(itemList);
        }
        return items;
    }
}
