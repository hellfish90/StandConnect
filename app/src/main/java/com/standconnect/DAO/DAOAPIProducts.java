package com.standconnect.DAO;

import android.util.Log;

import com.standconnect.API.APICALL;
import com.standconnect.Models.Product;
import com.standconnect.Models.Tag;
import com.standconnect.Utils.OnRefreshData;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Marc on 16/1/16.
 */
public class DAOAPIProducts extends DAOAPI<Product> {

    List<Product> product = null;

    public DAOAPIProducts(OnRefreshData view) {
        super(view);
    }

    @Override
    public void downloadData(String eventID) throws NoInternetException {
        view.onDownload();

        Log.d("DAOAPTags", eventID);

        Call<List<Product>> call = APICALL.getAPIStandConnect().getProducts(eventID);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Response<List<Product>> response) {
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
    public List<Product> getAll(String eventID) {
        return product;
    }

    @Override
    public Product getDetail(int id) {
        return null;
    }
}
