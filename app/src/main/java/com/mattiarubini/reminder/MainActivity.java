package com.mattiarubini.reminder;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.view.View;

import com.mattiarubini.reminder.database.AppDatabase;
import com.mattiarubini.reminder.database.CategoryReminderEntity;
import com.mattiarubini.reminder.database.CategoryReminderDao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

// TODO: make the ScrollView work with dynamic content

// sudo adb start-server
// TODO: add SQLite 3 using Room: https://developer.android.com/training/data-storage/room
// TODO: add a Floating Action Button: https://developer.android.com/guide/topics/ui/floating-action-button
public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private ReminderListViewWrapper[] reminderListViewWrappers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        createReminderListLayout();
    }

    /**
     * Initialize the connection with the database
     * Also adds all the basic categories
     * */
    private void initDatabase(){
        // Opening the connection to the database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ReminderDatabase")
                .allowMainThreadQueries()
                .build();
        // Getting the Dao for categories
        CategoryReminderDao categoryDao = database.getCategoryReminderDao();
        // Adding the 3 base categories
        try{
            CategoryReminderEntity category = new CategoryReminderEntity();
            category.name = "At a time"; // Still has to happen
            categoryDao.addCategory(category);
            category.name = "Ongoing"; // It's going
            categoryDao.addCategory(category);
            category.name = "Done"; // Completed
        categoryDao.addCategory(category);}
        catch(Exception e){

        }
    }

    /**
     * Create a reminder_list_layout for each category
     * */
    private void createReminderListLayout(){
        // Getting the Dao for Categories
        CategoryReminderDao categoriesDao = database.getCategoryReminderDao();
        // Getting all the categories
        List<CategoryReminderEntity> categories = categoriesDao.getAll();
        // Initializing the categories views
        reminderListViewWrappers = new ReminderListViewWrapper[categories.size()];
        // Implementing all the categories
        List<Reminder> r = new ArrayList<Reminder>();
        r.add(new Reminder("Reminder 1", new Date()));
        r.add(new Reminder("Reminder 2", new Date()));
        for (int i=0; i<categories.size(); i++) {
            reminderListViewWrappers[i] = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder),  categories.get(i).name, r);
            reminderListViewWrappers[i].addReminder(new Reminder("Reminder 3", new Date()));
        }
        /*
        List<Reminder> r = new ArrayList<Reminder>();
        r.add(new Reminder("Reminder 1", new Date()));
        r.add(new Reminder("Reminder 2", new Date()));
        ReminderListViewWrapper rl = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder), "example 1", r);
        rl.addReminder(new Reminder("Reminder 3", new Date()));
        ReminderListViewWrapper rl2 = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder), "example 2", r);
        */
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
