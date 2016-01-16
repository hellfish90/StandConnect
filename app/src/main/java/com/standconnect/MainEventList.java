package com.standconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.standconnect.Controllers.EventsController;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.Event;
import com.standconnect.Utils.OnRefreshData;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainEventList extends AppCompatActivity implements OnRefreshData {

    List<Event> eventList;

    EventsController eventsController;

    ProgressBar progressBar;

    EventListAdapter eventListAdapter;

    ListView events_list_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        eventsController = new EventsController(this);


        try {
            eventList = eventsController.getAllEvents();
        } catch (NoInternetException e) {
            e.printStackTrace();
        }

        eventListAdapter = new EventListAdapter(this, eventList);

        events_list_View = (ListView) findViewById(R.id.listView_events_main);
        events_list_View.setAdapter(eventListAdapter);
        events_list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainEventList.this, EventContainer.class);
                Bundle extra = new Bundle();
                extra.putSerializable("event", eventList.get(position));
                i.putExtras(extra);
                startActivity(i);
            }
        });

    }

    @Override
    public void onDownload() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dataDownloaded() {
        progressBar.setVisibility(View.INVISIBLE);
        try {
            List<Event> eventsArray = eventsController.getAllEvents();
            refreshList(eventsArray);

        } catch (NoInternetException e) {
            Log.e("ListEventsF", "No InternetConnection" + e.getMessage());
        }

    }

    public void refreshList(List<Event> eventsArray){

        eventListAdapter.notifyDataSetInvalidated();
        eventListAdapter.clear();
        eventList= (ArrayList<Event>) eventsArray;
        Log.d("ListEventsF", "Refresh Data: " + eventsArray.toString());
        eventListAdapter.addAll(eventList);
        eventListAdapter.notifyDataSetChanged();
        events_list_View.setAdapter(eventListAdapter);

    }


    private class EventListAdapter extends ArrayAdapter<Event> {
        public EventListAdapter(Context context, List<Event> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Event event = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row_main_event_list, parent, false);
            }
            // Lookup view for data population
            TextView eventName = (TextView) convertView.findViewById(R.id.textview_item_event_list_row);

            // Populate the data into the template view using the data object
            eventName.setText(event.getName()+" "+event.getId());

            // Return the completed view to render on screen
            return convertView;
        }
    }

}
