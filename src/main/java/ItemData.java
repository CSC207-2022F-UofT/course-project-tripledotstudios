/**
 * ItemData class that has information of what one Item object has as attributes and methods.
 */
public class ItemData {

    private final String itemname; // Name of the item

    private final String itemattrib; // The Attribute the item affects

    private final int itemvalue; // The value of the item's effect

    public ItemData(String itemname, String itemattrib, int itemvalue){
        this.itemname = itemname;
        this.itemattrib = itemattrib;
        this.itemvalue = itemvalue;
    }

    public String getItemname(){
        return this.itemname;
    }

    public String getItemattrib(){
        return this.itemattrib;
    }

    public int getItemvalue(){
        return this.itemvalue;
    }

}
