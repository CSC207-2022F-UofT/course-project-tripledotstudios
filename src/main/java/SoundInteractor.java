import java.io.File;
import javax.sound.sampled.*;
import java.lang.Exception;

public class SoundInteractor {
    private Clip sound;
    private boolean isPlaying;
    private AudioInputStream inputStream;

    public SoundInteractor() {
        this.isPlaying = false;
    }

    /**
     * Plays audio file located at </filepath>.
     */
    public void playSound(String filepath) {
        try {
            this.stopSound();
            File audioFile = new File(filepath);
            this.inputStream = AudioSystem.getAudioInputStream(audioFile);
            this.sound = AudioSystem.getClip();
            this.sound.open(inputStream);
            this.sound.start();
            this.isPlaying = true;
        }
        catch(Exception e) {
            System.out.println("Cannot find audio file.");
        }
    }

    /**
     * Stops playing audio if an audio is currently playing.
     */
    public void stopSound() {
        if (this.isPlaying) {
            this.sound.stop();
            this.sound.close();
            this.sound = null;
            this.isPlaying = false;
            this.inputStream = null;
        }
    }

}
