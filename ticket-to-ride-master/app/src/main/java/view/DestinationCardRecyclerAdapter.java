package view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a340team.tickettoride.R;

import java.util.ArrayList;
import java.util.List;

import model.ClientRoot;
import presenter.CreateJoinPresenter;
import presenter.GameInfoPresenter;
import presenter.GameSelectionPresenter;
import shared.model.DestCard;

/**
 * Created by mikeporet on 2/8/18.
 */

class DestinationCardRecyclerAdapter extends RecyclerView.Adapter<DestinationCardRecyclerAdapter.ViewHolder>{
    private List<DestCard> _destinationCards;
    private ArrayList<Integer> _destinationCardImages;
    private List<Boolean> _complete;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView DestinationCardImage;
        public TextView Complete;



        public ViewHolder(final View itemView) {
            super(itemView);
            DestinationCardImage = (ImageView) itemView.findViewById(R.id.recycler_dest_card);
            Complete = (TextView) itemView.findViewById(R.id.complete_dest_card);

        }

        @Override
        public void onClick(View v) {
            //This recycler view just displays information. YAY! Easy!
        }
    }

    public DestinationCardRecyclerAdapter(List<DestCard> _destCards, List<Boolean> _complete) {
        this._destinationCards = _destCards;
        this._complete = _complete;

        //Get Images for the cards
        _destinationCardImages = ViewUtilities.createDestCardImagePaths( _destinationCards);
    }


    @Override
    public DestinationCardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.dest_card_list, parent, false);
        DestinationCardRecyclerAdapter.ViewHolder viewHolder = new DestinationCardRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DestinationCardRecyclerAdapter.ViewHolder holder, int position) {
        //Setup Image displays
        ImageView cardImage = holder.DestinationCardImage;
        cardImage.setImageDrawable(cardImage.getContext().getDrawable(_destinationCardImages.get(position)));

        //Display Complete if complete
        TextView Complete = holder.Complete;
        if(_complete.get(position)){
            Complete.setAlpha(1.0f);
        }
        else{
            Complete.setAlpha(0.0f);
        }

    }

    @Override
    public int getItemCount() {
        return _destinationCards.size();
    }
}
