package com.example.msusportsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.modals.SportStudentsModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChooseTeamAdapter extends RecyclerView.Adapter<ChooseTeamAdapter.MyViewHolder>{


    private Context context;
    private ArrayList<SportStudentsModal> sportStudentsModals;
   public String extra;

    public ChooseTeamAdapter(Context context, ArrayList<SportStudentsModal> sportStudentsModals, String exta) {
        this.context = context;
        this.sportStudentsModals = sportStudentsModals;
        this.extra =exta;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_team_item, parent, false);
                return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return sportStudentsModals.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.regnum.setText(sportStudentsModals.get(position).getRegnumber());
        holder.fname.setText(sportStudentsModals.get(position).getFirstname());
        holder.lname.setText(sportStudentsModals.get(position).getSurname());
        holder.level.setText(sportStudentsModals.get(position).getLevel());

    }

    public void removeItem(int position) {
        sportStudentsModals.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }



    public void restoreItem(SportStudentsModal item, int position) {
        sportStudentsModals.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{

       private TextView regnum;
        private TextView fname;
       private TextView lname;
       private TextView level;
       public LinearLayout viewForeground ;
       public RelativeLayout viewBackground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            regnum = itemView.findViewById(R.id.regnum);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            level = itemView.findViewById(R.id.level);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            viewBackground = itemView.findViewById(R.id.view_background);
        }
    }
}
