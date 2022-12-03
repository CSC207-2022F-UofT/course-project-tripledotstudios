package usecases;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class SoundInteractor {
    /** Clip that contains the audio */
    private Clip sound;

    /** Stores whether playSound has been called more recently than stopSound if at all */
    private boolean isPlaying;

    /** InputStream through which audio is played */
    private AudioInputStream inputStream;

    /** Stores whether the sound setting is set to on */
    private boolean soundChoice;

    public SoundInteractor() {
        isPlaying = false;
        soundChoice = true;
    }

    /**
     *
     */
    public Clip getSound() { return sound;}

    /**
     * Getter method for isPlaying.
     * @return isPlaying.
     */
    public boolean getIsPlaying() {
        return isPlaying;
    }

    /**
     * Getter method for soundChoice.
     * @return soundChoice.
     */
    public boolean getSoundChoice() { return soundChoice; }

    /**
     * Changes soundChoice to store the opposite boolean that it currently stores.
     */
    public void switchSoundChoice() {
        soundChoice = !soundChoice;
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
            e.printStackTrace();
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
