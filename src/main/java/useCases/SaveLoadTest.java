package useCases;
import entities.PlayerData;
import entities.ItemData;


import java.io.*;
import java.io.IOException;
import java.io.Serializable;



public class SaveLoadTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PlayerData player = new PlayerData("hualaina", 1, 50);


        try{
            SaveInteractor saver = new SaveInteractor();
            saver.saveToFile("player.ser", player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done saving");

        LoadInteractor loader = new LoadInteractor();
        PlayerData loadedPlayer = loader.readFromFile("player.ser");
        System.out.println(loadedPlayer.getUsername());
        System.out.println(loadedPlayer.getEventID());
        System.out.println(loadedPlayer.getAttackPower());
    }

}