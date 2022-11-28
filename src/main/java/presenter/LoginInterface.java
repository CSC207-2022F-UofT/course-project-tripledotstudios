package presenter;

public interface LoginInterface {
    public void updateLogin(int view, String username); //when click sign in button, present the result
    public void updateRegister(int view, String username); //when click sign up button, present the result
}
