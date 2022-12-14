package adapters.gamesave;
import entities.player.PlayerData;
import usecases.gamesave.Loader;

import java.io.*;


public class LoadInteractor implements Loader {

    public LoadInteractor() {}

    /**
     * Loads the player data from the file path
     *
     * @param filePath file where the player data is stored
     * @return entities.player.PlayerData, the player's data
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
