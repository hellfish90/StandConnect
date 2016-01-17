package com.standconnect.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.standconnect.DetailActivityContainer;
import com.standconnect.Models.Stand;
import com.standconnect.R;
import com.standconnect.Utils.DownloadImageTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StandDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StandDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PRODUCT = "PRODUCT";

    // TODO: Rename and change types of parameters
    private Stand stand;

    private TextView name, number, business;
    private ImageView image;

    private DownloadImageTask taskLoadImage;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ProductDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StandDetailFragment newInstance(Stand argProduct) {
        StandDetailFragment fragment = new StandDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, argProduct);

        fragment.setArguments(args);
        return fragment;
    }

    public StandDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.stand = (Stand) getArguments().getSerializable(ARG_PRODUCT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stand_detail, container, false);

        name = (TextView) view.findViewById(R.id.textview_name_detail_stand);
        number = (TextView) view.findViewById(R.id.textview_number_detail_stand);
        business = (TextView) view.findViewById(R.id.textview_business_detail_stand);
        image = (ImageView) view.findViewById(R.id.imageview_detail_product);

        Log.d("ProductDetailFragment", stand.toString());

        name.setText(stand.getName());
        number.setText(stand.getNumber().toString());

        if (stand.getBusinesses().size()>0){
            business.setText(String.valueOf(stand.getBusinesses().get(0).getName()));



            business.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getActivity(), DetailActivityContainer.class);
                    Bundle extras = new Bundle();

                    extras.putSerializable(DetailActivityContainer.ARG_DETAIL_CONTENT_ENTITY, stand.getBusinesses().get(0));
                    i.putExtras(extras);
                    startActivity(i);
                }
            });



        }else{
            business.setText("No business");
        }


        taskLoadImage = new DownloadImageTask();
        taskLoadImage.loadImageView(image);
        taskLoadImage.execute(stand.getImage());
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        public void onFragmentInteraction(Uri uri);
    }

}
