package com.mattiarubini.reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.view.View;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

// TODO: add a Floating Action Button
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

    /**
     * toggleRecyclerView is called when the TextView above the RecyclerView is clicked
     * Hides the RecyclerView and keeps the TextView
     * This function only works with the text_view_reminder in reminder_list_layout.xml
     * */
    public void toggleRecyclerView(View v) {
        // First I get the root of the reminder_list_layout.xml which is a LinearLayout
        LinearLayout linearLayout = (LinearLayout) v.getParent();
        // From the LinearLayout I extract it's child the RecyclerView
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(R.id.recycler_view_reminders);
        // If the RecyclerView is visible, set it to gone and viceversa
        if (recyclerView.getVisibility() == View.VISIBLE) recyclerView.setVisibility(View.GONE);
        else recyclerView.setVisibility(View.VISIBLE);
    }
}
