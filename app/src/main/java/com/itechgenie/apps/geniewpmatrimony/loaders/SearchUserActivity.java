package com.itechgenie.apps.geniewpmatrimony.loaders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.itechgenie.apps.geniewpmatrimony.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchUserActivity extends AppCompatActivity implements View.OnClickListener {

    final static String LOGGER_TAG = "SearchUserActivity";

    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    Button b;
    int ageStart = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        b = (Button) findViewById(R.id.numberPickerBtn);
        b.setText("" + ageStart);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showYearDialog();
            }
        });

    }

    public void searchUserProfiles(View view) {

        final EditText nameField = (EditText) findViewById(R.id.gwpmUserId);
        String userId = nameField.getText().toString();

    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.gwpmUserFromDOB);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.gwpmUserToDOB);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        Log.d(LOGGER_TAG, "Onclick is called: " + view.getId());
        if (view == fromDateEtxt) {
            showAgeSelector(view);
        } else if (view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }

    public void showAgeSelector(final View view) {

        final Dialog d = new Dialog(SearchUserActivity.this);
        d.setTitle(R.string.age_picker_title);
        d.setContentView(R.layout.number_picker_dialog);
        Button set = (Button) d.findViewById(R.id.button1);
        Button cancel = (Button) d.findViewById(R.id.button2);
        final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPicker1);


        TextView pickerLbl = (TextView) d.findViewById(R.id.numberPickerLabelId);
        pickerLbl.setText(R.string.age_picker_title);

        nopicker.setMaxValue(ageStart + 60);
        nopicker.setMinValue(ageStart);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(ageStart);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)view).setText(String.valueOf(nopicker.getValue()));
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

    public void showYearDialog() {

        final Dialog d = new Dialog(SearchUserActivity.this);
        d.setTitle(R.string.age_picker_title);
        d.setContentView(R.layout.number_picker_dialog);
        Button set = (Button) d.findViewById(R.id.button1);
        Button cancel = (Button) d.findViewById(R.id.button2);
        final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPicker1);


        TextView pickerLbl = (TextView) d.findViewById(R.id.numberPickerLabelId);
        pickerLbl.setText(R.string.age_picker_title);

        nopicker.setMaxValue(ageStart + 60);
        nopicker.setMinValue(ageStart);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(ageStart);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setText(String.valueOf(nopicker.getValue()));
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
