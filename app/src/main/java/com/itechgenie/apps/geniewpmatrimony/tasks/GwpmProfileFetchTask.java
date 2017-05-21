package com.itechgenie.apps.geniewpmatrimony.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmBusinessEntity;

/**
 * Created by Prakash-hp on 22-05-2017.
 */

public class GwpmProfileFetchTask extends AsyncTask<Object, Void, Object> {

    public interface callBack
    {
        public void returnText(Object value);
    };

    callBack cb;
    ProgressDialog pd;

    public GwpmProfileFetchTask(Context context) {
        cb = (callBack) context;
        pd = new ProgressDialog(context);
        pd.setMessage("Fetching data...");
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return GwpmBusinessEntity.object().getMyProfile() ;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        pd.dismiss();
        if(cb!=null)
        {
            cb.returnText(result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }


}
