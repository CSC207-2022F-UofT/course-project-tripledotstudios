package Controller;

public interface StorylineInterface {
    void display_event(String narration); //display string objects
    void display_event_choices(String narrationChoices); //display choices string narration
    void display_lose(); //display losing screen
    void display_exit_options(); //display exit option screen
    void returnHomeScreen(); //return to the HomeScreen
}
