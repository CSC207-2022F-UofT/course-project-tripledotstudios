package presenter;

import UI.EnterAGameScreen;
import UI.ExitOptionsScreen;
import UI.LoginScreen;
import UI.RegisterScreen;

import javax.swing.*;

public class LoginPresenter implements LoginInterface {
    RegisterScreen registerScreen;
    LoginScreen loginScreen;
    EnterAGameScreen enterAGameScreen;

    public LoginPresenter(RegisterScreen rs, LoginScreen ls, EnterAGameScreen egs) {
        registerScreen = rs;
        loginScreen = ls;
        enterAGameScreen = egs;
    }

    @Override
    public void updateLogin(int view, String username) { //when click sign in button, present the result
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
    public void updateRegister(int view, String username) { //when click sign up button, present the result
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
        else if (view == 4) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
        }
        else {
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }

}
