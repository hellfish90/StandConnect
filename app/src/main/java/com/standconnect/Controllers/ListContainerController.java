package com.standconnect.Controllers;

import android.util.Log;

import com.standconnect.DAO.DAOAPTags;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.Entity;
import com.standconnect.Utils.OnRefreshData;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;

/**
 * Created by Marc on 16/1/16.
 */
public class ListContainerController implements OnRefreshData {

    DAOAPTags daoTags;

    OnRefreshData view;

    public ListContainerController(OnRefreshData view){
        daoTags = new DAOAPTags(this);
        this.view = view;
    }

    public ArrayList<Entity> getAllBusiness(String id){

        return DummyContent.ITEM_BUSINESS_DUMMY;
    }

    public ArrayList<Entity> getAllProducts(String id){
        return DummyContent.ITEM_PRODUCT_DUMMY;
    }

    public ArrayList<Entity> getAllTags(String id) throws NoInternetException {


        ArrayList<? extends Entity> tags = (ArrayList<? extends Entity>) daoTags.getAll(id);
        if (tags!= null){
            Log.d("ListContainerController", tags.toString());
            return (ArrayList<Entity>) tags;
        }else{
            view.onDownload();
            daoTags.downloadData(id);
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
