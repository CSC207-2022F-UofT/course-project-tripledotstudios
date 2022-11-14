package useCases;
import entities.PlayerData;


import java.io.IOException;

public interface Writer {
    /**
     * @param filepath location of ser file
     * */
    PlayerData readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
