package com.mattiarubini.reminder.Activities.AddReminder;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

public class DateSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using parent.getItemAtPosition(pos)
        if ("Select date".equals(parent.getItemAtPosition(pos))) {

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
