package com.mattiarubini.reminder.RecyclerViewManagers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mattiarubini.reminder.R;
import com.mattiarubini.reminder.database.AppDatabase;
import com.mattiarubini.reminder.database.ReminderEntity;

import java.util.List;

// TODO: add all the methods to fully use the RecyclerView https://guides.codepath.com/android/Using-the-RecyclerView (ex: updateReminder, deleteReminder, etcetera...)
/**
 * Build and manage a list of reminders
 * */
public class ReminderListViewWrapper {

    // Variable needed by the RecyclerView for it's management
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    /**
     * reminderList is the view containing the RecyclerView
     * the layout is defined in reminder_list_view.xml
     * */
    private View reminderList;
    /**
     * Collection of Reminder shown in the RecyclerView
     * */
    private List<ReminderEntity> reminders;

    /**
     * The constructor add a reminder_list_view to the specified LinearLayout
     * The RecyclerView is started and the name of: the LinearLayout, the TextView and the RecyclerView are changed in function of the passed name from:
     * */
    public ReminderListViewWrapper(Context context, LinearLayout listContainerView, String name, AppDatabase database) {
        // Getting the reminders from the database
        List<ReminderEntity> remindersList = database.getReminderDao().getFromCategory(name);
        this.reminders = remindersList;
        // Getting the LayoutInflater from the context
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // Inflating the list containing the reminders
        reminderList = layoutInflater.inflate(R.layout.reminder_list_view, null);
        // Changing the title in the TextView whit the passed name
        TextView titleView = reminderList.findViewById(R.id.text_view_reminder);
        titleView.setText(name);
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
    public void addReminder(ReminderEntity reminder) {
        // In Java the list are passed by reference and not value
        // Therefore when I update the list in the wrapper, the list in the RecyclerAdapter is updated as well
        reminders.add(reminder);
        // The list has placed the element in the position 0
        // Finally just warn the recycler of the update
        recyclerAdapter.notifyItemInserted(0);
    }

}
