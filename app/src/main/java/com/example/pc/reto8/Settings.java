package com.example.pc.reto8;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.jage.sqliteapp.R;

public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
