package shared.facades.server;

import shared.results.LoginResult;
import shared.results.RegisterResult;

/**
 * Created by Ben on 2/6/2018.
 */

public interface ILoginServerFacade {
    public LoginResult signin(String username, String password);
    public RegisterResult register(String username, String password);
}
