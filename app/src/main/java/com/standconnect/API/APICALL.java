package com.standconnect.API;

import android.widget.ProgressBar;

import com.squareup.okhttp.OkHttpClient;
import com.standconnect.BuildConfig;
import com.standconnect.Models.Business;
import com.standconnect.Models.Event;
import com.standconnect.Models.Product;
import com.standconnect.Models.Stand;
import com.standconnect.Models.Tag;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Marc on 16/1/16.
 */
public class APICALL {

    private static SmartVilaApiInterface standConnectAPI;


    private static final String API_URL = "http://alumnes-grp02.udl.cat/StandConnect/api/";

    private static Retrofit generateRetrofit(){

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(45, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);


        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SmartVilaApiInterface getAPIStandConnect() {
        if (standConnectAPI == null) {
            Retrofit retrofit = generateRetrofit();

            standConnectAPI = retrofit.create(SmartVilaApiInterface.class);
        }

        return standConnectAPI;
    }

    public interface SmartVilaApiInterface {
        @GET("events/")
        Call<List<Event>> getEvents();

        @GET("events/{eventID}/tags/")
        Call<List<Tag>> getTags(@Path("eventID") String eventID);

        @GET("events/{eventID}/products/")
        Call<List<Product>> getProducts(@Path("eventID") String eventID);

        @GET("events/{eventID}/businesses/")
        Call<List<Business>> getBusiness(@Path("eventID") String eventID);

        @GET("events/{eventID}/stands/")
        Call<List<Stand>> getStands(@Path("eventID") String eventID);
    }
}
