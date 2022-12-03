package usecases;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoundInteractorTest {

    SoundInteractor si = new SoundInteractor();
    String sound = "src/test/data/StarWars3.wav"; // must be repository root

    @Test
    public void SoundInteractorIsPlaying() {
        Assertions.assertFalse(si.getIsPlaying());
        si.playSound(sound);
        Assertions.assertTrue(si.getIsPlaying());
        si.stopSound();
        Assertions.assertFalse(si.getIsPlaying());
    }

    // no way to test sound.isActive() accuracy
    @Test
    public void SoundInteractorPlayAndStopSound() {
        Assertions.assertNull(si.getSound());
        si.playSound(sound);
        Assertions.assertNotEquals(si.getSound(), null);
        si.stopSound();
        Assertions.assertNull(si.getSound());
    }
}
