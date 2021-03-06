package com.example.css_545_hello_world.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You've navigated to the Dashboards page using the app's icon");
    }

    public LiveData<String> getText() {
        return mText;
    }
}