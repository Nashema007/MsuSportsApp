package com.example.msusportsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msusportsapp.R;
import com.example.msusportsapp.modals.SportStudentsModal;

import java.util.ArrayList;

public class DeleteUserAdapter extends RecyclerView.Adapter<DeleteUserAdapter.MyViewHolder>{


    private Context context;
    private ArrayList<SportStudentsModal> sportStudentsModals;

    public DeleteUserAdapter(Context context, ArrayList<SportStudentsModal> sportStudentsModals) {
        this.context = context;
        this.sportStudentsModals = sportStudentsModals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.regnum.setText(sportStudentsModals.get(position).getRegnumber());

    }

    @Override
    public int getItemCount() {
        return sportStudentsModals.size();
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
        public LinearLayout viewForeground ;
        public RelativeLayout viewBackground;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            regnum = itemView.findViewById(R.id.regnum);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            viewBackground = itemView.findViewById(R.id.view_background);
        }
    }
}
