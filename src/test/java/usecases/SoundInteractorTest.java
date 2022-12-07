package usecases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecases.dummyclasses.DummySoundInteractor;

class SoundInteractorTest {

    DummySoundInteractor si = new DummySoundInteractor();

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

    // tests for createSound

    @Test
    public void SoundInteractorCreateSound() {
        si.createSound("src/test/data/epic-inspirational.wav");
        Assertions.assertNotNull(si.getSound());
    }

    // tests for closeSound

    @Test
    public void SoundInteractorCloseSound() {
        si.createSound("src/test/data/epic-inspirational.wav");
        si.closeSound();
        Assertions.assertNull(si.getSound());
    }
}
