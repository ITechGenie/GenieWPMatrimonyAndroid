package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itechgenie.apps.geniewpmatrimony.ConfigListActivity;
import com.itechgenie.apps.geniewpmatrimony.ConfigurationActivity;
import com.itechgenie.apps.geniewpmatrimony.MainActivity;
import com.itechgenie.apps.geniewpmatrimony.R;

/**
 * Created by Prakash-hp on 09-04-2017.
 */

public class ConfigsArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public ConfigsArrayAdapter(@NonNull Context context,  String[] values) {
        super(context, R.layout.config_item, values);
        this.context = context;
        this.values = values;
    }

    // @Override
    public View _getView(int position, View convertView, ViewGroup parent) {

       // LayoutInflater inflater = (LayoutInflater) context
       //         .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // View rowView = inflater.inflate(R.layout.config_item, parent, false);

        View rowView = LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.config_item, parent, false);

        // rowView.getLayoutParams ().width = parent.getWidth ();
       // rowView.setBackgroundColor(Color.GREEN);
       // rowView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        TextView textView = (TextView) rowView.findViewById(R.id.config_name_id);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo_id);
        // textView.setText(values[position]);
        textView.setText("Prakash ");

        // Change icon based on name
        String currentItem = values[position];

        Log.d("ConfigsArrayAdapter", "Iterating Name: " + currentItem) ;

//        imageView.setImageResource(R.drawable.ic_menu_manage);

        return rowView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d("ConfigsArrayAdapter", "Position value: " + position ) ;

        ViewHolderItem viewHolder ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.config_item, parent, false);

            viewHolder = new ViewHolderItem() ;
            viewHolder.textView = (TextView) convertView.findViewById(R.id.config_name_id);
            viewHolder.imageView =  (ImageView) convertView.findViewById(R.id.logo_id);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.textView .setText(values[position]);
        String currentItem = values[position];
        Log.d("ConfigsArrayAdapter", "Iterating Name: " + currentItem) ;

        return convertView;

    }

    public void onClick(View view, int position, Long id, Object item) {

        Log.d("ConfigsArrayAdapter", "Position: " + position + " - ID: " + id ) ;
        // int position=(Integer) v.getTag();
        Object object= getItem(position);

        Snackbar.make(view, "Clicked " + id + " - " + view.getWidth() + " - " + item , Snackbar.LENGTH_LONG)
                .setAction("No action", null).show();

        Intent intent= new Intent(context, ConfigurationActivity.class);
        context.startActivity(intent);

    }
}

class ViewHolderItem {
    ImageView imageView ;
    TextView textView;
}