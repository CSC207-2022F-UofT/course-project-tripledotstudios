import jdk.jfr.Event;

import java.io.IOException;
import java.util.*;

import dataclasses.*;
import entities.*;
import use_cases.*;
import controller.*;
import useCases.*;

public class StorylineInteractor {

    private static StorylinePresenter view;
    private static SoundInteractor sound;
    private static SaveInteractor save;
    private static LoadInteractor load;
    private static PlayerInteractor player_action;
    private static EventManager manager;
    private static CombatInteractor combat;
    private static LoginInteractor login;

    public StorylineInteractor(StorylinePresenter story, SoundInteractor soundInteractor,
                               SaveInteractor saveInteractor, LoadInteractor loadInteractor,
                               PlayerInteractor playerInteractor, EventManager eventManager,
                               CombatInteractor combatInteractor, LoginInteractor loginInteractor) {
        view = story;
        sound = soundInteractor;
        load = loadInteractor;
        save = saveInteractor;
        player_action = playerInteractor;
        manager = eventManager;
        combat = combatInteractor;
        login = loginInteractor;

    }

    /** Set Player on their first Event. The method
     * takes in the username set by LoginInteractor.
     */
    public void startGame() throws IOException, ClassNotFoundException {
        String username = login.getCurrentUser();
        HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>(); //empty Hash Map

        //dunno very first event ID
        PlayerData player = new PlayerData(username, 1, 100, inventory);

        //play the first event
        this.playEvent(player);
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

    /** Loads the final event after the Player beats the game
     */
    public void endGame() {
        //grab event map
        Map<Integer, dataclasses.Event> event_map = EventManager.getAllEvents();

        //grab last event
        dataclasses.Event event = event_map.get(1001);
        view.display_event(event.getNarration());
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
        Map<Integer, dataclasses.Event> event_map = EventManager.getAllEvents();
        dataclasses.Event event = event_map.get(player_action.getPlayerEventID());
        if (!sound.getIsPlaying()) {
            sound.playSound(event.getSoundFile());
        }
        else {
            sound.stopSound();
        }
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     * @param player the Player
     */
    public void playEvent(PlayerData player) throws IOException, ClassNotFoundException {
        Map<Integer, dataclasses.Event> event_map = EventManager.getAllEvents();
        dataclasses.Event event = event_map.get(player.getEventID());

        //finish the game. final event id is 1000
        if (player.getEventID() == 1000) {
            String filename = player.getUsername() + ".ser";
            save.saveToFile("/savefiles/" + filename, player);

            this.endGame();
        }

        if (sound.getIsPlaying()) {
            sound.playSound(event.getSoundFile());
        }

        if (event.getDoesAutoSave()) {
            String filename = player.getUsername() + ".ser";
            save.saveToFile("/savefiles/" + filename, player);
        }

        if (event instanceof CombatEvent) {

            view.display_event(event.getNarration());

            if (combat.combat(event.getUUID())) {
                player_action.updateEvent(event.getChoicesNextUUIDs().get(0));
            }
            else {
                ///Player loses
                this.lose(player);
            }
        }

        else {

            view.display_event(event.getNarration());

            StringBuilder choices = new StringBuilder();
            //User input system
            for (String choice : event.getChoicesNarrations()) {
                choices.append(", ").append(choice);
            }
            view.display_event_choices(choices);
            view.take_event_choice(player);


        }
    }
}
