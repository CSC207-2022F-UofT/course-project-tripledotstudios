import UI.*;
import controller.LoginController;
import presenter.CombatPresenter;
import presenter.LoginPresenter;
import presenter.StorylineInterface;
import presenter.StorylinePresenter;
import usecases.LoginInteractor;

import javax.swing.*;

public class Main {
    public static void main(String... args) {
        // set up UIFacade
        UIFacade uiFacade = new UIFacade();
        uiFacade.putControllerInUI();

        // let the first screen pop up
        uiFacade.getCreateAccountOrLogin().setVisible();
        //while
    }
}
