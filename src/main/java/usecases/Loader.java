package usecases;

import entities.player.PlayerData;

import java.io.IOException;


public interface Loader {
    /**
     * @param filepath location of ser file
     * */
    PlayerData readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
