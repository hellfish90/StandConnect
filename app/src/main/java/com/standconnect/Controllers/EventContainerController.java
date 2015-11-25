package com.standconnect.Controllers;

import android.app.Activity;

import com.standconnect.Models.Beacon;
import com.standconnect.Models.Entity;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;

/**
 * Created by Marc on 11/11/15.
 */
public class EventContainerController {

    private boolean isScanning = false;

    BeaconScannerController beaconScannerController;

    public EventContainerController(Activity act){
        beaconScannerController = new BeaconScannerController(act);
    }

    public ArrayList<Entity> getAllBusiness(String id){

        return DummyContent.ITEM_BUSINESS_DUMMY;
    }

    public ArrayList<Entity> getAllProducts(String id){
        return DummyContent.ITEM_PRODUCT_DUMMY;
    }

    public ArrayList<Entity> getAllTags(String id){
        return DummyContent.ITEM_TAGS_DUMMY;
    }

    public void scan(ArrayList<Beacon> beacons){
        isScanning = true;
        beaconScannerController.startScanner(beacons);
    }

    public void stopScan(){
        isScanning = false;
        beaconScannerController.stopScanner();
    }

    public boolean isScan(){
        return isScanning;
    }



}
