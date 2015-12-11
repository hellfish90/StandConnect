package com.standconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.standconnect.Models.Event;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;

public class MainEventList extends AppCompatActivity {

    ArrayList<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        eventList = DummyContent.ITEM_EVENT_DUMMY;

        EventListAdapter eventListAdapter = new EventListAdapter(this, eventList);

        ListView events_list = (ListView) findViewById(R.id.listView_events_main);
        events_list.setAdapter(eventListAdapter);
        events_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainEventList.this,EventContainer.class);
                Bundle extra = new Bundle();
                extra.putSerializable("event",eventList.get(position));
                i.putExtras(extra);
                startActivity(i);
            }
        });




    }


    private class EventListAdapter extends ArrayAdapter<Event> {
        public EventListAdapter(Context context, ArrayList<Event> users) {
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
            eventName.setText(event.getName());

            // Return the completed view to render on screen
            return convertView;
        }
    }

}
