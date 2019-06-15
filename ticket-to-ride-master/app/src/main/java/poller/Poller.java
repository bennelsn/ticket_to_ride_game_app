package poller;

import android.os.Handler;

import java.util.List;

import model.ClientRoot;
import proxies.ServerProxy;
import shared.command.ICommand;
import shared.logging.Level;
import shared.results.Result;
import shared.logging.Logger;

/**
 *
 * Created by bdemann on 2/14/18.
 */

public class Poller {
    private Poller _instance = new Poller();
    private Poller(){

    }

    public static void start(){
        final Handler handler = new Handler();
        final int delay = 2000; //milliseconds


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Result result = new ServerProxy().getCommands(ClientRoot.getClientPlayer().getUsername());
                Logger.log("We are polling", Level.SANITY_CHECK);
                if(result != null) {
                    ClientRoot.setServerDown(false);
                    List<ICommand> commandList = result.getClientCommands();
                    for (ICommand command : commandList) {
                        try {
                            command.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                else {
                    ClientRoot.setServerDown(true);
                }

                handler.postDelayed(this, delay);
            }
        }, delay);

    }
}
