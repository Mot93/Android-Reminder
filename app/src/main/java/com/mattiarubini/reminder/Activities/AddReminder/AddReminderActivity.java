package com.mattiarubini.reminder.Activities.AddReminder;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mattiarubini.reminder.R;

// TODO: add pickers for date https://developer.android.com/guide/topics/ui/controls/pickers
public class AddReminderActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        // Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void onClickRadioButton(View view){
        if (view.getId() == R.id.radiobutton_time) {
            // TODO: inflate the time picker fragment and defate the location one
            // use fragment manager and fragment transactions
        } // TODO: oposite of above
    }

    // TODO: move it in the appropriated fragment
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
