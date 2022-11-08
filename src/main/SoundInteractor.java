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
     * @throws e if sound cannot be played.
     */
    public static void playSound(String filepath) {
        try {
            if (this.sound != null){
                this.stopSound()
            }
            this.sound = new Media(filepath);
            this.mediaPlayer = new MediaPlayer(sound);
            this.mediaPlayer.play();
        }
        catch(Exception e) {
            System.out.println("Could not play sound.");
        }
    }

    /**
     * Stops playing audio file.
     */
    public static void stopSound() {
            this.mediaPlayer.stop();
            this.sound, this.mediaPlayer = null;
    }

}
