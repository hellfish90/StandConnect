package com.standconnect;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.standconnect.Models.Bussines;
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

        if (detailData instanceof Bussines){

            Bussines business = (Bussines) detailData;

            Fragment fragment= BusinessDetailFragment.newInstance(business);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_detail_container_frame, fragment).commit();

        }else if(detailData instanceof Product){

            Product product = (Product) detailData;

            Fragment fragment= ProductDetailFragment.newInstance(product);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_detail_container_frame, fragment).commit();

        }




    }

}
