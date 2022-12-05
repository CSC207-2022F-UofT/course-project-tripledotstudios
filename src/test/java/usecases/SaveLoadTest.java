package usecases;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entities.PlayerData;
import entities.ItemData;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Testing out the SaveInteractor and LoadInteractor to see if saving and loading works
public class SaveLoadTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Creating the PlayerData object to test
        HashMap<String, ArrayList<ItemData>> inv = new HashMap<>();
        PlayerData player = new PlayerData("hualaina", 1, 50, inv);
        ItemData item = new ItemData("Health Potion", "health", 100 );
        player.addToInventory(item);


        // file that the PlayerData gets saved to
        String filename = "data/savefiles/" + player.getUsername() + ".ser";


        // Testing out the save feature
        usecases.SaveInteractor saver = new usecases.SaveInteractor();
        try{
            saver.saveToFile(filename, player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done saving");


        // Testing out the loading feature
        usecases.LoadInteractor loader = new usecases.LoadInteractor();
        player = loader.readFromFile(filename);

        System.out.println(player.getUsername());
        System.out.println(player.getEventID());
        System.out.println(player.getAttackPower());

        // Updating the PlayerData object player to see if new updates are being saved
        player.setAttackPower(100);
        player.setEventID(10);

        try{
            saver.saveToFile(filename, player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done saving updated player");

        PlayerData newPlayer = loader.readFromFile(filename);
        System.out.println(newPlayer.getUsername());
        System.out.println(newPlayer.getEventID());
        System.out.println(newPlayer.getAttackPower());
        System.out.print(newPlayer.getUsername().equals(player.getUsername()));
        System.out.print(newPlayer.getEventID() == player.getEventID());
        System.out.print(newPlayer.getAttackPower() == player.getAttackPower());


    }

}
