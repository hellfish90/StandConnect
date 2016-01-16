package com.standconnect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.standconnect.Controllers.EventContainerController;
import com.standconnect.Models.Beacon;
import com.standconnect.Models.Event;
import com.standconnect.Models.Stand;
import com.standconnect.Utils.DataType;
import com.standconnect.Views.LocationFragment;
import com.standconnect.Views.MapsFragment;
import com.standconnect.Views.ProfileFragment;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Locale;

public class EventContainer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Beacon> beacons;
    public static ArrayList<Stand> stands;

    EventContainerController eventContainerController;

    String eventID;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra!=null){
            event = (Event) extra.getSerializable("event");
        }

        beacons = new ArrayList<>();
        stands = new ArrayList<>();

        eventContainerController = new EventContainerController(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (eventContainerController.isScan()){

                    eventContainerController.stopScan();

                    fab.setImageResource(R.mipmap.ic_gps_not_fixed_white_24dp);

                    Snackbar.make(view, "Stop Scanning", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                    eventContainerController.scan(DummyContent.ITEM_BEACONS_EVENT);
                    fab.setImageResource(R.mipmap.ic_gps_fixed_white_24dp);
                    Snackbar.make(view, "Is Scanning", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_container, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Bundle args = new Bundle();

         eventID = event.getId().toString();

        Log.d("EventContainer",eventID);

        DataType dataType = null;


            if (id == R.id.nav_business) {
                //listfragment
                fragment = new ListEventContainerFragment();
                dataType= DataType.Business;

                // Handle the camera action
            } else if (id == R.id.nav_products) {
                //listfragment
                fragment = new ListEventContainerFragment();
                dataType = DataType.Product;

            } else if (id == R.id.nav_events_map) {
                //stands image
                fragment = new MapsFragment();


            } else if (id == R.id.nav_location) {
                //maps
                fragment = new LocationFragment();


            } else if (id == R.id.nav_tags) {
                //checkList de tags
                fragment = new ListEventContainerFragment();
                dataType = DataType.TAGS;

            } else if (id == R.id.nav_profile) {
                //FormUserProfile
                fragment = new ProfileFragment();
                dataType = null;

            }else if (id == R.id.nav_stand) {
                //FormUserProfile
                fragment = new ListEventContainerFragment();
                dataType = DataType.Stand;

            }else{
                //error fragment
                fragment = new ListEventContainerFragment();

            }



        args.putSerializable(ListEventContainerFragment.ARG_EVENT_CONTENT_LIST, dataType);
        args.putSerializable("eventID", event.getId().toString());
        args.putSerializable("event", event);

        fragment.setArguments(args);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_event_container_frame, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.planets_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                    "drawable", getActivity().getPackageName());
            ((TextView) rootView.findViewById(R.id.text_fragment)).setText(planet);
            getActivity().setTitle(planet);
            return rootView;
        }
    }

}
