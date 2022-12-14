package usecases.dummyclasses;
import entities.player.PlayerData;
import usecases.gamesave.Saver;


import java.io.*;

public class DummySaveInteractor implements Saver {

    public DummySaveInteractor() {}

    /**
     * Writes the player data to file at filepath.
     * @param filePath the file to write the records to
     * @param playerData the player data that needs to be serialized
     */

    @Override
    public void saveToFile(String filePath, PlayerData playerData) throws IOException{

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(playerData);
        output.close();
    }
}
