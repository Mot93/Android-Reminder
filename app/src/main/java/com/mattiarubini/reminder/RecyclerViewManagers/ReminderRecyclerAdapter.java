package com.mattiarubini.reminder.RecyclerViewManagers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mattiarubini.reminder.R;
import com.mattiarubini.reminder.Utilities.DateManager;
import com.mattiarubini.reminder.database.ReminderEntity;

import org.w3c.dom.Entity;

import java.text.ParseException;
import java.util.List;

/**
 * The adapter is used to create the
* */
public class ReminderRecyclerAdapter extends RecyclerView.Adapter {

    /**
     * Array containing all my reminders
     * */
    private List<ReminderEntity> reminders;

    /**
     * Tipe of element contained in the RecyclerView using this adapter
     *
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * You provide access to all the views for a data item in a view holder
     */
    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        // The following views are public to ease modification in onBindViewHolder
        public TextView reminderContent;
        public TextView reminderTrigger;

        // Must: super(android.view.View)
        public ReminderViewHolder(View v) {
            super(v);
            this.reminderContent = v.findViewById(R.id.reminder_content);
            this.reminderTrigger = v.findViewById(R.id.reminder_trigger);
        }

        /**
         * Replace the content of the views with the new data
         * A specific class was created, in order to deal with different texts (plain text, bullet list, etcetera...) and triggers (time, location, etcetera...)
         * */
        public void ReplaceContent(ReminderEntity reminderEntity){
            this.reminderContent.setText(reminderEntity.getContent());
            this.reminderTrigger.setText(this.getTrigger(reminderEntity.getTrigger()));
        }

        private String getTrigger(String trigger){
            try {
                // For a better formatting, I am converting a string to a Data, back to a string
                // TODO: better formatting than this
                return DateManager.stringToDate(trigger).toString();
            } catch (ParseException pe) {
                return trigger;
            }
        }

    }// ReminderViewHolder

    /**
     * Initialize the data inside to be showed in the RecycleView
     * */
    public ReminderRecyclerAdapter(List<ReminderEntity> reminders) {
        this.reminders = reminders;
    }

    /**
     * Create new views (invoked by the layout manager)
     * */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Getting the inflater of the layout
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        // Creating and inflating the view inside the parent
        View reminderView = layoutInflater.inflate(R.layout.reminder_layout, viewGroup, false);
        // Creating a ViewHolder from the View just created and inflated
        ReminderViewHolder rvh = new ReminderViewHolder(reminderView);
        return rvh;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * Use the
     * */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int reminderListID) {
        // Type casting in order to work on the public attributes
        ReminderViewHolder reminderHolder = (ReminderViewHolder) viewHolder;
        // For a better management I used a custom function of ReminderViewHolder
        reminderHolder.ReplaceContent(reminders.get(reminderListID));
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     * */
    @Override
    public int getItemCount() {
        return this.reminders.size();
    }
}
