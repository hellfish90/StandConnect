package com.standconnect.DAO;

import android.util.Log;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Business;
import com.standconnect.Models.Stand;
import com.standconnect.Utils.OnRefreshData;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPIStands extends DAOAPI<Stand> {

    List<Stand> product = null;

    public DAOAPIStands(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String eventID) throws NoInternetException {
        view.onDownload();

        Log.d("DAOAPIBusiness", eventID);

        Call<List<Stand>> call = APICALL.getAPIStandConnect().getStands(eventID);

        call.enqueue(new Callback<List<Stand>>() {
            @Override
            public void onResponse(Response<List<Stand>> response) {
                product = response.body();
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
    public List<Stand> getAll(String eventID) {
        return product;
    }

    @Override
    public Stand getDetail(int id) {
        return null;
    }
}
