package com.midterm.restaurant_app.model.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.model.Message;

public class MessageRepository extends Repository<Message>{
    public static MessageRepository instance;
    @Override
    protected String getDatabaseReference() {
        return "Message";
    }

    @Override
    protected Class<Message> getModelClass() {
        return Message.class;
    }

    public static synchronized MessageRepository getInstance() {
        if (instance == null) {
            instance = new MessageRepository();
        }
        return instance;
    }

    @Override
    public void insert(Message item) {
        String key = databaseReference.push().getKey();
        super.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Message msg = new Message(key, item.getIdAcc(), item.getContent(), item.getDtimeMess());
                databaseReference.child(key).setValue(msg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void handleDatabaseError(DatabaseError databaseError) {
        // Get the error message from the database error
        String errorMessage = databaseError.getMessage();

        // Display a message to the user
//        Toast.makeText(context, "Database error: " + errorMessage, Toast.LENGTH_SHORT).show();

        // Log the error for further analysis
        Log.e("DEBUG", "Database error: " + errorMessage);
    }
}
