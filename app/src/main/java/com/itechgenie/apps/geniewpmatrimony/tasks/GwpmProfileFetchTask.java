package com.itechgenie.apps.geniewpmatrimony.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.utilities.GwpmBusinessEntity;
import com.itechgenie.apps.geniewpmatrimony.utilities.ITWException;

/**
 * Created by Prakash-hp on 22-05-2017.
 */

public class GwpmProfileFetchTask extends AsyncTask<Object, Void, Object> {

    final static String LOGGER_TAG = "GwpmProfileFetchTask";

    public interface callBack {
        public void returnText(Object value);
    }

    callBack cb;
    ProgressDialog pd;

    public GwpmProfileFetchTask(Context context) {
        cb = (callBack) context;
        pd = new ProgressDialog(context);
        pd.setMessage("Fetching data...");
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Log.d(LOGGER_TAG, "Inside doInBackground: " + params);
        try {
            if (params != null && params.length > 0) {
                for (Object f : params) {
                    Log.d(LOGGER_TAG, "Param: " + f);
                }
                if (params[0] instanceof GwpmSearchProfileDTO) {
                    return GwpmBusinessEntity.object().searchProfile((GwpmSearchProfileDTO) params[0]);
                } else {
                    return GwpmBusinessEntity.object().getProfile((String) params[0]) ;
                }
            } else {
                Log.d(LOGGER_TAG, "No Param obtained, fetching my profile !");
                return GwpmBusinessEntity.object().getMyProfile();
            }
        } catch (ITWException itwExec) {
             Log.e(LOGGER_TAG, "Exception occurred: " + itwExec.getMessage()) ;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (cb != null) {
            cb.returnText(result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }


}
