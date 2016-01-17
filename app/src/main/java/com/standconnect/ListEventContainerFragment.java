package com.standconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.standconnect.Controllers.ListContainerController;
import com.standconnect.DAO.NoInternetException;
import com.standconnect.Models.DataForScanner;
import com.standconnect.Models.Entity;
import com.standconnect.Models.ScannerData;
import com.standconnect.Models.Tag;
import com.standconnect.Utils.DataType;
import com.standconnect.Utils.OnRefreshData;
import com.standconnect.dummy.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ListEventContainerFragment extends Fragment implements AbsListView.OnItemClickListener, OnRefreshData {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Entity> dataEventContentList;

    DataType dataType;

    String evetnId;

    ListContainerController listContainerController;

    public static final String ARG_EVENT_CONTENT_LIST = "EVENT_LIST_CONTENT";

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private EntityListAdapter mAdapter;


    private ArrayList<String> tagsPressed;

    // TODO: Rename and change types of parameters
    public static ListEventContainerFragment newInstance(String param1, String param2, ArrayList<DummyItem> dataList) {
        ListEventContainerFragment fragment = new ListEventContainerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putSerializable(ARG_EVENT_CONTENT_LIST, dataList);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListEventContainerFragment() {

        listContainerController = new ListContainerController(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            dataType = (DataType) getArguments().get(ARG_EVENT_CONTENT_LIST);

            evetnId= (String) getArguments().get("eventID");
        }
        tagsPressed = new ArrayList<>();
        dataEventContentList = getDataByType();

        // TODO: Change Adapter to display your content
        mAdapter = new EntityListAdapter(getActivity(),dataEventContentList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listcontentevent, container, false);

        TextView nameList = (TextView) view.findViewById(R.id.textView_list_name);

        switch (dataType){
            case Business:
                nameList.setText("Business");
                break;
            case TAGS:
                nameList.setText("TAGS");
                break;
            case Product:
                nameList.setText("Products");
                break;
            case Stand:
                nameList.setText("Stands");
                break;
        }



        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(dataEventContentList.get(position).getName());
        }

                if (dataType == DataType.TAGS){
                    if (tagsPressed.contains(String.valueOf(position))){
                        tagsPressed.remove(String.valueOf(position));




                    }else{
                        Tag tag = (Tag) dataEventContentList.get(position);

                        Log.d("FILTERBUSSINES",tag.toString());

                        tagsPressed.add(String.valueOf(position));

                        ArrayList<DataForScanner> datacontainsTag = new ArrayList<>();

                        Log.d("FILTERBUSSINES",EventContainer.dataforScanner.toString());

                        for (DataForScanner datafSC: EventContainer.dataforScanner){
                            if (datafSC.getTags().contains(tag)){
                                datacontainsTag.add(datafSC);
                            }
                        }

                        Log.d("FILTERBUSSINES",datacontainsTag.toString());
                        Log.d("FILTERBUSSINES","_________________________");

                        ArrayList<ScannerData> scannerdata =new ArrayList<>();

                        for (DataForScanner df: datacontainsTag){
                            ScannerData sc = new ScannerData();
                            sc.setTags(df.getTags());
                            sc.setBeacon(df.getBeacons().get(0));
                            sc.setStand(df.getStands().get(0));
                            scannerdata.add(sc);

                        }

                        EventContainer.scannerData.addAll(scannerdata);


                    }
                }else{
                    Entity dataSelected = dataEventContentList.get(position);

                    Bundle args = new Bundle();

                    args.putSerializable(DetailActivityContainer.ARG_DETAIL_CONTENT_ENTITY,dataSelected);

                    Intent i =new Intent(getActivity(),DetailActivityContainer.class);
                    i.putExtras(args);

                    startActivity(i);
                }

    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onDownload() {
        //loadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void dataDownloaded() {

        Log.d("ListEventsF", "onDownloaded");
        //loadingBar.setVisibility(View.INVISIBLE);

        List<Entity> eventsArray = getDataByType();
        refreshList(eventsArray);


    }

    public void refreshList(List<Entity> eventsArray){

        mAdapter.notifyDataSetInvalidated();
        mAdapter.clear();
        dataEventContentList= (ArrayList<Entity>) eventsArray;
        Log.d("ListF", "Refresh Data: " + eventsArray.toString());
        mAdapter.addAll(dataEventContentList);
        mAdapter.notifyDataSetChanged();
        mListView.setAdapter(mAdapter);


    }

    public List<Entity> getDataByType() {
        List<Entity> dataByType = null;
        try {
        switch (dataType){
            case Business:
                dataByType = listContainerController.getAllBusiness(evetnId);
                break;
            case TAGS:
                dataByType = listContainerController.getAllTags(evetnId);
                break;
            case Product:
                dataByType = listContainerController.getAllProducts(evetnId);
                break;
            case Stand:
                dataByType = listContainerController.getAllStands(evetnId);
                break;

        }

        } catch (NoInternetException e) {

            Toast.makeText(getActivity(), "No internetConnection",
                    Toast.LENGTH_LONG).show();
            return new ArrayList<>();
        }

        return dataByType;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }


    private class EntityListAdapter extends ArrayAdapter<Entity> {
        public EntityListAdapter(Context context, List<Entity> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Entity event = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row_generic_list, parent, false);
            }
            // Lookup view for data population
            TextView eventName = (TextView) convertView.findViewById(R.id.textview_item_event_list_row);

            // Populate the data into the template view using the data object
            eventName.setText(event.getName()+" "+event.getName());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
