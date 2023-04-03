package com.midterm.restaurant_app.model.repository;
import android.util.Log;

import com.google.firebase.database.DatabaseError;

public class FoodRepository extends Repository<FoodItem>{

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
        // Get the error message from the database error
        String errorMessage = databaseError.getMessage();

        // Display a message to the user
//        Toast.makeText(context, "Database error: " + errorMessage, Toast.LENGTH_SHORT).show();

        // Log the error for further analysis
        Log.e("DEBUG", "Database error: " + errorMessage);
    }
}
