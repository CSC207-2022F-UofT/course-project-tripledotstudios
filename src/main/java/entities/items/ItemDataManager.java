package entities.items;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemDataManager class that reads through the entities.items.ItemData csv and stores objects of type ItemData
 * in a list.
 */
public class ItemDataManager {
    private final List<ItemData> lst = new ArrayList<>(); // List of ItemData Objects;

    public ItemDataManager() {  // Loads data of all possible items from file.
        String line;
        String splitBy = ",";
        String fileName = "data/ItemObjects.csv";

        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] e = line.split(splitBy);    // use comma as separator
                ItemData item = new ItemData(e[0], e[1], Integer.parseInt(e[2]));
                lst.add(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method that returns the list of ItemData objects made from ItemManager
     * @return list
     */
    public List<ItemData> getItemData(){
        return this.lst;
    }
}