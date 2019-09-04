package com.example.msusportsapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.ChooseTeamAdapter;
import com.example.msusportsapp.modals.SportStudentsModal;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.RecyclerItemTouchHelper;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FemalePlayers extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    public FemalePlayers() {
    }

    private RecyclerView chooseTeamRecyclerView;
    private ArrayList<SportStudentsModal> sportStudentsModals = new ArrayList<>();
    private ChooseTeamAdapter teamAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        chooseTeamRecyclerView = view.findViewById(R.id.chooseTeamRecyclerView);
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        chooseTeamRecyclerView.setLayoutManager(layoutManager);
        chooseTeamRecyclerView.setHasFixedSize(true);

        chooseTeamRecyclerView.setItemAnimator(new DefaultItemAnimator());
        chooseTeamRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        String soccer = getActivity().getIntent().getStringExtra("soccer");
        String basketball = getActivity().getIntent().getStringExtra("basketball");
        String hockey = getActivity().getIntent().getStringExtra("hockey");

        String url = " ";
        String extra = setURL(soccer, basketball, hockey);


        switch (extra) {
            case "choose_basketball":
                url = ApiUrl.URL_GET_BASKETBALL_STUDENTS;
                break;
            case "choose_hockey":
                url = ApiUrl.URL_GET_HOCKEY_STUDENTS;
                break;
            case "choose_soccer":
                url = ApiUrl.URL_GET_SOCCER_STUDENTS;
                break;
        }

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(chooseTeamRecyclerView);

        fetchStudents(url, extra);
        return view;
    }


    private String setURL(String soccer, String basketball, String hockey) {

        String extra = " ";
        if (soccer == null && hockey == null) {
            soccer = " ";
            hockey = " ";
            extra = "choose_basketball";


        } else if (soccer == null && basketball == null) {

            soccer = " ";
            basketball = " ";

            extra = "choose_hockey";

        } else if (basketball == null && hockey == null) {

            basketball = " ";
            hockey = " ";
            extra = "choose_soccer";

        }

        return extra;
    }

    private void fetchStudents(String url, String extra) {

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject eventsArray = jsonArray.getJSONObject(0);
                JSONArray eventsDetails = eventsArray.getJSONArray(extra);


                for (int i = 0; i < eventsDetails.length(); i++) {
                    JSONObject index = eventsDetails.getJSONObject(i);

                    String regnumberSport = index.getString("regnumber");
                    String firstnameSport = index.getString("firstname");
                    String surnameSport = index.getString("surname");
                    String levelSport = index.getString("level");
                    String gender = index.getString("gender").toLowerCase();
                    ArrayList<String> firstname = new ArrayList<>();
                    ArrayList<String> regnumber = new ArrayList<>();
                    ArrayList<String> level = new ArrayList<>();
                    ArrayList<String> surname = new ArrayList<>();

                    if(gender.equals("female")){
                        firstname.add(firstnameSport);
                        surname.add(surnameSport);
                        regnumber.add(regnumberSport);
                        level.add(levelSport);

                        sportStudentsModals.add(new SportStudentsModal(regnumber.get(0), firstname.get(0),
                                surname.get(0), level.get(0)));
                        teamAdapter = new ChooseTeamAdapter(getContext(), sportStudentsModals, extra);
                    }
                }

                chooseTeamRecyclerView.setAdapter(teamAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {
            if (error instanceof TimeoutError) {
                Toast.makeText(getContext(), "Login attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(getContext(), "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();


        });

        AppSingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ChooseTeamAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = sportStudentsModals.get(viewHolder.getAdapterPosition()).getRegnumber();
            String sport = teamAdapter.extra;


            // backup of removed item for undo purpose
            final SportStudentsModal selectedItem = sportStudentsModals.get(viewHolder.getAdapterPosition());
            final int selectedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            teamAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " selected player!", Snackbar.LENGTH_LONG);

            snackbar.setAction("UNDO", view -> {

                // undo is selected, restore the deleted item
                teamAdapter.restoreItem(selectedItem, selectedIndex);
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

            snackbar.addCallback(new Snackbar.Callback() {

                @Override
                public void onDismissed(Snackbar snackbar, int event) {


                    if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {

                        addFemalePlayers(name, sport);
                    }

                }


            });
        }
    }

    private void addFemalePlayers(String player, String extra) {
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, ApiUrl.URL_SELECT_TEAM, response -> {

            try {

                // gets response from php file for success or failure
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");

                switch (code) {

                    case "successful":

                        Toast.makeText(getContext(), "Player Added", Toast.LENGTH_LONG).show();

                        break;
                    case "Login failed":

                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            if (error instanceof TimeoutError) {
                Toast.makeText(getContext(), "Submission attempt has timed out. Please try again.",
                        Toast.LENGTH_LONG).show();

            } else if (error instanceof NetworkError) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_LONG).show();

            } else if (error instanceof ServerError) {
                Toast.makeText(getContext(), "Server is down", Toast.LENGTH_LONG).show();

            }
            error.printStackTrace();

        }) {
            @Override
            protected Map<String, String> getParams() {
                // stores the login details using key pair system
                Map<String, String> params = new HashMap<>();
                params.put("Player", player);
                params.put("sport", extra);


                return params;

            }
        };

        AppSingleton.getInstance(getContext()).addToRequestQueue(myStringRequest);

    }
}
