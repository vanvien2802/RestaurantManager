package com.midterm.restaurant_app.viewmodel.modelView;

import com.midterm.restaurant_app.model.Message;
import com.midterm.restaurant_app.model.repository.MessageRepository;

public class MessageViewModel extends BaseViewModel<Message>{
    public MessageViewModel(){
        super(MessageRepository.getInstance());
    }
}
