package com.mattiarubini.reminder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * The adapter is used to create the
* */
public class ReminderRecyclerAdapter extends RecyclerView.Adapter {

    /**
     * Array containing all my reminders
     * */
    private List<Reminder> reminders;

    /**
     * Tipe of element contained in the RecyclerView using this adapter
     *
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * You provide access to all the views for a data item in a view holder
     */
    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        // The following views are public to ease modification in onBindViewHolder
        public TextView reminderTitle;
        public TextView reminderTrigger;

        // Must: super(android.view.View)
        public ReminderViewHolder(View v) {
            super(v);
            reminderTitle = v.findViewById(R.id.reminder_title);
            reminderTrigger = v.findViewById(R.id.reminder_trigger);
        }
    }

    /**
     * Initialize the data inside to be showed in the RecycleView
     * */
    public ReminderRecyclerAdapter(List<Reminder> reminders) {
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
     * */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // Type casting in order to work on the public attributes
        ReminderViewHolder holder = (ReminderViewHolder) viewHolder;
        // Working on the views that are stored in public types
        holder.reminderTitle.setText(reminders.get(i).getTitle());
        holder.reminderTrigger.setText(reminders.get(i).getDate().toString());
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     * */
    @Override
    public int getItemCount() {
        return this.reminders.size();
    }
}
