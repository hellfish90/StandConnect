package com.standconnect.DAO;

import com.standconnect.Utils.OnRefreshData;

import java.util.List;


/**
 * Created by Marc on 10/9/15.
 */
public abstract class DAOAPI<T> {

    protected OnRefreshData view;

    public DAOAPI(OnRefreshData view){
        super();

        this.view = view;

    }

    public abstract void downloadData(String eventID) throws NoInternetException;
    public abstract void downloadDetailData(String towns) throws NoInternetException;
    public abstract List<T> getAll(String eventID);
    public abstract T getDetail(int id);
}