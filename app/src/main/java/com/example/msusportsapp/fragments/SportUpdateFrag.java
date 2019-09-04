package com.example.msusportsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.example.msusportsapp.R;
import com.example.msusportsapp.adapters.EventsAdapter;
import com.example.msusportsapp.modals.EventModal;
import com.example.msusportsapp.utilities.ApiUrl;
import com.example.msusportsapp.utilities.AppSingleton;
import com.example.msusportsapp.utilities.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SportUpdateFrag extends Fragment {

    private EventsAdapter eventsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<EventModal> eventModals = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = view.findViewById(R.id.events);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshEvent);
        TextView unregistred = view.findViewById(R.id.unregistered);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        SharedPref sharedPref = new SharedPref(view.getContext());


        String sharedSport = sharedPref.getString("sport", "");

        if(sharedSport.equals("null") || sharedSport.equals("")) {
            recyclerView.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.GONE);
            unregistred.setVisibility(View.VISIBLE);
        }
        else {

            recyclerView.setVisibility(View.VISIBLE);
            unregistred.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);

            retrieveEvent();
            swipeRefreshLayout.setOnRefreshListener(() -> {
                retrieveEvent();
                eventsAdapter.onNewData(eventModals);
                swipeRefreshLayout.setRefreshing(false);

            });
        }

        return view;
    }

    private void retrieveEvent() {


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.URL_GET_EVENTS, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject eventsArray = jsonArray.getJSONObject(0);
                JSONArray eventsDetails = eventsArray.getJSONArray("soccer");


                for (int i = 0; i < eventsDetails.length(); i++) {
                    JSONObject index = eventsDetails.getJSONObject(i);

                    String eventTitles = index.getString("title");
                    String eventSummary = index.getString("description");
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> summaries = new ArrayList<>();
                    titles.add(eventTitles);

                    summaries.add(eventSummary);
                    eventModals.add(new EventModal(titles.get(0), summaries.get(0)));
                    eventsAdapter = new EventsAdapter(eventModals, getContext());


                }

                recyclerView.setAdapter(eventsAdapter);
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
}
