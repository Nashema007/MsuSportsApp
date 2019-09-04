package com.example.msusportsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.activities.CreateUserActivity;
import com.example.msusportsapp.activities.DeleteUserActivity;
import com.example.msusportsapp.activities.EventUpdatesActivity;
import com.example.msusportsapp.activities.ViewAppRatingActivity;
import com.example.msusportsapp.activities.ViewLogActivity;
import com.example.msusportsapp.modals.GetTitleModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {


    Context context;
    ArrayList<GetTitleModal> coachModels;

    public AdminAdapter(Context context, ArrayList<GetTitleModal> coachModels) {
        this.context = context;
        this.coachModels = coachModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return coachModels.size();
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.coachSelection.setText(coachModels.get(position).getTitle());

        String titleEvent = coachModels.get(position).getTitle();

        holder.itemView.setOnClickListener(v -> {
            if (titleEvent.equals(coachModels.get(0).getTitle())){
                startIntent(context, ViewLogActivity.class);
            }
            else if (titleEvent.equals(coachModels.get(1).getTitle())){
                startIntent(context, DeleteUserActivity.class);
            }
            else if (titleEvent.equals(coachModels.get(2).getTitle())){
                startIntent(context, CreateUserActivity.class);
            }
            else if (titleEvent.equals(coachModels.get(3).getTitle())){
                startIntent(context, ViewAppRatingActivity.class);
            }else if (titleEvent.equals(coachModels.get(4).getTitle())){
                startIntent(context, EventUpdatesActivity.class);
            }
        });

    }


    public void startIntent(Context context, Class getClass){
        Intent intent = new Intent(context, getClass);
        context.startActivity(intent);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView coachSelection;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            coachSelection = itemView.findViewById(R.id.coachSelection);
        }
    }
}
