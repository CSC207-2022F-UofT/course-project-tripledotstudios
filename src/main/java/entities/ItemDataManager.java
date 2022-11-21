package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * entities.ItemDataManager class that reads through the entities.ItemData csv and stores objects of type entities.ItemData
 * in a list.
 */
public class ItemDataManager {
    public List<ItemData> lst = new ArrayList<>(); // List of entities.ItemData Objects;

    public ItemDataManager(String fileName) {
        String line = "";
        String splitBy = ",";

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
     * method that returns the list of entities.ItemData objects made from ItemManager
     * @return list
     */
    public List<ItemData> getItemData(){
        return this.lst;
    }
}