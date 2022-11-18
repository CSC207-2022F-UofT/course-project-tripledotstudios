package useCases;


import java.io.*;

public class SaveInteractor implements Saver {
    /**
     * Writes the player data to file at filepath.
     *
     * @param filePath the file to write the records to
     * @param playerData the player data that needs to be serialized
     */

    @Override
    public void saveToFile(String filePath, Object playerData) throws IOException{

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(playerData);
        output.close();
    }

}
