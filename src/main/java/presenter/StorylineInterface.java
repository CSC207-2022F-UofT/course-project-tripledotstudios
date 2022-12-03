package presenter;

import entities.*;
import java.util.List;

public interface StorylineInterface {
    void displayNarration(String narration); //display string objects
    void askQuestion(String question, List<String> answers, List<String> responses);
    void displayLose(); //display losing screen
    void display_exit_options(); //display exit option screen
    void returnHomeScreen(); //return to the HomeScreen
}
