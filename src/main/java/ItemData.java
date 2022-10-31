public class ItemData {

    private String itemname; // Name of the item

    private String itemattrib; // The Attribute the item affects

    private int itemvalue; // The value of the item's effect

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
