import java.util.Scanner;

interface StorylineInterface {
    public void display_event(String string); //display string objects
    public int display_event_choices(String string); //prompt Player to input choice and return the choice
    public void display_lose(); //display losing screen
    public void display_exit(); //display Exit screen
    public String display_exit_options(); //prompt Player to input choice from Exit options and return the choice
    public void returnHomeScreen(); //return to the HomeScreen
}
public class StorylineInteractor {

    /**Create and return a Player
     */
    public PlayerData createPlayer(String username, int eventID) {
        player = PlayerData(username, eventID, 100, 10);
        return player;
    }

    /** Set Player on their first Event. The method
     * takes in the username set by LoginInteractor.
     */
    public void startGame(PlayerData player) {
        StorylineInteractor.playEvent(player);
    }

    /** If the Player loses the game, bring the Player back to the last save
     */
    public static void lose(PlayerData player) {
        ViewController.display_lose();

        //Choice options
        Scanner choice_reader = new Scanner(System.in);
        ViewController.display_exit_options();
        String choice = choice_reader.nextLine();

        SaveInteractor.loadSave();
        if (choice.equals("Quit")) {
            ViewController.returnHomeScreen();
        }

        else {
            StorylineInteractor.playEvent(player);
        }
    }

    /** Exit the game
     */
    public static void exitGame() {
        ViewController.display_exit();

        //Choice options
        Scanner choice_reader = new Scanner(ViewController.display_exit_options());
        String choice = choice_reader.nextLine();

        SaveInteractor.loadSave();
        if (choice.equals("Quit")) {
            ViewController.returnHomeScreen();
        }

        else {
            StorylineInteractor.playEvent(player);
        }
    }

    /** Grab an event based on the inputted UUID and output its sound file and narration.
     * At the end of the narration, let the Player make a choice based on the Event's
     * choicesNarration. If Event is a CombatEvent, make the Player fight the Enemy after
     * the narration. The method should also Save the game if the Event has an Auto Save
     */
    public static void playEvent(PlayerData player) {
        event = EventManager.getEvent(player.eventID);
        SoundInteractor.playSound(event.getSoundFile);

        if (event.doesAutosave == True) {
            SaveInteractor.save();
        }

        if (event.instanceof(CombatEvent)) {

            ViewController.display_event(event.getNarration());

            if (CombatInteractor.initiateCombat(event) == True) {
                PlayerInteractor.updateEvent.(event.getChoicesNextUUIDs()[0]);
            }
            else {
                ///Player loses
                StorylineInteractor.lose(player);
            }


        }

        else {

            ViewController.display_event(event.getNarration());

            //User input system
            Scanner choice_reader = new
                    Scanner(ViewController.display_event_choices(event.getChoicesNarrations()));

            //idk if choice buttons are integers
            int choice = choice_reader.nextInt();

            for (int next_event : event.getChoicesNextUUIDs()) {
                if (choice == next_event) {
                    PlayerInteractor.updateEvent(choice);
                    break;
                }
            }

        }

        SoundInteractor.stopSound(event.getSoundFile);
        StorylineInteractor.playEvent(player);

    }

}
