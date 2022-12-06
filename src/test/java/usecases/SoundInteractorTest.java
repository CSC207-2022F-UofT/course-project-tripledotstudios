package usecases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoundInteractorTest {

    SoundInteractor si = new SoundInteractor();
    String sound = "src/test/data/epic-inspirational.wav"; // must be repository root

    // tests for playSound

    @Test
    public void SoundInteractorPlaySound() {
        si.createSound(sound);
        si.playSound();
        Assertions.assertTrue(si.playSound.isAlive());
        si.stopSound();
    }

    // tests for stopSound

    @Test
    public void SoundInteractorStopSound() {
        si.createSound(sound);
        si.playSound();
        si.stopSound();
        Assertions.assertFalse(si.getSound().isActive());
    }
}
