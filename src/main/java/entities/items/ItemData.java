package entities.items;

import java.io.Serializable;

/**
 * entities.items.ItemData class that has information of what one Item object has as attributes and methods.
 */
public class ItemData implements Serializable{

    private final String name; // Name of the item

    private final String attribute; // The Attribute the item affects

    private final int value; // The value of the item's effect

    public ItemData(String name, String attribute, int value){
        this.name = name;
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Returns the name of the item (as a String).
     * @return the name of the item
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the attribute of the item. So far, this is either "health" or "attack".
     * @return the attribute of the item, either "health" or "attack"
     */
    public String getAttribute(){
        return this.attribute;
    }

    /**
     * Returns the effective numerical value of the item, i.e. how many points of
     * health or attack can this item induce.
     * @return the effective numerical value of the item
     */
    public int getValue(){
        return this.value;
    }


}
