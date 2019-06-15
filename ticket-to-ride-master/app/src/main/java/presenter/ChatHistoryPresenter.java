package presenter;

import android.content.Intent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import facade.guifacade.ChatHistoryGuiFacade;
import model.ClientRoot;
import shared.model.Chat;
import shared.model.history.events.GameEvent;
import shared.model.interfaces.IGame;
import view.ChatActivity;
import view.GameLobbyActivity;
import view.ViewUtilities;

/**
 * Created by bdemann on 3/8/18.
 */

public class ChatHistoryPresenter implements Observer {

    private ChatActivity _chatActivity;

    public ChatHistoryPresenter(ChatActivity chatActivity){
        this._chatActivity = chatActivity;
        ClientRoot.addClientRootObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        ViewUtilities.checkServerStatus(_chatActivity);
        _chatActivity.updateChat(getChats());
        _chatActivity.updateEvents(getEvents());
    }

    public void sendChat(){
        if(_chatActivity.getMessage() != ""){
            ChatHistoryGuiFacade.sendChat(_chatActivity.getMessage());
            _chatActivity.clearMessage();
        }
    }

    public List<Chat> getChats() {
        return ChatHistoryGuiFacade.getChats();
    }

    public List<GameEvent> getEvents() {
        return ChatHistoryGuiFacade.getEvents();
    }
}
