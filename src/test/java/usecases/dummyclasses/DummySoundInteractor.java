package usecases.dummyclasses;

public class DummySoundInteractor {
    /** Stores whether playSound has been called more recently than stopSound if at all */
    boolean isPlaying;

    /** Stores whether the sound setting is set to on */
    public boolean soundChoice;

    public DummySoundInteractor() {
        isPlaying = false;
        soundChoice = true;
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
}
