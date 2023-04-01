package com.midterm.restaurant_app.viewmodel.modelView;

import com.midterm.restaurant_app.model.FoodItem;
import com.midterm.restaurant_app.model.repository.FoodRepository;

public class FoodViewModel extends BaseViewModel<FoodItem> {
    public FoodViewModel(){
        super(FoodRepository.getInstance());
    }
}
