package usecases;

import java.io.IOException;
import java.util.*;
import presenter.*;
import entities.*;


public class StorylineInteractor {

    private final StorylineInterface VIEW;
    private final SoundInteractor SOUND;
    private final SaveInteractor SAVE;
    private final LoadInteractor LOAD;
    private PlayerInteractor player_action;
    private final CombatInteractor COMBAT;
    private final EventManager MANAGER;
    private final LoginInteractor LOGIN;

    public StorylineInteractor(StorylineInterface story, SoundInteractor soundInteractor,
                               SaveInteractor saveInteractor, LoadInteractor loadInteractor,
                               PlayerInteractor playerInteractor, CombatInteractor combatInteractor,
                               EventManager eventManager, LoginInteractor loginInteractor) {
        VIEW = story;
        SOUND = soundInteractor;
        LOAD = loadInteractor;
        SAVE = saveInteractor;
        player_action = playerInteractor;
        COMBAT = combatInteractor;
        MANAGER = eventManager;
        LOGIN = loginInteractor;
    }

    /** Set Player on their first Event. The method
     * takes in the username set by LoginInteractor.
     */
    public void startGame() throws IOException {
        //create a file to save this user
        saveGame();

        //Setting up a player
        String username = LOGIN.getCurrentUser();
        HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>(); //empty Hash Map
        player_action.updatePlayer((PlayerFactory.generatePlayer(username,
                0, 100, inventory)));

        // stop sounds before the first event begins
        SOUND.stopSound();
        SOUND.switchSoundChoice();

        //play the first event
        playEvent();
    }

    /** update the Player's current event based on the choice Player makes that correspond
     * to the UUID from ChoicesNextUUIDs in Event
    * @param choice the integer choice the Player makes
    */
    public void updateEventID(int choice) {
        Map<Integer, Event> event_map = MANAGER.getAllEvents();
        Event event = event_map.get(player_action.getPlayerEventID());

        player_action.updateEvent(event.getChoicesNextUUIDs().get(choice));
    }

    /** Loads the current event of the Player
     */
    public void loadGame() throws IOException, ClassNotFoundException {
        String username = LOGIN.getCurrentUser() + ".ser";

        //load method
        PlayerData player = LOAD.readFromFile("data/savefiles/" + username); //since it returns a PlayerData
        player_action = new PlayerInteractor(player);

        //start the game on the current event
        this.playEvent();
    }

    /** Saves PLayer data to the file
     */
    public void saveGame() throws IOException {
        String filename = "data/savefiles/" + LOGIN.getCurrentUser() + ".ser";

        SAVE.saveToFile(filename, player_action.getReference());
        System.out.println("saved!");
    }

    /** Returns the Player back to the homescreen
     */
    public void endGame() {
        VIEW.returnHomeScreen();
    }

    /** If the Player loses the game, bring the Player back to the last save
     * The player
     */
    public void lose() {
        VIEW.displayLose();
        VIEW.display_exit_options();
    }

    /** Exit the game
     * @param player The Player
     */
    public void exitGame(PlayerData player) {
        VIEW.display_exit_options();
    }

    /**Turn sound on or off
     */
    public void soundSwitch() {
        SOUND.switchSoundChoice();
        if (SOUND.getSoundChoice()) {
            SOUND.playSound();
        }
        else {
            SOUND.stopSound();
        }
    }

    /**Play Homescreen sound*/
    public void homeSoundPlay() {
        String HOME_SOUND_FILE = "data/sound/morning-funny-beat.wav";
        SOUND.createSound(HOME_SOUND_FILE);
        SOUND.playSound();
    }

    /**Stop Homescreen sound*/
    public void homeSoundStop() {
        SOUND.stopSound();
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     */
    public void playEvent() throws IOException {
        Map<Integer, Event> event_map = MANAGER.getAllEvents();
        Event event = event_map.get(player_action.getPlayerEventID());

        //finish the game. final event id is 1000
        if (player_action.getPlayerEventID() == 1000) {
            this.saveGame();
            this.endGame();
        }

        //load the sound file
        SOUND.closeSound();
        SOUND.createSound(event.getSoundFile());

        // playing or not playing sound
        if (SOUND.getSoundChoice()) {
            SOUND.stopSound();
            SOUND.playSound();
        }

        if (event.getDoesAutoSave()) {
            this.saveGame();
        }

        if (event instanceof CombatEvent) {
            VIEW.displayNarration(event.getNarration());

            if (COMBAT.combat(event.getUUID())) {
                player_action.updateEvent(event.getChoicesNextUUIDs().get(1));
            }
            else {
                //Player loses
                this.lose();
            }
        }
        else {
            System.out.println(event.getNarration());
            VIEW.displayNarration(event.getNarration());
            VIEW.askQuestion("", event.getChoicesNarrations(), null, false);
        }
    }
}
