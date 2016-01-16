package com.standconnect;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.standconnect.Models.Business;
import com.standconnect.Models.Entity;
import com.standconnect.Models.Product;
import com.standconnect.Views.BusinessDetailFragment;
import com.standconnect.Views.ProductDetailFragment;

public class DetailActivityContainer extends AppCompatActivity {

    public static final String ARG_DETAIL_CONTENT_ENTITY = "DETAIL_ENTITY_CONTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Entity detailData = null;

        Bundle args = getIntent().getExtras();

        if (args!=null){
            detailData = (Entity) args.getSerializable(ARG_DETAIL_CONTENT_ENTITY);
        }else{
            Log.e("DetailActivityContainer","Error no savedInstanceState");
        }

        if (detailData instanceof Business){

            Business business = (Business) detailData;

            Fragment fragment= BusinessDetailFragment.newInstance(business);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_detail_container_frame, fragment).commit();

        }else if(detailData instanceof Product){

            Product product = (Product) detailData;

            Fragment fragment= ProductDetailFragment.newInstance(product);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_detail_container_frame, fragment).commit();

        }

    }

}
