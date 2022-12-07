package usecases.gamesave;

import entities.player.PlayerData;

import java.io.IOException;


public interface Saver {
    /**
     * @param filepath location of ser file
     * @param p object to be serialized
     */
    void saveToFile(String filepath, PlayerData p) throws IOException;
}
