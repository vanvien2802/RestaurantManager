package com.midterm.restaurant_app.viewmodel.modelView;

import com.midterm.restaurant_app.model.Product;
import com.midterm.restaurant_app.model.repository.FoodRepository;

public class FoodViewModel extends BaseViewModel<Product> {
    public FoodViewModel(){
        super(FoodRepository.getInstance());
    }
}
