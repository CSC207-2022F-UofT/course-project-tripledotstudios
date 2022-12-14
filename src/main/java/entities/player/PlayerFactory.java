package entities.player;

import entities.items.ItemData;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {
    public static PlayerData generatePlayer(String username, int eventID, int ap, HashMap<String, ArrayList<ItemData>> inventory) {
        return new PlayerData(username, eventID, ap, inventory);
    }
}
