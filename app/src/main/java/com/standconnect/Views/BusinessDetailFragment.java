package com.standconnect.Views;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.standconnect.Models.Business;
import com.standconnect.R;
import com.standconnect.Utils.DownloadImageTask;

import java.io.InputStream;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BusinessDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BusinessDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_BUSINESS = "BUSINESS";

    TextView name, description,contact,phone,adress;
    ImageView image;

    private DownloadImageTask taskLoadImage;

    // TODO: Rename and change types of parameters
    private Business business;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BusinessDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessDetailFragment newInstance(Business argBusiness) {
        BusinessDetailFragment fragment = new BusinessDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BUSINESS, argBusiness);
        fragment.setArguments(args);
        return fragment;
    }

    public BusinessDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.business = (Business) getArguments().getSerializable(ARG_BUSINESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_detail_business, container, false);

        name = (TextView) view.findViewById(R.id.textview_name_detail_business);
        description = (TextView) view.findViewById(R.id.textview_description_detail_business);
        contact = (TextView) view.findViewById(R.id.textview_contact_detail_business);
        phone = (TextView) view.findViewById(R.id.textview_phone_detail_business);
        adress = (TextView) view.findViewById(R.id.textview_adress_detail_business);
        image = (ImageView) view.findViewById(R.id.imageview_detail_business);

        Log.d("BusinessDetailFragment", business.toString());

        name.setText(business.getName());
        description.setText(business.getDescription());
        contact.setText(business.getContact());
        phone.setText(business.getPhone());
        adress.setText(business.getAddress());

        taskLoadImage = new DownloadImageTask();
        taskLoadImage.loadImageView(image);
        taskLoadImage.execute(business.getImage());

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
