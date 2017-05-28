package com.itechgenie.apps.geniewpmatrimony.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.R;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchResultDTO;
import com.itechgenie.apps.geniewpmatrimony.tasks.GwpmImageDownloaderTask;

/**
 * Created by Prakash-hp on 28-05-2017.
 */

public class ProfileArrayAdapter extends ArrayAdapter<GwpmSearchResultDTO> {

    private static final String LOGGER_NAME = "ProfileArrayAdapter";

    Context mContext;
    int layoutResourceId;
    GwpmSearchResultDTO data[] = null;

    public ProfileArrayAdapter(Context mContext, int layoutResourceId, GwpmSearchResultDTO[] data) {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        GwpmSearchResultDTO objectItem = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView profileNameId = (TextView) convertView.findViewById(R.id.gwpmProfileNameAndId);
        profileNameId.setText(objectItem.getDisplayName() + " (gwpm_" + objectItem.getID() + ")");
        profileNameId.setTag("name_id_" + objectItem.getID());

        TextView profileEmailId = (TextView) convertView.findViewById(R.id.gwpmProfileEmailId);
        profileEmailId.setText(objectItem.getUserEmail());
        profileEmailId.setTag("email_" + objectItem.getID());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.gwpmProfileThumbId);

        if (objectItem.getThumbImageUrl() != null) {
            new GwpmImageDownloaderTask(imageView)
                    .execute(objectItem.getThumbImageUrl());
        } else {
            imageView.setImageResource(R.drawable.gwpm_avatar);
        }
        imageView.setTag("thumb_" + objectItem.getID());

        Button button = (Button) convertView.findViewById(R.id.viewProfileBtnId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGGER_NAME, "Click handler " + v.getId() ) ;
            }
        });

        return convertView;

    }
}
