import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import dataclasses.*;
import entities.*;
import use_cases.*;

public class StorylineInteractor {

    private static StorylineInterface View;
    private static SoundInteractor Sound;
    private static SaveInteractor Save;
    private static LoadInteractor Load;
    private static PlayerInteractor PlayerAction;
    private static EventManager Manager;
    private static CombatInteractor Combat;
    private static LoginInteractor Login;

    public StorylineInteractor(StorylineInterface story, SoundInteractor soundInteractor,
                               SaveInteractor saveInteractor, LoadInteractor loadInteractor,
                               PlayerInteractor playerInteractor, EventManager eventManager,
                               CombatInteractor combatInteractor, LoginInteractor loginInteractor) {
        View = story;
        Sound = soundInteractor;
        Load = loadInteractor;
        Save = saveInteractor;
        PlayerAction = playerInteractor;
        Manager = eventManager;
        Combat = combatInteractor;
        Login = loginInteractor;

    }

    /** Set Player on their first Event. The method
     * takes in the username set by LoginInteractor.
     */
    public void startGame() {
        String username = Login.getCurrentUser();
        HashMap<String, ArrayList<ItemData>> inventory = new HashMap<>(); //empty Hash Map

        //dunno very first event ID
        PlayerData player = new PlayerData(username, 1, 100, inventory);
        this.playEvent(player);
    }

    /** Loads the current event of the Player
     */
    public void loadGame() {
        String username = Login.getCurrentUser();
        String filename = username + ".ser";
        PlayerData player = Load.readFromFile("/savefiles/" + filename); //since it returns a PlayerData
        this.playEvent(player);
    }

    /** Loads the final event after the Player beats the game
     */
    public static void endGame() {
        //ending text id is 1001
        View.display_event(1001);
        View.returnHomeScreen();
    }

    /** If the Player loses the game, bring the Player back to the last save
     */
    public void lose(PlayerData player) {
        View.display_lose();

        //Choice options
        Scanner choice_reader = new Scanner(System.in);
        View.display_exit_options();
        String choice = choice_reader.nextLine();

        String username = Login.getCurrentUser();
        String filename = username + ".ser";
        Load.readFromFile("/savefiles/" + filename);
        if (choice.equals("Quit")) {
            View.returnHomeScreen();
        }

        else {
            this.playEvent(player);
        }
    }

    /** Exit the game
     */
    public void exitGame(PlayerData player) {
        View.display_exit();

        //Choice options
        Scanner choice_reader = new Scanner(View.display_exit_options());
        String choice = choice_reader.nextLine();

        String username = Login.getCurrentUser();
        String filename = username + ".ser";
        Load.readFromFile("/savefiles/" + filename);

        if (choice.equals("Quit")) {
            View.returnHomeScreen();
        }

        else {
            this.playEvent(player);
        }
    }

    /**Turn sound on or off
     */
    public static void soundSwitch(Event event, boolean sound_status) {
        if (!Sound.getIsPlaying()) {
            Sound.playSound(event.getSoundFile());
        }
        else {
            Sound.stopSound();
            sound_status = false;
        }
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     */
    public void playEvent(PlayerData player) {
        event = Manager.getEvent(player.getEventID());

        boolean sound_status = true;
        //the sound plays when the event starts, player can call soundSwitch to turn it off
        StorylineInteractor.soundSwitch(event, sound_status);

        if (event.doesAutosave()) {
            String filename = player.getUsername() + ".ser";
            Save.saveToFile("/savefiles/" + filename, player);
        }

        if (event instanceof CombatEvent) {

            View.display_event(event.getNarration());

            if (Combat.initiateCombat(event)) {
                PlayerAction.updateEvent(event.getChoicesNextUUIDs()[0]);
            }
            else {
                ///Player loses
                this.lose(player);
            }
        }

        else {

            View.display_event(event.getNarration());

            //User input system
            Scanner choice_reader = new
                    Scanner(View.display_event_choices(event.getChoicesNarrations()));

            int choice = choice_reader.nextInt();

            for (int next_event : event.getChoicesNextUUIDs()) {
                if (choice == next_event) {
                    PlayerAction.updateEvent(choice);
                    break;
                }
            }

        }

        Sound.stopSound();

        //finish the game. final event id is 1000
        if (player.getEventID() == 1000) {
            String filename = player.getUsername() + ".ser";
            Save.saveToFile("/savefiles/" + filename, player);

            StorylineInteractor.endGame();
        }

        else {
            this.playEvent(player);
        }
    }
}
