package view;

/**
 *
 * Created by BenNelson on 2/6/18.
 */

interface ILoginView {

    void enableSignIn(boolean enabled);
    void enableRegister(boolean enabled);
    String getUsernameSignIn();
    String getPasswordSignIn();

    void setUsernameSignIn(String username);

    void setPasswordSignIn(String username);

    String getUsernameRegister();
    String getPasswordRegister();
    void displayMessage(String message);


}
