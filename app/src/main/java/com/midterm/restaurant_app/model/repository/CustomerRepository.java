package com.midterm.restaurant_app.model.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midterm.restaurant_app.model.CustomerItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static CustomerRepository instance;
    private final DatabaseReference databaseReference;
    private final List<CustomerItem> customerItems = new ArrayList<>();
    private final MutableLiveData<List<CustomerItem>> mutableLiveData = new MutableLiveData<>();

    private CustomerRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference("customer");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerItems.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    CustomerItem customerItem = snapshot.getValue(CustomerItem.class);
                    customerItems.add(customerItem);
                }
                mutableLiveData.postValue(customerItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static synchronized CustomerRepository getInstance(){
        if(instance == null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    public MutableLiveData<List<CustomerItem>> getMutableLiveData(){
        return mutableLiveData;
    }

    public void addCustomerItem(CustomerItem customerItem){
        databaseReference.push().setValue(customerItem);
    }

    public void updateCustomerItem(String key, CustomerItem customerItem){
        databaseReference.child(key).setValue(customerItem);
    }

    public void deleteCustomerItem(String key){
        databaseReference.child(key).removeValue();
    }
}
