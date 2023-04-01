package com.midterm.restaurant_app.viewmodel.modelView;

import com.midterm.restaurant_app.model.TableItem;
import com.midterm.restaurant_app.model.repository.Repository;
import com.midterm.restaurant_app.model.repository.TableRepository;

public class TableViewModel extends BaseViewModel<TableItem> {
    public TableViewModel(){
        super(TableRepository.getInstance());
    }
}
