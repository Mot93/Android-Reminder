package com.mattiarubini.reminder.Activities;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.view.View;

import com.mattiarubini.reminder.R;
import com.mattiarubini.reminder.RecyclerViewManagers.ReminderListViewWrapper;
import com.mattiarubini.reminder.Utilities.BaseCategories;
import com.mattiarubini.reminder.database.AppDatabase;
import com.mattiarubini.reminder.database.CategoryReminderEntity;
import com.mattiarubini.reminder.database.CategoryReminderDao;
import com.mattiarubini.reminder.database.ReminderDao;
import com.mattiarubini.reminder.database.ReminderEntity;

import java.util.List;
import java.util.Date;

// sudo adb start-server
// TODO: add a Tool Bar
// TODO: add animation when a category collapse
public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private ReminderListViewWrapper[] reminderListViewWrappers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Init
        super.onCreate(savedInstanceState);
        // Layout
        setContentView(R.layout.activity_main);
        // Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // App logic
        initDatabase();
        try {
            populateFakeReminders();
        } catch (Exception e) {
            Log.e("add error", e.toString());
        }
        createReminderListLayout();
        try {
            deleteAll();
        } catch (Exception e){
            Log.e("delete error", e.toString());
        }
    }

    /**
     * Initialize the connection with the database
     * Also adds all the basic categories
     * */
    private void initDatabase(){
        // This code will be executed when the database is fist created, to pre-populate with the base categories
        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            // onCreate populates the database with the base categories when it is fist created
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                // Populating the database with the base categories
                // Getting the baseCategories
                String[] baseCategories = BaseCategories.getBaseCategories();
                // Adding the base categories to the database
                for (int i=0; i<baseCategories.length; i++){
                    String sqlStatement = "INSERT INTO CategoryReminderEntity ( name ) VALUES ('" + baseCategories[i] + "')";
                    db.execSQL(sqlStatement);
                }
                Log.e("onCreate", "onCreate was executed");
            }
            // onOpen populates the database with the base categories when it is fist created if the base categories are not present
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                // TODO: instead of using a try catch, check if a category exist and add it if needed
                String[] baseCategories = BaseCategories.getBaseCategories();
                for (int i=0; i<baseCategories.length; i++){
                    try{
                    String sqlStatement = "INSERT INTO CategoryReminderEntity ( name ) VALUES ('" + baseCategories[i] + "')";
                    db.execSQL(sqlStatement);} catch (Exception e){}
                }
                Log.e("onOpen", "onOpen was executed");
            }
        };
        // Opening the connection to the database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ReminderDatabase")
                .allowMainThreadQueries() // TODO: document this one
                .fallbackToDestructiveMigration() // TODO: this line destroy the previous schema and data, remember to update the schema, not dispose of it
                .addCallback(rdc) // Adds a callBack function, in this case is used to pre-populate the database with the base categories
                .build();
    }// initDatabase

    /**
     * Create a reminder_list_layout for each category
     * */
    private void createReminderListLayout(){
        // Getting the Dao for Categories
        CategoryReminderDao categoriesDao = database.getCategoryReminderDao();
        // Getting all the categories in the database
        List<CategoryReminderEntity> categories = categoriesDao.getAll();
        // Initializing the categories views
        reminderListViewWrappers = new ReminderListViewWrapper[categories.size()];
        // TODO: order to display each category
        for (int i=0; i<categories.size(); i++) {
            reminderListViewWrappers[i] = new ReminderListViewWrapper(this, (LinearLayout) findViewById(R.id.list_holder),  categories.get(i).getName(), database);
        }
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

    /**
     * TODO: When the floating button is pressed: add a reminder in the activity AddReminderActivity
     * */
    public void floatingActionButton(View view){
        Intent intent = new Intent(this, AddReminderActivity.class);
        startActivity(intent);
    }

    /**
     * Insert fake reminders for each categories
     * */
    private void populateFakeReminders() throws Exception{
        ReminderDao reminderDao = database.getReminderDao();
        Date date = new Date();
        String[] baseCategories = BaseCategories.getBaseCategories();
        for (int i=0; i<baseCategories.length; i++){
            ReminderEntity[] reminders = new ReminderEntity[i+2];
            for (int y=0; y<reminders.length; y++){
                reminders[y] = new ReminderEntity("Reminder number : "+y, date.toString(), baseCategories[i]);
            }
            reminderDao.insert(reminders);
        }
    }

    /**
     * Delete all reminders in all categories
     * */
    private void deleteAll() throws  Exception {
        ReminderDao reminderDao = database.getReminderDao();
        Date date = new Date();
        String[] baseCategories = BaseCategories.getBaseCategories();
        for (int i=0; i<baseCategories.length; i++){
            reminderDao.deleteCategory(baseCategories[i]);
        }
    }

}
