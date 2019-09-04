package com.example.msusportsapp.utilities;

import android.os.Bundle;

import com.example.msusportsapp.modals.EventModal;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by kuliza-319 on 16/2/17.
 */

public class MyDiffUtilCallback extends DiffUtil.Callback {
    ArrayList<EventModal> newList;
    ArrayList<EventModal> oldList;

    public MyDiffUtilCallback(ArrayList<EventModal> newList, ArrayList<EventModal> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
//        if (result==0){
//            return true;
//        }
//        return false;

        // code below is same as code above
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        EventModal newEventModal = newList.get(newItemPosition);
        EventModal oldEventModal = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if (!newEventModal.getEventTitle().equals(oldEventModal.getEventTitle())) {
            diff.putString("title", newEventModal.getEventTitle());
        }
        if (!newEventModal.getEventSummary().equals(oldEventModal.getEventSummary())) {
            diff.putString("summary", oldEventModal.getEventSummary());
        }
        if (diff.size() == 0) {
            return null;
        }
//        LoginTimes.d("diffutilcheck", "Diff");
        return diff;
//        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
