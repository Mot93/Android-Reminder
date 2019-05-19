package com.mattiarubini.reminder.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ReminderDao {

    @Query("SELECT * FROM ReminderEntity WHERE category = :category_name")
    List<ReminderEntity> getFromCategory(String category_name);

    @Query("DELETE FROM ReminderEntity WHERE category = :category")
    void deleteCategory(String category);

    @Insert
    void insert(ReminderEntity... reminders);

    @Update
    void update(ReminderEntity... reminders);

    @Delete
    void delete(ReminderEntity... reminders);

}
