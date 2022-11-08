import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundInteractor {
    private static Media sound;
    private static MediaPlayer mediaPlayer;

    /**
     * Constructor.
     * @param sound
     * @param mediaPlayer
     */
    public SoundInteractor() {
        this.sound = null;
        this.mediaPlayer = null;
    }

    /**
     * Plays audio file at location filepath.
     * @throws e if
     */
    public static void playSound(String filepath) {
        try {
            if (this.sound != null){
                this.stopSound()
            }
            this.sound = new Media(filepath);
            this.mediaPlayer = new MediaPlayer(sound)
            this.mediaPlayer.play();
        }
        catch(Exception e) {
            System.out.println("Could not play sound.");
        }
    }

    /**
     * Stops playing audio file.
     * @throws e if
     */
    public static void stopSound() {
        try {
            this.mediaPlayer.stop();
        }
        catch(Exception e) {
            System.out.println("Could not stop playing sound.");
        }
    }

}
