package com.mattiarubini.reminder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Build and manage a list of reminders
 * */
public class ReminderListViewWrapper {

    private View reminderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Reminder> reminders;

    /**
     * The constructor add a reminder_list_view to the specified LinearLayout
     * The RecyclerView is started and the name of: the LinearLayout, the TextView and the RecyclerView are changed in function of the passed name from:
     * */
    public ReminderListViewWrapper(Context context, LinearLayout listContainerView, String name, List<Reminder> remindersList) {
        this.reminders = remindersList;
        // Getting the LayoutInflater from the context
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // Inflating the list containing the reminders
        reminderList = layoutInflater.inflate(R.layout.reminder_list_view, null);
        // TODO: Changing the name the LinearLayout
        // TODO: Changing the name the TextView
        // TODO: Changing the name the RecyclerView
        // Setup of the RecyclerView
        recyclerView = (RecyclerView) reminderList.findViewById(R.id.recycler_view_reminders);
        // Setup layout manager of the recycleView
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // Setup the adapter to load the data in the RecyclerView
        recyclerAdapter = new ReminderRecyclerAdapter(reminders);
        recyclerView.setAdapter(recyclerAdapter);
        // Adding the list to the main linear layout
        listContainerView.addView(reminderList);
    }

    /**
     * Add a reminder to the RecyclerView
     * */
    public void addReminder(Reminder reminder) {
        // In Java the list are passed by reference and not value
        // Therefore when I update the list in the wrapper, the list in the RecyclerAdapter is updated as well
        reminders.add(reminder);
        // The list has placed the element in the position 0
        // Finally just warn the recycler of the update
        recyclerAdapter.notifyItemInserted(0);
    }

}
