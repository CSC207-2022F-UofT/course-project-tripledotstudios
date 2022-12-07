package usecases;

import entities.items.ItemData;
import entities.player.PlayerData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecases.dummyclasses.DummyLoadInteractor;
import usecases.dummyclasses.DummySaveInteractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveAndLoadInteractorTest {

    DummySaveInteractor si = new DummySaveInteractor();
    DummyLoadInteractor li = new DummyLoadInteractor();

    @Test
    public void SaveAndLoadInteractor() throws IOException, ClassNotFoundException {
        // create a player (expected data)
        String username = "username";
        HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>();
        PlayerData player = new PlayerData(username, 0, 15, inventory);

        String filename = "src/test/data/savefiles/" + username;

        // save
        si.saveToFile(filename, player);

        // load
        PlayerData loadedPlayer = li.readFromFile(filename);

        // assert statements
        Assertions.assertEquals(player.getUsername(), loadedPlayer.getUsername());
        Assertions.assertEquals(player.getEventID(), loadedPlayer.getEventID());
        Assertions.assertEquals(player.getAttackPower(), loadedPlayer.getAttackPower());
        Assertions.assertEquals(player.getInventoryItems(), loadedPlayer.getInventoryItems());
    }
}
