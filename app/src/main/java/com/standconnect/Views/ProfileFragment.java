package com.standconnect.Views;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.standconnect.Models.Visitor;
import com.standconnect.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_VISITOR = "VISITOR";

    // TODO: Rename and change types of parameters
    private Visitor visitor;

    private EditText name,age,gender,city,zip,address,email;
    private Button saveButton;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(Visitor argVisitor) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_VISITOR, argVisitor);

        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.visitor = (Visitor) getArguments().getSerializable(ARG_VISITOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (EditText) view.findViewById(R.id.editText_name_profile);
        age = (EditText) view.findViewById(R.id.editText_age_profile);
        gender = (EditText) view.findViewById(R.id.editText_gender_profile);
        city = (EditText) view.findViewById(R.id.editText_city_profile);
        zip = (EditText) view.findViewById(R.id.editText_zip_profile);
        address = (EditText) view.findViewById(R.id.editText_address_profile);
        email = (EditText) view.findViewById(R.id.editText_email_profile);
        saveButton = (Button) view.findViewById(R.id.button_save_profile);
        loadSavedPreferences();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("ProfileFragment", name.getText().toString());
                Log.d("ProfileFragment", age.getText().toString());
                Log.d("ProfileFragment", gender.getText().toString());
                Log.d("ProfileFragment", city.getText().toString());
                Log.d("ProfileFragment", zip.getText().toString());
                Log.d("ProfileFragment", address.getText().toString());
                Log.d("ProfileFragment", email.getText().toString());

                savePreferences("StoredName", name.getText().toString());
                savePreferences("StoredGender", gender.getText().toString());

            }
        });

        return view;
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences(ARG_VISITOR, 0);
        String nombre = sharedPreferences.getString("StoredName", "");
        String edad = sharedPreferences.getString("StoredAge", "");
        String genero = sharedPreferences.getString("StoredGender", "");
        String ciudad = sharedPreferences.getString("StoredCity", "");
        String CP = sharedPreferences.getString("StoredZIP", "");
        String direccion = sharedPreferences.getString("StoredAddress", "");
        String correo_e = sharedPreferences.getString("StoredEMail", "");
        name.setText(nombre);
        age.setText(edad);
        gender.setText(genero);
        city.setText(ciudad);
        zip.setText(CP);
        address.setText(direccion);
        email.setText(correo_e);
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ARG_VISITOR, 0);
        Editor editor = sharedPreferences.edit();
        editor.putString("StoredName", name.getText().toString());
        editor.putString("StoredAge", age.getText().toString());
        editor.putString("StoredGender", gender.getText().toString());
        editor.putString("StoredCity", city.getText().toString());
        editor.putString("StoredZIP", zip.getText().toString());
        editor.putString("StoredAddress", address.getText().toString());
        editor.putString("StoredEMail", email.getText().toString());

        editor.commit();
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
