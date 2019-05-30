package com.mattiarubini.reminder.Activities.AddReminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.mattiarubini.reminder.R;

// TODO: add the necessary pickers
public class TriggerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trigger_time, container, false);
        // TODO: set the spinners
        return view;
    }


}
