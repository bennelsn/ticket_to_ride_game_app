package tasks;

import android.os.AsyncTask;

import comm.ClientCommunicator;
import shared.command.ICommand;
import shared.results.Result;

/**
 * Created by BenNelson on 2/10/18.
 *
 */

public class CommandTask extends AsyncTask<ICommand,Void,Result>{

    @Override
    protected Result doInBackground(ICommand... commands) {
        return ClientCommunicator.sendCommand(commands[0]);
    }
}
