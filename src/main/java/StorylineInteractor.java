import jdk.jfr.Event;

import java.io.IOException;
import java.util.*;
import presenter.*;
import dataclasses.*;
import entities.*;
import use_cases.*;
import useCases.*;


public class StorylineInteractor {

    private static StorylineInterface view;
    private static SoundInteractor sound;
    private static SaveInteractor save;
    private static LoadInteractor load;
    private static PlayerInteractor player_action;
    private static CombatInteractor combat;
    private static EventManager manager;
    private static LoginInteractor login;
    private static int first_event;
    private static int last_event;

    public StorylineInteractor(StorylineInterface story, SoundInteractor soundInteractor,
                               SaveInteractor saveInteractor, LoadInteractor loadInteractor,
                               PlayerInteractor playerInteractor, CombatInteractor combatInteractor,
                               EventManager eventManager, LoginInteractor loginInteractor, int first_UUID,
                               int last_UUID) {
        view = story;
        sound = soundInteractor;
        load = loadInteractor;
        save = saveInteractor;
        player_action = playerInteractor;
        combat = combatInteractor;
        manager = eventManager;
        login = loginInteractor;
        first_event = first_UUID;
        last_event = last_UUID;


    }

    /** Set Player on their first Event. The method
     * takes in the username set by LoginInteractor.
     */
    public void startGame() throws IOException, ClassNotFoundException {
        String username = login.getCurrentUser();
        HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>(); //empty Hash Map

        //dunno very first event ID
        PlayerData player = new PlayerData(username, first_event, 100, inventory);

        //play the first event
        this.playEvent(player);
    }

    /** update the Player's current event based on the choice Player makes that correspond
     * to the UUID from ChoicesNextUUIDs in Event
    * @param event current event being played
    * @param choice the integer choice the PLayer makes
    */
    public void updateEventID(PlayerData player, int choice) {
        Map<Integer, dataclasses.Event> event_map = manager.getAllEvents();
        dataclasses.Event event = event_map.get(player_action.getPlayerEventID());

        player_action.updateEvent(event.getChoicesNextUUIDs().get(choice));
    }

    /** Loads the current event of the Player
     */
    public void loadGame() throws IOException, ClassNotFoundException {
        String username = login.getCurrentUser();
        String filename = username + ".ser";

        //load method
        PlayerData player = load.readFromFile("/savefiles/" + filename); //since it returns a PlayerData
        //start the game on the current event
        this.playEvent(player);
    }

    /** Saves PLayer data to the file
     * @param player The Player
     */
    public void saveGame(PlayerData player) throws IOException {
        String filename = "/savefiles/" + player.getUsername() + ".ser";
        save.saveToFile(filename, player);
    }

    /** Returns the Player back to the homescreen
     */
    public void endGame() {
        view.returnHomeScreen();
    }

    /** If the Player loses the game, bring the Player back to the last save
     * @param player The player
     */
    public void lose(PlayerData player) throws IOException, ClassNotFoundException {
        view.display_lose();
        view.display_exit_options();
    }

    /** Exit the game
     * @param player The Player
     */
    public void exitGame(PlayerData player) throws IOException, ClassNotFoundException {
        view.display_exit_options();
    }

    /**Turn sound on or off
     */
    public void soundSwitch() {
        Map<Integer, dataclasses.Event> event_map = manager.getAllEvents();
        dataclasses.Event event = event_map.get(player_action.getPlayerEventID());
        if (!sound.getIsPlaying()) {
            sound.playSound(event.getSoundFile());
            sound.switchSoundChoice();
        }
        else {
            sound.stopSound();
            sound.switchSoundChoice();
        }
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     * @param player the Player
     */
    public void playEvent(PlayerData player) throws IOException, ClassNotFoundException {
        Map<Integer, dataclasses.Event> event_map = manager.getAllEvents();
        dataclasses.Event event = event_map.get(player.getEventID());

        //finish the game. final event id is 1000
        if (player.getEventID() == last_event) {
            this.saveGame(player);

            this.endGame();
        }

        if (sound.getSoundChoice()) {
            sound.playSound(event.getSoundFile());
        }

        if (event.getDoesAutoSave()) {
            this.saveGame(player);
        }

        if (event instanceof CombatEvent) {

            view.display_event(event.getNarration());

            if (combat.combat(event.getUUID())) {
                player_action.updateEvent(event.getChoicesNextUUIDs().get(1));
            }
            else {
                ///Player loses
                this.lose(player);
            }
        }
        else {

            view.display_event(event.getNarration());
            view.display_event_choices(event.getChoicesNarrations());
            view.take_event_choice(player);


        }
    }
}
