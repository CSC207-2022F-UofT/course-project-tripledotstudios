import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemDataManager class that reads through the ItemData csv and stores objects of type ItemData
 * in a list.
 */
public class ItemDataManager{
    public List<ItemData> lst = new ArrayList<>(); // List of ItemData Objects;

    public ItemDataManager(){
        String line = "";
        String splitBy = ",";

        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("CSVDemo.csv"));

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] e = line.split(splitBy);    // use comma as separator
                ItemData item = new ItemData(e[0], e[1], Integer.parseInt(e[2]));
                lst.add(item);
            }
        }
        catch (IOException e){
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