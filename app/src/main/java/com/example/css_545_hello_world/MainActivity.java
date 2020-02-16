package com.example.css_545_hello_world;

import com.example.css_545_hello_world.SettingsManagement.SettingsManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private CharSequence savedSettingsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void saveSettings(View view) {
        Context context = App.context;
        SettingsManager.saveSettings(context);
    }

    public void loadSettings(View view) {
        Context context = App.context;
        TextView textView = findViewById(R.id.user_settings_text);
        String settings = SettingsManager.loadSettings(context);
        textView.setText(settings);
    }

    @Override
    protected void onPause () {
        super.onPause();
        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
        // save current state of the text view on the settings page
        TextView settingsTextView = findViewById(R.id.user_settings_text);
        if (settingsTextView != null) {
            this.savedSettingsText = settingsTextView.getText();
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        Toast.makeText(this, "resumed", Toast.LENGTH_SHORT).show();
        // load the previously saved text from the settings page if it was saved
        TextView settingsTextView = findViewById(R.id.user_settings_text);
        if (this.savedSettingsText != null)
            settingsTextView.setText(this.savedSettingsText);

    }

    @Override
    protected void onStop () {
        super.onStop();
        Toast.makeText(this, "stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();
    }
}
