import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundInteractor {

    /**
     * Plays audio file.
     */
    public static void playSound(String filepath) {
        // need to stop any sound that is already playing first
        try {
            Media sound = new Media(filepath);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        catch(Exception e) {
            System.out.println("Could not play sound.");
        }
    }


}
