import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundInteractor {
    private Media sound;
    private MediaPlayer mediaPlayer;

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
            this.stopSound()
            this.sound = new Media(filepath);
            this.mediaPlayer = new MediaPlayer(this.sound);
            this.mediaPlayer.play();
        }
        catch(Exception e) {
            System.out.println("Could not play sound.");
        }
    }

    /**
     * Stops playing any audio that is currently playing.
     */
    public static void stopSound() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
        }
        this.sound, this.mediaPlayer = null;
    }

}
