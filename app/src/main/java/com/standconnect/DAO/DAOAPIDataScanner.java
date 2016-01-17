package com.standconnect.DAO;

import android.util.Log;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Business;
import com.standconnect.Models.DataForScanner;
import com.standconnect.Utils.OnRefreshData;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPIDataScanner extends DAOAPI<DataForScanner> {

    List<DataForScanner> product = null;

    public DAOAPIDataScanner(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String eventID) throws NoInternetException {


        Log.d("DAOAPIBusiness", eventID);
        Call<List<DataForScanner>> call = APICALL.getAPIStandConnect().getDataForScanner(eventID);
        call.enqueue(new Callback<List<DataForScanner>>() {
            @Override
            public void onResponse(Response<List<DataForScanner>> response) {
                product = response.body();
                Log.d("DAOAPIDataScanner DATA", product.toString());
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
    public List<DataForScanner> getAll(String eventID) {

        return product;
    }

    @Override
    public DataForScanner getDetail(int id) {
        return null;
    }
}
