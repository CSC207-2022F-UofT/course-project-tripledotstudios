package entities;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class SoundInteractor {
    private Clip sound;
    private boolean isPlaying;
    private AudioInputStream inputStream;

    public SoundInteractor() {
        isPlaying = false;
    }

    /**
     * Getter method for isPlaying, which indicates whether sound is currently playing.
     * @return isPlaying.
     */
    public boolean getIsPlaying() {
        return isPlaying;
    }

    /**
     * Plays new audio located at </filepath>.
     * @param filepath a String of the desired audio's path.
     */
    public void playSound(String filepath) {
        try {
            // Stop any sound that may already be playing
            stopSound();
            // Create a File from the desired audio's path
            File audioFile = new File(filepath);
            // Assign a new AudioInputStream to inputStream using the File that was just created
            inputStream = AudioSystem.getAudioInputStream(audioFile);
            // Create and play new Clip item
            sound = AudioSystem.getClip();
            sound.open(inputStream);
            sound.start();
            // Update status
            isPlaying = true;
            // Print statement is necessary for sound to play
            System.out.println();
            // Ensures that sound will not stop prematurely
            while (sound.isActive()) {}
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Stops playing audio if an audio is currently playing.
     */
    public void stopSound() {
        // Checks if sound is playing before executing
        if (isPlaying) {
            sound.stop();
            sound.close();
            sound = null;
            isPlaying = false;
            inputStream = null;
        }
    }

}
