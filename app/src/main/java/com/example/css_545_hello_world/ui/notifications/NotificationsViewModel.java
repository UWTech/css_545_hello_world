package com.example.css_545_hello_world.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You've navigated to the Notifications page using the Notifications icon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}