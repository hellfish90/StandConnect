package com.standconnect.DAO;

import android.util.Log;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Event;
import com.standconnect.Models.Tag;
import com.standconnect.Utils.OnRefreshData;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPTags extends DAOAPI<Tag> {

    List<Tag> tags = null;

    public DAOAPTags(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String eventID) throws NoInternetException {
        view.onDownload();

        Log.d("DAOAPTags", eventID);

        Call<List<Tag>> call = APICALL.getAPIStandConnect().getTags(eventID);

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Response<List<Tag>> response) {
                tags = response.body();
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
    public List<Tag> getAll(String eventID) {
        return tags;
    }

    @Override
    public Tag getDetail(int id) {
        return null;
    }
}
