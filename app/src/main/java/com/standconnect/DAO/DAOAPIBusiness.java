package com.standconnect.DAO;

import android.util.Log;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Business;
import com.standconnect.Models.Product;
import com.standconnect.Utils.OnRefreshData;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPIBusiness extends DAOAPI<Business> {

    List<Business> product = null;

    public DAOAPIBusiness(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String eventID) throws NoInternetException {
        view.onDownload();

        Log.d("DAOAPIBusiness", eventID);

        Call<List<Business>> call = APICALL.getAPIStandConnect().getBusiness(eventID);

        call.enqueue(new Callback<List<Business>>() {
            @Override
            public void onResponse(Response<List<Business>> response) {
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
    public List<Business> getAll(String eventID) {
        return product;
    }

    @Override
    public Business getDetail(int id) {
        return null;
    }
}
