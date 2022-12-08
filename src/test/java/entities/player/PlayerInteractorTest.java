package entities.player;

import entities.items.ItemData;
import entities.player.PlayerData;
import entities.player.PlayerInteractor;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PlayerInteractorTest {

    HashMap<String, ArrayList<ItemData>> inv = new HashMap<>();
    PlayerData player = new PlayerData("username", 1, 50, inv);
    ;
    PlayerData newPlayer;
    PlayerInteractor playerInt = new PlayerInteractor(player);

    /**
     * TestPlayerIntGetter tests if PlayerInteractor's getter methods are working getting the PlayerData attributes
     * correctly.
     */

    @Test
    public void TestPlayerIntGetter() {
        ItemData item = new ItemData("Health Potion", "health", 100);
        player.addToInventory(item);
        Assertions.assertEquals("username", playerInt.getPlayerUsername());
        Assertions.assertEquals(1, playerInt.getPlayerEventID());
        Assertions.assertEquals(50, playerInt.getAttackPower());
        Assertions.assertEquals(1, playerInt.itemCount(item));
    }

    /**
     * TestUpdatePlayerInt tests if PlayerInteractor's update methods are updating
     * the player's attack power, event ID, etc.
     */
    @Test
    public void TestUpdatePlayerInt() {
        playerInt.updateAttackPower(1000);
        Assertions.assertEquals(1050, playerInt.getAttackPower());
        playerInt.updateEvent(10);
        Assertions.assertEquals(10, playerInt.getPlayerEventID());
    }

    /**
     * TestUpdatePlayer tests if the getter methods work when a new PlayerData object is updated as the new player in
     * PlayerInteractor
     */
    @Test
    public void TestUpdatePlayer() {
        HashMap<String, ArrayList<ItemData>> newInv = new HashMap<>();
        newPlayer = new PlayerData("newuser", 2, 100, newInv);
        playerInt.updatePlayer(newPlayer);
        Assertions.assertEquals("newuser", playerInt.getPlayerUsername());
        Assertions.assertEquals(2, playerInt.getPlayerEventID());
        Assertions.assertEquals(100, playerInt.getAttackPower());
    }
}
