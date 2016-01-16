package com.standconnect.Controllers;

import android.util.Log;

import com.standconnect.DAO.DAOAPIEvents;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.Event;
import com.standconnect.Utils.OnRefreshData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 16/1/16.
 */
public class EventsController implements OnRefreshData {

    DAOAPIEvents daoapiEvents;

    OnRefreshData view;


    public EventsController(OnRefreshData view){
        daoapiEvents = new DAOAPIEvents(this);
        this.view = view;
    }

    public List<Event> getAllEvents() throws NoInternetException {

        List<Event> townHallList = daoapiEvents.getAll(null);
        if (townHallList!= null){
            Log.d("TownHallController", "notNull");
            return townHallList;
        }else{
            view.onDownload();
            daoapiEvents.downloadData(null);
            return new ArrayList<>();
        }

    }

    @Override
    public void onDownload() {
        view.onDownload();

    }

    @Override
    public void dataDownloaded() {
        view.dataDownloaded();

    }
}
