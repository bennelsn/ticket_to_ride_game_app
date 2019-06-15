package view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a340team.tickettoride.R;

import java.util.ArrayList;

import model.ClientRoot;
import presenter.CreateJoinPresenter;
import presenter.GameSelectionPresenter;

/**
 * Created by mikeporet on 2/8/18.
 */

class GameListRecyclerAdapter extends RecyclerView.Adapter<GameListRecyclerAdapter.ViewHolder>{
    private ArrayList<String> _gameList;
    private ArrayList<Integer> _gameIDList;
    private ArrayList<String> _gameNumPlayersList;

    private GameSelectionPresenter presenter;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView GameName;
        public TextView NumberPlayers;
        public TextView GameID;



        public ViewHolder(final View itemView) {
            super(itemView);
            GameName = (TextView) itemView.findViewById(R.id.game_name);
            GameID = (TextView) itemView.findViewById(R.id.game_id_display);
            NumberPlayers = (TextView) itemView.findViewById(R.id.number_players);
            GameName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Send request to join game
            int ChosenGameID =  getAdapterPosition();
            String ChosenGameName = GameName.getText().toString();
//            CreateJoinPresenter gameSelectionPresenter = new CreateJoinPresenter(ClientRoot.instance());

            if (presenter.joinGame(ChosenGameID)) {
                Toast toast = Toast.makeText(v.getContext(), "Join Game Success", Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(v.getContext(), "Join Game Failed", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    public GameListRecyclerAdapter(ArrayList<String> _gameList, ArrayList<Integer> _gameIDList, ArrayList<String> _gameNumPlayersList,
                                   GameSelectionPresenter presenter) {
        this._gameList = _gameList;
        this._gameIDList = _gameIDList;
        this._gameNumPlayersList = _gameNumPlayersList;
        this.presenter = presenter;
    }


    @Override
    public GameListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.game_list_item, parent, false);
        GameListRecyclerAdapter.ViewHolder viewHolder = new GameListRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListRecyclerAdapter.ViewHolder holder, int position) {
        //Setup text displays
        TextView name = holder.GameName;
        name.setText(_gameList.get(position));

        TextView ID = holder.GameID;
        ID.setText(Integer.toString(_gameIDList.get(position)));

        TextView NumPlayers = holder.NumberPlayers;
        NumPlayers.setText(_gameNumPlayersList.get(position));

    }

    @Override
    public int getItemCount() {
        return _gameList.size();
    }

    public void updateGameList(ArrayList<String> _gameList, ArrayList<Integer> _gameIDList, ArrayList<String> _gameNumPlayersList) {
        this._gameList = _gameList;
        this._gameIDList = _gameIDList;
        this._gameNumPlayersList = _gameNumPlayersList;
//        notifyDataSetChanged();
    }
}
