package com.mattiarubini.reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Reminder> r = new ArrayList<Reminder>();
        r.add(new Reminder("Reminder 1", new Date()));
        r.add(new Reminder("Reminder 2", new Date()));
        ReminderListViewWrapper rl = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder), "example", r);
        rl.addReminder(new Reminder("Reminder 3", new Date()));
        ReminderListViewWrapper rl2 = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder), "example", r);

    }

    /*public void toggleRecyclerView(View v) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_reminders);
        if (recyclerView.getVisibility() == View.VISIBLE) recyclerView.setVisibility(View.GONE);
        else recyclerView.setVisibility(View.VISIBLE);
    }*/
}
