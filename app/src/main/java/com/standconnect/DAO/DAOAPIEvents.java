package com.standconnect.DAO;

import android.util.Log;
import android.widget.ListView;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Event;
import com.standconnect.Utils.OnRefreshData;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPIEvents extends DAOAPI<Event> {

    List<Event> events= null;

    public DAOAPIEvents(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String towns) throws NoInternetException {
        view.onDownload();

        Call<List<Event>> call = APICALL.getAPIStandConnect().getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Response<List<Event>> response) {
                events = response.body();
                view.dataDownloaded();
            }

            @Override
            public void onFailure(Throwable t) {
                view.dataDownloaded();
                Log.d("Error Retrofid DATA", t.toString());
                Log.e("Error Retrofit", t.getMessage());
            }
        });
    }

    @Override
    public void downloadDetailData(String towns) throws NoInternetException {

    }

    @Override
    public List<Event> getAll(String towns) {
        return events;
    }

    @Override
    public Event getDetail(int id) {
        return null;
    }
}
