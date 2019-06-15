package presenter;

import android.content.Context;

/**
 *
 * Created by BenNelson on 2/6/18.
 */

interface ILoginPresenter {

    void registerPasswordChanged();
    void confirmPasswordChanged();
    String signIn(String username, String password);
    String register(String username, String password);
    void changeHost(String serverHost);
}
