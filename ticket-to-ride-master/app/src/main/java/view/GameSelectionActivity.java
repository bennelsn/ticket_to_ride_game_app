package view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.a340team.tickettoride.R;

import java.util.ArrayList;

import model.ClientRoot;
import presenter.GameSelectionPresenter;

public class GameSelectionActivity extends AppCompatActivity implements IGameSelection{

    private ArrayList<String> _gameNameList;
    private ArrayList<Integer> _gameIDList;
    private ArrayList<String> _gameNumPlayersList;
    private RecyclerView _recyclerView;
    private Button _createGameButton;
    private GameSelectionPresenter _gameSelectionPresenter;

    //Adapter
    private GameListRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        _setUpObserver();

        //The list of games from the server needs to get updated.
        _gameNameList = _gameSelectionPresenter.getGameNames();
        _gameIDList = _gameSelectionPresenter.getGameIDs();
        _gameNumPlayersList = _gameSelectionPresenter.getGameNumPlayers();

        _recyclerView = (RecyclerView) findViewById(R.id.GameRecycler);
        _createGameButton = (Button) findViewById(R.id.create_new_game_button);

        adapter = new GameListRecyclerAdapter(_gameNameList, _gameIDList, _gameNumPlayersList, _gameSelectionPresenter);
        _recyclerView.setAdapter(adapter);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));



        _createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateGameActivity.class);
                startActivity(intent);
            }
        });

        setTitle("Select a Game");
    }

    @Override
    public void onBackPressed(){
        //This makes it so the back button should be disabled
        ViewUtilities.displayMessage("You're signed in.\nYou can't go back.\n #sorrynotsorry", this);
    }

    private void _setUpObserver(){
        _gameSelectionPresenter = new GameSelectionPresenter(this);
        ClientRoot.addClientRootObserver(_gameSelectionPresenter);

    }

    public void updateGameList(ArrayList<String> _gameList, ArrayList<Integer> _gameIDList, ArrayList<String> _gameNumPlayersList){
        _recyclerView.invalidate();
        adapter.updateGameList(_gameList,_gameIDList,_gameNumPlayersList);
        adapter.notifyDataSetChanged();
    }

    public void goToGameLobby(){
        Intent intent = new Intent(this, GameLobbyActivity.class);
        startActivity(intent);
    }
}
