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

//        messageEditText = findViewById(R.id.chat_message_edit_text);
//        sendButton = findViewById(R.id.btn_send_messenger);
//        LinearLayout chatHeader = findViewById(R.id.chat_header);
//
//        ImageView chatAvatar = findViewById(R.id.user_avatar);
//        chatAvatar.setImageResource(R.drawable.baseline_person_24); // set ảnh cho ImageView
//
//        TextView chatUsername = findViewById(R.id.chat_username);
//        chatUsername.setText("John Doe"); // set tên cho TextView
//
//        ViewGroup chatLayout = findViewById(R.id.chat_layout);
//        chatLayout.addView(chatHeader, 0); // Thêm chatHeader vào trước RecyclerView
//
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = messageEditText.getText().toString();
//                if (!TextUtils.isEmpty(message)) {
//                    adapter.addMessage(message);
//                    messageEditText.setText("");
//                }
//            }
//        });
//
//        // Inflate message item layout
//        LayoutInflater inflater = getLayoutInflater();
//        View messageItem = inflater.inflate(R.layout.item_chat_message, chatLayout, false);
//
//        // Add message item to chat layout
//        chatLayout.addView(messageItem);
        }
}
