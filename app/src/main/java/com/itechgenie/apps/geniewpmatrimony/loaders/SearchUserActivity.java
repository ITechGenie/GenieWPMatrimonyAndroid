package com.itechgenie.apps.geniewpmatrimony;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SearchUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
    }

    public void searchUserProfiles(View view) {

        final EditText nameField = (EditText) findViewById(R.id.gwpmUserId);
        String userId = nameField.getText().toString();



    }
}
