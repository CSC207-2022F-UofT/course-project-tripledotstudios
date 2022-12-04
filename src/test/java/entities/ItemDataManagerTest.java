package entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * ItemDataManagerTest tests the functionality of ItemDataManager, specifically:
 * loading all events from data/ItemObjects.csv into a List of ItemData objects,
 * containing all the *possible items* that the player can get.
 * (essentially an item library)
 * .
 * (This class effectively tests both ItemDataManager and ItemData)
 */
public class ItemDataManagerTest {
    /**
     * ItemDataManagerReadFile tests if ItemDataManager reads from data/ItemObjects.csv
     * correctly.
     */
    @Test
    public void ItemDataManagerReadFile(){
        ItemDataManager itemManager = new ItemDataManager("data/ItemObjects.csv");
        List<ItemData> items = itemManager.getItemData();

        for(ItemData item : items){
            Assertions.assertTrue(item.getName().length() > 0);

            String itemAttribute = item.getAttribute();
            Assertions.assertTrue(itemAttribute.equals("attack")  || itemAttribute.equals("health"));
            // So far attributes can only be of these 2 values, may change later.
        }
    }
}
