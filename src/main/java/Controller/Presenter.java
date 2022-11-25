package Controller;

import UI.EnterAGameScreen;
import UI.ExitOptionsScreen;
import UI.LoginScreen;
import UI.RegisterScreen;

import javax.swing.*;

public class Presenter implements StorylineInterface, LoginInterface {
    RegisterScreen registerScreen;
    LoginScreen loginScreen;
    EnterAGameScreen enterAGameScreen;
    ExitOptionsScreen exitOptionsScreen;

    public Presenter(RegisterScreen rs, LoginScreen ls, EnterAGameScreen egs, ExitOptionsScreen eos) {
        registerScreen = rs;
        loginScreen = ls;
        enterAGameScreen = egs;
        exitOptionsScreen = eos;
    }

    @Override
    public void updateLogin(int view, String username) {
        if (view == 1) {
            JOptionPane.showMessageDialog(null, String.format("%s logged in.", username));
            loginScreen.getFrame().dispose(); //close loginScreen
            enterAGameScreen.setVisible(); //jump to the EnterAGameScreen
        }
        else if (view == 2) {
            JOptionPane.showMessageDialog(null, "Username does not exist");
        }
        else {
            JOptionPane.showMessageDialog(null, "Wrong password");
        }
    }

    @Override
    public void updateRegister(int view, String username) {
        if (view == 1) {
            JOptionPane.showMessageDialog(null, String.format("%s created.", username));
            registerScreen.getFrame().dispose(); //close loginScreen
            loginScreen.setVisible(); //return to loginScreen
        }
        else if (view == 2) {
            JOptionPane.showMessageDialog(null, "Username already exists");
        }
        else if (view == 3) {
            JOptionPane.showMessageDialog(null, "Passwords too short");
        }
        else {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
        }
    }

    @Override
    public void display_event(String narration) {  //display string objects
        System.out.println(narration);
    }

    @Override
    public void display_event_choices(String narrationChoices) { //display choices string narration
        System.out.println(narrationChoices);
    }

    @Override
    public void display_lose() { //display losing screen
        JOptionPane.showMessageDialog(null, "Ahh...You lose.Try it again!");
    }

    @Override
    public void display_exit_options() { //display exit option screen
        exitOptionsScreen.setVisible();
    }

    @Override
    public void returnHomeScreen() { //return to the HomeScreen
        enterAGameScreen.setVisible();
    }

}
