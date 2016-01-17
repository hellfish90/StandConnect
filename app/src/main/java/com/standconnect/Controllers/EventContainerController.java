package com.standconnect.Controllers;

import android.app.Activity;
import android.util.Log;

import com.standconnect.DAO.DAOAPIDataScanner;
import com.standconnect.DAO.DAOAPTags;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.Beacon;
import com.standconnect.Models.DataForScanner;
import com.standconnect.Models.Entity;
import com.standconnect.Models.Event;
import com.standconnect.Models.ScannerData;
import com.standconnect.Models.Tag;
import com.standconnect.Utils.OnRefreshData;
import com.standconnect.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 11/11/15.
 */
public class EventContainerController implements OnRefreshData{

    private boolean isScanning = false;

    BeaconScannerController beaconScannerController;
    DAOAPIDataScanner daoapiDataScanner;

    OnRefreshData view;

    public EventContainerController(Activity act,OnRefreshData v){
        beaconScannerController = new BeaconScannerController(act);
        daoapiDataScanner = new DAOAPIDataScanner(this);
        view=v;

    }


    public void scan(List<ScannerData> beacons){
        isScanning = true;
        beaconScannerController.startScanner(beacons,null);
    }

    public void stopScan(){
        isScanning = false;
        beaconScannerController.stopScanner();
    }

    public boolean isScan(){
        return isScanning;
    }


    public List<DataForScanner> getDataForScanner(String id){
        List<DataForScanner> townHallList = daoapiDataScanner.getAll(id);
        if (townHallList!= null){
            Log.d("EventContainerController", "notNull");
            return townHallList;
        }else{
            view.onDownload();
            try {
                daoapiDataScanner.downloadData(id);
            } catch (NoInternetException e) {
                e.printStackTrace();
            }
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
