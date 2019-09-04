package com.example.msusportsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.activities.ArticleActivity;
import com.example.msusportsapp.modals.EventModal;
import com.example.msusportsapp.utilities.MyDiffUtilCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    ArrayList<EventModal> eventModals;
    Context context;


    public EventsAdapter(ArrayList<EventModal> eventModals, Context context) {
        this.eventModals = eventModals;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return eventModals.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.eventTitle.setText(eventModals.get(position).getEventTitle());
        holder.eventSummary.setText(eventModals.get(position).getEventSummary());
        holder.eventImage.setImageResource(R.drawable.news_96px);

        holder.eventCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(holder.itemView.getContext(), ArticleActivity.class);
            intent.putExtra("title", eventModals.get(position).getEventTitle());
            intent.putExtra("description", eventModals.get(position).getEventDescription());
            holder.itemView.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        }
        else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("title")) {
                    holder.eventTitle.setText(eventModals.get(position).getEventTitle());
                }
                if (key.equals("summary")) {
                    holder.eventSummary.setText(eventModals.get(position).getEventSummary());
                }
            }
            holder.eventImage.setImageResource(R.drawable.news_96px);
                    }
    }

    public void onNewData(ArrayList<EventModal> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(newData, eventModals));
        diffResult.dispatchUpdatesTo(this);
        this.eventModals.clear();
        this.eventModals.addAll(newData);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView eventImage;
        TextView eventSummary;
        TextView eventTitle;
        CardView eventCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.event_image);
            eventSummary = itemView.findViewById(R.id.event_summary);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventCardView = itemView.findViewById(R.id.eventCardView);
        }
    }
}
