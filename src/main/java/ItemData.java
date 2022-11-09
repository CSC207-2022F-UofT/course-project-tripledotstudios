/**
 * ItemData class that has information of what one Item object has as attributes and methods.
 */
public class ItemData {

    private final String name; // Name of the item

    private final String attribute; // The Attribute the item affects

    private final int value; // The value of the item's effect

    public ItemData(String name, String attribute, int value){
        this.name = name;
        this.attribute = attribute;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public String getAttribute(){
        return this.attribute;
    }

    public int getValue(){
        return this.value;
    }

}