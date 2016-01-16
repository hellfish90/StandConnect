package com.standconnect.Controllers;

import android.util.Log;

import com.standconnect.DAO.DAOAPIBusiness;
import com.standconnect.DAO.DAOAPIProducts;
import com.standconnect.DAO.DAOAPIStands;
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
    DAOAPIProducts daoapiProducts;
    DAOAPIBusiness daoapiBusiness;
    DAOAPIStands daoapiStands;

    OnRefreshData view;

    public ListContainerController(OnRefreshData view){
        daoTags = new DAOAPTags(this);
        daoapiBusiness = new DAOAPIBusiness(this);
        daoapiProducts = new DAOAPIProducts(this);
        daoapiStands = new DAOAPIStands(this);
        this.view = view;
    }

    public ArrayList<Entity> getAllStands(String id) throws NoInternetException {
        ArrayList<? extends Entity> tags = (ArrayList<? extends Entity>) daoapiStands.getAll(id);
        if (tags!= null){
            Log.d("ListContainerController", tags.toString());
            return (ArrayList<Entity>) tags;
        }else{
            view.onDownload();
            daoapiStands.downloadData(id);
            return new ArrayList<>();
        }
    }

    public ArrayList<Entity> getAllBusiness(String id) throws NoInternetException {
        ArrayList<? extends Entity> tags = (ArrayList<? extends Entity>) daoapiBusiness.getAll(id);
        if (tags!= null){
            Log.d("ListContainerController", tags.toString());
            return (ArrayList<Entity>) tags;
        }else{
            view.onDownload();
            daoapiBusiness.downloadData(id);
            return new ArrayList<>();
        }
    }

    public ArrayList<Entity> getAllProducts(String id) throws NoInternetException {

        ArrayList<? extends Entity> tags = (ArrayList<? extends Entity>) daoapiProducts.getAll(id);
        if (tags!= null){
            Log.d("ListContainerController", tags.toString());
            return (ArrayList<Entity>) tags;
        }else{
            view.onDownload();
            daoapiProducts.downloadData(id);
            return new ArrayList<>();
        }
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
