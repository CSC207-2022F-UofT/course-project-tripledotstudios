import jdk.jfr.Event;

import java.util.Scanner;

public class StorylineInteractor {

    private static final ViewController View;
    private static final SoundInteractor Sound;
    private static final SaveInteractor Save;
    private static final LoadInteractor Load;
    private static final PlayerInteractor PlayerAction;
    private static final EventManager Manager;
    private static final CombatInteractor Combat;
    private static final LoginInteractor Login;

    public StorylineInteractor(ViewController viewController, SoundInteractor soundInteractor,
                               SaveInteractor saveInteractor, LoadInteractor loadInteractor,
                               PlayerInteractor playerInteractor, EventManager eventManager,
                               CombatInteractor combatInteractor, LoginInteractor loginInteractor) {
        View = viewController;
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

        //dunno very first event ID
        player = PlayerData(username, 1, 100, 10);
        StorylineInteractor.playEvent(player);
    }

    /** Loads the current event of the Player
     */
    public void loadGame() {
        String username = Login.getCurrentUser();
        String filename = username + ".ser";
        Load.readFromFile("/savefiles/" + filename);
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
    public static void lose(PlayerData player) {
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
            StorylineInteractor.playEvent(player);
        }
    }

    /** Exit the game
     */
    public static void exitGame() {
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
            StorylineInteractor.playEvent(player);
        }
    }

    /**Gets narration from Event
     */
    public static String getEventNarration(PlayerData player) {
        event = Manager.getEvent(player.getEventID);
        return event.getNarration();
    }

    /**Gets event choices from Event
     */
    public static String getEventChoices(PlayerData player) {
        event = Manager.getEvent(player.getEventID);
        return event.getChociesNextUUIDs();
    }

    public static void SoundOff() {
        if (Sound.getIsPlaying == true) {
            Sound.stopSound();
        }
    }

    public static void SoundOn(PlayerData player) {
        event = Manager.getEvent(player.eventID);
        if (Sound.getIsPlaying != true) {
            Sound.playSound(event.getSoundFile);
        }
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     */
    public static void playEvent(PlayerData player) {
        event = Manager.getEvent(player.eventID);

        Sound.playSound(event.getSoundFile);

        if (event.doesAutosave == True) {
            String filename = player.username + ".ser";
            Save.saveToFile("/savefiles/" + filename, player);
        }

        if (event instanceof CombatEvent) {

            View.display_event(event.getNarration());

            if (Combat.initiateCombat(event) == True) {
                PlayerAction.updateEvent(event.getChoicesNextUUIDs()[0]);
            }
            else {
                ///Player loses
                StorylineInteractor.lose(player);
            }
        }

        else {

            View.display_event(event.getNarration());

            //User input system
            Scanner choice_reader = new
                    Scanner(View.display_event_choices(event.getChoicesNarrations()));

            String choice = choice_reader.nextLine();

            for (String next_event : event.getChoicesNextUUIDs()) {
                if (choice.equals(next_event)) {
                    PlayerAction.updateEvent(choice);
                    break;
                }
            }

        }

        Sound.stopSound();

        //finish the game. final event id is 1000
        if (player.eventID == 1000) {
            String filename = player.getUsername() + ".ser";
            Save.saveToFile("/savefiles/" + filename, player);

            StorylineInteractor.endGame();
        }

        else {
            StorylineInteractor.playEvent(player);
        }
    }
}
