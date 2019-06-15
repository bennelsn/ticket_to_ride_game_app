package view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a340team.tickettoride.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import presenter.GameOverPresenter;

public class GameOverActivity extends AppCompatActivity {

    //Fields for all of the widgets
    ArrayList<TextView> player_names = new ArrayList<>();
    ArrayList<ImageView> rank = new ArrayList<>();
    ArrayList<TextView> destination_points = new ArrayList<>();
    ArrayList<TextView> unclaimed_destinations = new ArrayList<>();
    ArrayList<TextView> route_points = new ArrayList<>();
    ArrayList<TextView> longest_route = new ArrayList<>();
    ArrayList<TextView> totals = new ArrayList<>();

    Button done_button;

    GameOverPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        intializeViews();
        presenter = new GameOverPresenter(this);

    }

    @Override
    public void onBackPressed(){
        //This makes it so the back button should be disabled
        ViewUtilities.displayMessage("Game is over! You can't go back", this);
    }


    private void intializeViews(){

        player_names.add((TextView) findViewById(R.id.go_player1));
        player_names.add((TextView) findViewById(R.id.go_player2));
        player_names.add((TextView) findViewById(R.id.go_player3));
        player_names.add((TextView) findViewById(R.id.go_player4));
        player_names.add((TextView) findViewById(R.id.go_player5));

        rank.add((ImageView) findViewById(R.id.player_1_rank));
        rank.add((ImageView) findViewById(R.id.player_2_rank));
        rank.add((ImageView) findViewById(R.id.player_3_rank));
        rank.add((ImageView) findViewById(R.id.player_4_rank));
        rank.add((ImageView) findViewById(R.id.player_5_rank));

        destination_points.add((TextView) findViewById(R.id.player_1_dest));
        destination_points.add((TextView) findViewById(R.id.player_2_dest));
        destination_points.add((TextView) findViewById(R.id.player_3_dest));
        destination_points.add((TextView) findViewById(R.id.player_4_dest));
        destination_points.add((TextView) findViewById(R.id.player_5_dest));

        unclaimed_destinations.add((TextView) findViewById(R.id.player_1_udest));
        unclaimed_destinations.add((TextView) findViewById(R.id.player_2_udest));
        unclaimed_destinations.add((TextView) findViewById(R.id.player_3_udest));
        unclaimed_destinations.add((TextView) findViewById(R.id.player_4_udest));
        unclaimed_destinations.add((TextView) findViewById(R.id.player_5_udest));

        route_points.add((TextView) findViewById(R.id.player_1_claimed));
        route_points.add((TextView) findViewById(R.id.player_2_claimed));
        route_points.add((TextView) findViewById(R.id.player_3_claimed));
        route_points.add((TextView) findViewById(R.id.player_4_claimed));
        route_points.add((TextView) findViewById(R.id.player_5_claimed));

        longest_route.add((TextView) findViewById(R.id.player_1_long));
        longest_route.add((TextView) findViewById(R.id.player_2_long));
        longest_route.add((TextView) findViewById(R.id.player_3_long));
        longest_route.add((TextView) findViewById(R.id.player_4_long));
        longest_route.add((TextView) findViewById(R.id.player_5_long));

        totals.add((TextView) findViewById(R.id.player_1_total));
        totals.add((TextView) findViewById(R.id.player_2_total));
        totals.add((TextView) findViewById(R.id.player_3_total));
        totals.add((TextView) findViewById(R.id.player_4_total));
        totals.add((TextView) findViewById(R.id.player_5_total));

        done_button = (Button) findViewById(R.id.game_over_done);

        //Set the onclick Listener for the button
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GameSelectionActivity.class);
                startActivity(i);
            }
        });
    }

    public void set_player_names(List<String> players){

        for (int i = 0; i < players.size(); i++){
            player_names.get(i).setText(players.get(i));
        }
    }

    public void set_destination_points(List<String> dest_points){

        for (int i = 0; i < dest_points.size(); i++){
            destination_points.get(i).setText(dest_points.get(i));
        }
    }

    public void set_unclaimed_points(List<String> unclaimed){

        for (int i = 0; i < unclaimed.size(); i++){
            unclaimed_destinations.get(i).setText(unclaimed.get(i));
        }
    }

    public void set_route_points(List<String> claimed){

        for (int i = 0; i < claimed.size(); i++){
            route_points.get(i).setText(claimed.get(i));
        }
    }

    public void set_longest(List<String> longest){

        for (int i = 0; i < longest.size(); i++){
            longest_route.get(i).setText(longest.get(i));
        }
    }

    public void set_totals(List<String> total_list){

        for (int i = 0; i < total_list.size(); i++){
            totals.get(i).setText(total_list.get(i));
        }
    }

    public void set_winner(int index){
        rank.get(index).setImageDrawable(getDrawable(R.drawable.winner));
    }
}
