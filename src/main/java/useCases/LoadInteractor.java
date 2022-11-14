package useCases;
<<<<<<< HEAD
import entities.PlayerData;

import java.io.*;

public class LoadInteractor implements Writer {
=======

import java.io.*;

public class LoadInteractor {
>>>>>>> bfc2a64ff052ae705eb93158466dd983a3a14b6a
    /**
     * Loads the player data from the file path
     *
     * @param filePath file where the player data is stored
     * @return entities.PlayerData, the player's data
     *
     */
    @Override
    public PlayerData readFromFile(String filePath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        PlayerData playerData = (PlayerData) input.readObject();
        input.close();
        return playerData;
    }

}
