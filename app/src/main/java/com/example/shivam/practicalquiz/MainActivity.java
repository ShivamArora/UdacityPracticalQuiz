package com.example.shivam.practicalquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PERSONAL_INFO = "personal_info";

    private EditText etUsername,etEmail,etPersonalInfo;
    private Button btnNext;

    private String mUsername,mEmail,mPersonalInfo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPersonalInfo = findViewById(R.id.et_personal_info);
        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(KEY_USERNAME)){
                mUsername = savedInstanceState.getString(KEY_USERNAME);
                mEmail = savedInstanceState.getString(KEY_EMAIL);
                mPersonalInfo = savedInstanceState.getString(KEY_PERSONAL_INFO);
                etUsername.setText(mUsername);
                etEmail.setText(savedInstanceState.getString(mEmail));
                etPersonalInfo.setText(savedInstanceState.getString(mPersonalInfo));
            }
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.btn_next:
                saveDataToPreferencesAndOpenDetails();
                break;
        }
    }

    private void saveDataToPreferencesAndOpenDetails() {
        mUsername = etUsername.getText().toString();
        mEmail = etEmail.getText().toString();
        mPersonalInfo = etPersonalInfo.getText().toString();

        saveDataToSharedPreferences(mUsername,mEmail,mPersonalInfo);

        openDetailsActivity();

        clearFields();
    }

    private void clearFields() {
        etUsername.setText("");
        etEmail.setText("");
        etPersonalInfo.setText("");
    }

    private void openDetailsActivity() {
        Intent detailActivity = new Intent(MainActivity.this,DetailActivity.class);
        startActivity(detailActivity);
    }

    private void saveDataToSharedPreferences(String username, String email, String personalInfo) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PERSONAL_INFO,personalInfo);

        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_USERNAME,etUsername.getText().toString());
        outState.putString(KEY_EMAIL,etEmail.getText().toString());
        outState.putString(KEY_PERSONAL_INFO,etPersonalInfo.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_detail_activity:
                openDetailsActivity();
        }
        return super.onOptionsItemSelected(item);
    }
}
