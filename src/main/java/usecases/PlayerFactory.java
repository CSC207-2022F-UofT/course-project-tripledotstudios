package usecases;

import entities.ItemData;
import entities.PlayerData;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerFactory {
    static PlayerData generatePlayer(String username, int eventID, int ap, HashMap<String, ArrayList<ItemData>> inventory) {
        return new PlayerData(username, eventID, ap, inventory);
    }
}
