package com.midterm.restaurant_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.Account;
import com.midterm.restaurant_app.model.Message;
import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.viewmodel.adapter.ChatAdapter;
import com.midterm.restaurant_app.viewmodel.modelView.MessageViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chat extends AppCompatActivity {

    ArrayList<Message> messageList;
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private FloatingActionButton backButton;
    private MessageViewModel messageViewModel;
    private FloatingActionButton sendMsgButton;
    private EditText contentMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        MainActivity mainActivity = new MainActivity();

        sendMsgButton = findViewById(R.id.btn_send_messenger);
        contentMsg = findViewById(R.id.chat_message_edit_text);

        messageList = new ArrayList<Message>();
        recyclerView = findViewById(R.id.chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(messageList);

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        messageViewModel.getAll().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setData(messages);
            }
        });

        sendMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageViewModel.insert(new Message("1", mainActivity.accountSignIn.getIdAcc(),
                        contentMsg.getText().toString(), new Date().toString()));
                contentMsg.setText("");
            }
        });



        backButton = findViewById(R.id.btnBackDetailMessenger);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Chat.this, ChatMain.class);
                    if(mainActivity.accountSignIn.getIdRole() == 1){
                        intent = new Intent(Chat.this, ChatMain.class);
                    }
                    else {
                        intent = new Intent(Chat.this, MainActivity.class);
                    }
                    startActivity(intent);
                }
            });

    }


}
