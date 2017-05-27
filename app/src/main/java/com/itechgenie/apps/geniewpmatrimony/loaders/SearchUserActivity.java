package com.itechgenie.apps.geniewpmatrimony.loaders;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itechgenie.apps.geniewpmatrimony.R;
import com.itechgenie.apps.geniewpmatrimony.dtos.GwpmSearchProfileDTO;
import com.itechgenie.apps.geniewpmatrimony.tasks.GwpmProfileFetchTask;

import static com.itechgenie.apps.geniewpmatrimony.utilities.ITGUtility.isNotNull;

public class SearchUserActivity extends AppCompatActivity implements View.OnClickListener, GwpmProfileFetchTask.callBack {

    final static String LOGGER_TAG = "SearchUserActivity";

    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private EditText gwpmUserId;
    private EditText gwpmAgeFrom;
    private EditText gwpmAgeTo;
    private Spinner gwpmGender;


    int ageStart = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        findViewsAndLoad();

    }

    @Override
    public void returnText(Object value) {
        Log.d(LOGGER_TAG, "Obtained response: " + value);
    }

    public void clearFields(View view) {

        gwpmUserId.setText("");
        gwpmAgeFrom.setText("");
        gwpmAgeTo.setText("");

    }

    public void searchUserProfiles(View view) {

        String userId = gwpmUserId.getText().toString();
        String fromAge = gwpmAgeFrom.getText().toString();
        String toAge = gwpmAgeTo.getText().toString();
        String gender = gwpmGender.getSelectedItem().toString();

        Log.d(LOGGER_TAG, "userId: " + userId + " - fromAge: " + fromAge + " - toAge: " + toAge + " - gender: " + gender);

        GwpmSearchProfileDTO gwpmSearchProfileDTO = new GwpmSearchProfileDTO();

        if (isNotNull(userId)) {
            gwpmSearchProfileDTO.setUserId(userId);
        }
        if (isNotNull(fromAge)) {
            gwpmSearchProfileDTO.setGwpm_age_from(fromAge);
        }
        if (isNotNull(toAge)) {
            gwpmSearchProfileDTO.setGwpm_age_from(toAge);
        }
        if (isNotNull(gender)) {
            if ("Female".equalsIgnoreCase(gender)) {
                gwpmSearchProfileDTO.setGwpm_gender("2");
            } else {
                gwpmSearchProfileDTO.setGwpm_gender("1");
            }
        } else {
            Toast.makeText(SearchUserActivity.this, "Select at least one option for search !", Toast.LENGTH_SHORT).show();
            return;
        }

        new GwpmProfileFetchTask(SearchUserActivity.this).execute(gwpmSearchProfileDTO);

    }

    private void findViewsAndLoad() {
        fromDateEtxt = (EditText) findViewById(R.id.gwpmUserFromDOB);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.gwpmUserToDOB);
        toDateEtxt.setInputType(InputType.TYPE_NULL);

        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        gwpmUserId = (EditText) findViewById(R.id.gwpmUserId);
        gwpmAgeFrom = (EditText) findViewById(R.id.gwpmUserFromDOB);
        gwpmAgeTo = (EditText) findViewById(R.id.gwpmUserToDOB);

        gwpmGender = (Spinner) findViewById(R.id.gwpmUserGender);


    }


    @Override
    public void onClick(View view) {
        Log.d(LOGGER_TAG, "Onclick is called: " + view.getId());
        if (view == fromDateEtxt) {
            showAgeSelector(view);
        } else if (view == toDateEtxt) {
            showAgeSelector(view);
        }
    }

    public void showAgeSelector(final View view) {

        final Dialog d = new Dialog(SearchUserActivity.this);
        d.setTitle(R.string.age_picker_title);
        d.setContentView(R.layout.number_picker_dialog);
        Button set = (Button) d.findViewById(R.id.search_btn_ok);
        Button cancel = (Button) d.findViewById(R.id.search_btn_cancel);
        final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPickerId);


        TextView pickerLbl = (TextView) d.findViewById(R.id.numberPickerLabelId);
        pickerLbl.setText(R.string.age_picker_title);

        nopicker.setMaxValue(ageStart + 60);
        nopicker.setMinValue(ageStart);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(ageStart + 4);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) view).setText(String.valueOf(nopicker.getValue()));
                d.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

}
