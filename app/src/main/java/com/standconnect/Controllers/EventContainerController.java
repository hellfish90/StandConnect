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

        return DummyContent.ITEMS;
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
