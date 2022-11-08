import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundInteractor {
    private static Media sound;
    private static MediaPlayer mediaPlayer;
    /**
     *
     */
    public SoundInteractor(String filepath) {
        try {
            // these all need to be static variables!
            this.sound = new Media(filepath);
            this.mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        catch(Exception e) {
            System.out.println(""); // add error message
        }
    }

    /**
     * Plays audio file.
     */
    public static void playSound() {
        try {

        }
        catch(Exception e) {
            System.out.println("Could not play sound.");
        }
    }

    /**
     * Stops playing audio file.
     */
    public static void stopSound() {

    }

}
