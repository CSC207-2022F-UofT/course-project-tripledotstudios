package use_cases;

import entities.*;
import java.util.List;

public interface StorylineInterface {
    void display_event(String narration); //display string objects
    void display_event_choices(List<String> narrationChoices); //display choices string narration
    void take_event_choice(PlayerData player); //asks the Player to choose from the choices presented by display_event_options
    void display_lose(); //display losing screen
    void display_exit_options(); //display exit option screen
    void returnHomeScreen(); //return to the HomeScreen
}
