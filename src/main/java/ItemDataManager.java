import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemDataManager{
    public ArrayList<ItemData> lst = new ArrayList<>(); // List of ItemData Objects;

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

    public ArrayList<ItemData> getItemData(){
        return this.lst;
    }
}