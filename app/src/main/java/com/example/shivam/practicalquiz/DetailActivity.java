package com.example.shivam.practicalquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private String mUsername, mEmail, mPersonalInfo;

    private TextView tvUsername, tvEmail, tvPersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        tvPersonalInfo = findViewById(R.id.tv_personal_info);
        retrieveAndSetValues();
    }

    private void retrieveAndSetValues() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(DetailActivity.this);
        mUsername = sharedPreferences.getString(MainActivity.KEY_USERNAME, "");
        mEmail = sharedPreferences.getString(MainActivity.KEY_EMAIL, "");
        mPersonalInfo = sharedPreferences.getString(MainActivity.KEY_PERSONAL_INFO, "");

        tvUsername.setText(mUsername);
        tvEmail.setText(mEmail);
        tvPersonalInfo.setText(mPersonalInfo);

    }
}
