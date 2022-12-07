package entities.player;

import entities.items.ItemData;

import java.util.ArrayList;

public class PlayerFactory {
    public static PlayerData generatePlayer(String username, int eventID, int ap, ArrayList<ItemData> inventory) {
        return new PlayerData(username, eventID, ap, inventory);
    }
}
