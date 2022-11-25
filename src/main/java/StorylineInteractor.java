import jdk.jfr.Event;

import java.io.IOException;
import java.util.*;

import dataclasses.*;
import entities.*;
import use_cases.*;
import controller.*;
import useCases.*;

public class StorylineInteractor {

    private static StorylineInterface view;
    private static SoundInteractor sound;
    private static SaveInteractor save;
    private static LoadInteractor load;
    private static PlayerInteractor player_action;
    private static EventManager manager;
    private static CombatInteractor combat;
    private static LoginInteractor login;

    public StorylineInteractor(StorylineInterface story, SoundInteractor soundInteractor,
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
    public static void endGame() {
        //grab event map
        Map<Integer, dataclasses.Event> event_map = EventManager.getAllEvents();

        //grab last event
        dataclasses.Event event = event_map.get(1001);
        view.display_event(event.getNarration());
        view.returnHomeScreen();
    }

    /** If the Player loses the game, bring the Player back to the last save
     * @param player: The player
     */
    public void lose(PlayerData player) throws IOException, ClassNotFoundException {
        view.display_lose();

        //Choice options
        Scanner choice_reader = new Scanner(System.in);
        view.display_exit_options();
        String choice = choice_reader.nextLine();

        String username = login.getCurrentUser();
        String filename = username + ".ser";
        load.readFromFile("/savefiles/" + filename);
        if (choice.equals("Quit")) {
            view.returnHomeScreen();
        }

        else {
            this.playEvent(player);
        }
    }

    /** Exit the game
     * @param player The Player
     */
    public void exitGame(PlayerData player) throws IOException, ClassNotFoundException {
        view.display_exit_options();

        //Choice options
        Scanner choice_reader = new Scanner(view.display_exit_options());//why?? what??
        String choice = choice_reader.nextLine();

        String username = login.getCurrentUser();
        String filename = username + ".ser";
        load.readFromFile("/savefiles/" + filename);

        if (choice.equals("Quit")) {
            view.returnHomeScreen();
        }

        else {
            this.playEvent(player);
        }
    }

    /**Turn sound on or off
     * @param event Event
     */
    public static void soundSwitch(dataclasses.Event event, boolean sound_status) {
        if (!sound.getIsPlaying()) {
            sound.playSound(event.getSoundFile());
        }
        else {
            sound.stopSound();
            sound_status = false;
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

        boolean sound_status = true;
        //the sound plays when the event starts, player can call soundSwitch to turn it off
        StorylineInteractor.soundSwitch(event, sound_status);

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

            //User input system
            Scanner choice_reader = new
                    Scanner(view.display_event_choices(event.getChoicesNarrations()));

            String choice = choice_reader.nextLine();

            for (int i = 0; i < event.getChoicesNarrations().size(); i++) {
                if (choice.equals(event.getChoicesNarrations().get(i))) {
                    player_action.updateEvent(event.getChoicesNextUUIDs().get(i));
                    break;
                }
            }
        }

        sound.stopSound();

        //finish the game. final event id is 1000
        if (player.getEventID() == 1000) {
            String filename = player.getUsername() + ".ser";
            save.saveToFile("/savefiles/" + filename, player);

            StorylineInteractor.endGame();
        }

        else {
            this.playEvent(player);
        }
    }
}
