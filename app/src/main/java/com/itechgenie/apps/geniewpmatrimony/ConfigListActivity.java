package com.itechgenie.apps.geniewpmatrimony;

import android.app.ListActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itechgenie.apps.geniewpmatrimony.utilities.ConfigsArrayAdapter;

public class ConfigListActivity extends AppCompatActivity {

    ListView listView;
    private static ConfigsArrayAdapter adapter;
    static final String[] ITEMS_LIST =
            new String[]{"Android", "iOS", "WindowsMobile", "Blackberry", "Android", "iOS",
                    "WindowsMobile", "Blackberry", "Android", "iOS", "WindowsMobile",
                    "Blackberry", "Android", "iOS", "WindowsMobile", "Blackberry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_list);

        listView = (ListView) findViewById(R.id.configList);
        adapter = new ConfigsArrayAdapter(getApplicationContext(), ITEMS_LIST);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Snackbar.make(view, "Clicked " + id + " - " + view.getWidth() + " - " + ITEMS_LIST[position] , Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();

                ((ConfigsArrayAdapter) parent.getAdapter()).onClick(view, position, id, ITEMS_LIST[position]);

            }
        });

    }


}
