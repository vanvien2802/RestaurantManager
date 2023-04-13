package com.midterm.restaurant_app.viewmodel.modelView;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.model.Table;

import java.util.concurrent.ExecutionException;

public class OrderViewModel extends ViewModel {

    public String getNameTableByIDTable(String tableId) {
        DatabaseReference tableRef = FirebaseDatabase.getInstance().getReference().child("Table").child(tableId);
        DataSnapshot dataSnapshot = null;
        try {
            dataSnapshot = Tasks.await(tableRef.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (dataSnapshot != null && dataSnapshot.exists()) {
            Table table = dataSnapshot.getValue(Table.class);
            if (table != null) {
                return table.getNameTable();
            }
        }
        return "null";
    }

}

