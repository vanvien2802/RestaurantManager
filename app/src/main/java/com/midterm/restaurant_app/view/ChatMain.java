package com.midterm.restaurant_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.midterm.restaurant_app.MainActivity;
import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.ChatItem;
import com.midterm.restaurant_app.viewmodel.ChatAdapter;
import com.midterm.restaurant_app.viewmodel.ChatMainAdapter;

import java.util.ArrayList;

public class ChatMain extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAllMessages;
    private Button btnRead;
    private Button btnUnread;
    private FloatingActionButton btnBack;
    private LinearLayout linearItemChat;

    ArrayList<ChatItem> chatItemList;

    private ChatMainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);

        recyclerView = findViewById(R.id.chat_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatItemList = new ArrayList<ChatItem>();
        adapter = new ChatMainAdapter(chatItemList);
        recyclerView.setAdapter(adapter);
        chatItemList.add(new ChatItem(getDrawable(R.drawable.baseline_person_24), "Nguyen Xuan Hung", "Latest Message"));
        adapter.notifyDataSetChanged();

        btnBack = findViewById(R.id.btnBackMessager);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatMain.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}