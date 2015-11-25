package com.standconnect.Controllers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.standconnect.Models.Beacon;
import com.standconnect.Utils.Util;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Marc on 25/11/15.
 */
public class BeaconScannerController {

    private static String TAG = "BLEScanner";

    private BluetoothAdapter mBluetoothAdapter;
    private final int REQUEST_ENABLE_BT = 1;

    private Handler scanhandler = null;

    final String KEY_BEACON_ID = "ID", KEY_RSSI = "RSSI";

    private Activity activity;

    private ArrayList<Beacon> standBeacons;


    public BeaconScannerController(Activity act){
        this.activity = act;

        final BluetoothManager mBluetoothManager = (BluetoothManager) act.getSystemService(act.BLUETOOTH_SERVICE);

        mBluetoothAdapter = mBluetoothManager.getAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(act, "Device not support Bluetooth", Toast.LENGTH_LONG);
        }

        scanhandler = new Handler();


    }

    public void startScanner(ArrayList<Beacon> beacons){

        standBeacons=beacons;
        scanRunable.run();
    }

    public void stopScanner(){
        stopscanRunable.run();
    }


    final Runnable scanRunable  = new Runnable() {
        @Override
        public void run() {

            if (!mBluetoothAdapter.isEnabled() ) {

                Intent mIntentOpenBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                activity.startActivityForResult(mIntentOpenBT, REQUEST_ENABLE_BT);

                Log.d(TAG, "enable Bluetooth");

            }
                mBluetoothAdapter.startLeScan(mLeScanCallback);

                Log.d(TAG, "start Le scan");

        }
    };

    final Runnable stopscanRunable = new Runnable() {
        @Override
        public void run() {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);

            scanhandler.postDelayed(scanRunable, 1000);
        }

    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {

                // get bluetooth device
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Log.d(TAG, "device name: " + device.getName());


            }
        }

    };

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            Log.d(TAG,"enter Scan callback");
            int startByte = 2;
            boolean patternFound = false;
            String uuid = null;
            int major = 0 , minor = 0;
            int Txpower = 0;

            /* Because advertisement packet
                | 0x02 | 0x15     | - - - - - - - - - - - - - - - - - - - | - - - - | - - - - | - - - - |
                |  ID  | Data Len |    uuid                               |   major |  minor  | Tx power|
                0      1          2                                      18        20        22        24
            example :
                02 | 15 | E2 0A 39 F4 73 F5 4B C4 A1 2F 17 D1 AD 07 A9 61 | 00 00 | 00 00 | C8
             */
            while (startByte <= 5) {
                if (    ((int) scanRecord[startByte + 2] & 0xff) == 0x02 && //Identifies an iBeacon
                        ((int) scanRecord[startByte + 3] & 0xff) == 0x15) { //Identifies correct data length
                    patternFound = true;
                    break;
                }
                startByte++;
            }

            if (patternFound) {
                //Convert to hex String
                byte[] uuidBytes = new byte[16];
                System.arraycopy(scanRecord, startByte+4, uuidBytes, 0, 16);
                String hexString = Util.bytesToHex(uuidBytes);

                //Here is your UUID
                uuid = String.format("%s-%s-%s-%s-%s",hexString.substring(0,8),hexString.substring(8,12),hexString.substring(12,16),hexString.substring(16,20),hexString.substring(20,32));

                //Here is your Major value
                major = (scanRecord[startByte+20] & 0xff) * 0x100 + (scanRecord[startByte+21] & 0xff);

                //Here is your Minor value
                minor = (scanRecord[startByte+22] & 0xff) * 0x100 + (scanRecord[startByte+23] & 0xff);

                Txpower = scanRecord[startByte+ 24];
            }

            Beacon beacon = new Beacon(uuid,device.getName(),major,minor,rssi,device.getAddress());

            Log.i("BeaconScanerController", "NEW!!");
            Log.d(TAG, "Device name: " + beacon.getName() + " UUID: " + beacon.getUUID() + "  Major: " + beacon.getMajor() + " Minor: " + beacon.getMinor() + " rssi: " + beacon.getRssi() + " power: " + Txpower + " mac:" + device.getAddress());

            if (standBeacons.contains(beacon) && rssi >  -70){
                Log.i("StandBeacon",device.getAddress()+" ->>>" + rssi);
            }



            String ID_item = "Device name: " + beacon.getName() + "\nUUID: " + beacon.getUUID() + "\nMajor: " + beacon.getMajor() + "\nMinor: " + beacon.getMinor();

            String RSSI_item = "rssi: " + rssi;

            HashMap<String,String> hashitem = new HashMap<String,String>();

            hashitem.put(KEY_BEACON_ID, ID_item);
            hashitem.put(KEY_RSSI, RSSI_item);

            double Distance = Util.calucateDistance(rssi,Txpower);
            Log.d(TAG, "Distance: " + Distance);

/*
            if (listitems == null) {
                listitems.add(hashitem);
            } else {

                // check if listtime had have item, then update the rssi.
                Iterator it = listitems.iterator();
                HashMap savevalue = null;

                boolean getValue = false;
                while (it.hasNext()){
                    savevalue=(HashMap<String, String>) it.next();
                    if (savevalue.get(KEY_BEACON_ID).equals(ID_item)){
                        savevalue.put(KEY_RSSI,RSSI_item);
                        Log.d(TAG, "update " + ID_item + " rssi: " + RSSI_item);
                        getValue = true;
                    }
                }
                if (!getValue) {
                    listitems.add(hashitem);
                }

            }
            mAdapter.notifyDataSetChanged();
*/
        }

    };

}
