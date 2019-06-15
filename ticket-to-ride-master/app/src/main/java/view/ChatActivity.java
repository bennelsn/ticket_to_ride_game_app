package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.a340team.tickettoride.R;

import java.util.List;

import presenter.ChatHistoryPresenter;
import presenter.GameInfoPresenter;
import shared.model.Chat;
import shared.model.history.events.GameEvent;

public class ChatActivity extends AppCompatActivity {

    private ImageButton _sendButton;
    private EditText message;
    private ChatHistoryPresenter _chatHistPresenter;

    private MessageRecyclerAdapter _chatAdapter;
    private MessageRecyclerAdapter _historyAdapter;
    private RecyclerView _chatRecycler;
    private RecyclerView _historyRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        _sendButton = (ImageButton) findViewById(R.id.send_button);
        message = (EditText) findViewById(R.id.chat_text);
        _initializePresenter();
        _setUpRecycler();
        _setListeners();

    }

    private void _initializePresenter(){
        _chatHistPresenter = new ChatHistoryPresenter(this);
    }

    private void _setUpRecycler(){
        _chatRecycler = (RecyclerView) findViewById(R.id.chat_recycler);
        _chatAdapter = new MessageRecyclerAdapter(_chatHistPresenter.getChats());
        _chatRecycler.setAdapter(_chatAdapter);
        _chatRecycler.setLayoutManager(new LinearLayoutManager(this));

        _historyRecycler = (RecyclerView) findViewById(R.id.history_recycler);
        _historyAdapter = new MessageRecyclerAdapter(_chatHistPresenter.getEvents());
        _historyRecycler.setAdapter(_historyAdapter);
        _historyRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void _setListeners(){
        _sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _chatHistPresenter.sendChat();
            }
        });
    }

    public void updateChat(List<Chat> messages){
        _chatRecycler.invalidate();
        _chatAdapter.updateMessage(messages);
        _chatAdapter.notifyDataSetChanged();
    }

    public void updateEvents(List<GameEvent> events){
        _historyRecycler.invalidate();
        _historyAdapter.updateMessage(events);
        _historyAdapter.notifyDataSetChanged();
    }

    public String getMessage(){
        return message.getText().toString();
    }

    public void clearMessage() {
        message.getText().clear();
    }
}
