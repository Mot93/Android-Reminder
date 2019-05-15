package com.mattiarubini.reminder.Activities;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.view.View;

import com.mattiarubini.reminder.R;
import com.mattiarubini.reminder.RecyclerViewManagers.ReminderListViewWrapper;
import com.mattiarubini.reminder.database.AppDatabase;
import com.mattiarubini.reminder.database.CategoryReminderEntity;
import com.mattiarubini.reminder.database.CategoryReminderDao;
import com.mattiarubini.reminder.database.ReminderDao;
import com.mattiarubini.reminder.database.ReminderEntity;

import java.util.List;
import java.util.Date;

// sudo adb start-server
// TODO: add a Floating Action Button: https://developer.android.com/guide/topics/ui/floating-action-button
public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private ReminderListViewWrapper[] reminderListViewWrappers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                String[] baseCategories = CategoryReminderEntity.getBaseCategories();
                // Adding the base categories to the database
                for (int i=0; i<baseCategories.length; i++){
                    String sqlStatement = "INSERT INTO CategoryReminderEntity ( name ) VALUES ('" + baseCategories[i] + "')";
                    db.execSQL(sqlStatement);
                }
                Log.e("onCreate", "onCreate was executed");
            }
            // TODO: instead of using a try catch, check is a cegory exist and add it if needed
            // onOpen populates the database with the base categories when it is fist created if the base categories are not present
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                String[] baseCategories = CategoryReminderEntity.getBaseCategories();
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

    private void populateFakeReminders() throws Exception{
        ReminderDao reminderDao = database.getReminderDao();
        Date date = new Date();
        String[] baseCategories = CategoryReminderEntity.getBaseCategories();
        // inserting in "At a time"
        ReminderEntity[] reminders = new ReminderEntity[4];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[0]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[0]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[0]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[0]);
        reminderDao.insert(reminders);
        // Inserting Ongoing
        reminders = new ReminderEntity[5];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[1]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[1]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[1]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[1]);
        reminders[4] = new ReminderEntity("Fifth of it's name", date.toString(), baseCategories[1]);
        reminderDao.insert(reminders);
        // Inserting Done
        reminders = new ReminderEntity[6];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[2]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[2]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[2]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[2]);
        reminders[4] = new ReminderEntity("Fifth of it's name", date.toString(), baseCategories[2]);
        reminders[5] = new ReminderEntity("Sixth of it's name", date.toString(), baseCategories[2]);
        reminderDao.insert(reminders);
    }

    private void deleteAll() throws  Exception {
        ReminderDao reminderDao = database.getReminderDao();
        Date date = new Date();
        String[] baseCategories = CategoryReminderEntity.getBaseCategories();
        // inserting in "At a time"
        ReminderEntity[] reminders = new ReminderEntity[3];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[0]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[0]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[0]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[0]);
        reminderDao.delete(reminders);
        // Inserting Ongoing
        reminders = new ReminderEntity[4];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[1]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[1]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[1]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[1]);
        reminders[4] = new ReminderEntity("Fifth of it's name", date.toString(), baseCategories[1]);
        reminderDao.delete(reminders);
        // Inserting Done
        reminders = new ReminderEntity[5];
        reminders[0] = new ReminderEntity("First of it's name", date.toString(), baseCategories[2]);
        reminders[1] = new ReminderEntity("Second of it's name", date.toString(), baseCategories[2]);
        reminders[2] = new ReminderEntity("Third of it's name", date.toString(), baseCategories[2]);
        reminders[3] = new ReminderEntity("Fourth of it's name", date.toString(), baseCategories[2]);
        reminders[4] = new ReminderEntity("Fifth of it's name", date.toString(), baseCategories[2]);
        reminders[5] = new ReminderEntity("Sixth of it's name", date.toString(), baseCategories[2]);
        reminderDao.delete(reminders);
    }

}
