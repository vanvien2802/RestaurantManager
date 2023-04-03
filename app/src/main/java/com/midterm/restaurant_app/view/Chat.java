package com.midterm.restaurant_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.viewmodel.ChatAdapter;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    ArrayList<String> messageList;
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText messageEditText;
    private FloatingActionButton sendButton;
    private FloatingActionButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageList = new ArrayList<>();
        recyclerView = findViewById(R.id.chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(adapter);

        messageList.add("Xuan Hung");
        messageList.add("Ngu ngoc");
        adapter.notifyDataSetChanged();

        backButton = findViewById(R.id.btnBackDetailMessenger);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat.this, ChatMain.class);
                startActivity(intent);
            }
        });
        }
}
