package com.example.msusportsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.activities.ChooseTeamActivity;
import com.example.msusportsapp.activities.StatsPerLevelActivity;
import com.example.msusportsapp.activities.TeamUpdatesActivity;
import com.example.msusportsapp.activities.ViewTeamActivity;
import com.example.msusportsapp.modals.GetTitleModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.MyViewHolder> {


    Context context;
    ArrayList<GetTitleModal> coachModels;
    String extra;


    public CoachAdapter(Context context, ArrayList<GetTitleModal> coachModels, String extra) {
        this.context = context;
        this.coachModels = coachModels;
        this.extra = extra;
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
                startIntent(context, StatsPerLevelActivity.class, extra);
            }
            else if (titleEvent.equals(coachModels.get(1).getTitle())){
                startIntent(context, ChooseTeamActivity.class, extra);
            }
            else if (titleEvent.equals(coachModels.get(2).getTitle())){
                startIntent(context, ViewTeamActivity.class, extra);
            }
            else if (titleEvent.equals(coachModels.get(3).getTitle())){
                startIntent(context, TeamUpdatesActivity.class, extra);
            }
        });

    }


    public void startIntent(Context context, Class getClass, String extra){
        Intent intent = new Intent(context, getClass);
        intent.putExtra(extra, extra);
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
