package usecases;

import entities.PlayerData;

import java.io.IOException;


public interface Saver {
    /**
     * @param filepath location of ser file
     * @param p object to be serialized
     */
    void saveToFile(String filepath, PlayerData p) throws IOException;
}
