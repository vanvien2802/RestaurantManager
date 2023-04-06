package com.midterm.restaurant_app.model.repository;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.midterm.restaurant_app.model.Product;

public class FoodRepository extends Repository<Product>{

    private static FoodRepository instance;

    public static synchronized FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    @Override
    protected String getDatabaseReference() {
        return "foods";
    }

    @Override
    protected Class getModelClass() {
        return FoodRepository.class;
    }

    @Override
    protected void handleDatabaseError(DatabaseError databaseError) {
        String errorMessage = databaseError.getMessage();
        Log.e("DEBUG", "Database error: " + errorMessage);
    }
}
