import java.util.Objects;
import java.util.Scanner;

interface StorylineInteractorInterface {
    public void display_event(); //display string objects
    public void display_event_choices(); //display event choices
    public void display_lose();
    public void display_lose_options();
    public void returnHomeScreen();
    public void display_exit();
}
public class StorylineInteractor {

    /** Create the Player and set them on their first Event. The method
     * takes in the username set by LoginInteractor
     */
    public void startGame(String username) {
        first_event = EventManager.getEvent(1000);
        player = PlayerData(username, first_event, 100);
        StorylineInteractor.playEvent(player);
    }

    /** If the Player loses the game, bring the Player back to the last save
     */
    public static void lose(PlayerData player) {
        ViewController.display_lose();

        //Choice options
        Scanner choice_reader = new Scanner(System.in);
        ViewController.display_lose_options();
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
        Scanner choice_reader = new Scanner(System.in);
        ViewController.display_lose_options();
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

            //idk how to implement combat features

            if (CombatInteractor.initiateCombat(event) == True) {
                player.eventID = event.getChoicesNextUUIDs()[0];
            }
            else {
                ///Player loses
                StorylineInteractor.lose(player);
            }


        }

        else {

            ViewController.display_event(event.getNarration());

            //User input system
            Scanner choice_reader = new Scanner(System.in);
            ViewController.display_event_choices(event.getChoicesNarrations());

            //idk if choice buttons are integers
            int choice = choice_reader.nextInt();

            for (int next_event : event.getChoicesNextUUIDs()) {
                if (choice == next_event) {
                    player.eventID = choice;
                    break;
                }
            }

        }

        SoundInteractor.stopSound(event.getSoundFile);
        StorylineInteractor.playEvent(player);

    }

}
