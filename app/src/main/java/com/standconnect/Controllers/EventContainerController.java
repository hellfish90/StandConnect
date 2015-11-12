package com.standconnect.Controllers;

import com.standconnect.Models.Entity;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;

/**
 * Created by Marc on 11/11/15.
 */
public class EventContainerController {

    private boolean isScanning = false;

    public ArrayList<Entity> getAllBusiness(String id){

        return DummyContent.ITEM_BUSINESS_DUMMY;
    }

    public ArrayList<Entity> getAllProducts(String id){
        return DummyContent.ITEM_PRODUCT_DUMMY;
    }

    public ArrayList<Entity> getAllTags(String id){
        return DummyContent.ITEM_TAGS_DUMMY;
    }

    public void scan(){
        isScanning = true;
    }

    public void stopScan(){
        isScanning = false;
    }

    public boolean isScan(){
        return isScanning;
    }



}
