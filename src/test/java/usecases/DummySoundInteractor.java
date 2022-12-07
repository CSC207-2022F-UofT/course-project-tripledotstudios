package usecases;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class DummySoundInteractor {
    /** Clip that contains the audio */
    private Clip sound;

    /** Stores whether playSound has been called more recently than stopSound if at all */
    boolean isPlaying;

    /** InputStream through which audio is played */
    AudioInputStream inputStream;

    /** Stores whether the sound setting is set to on */
    boolean soundChoice;

    public Thread playSound;

    public DummySoundInteractor() {
        isPlaying = false;
        soundChoice = true;
    }

    /**
     * Getter method for sound.
     * @return sound.
     */
    public Clip getSound() { return sound; }

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
     * Providing a value to the clip object
     * @param filepath the file that contains the audio
     */
    public void createSound(String filepath) {
        try{
            // create a file object that is directed to the path specified
            File audioFile = new File(filepath);

            // set up the audio input stream
            inputStream = AudioSystem.getAudioInputStream(audioFile);

            // create new audio format
            AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // load information to the clip object
            sound = (Clip) AudioSystem.getLine(info);
            sound = AudioSystem.getClip();
            sound.open(inputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the clip object and set it to null.
     */
    public void closeSound() {
        // stop the sound first
        stopSound();

        // delete the sound
        sound.close();
        sound = null;
    }

    /**
     * Plays new audio located at </filepath>.
     */
    public void playSound() {
        // first checks if the thread is null to avoid problems
        if (playSound != null){
            // if the thread is running, we stop the sound
            if (playSound.isAlive()) {
                System.out.println("sound.stop() is executed here.");
            }
        }

        // create a new thread to play the sound
        playSound = new Thread(() -> {
            // stop any previous sounds
            stopSound();

            // play/resume the sound
            System.out.println("sound.start() is executed here.");
            isPlaying = true;
        });

        // start the thread
        playSound.start();
    }

    /**
     * Stops playing audio if an audio is currently playing.
     */
    public void stopSound() {
        // check if sound is a null object
        if (isPlaying && (sound != null)) {
            // stop the sound
            System.out.println("sound.stop() is called here.");
            isPlaying = false;
            inputStream = null;
        }

        // stop the thread
        playSound.interrupt();
    }
}
