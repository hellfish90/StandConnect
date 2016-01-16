package com.standconnect.Controllers;

import android.app.Activity;
import android.util.Log;

import com.standconnect.DAO.DAOAPTags;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.Beacon;
import com.standconnect.Models.Entity;
import com.standconnect.Models.Event;
import com.standconnect.Models.Tag;
import com.standconnect.Utils.OnRefreshData;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 11/11/15.
 */
public class EventContainerController {

    private boolean isScanning = false;

    BeaconScannerController beaconScannerController;

    public EventContainerController(Activity act){
        beaconScannerController = new BeaconScannerController(act);
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
