import UI.*;

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
