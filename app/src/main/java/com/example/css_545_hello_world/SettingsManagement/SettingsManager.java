package com.example.css_545_hello_world.SettingsManagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

public class SettingsManager {

    static String username = "Fake Name!";
    static String usernameKey = "username";

    static public boolean saveSettings(Context context){
        try {
            SharedPreferences userDetails = context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor userDetailsEditor= userDetails.edit();

            // test with by setting with some phony details that can be verified in the
            // load settings method
            userDetailsEditor.putString(usernameKey, username);
            // save changes
            userDetailsEditor.apply();

            return true;
        }
        catch (Exception e) {
            System.out.println("exception during load settings");
            throw e;
        }
    }

    static public boolean loadSettings(Context context) {
        try {
            SharedPreferences userDetails = context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
            // retrieve the username, and confirm it matches the constant from this class, else throw an exception

            String savedUsername = userDetails.getString(usernameKey,"");

            if (!savedUsername.equals(username)) {
                return false;
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("exception during load settings");
            throw e;
        }
    }
}
