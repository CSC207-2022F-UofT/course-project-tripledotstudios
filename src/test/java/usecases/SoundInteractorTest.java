package usecases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoundInteractorTest {

    DummySoundInteractor si = new DummySoundInteractor();
    String sound = "src/test/data/epic-inspirational.wav"; // must be repository root
    
    // tests for getSoundChoice
    
    @Test
    public void SoundInteractorGetSoundChoice() {
        Assertions.assertEquals(si.soundChoice, si.getSoundChoice());
    }
    
    // tests for switchSoundChoice
    
    @Test
    public void SoundInteractorSwitchSoundChoice() {
        boolean expected = !(si.getSoundChoice());
        si.switchSoundChoice();
        boolean actual = si.getSoundChoice();
        Assertions.assertEquals(expected, actual);
    }

    // tests for playSound

    @Test
    public void SoundInteractorPlaySound() {
        si.createSound(sound);
        si.playSound();
        Assertions.assertTrue(si.playSound.isAlive());
        si.closeSound();
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
