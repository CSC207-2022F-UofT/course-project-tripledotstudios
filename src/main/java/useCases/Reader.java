package useCases;

import java.io.IOException;

public interface Reader {
    /**
     * @param filepath location of ser file
     * @param o object to be serialized
     */
    void saveToFile(String filepath, Object o) throws IOException;
}
