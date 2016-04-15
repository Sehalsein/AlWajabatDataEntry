package com.alwajabat.alwajabatdataentry;


import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alwajabat.alwajabatdataentry.models.LocationModel;
import com.google.android.gms.maps.model.LatLng;


public class PrimaryDetailsFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private Button vLocate;
    private Spinner vAreaName;
    private EditText vLongitude, vLatitude, vRestaurantName, vHotelName, vMallName, vAddress, vEmail, vMobile, vWebsite;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Validate validate;


    public PrimaryDetailsFragment() {
    }

    public void getBasicDetails(){

    }



    public PrimaryDetailsFragment(Validate validate) {
        this.validate = validate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_basic_details, container, false);

        vLocate = (Button) layout.findViewById(R.id.btn_locate);
        vLatitude = (EditText) layout.findViewById(R.id.et_latitude);
        vLongitude = (EditText) layout.findViewById(R.id.et_longitude);
        vRestaurantName = (EditText) layout.findViewById(R.id.et_restaurant_name);
        vHotelName = (EditText) layout.findViewById(R.id.et_hotel_name);
        vMallName = (EditText) layout.findViewById(R.id.et_mall_name);
        vAddress = (EditText) layout.findViewById(R.id.et_address);
        vEmail = (EditText) layout.findViewById(R.id.et_email);
        vMobile = (EditText) layout.findViewById(R.id.et_mobile_no);
        vAreaName = (Spinner) layout.findViewById(R.id.spin_area_name);
        vWebsite = (EditText) layout.findViewById(R.id.et_website);


        vRestaurantName.addTextChangedListener(this);
        vHotelName.addTextChangedListener(this);
        vAddress.addTextChangedListener(this);
        vMobile.addTextChangedListener(this);
        vLatitude.addTextChangedListener(this);
        vLongitude.addTextChangedListener(this);

              vLatitude.setEnabled(false);
              vLongitude.setEnabled(false);


        vLocate.setOnClickListener(this);


        return layout;
    }

    private void validate() {

        String mRestaurantName = null, mLatitude = null, mLongitude = null, mAddress = null, mMobile = null, mEmail = null, mAreaName = null;

        try {
            //LATITUDE
            if (isEmpty(vLatitude)) {
                //vLatitude.setError("No Latitude");
                mLatitude = null;
            } else {
                mLatitude = vLatitude.getText().toString();
            }

            //LONGITUDE
            if (isEmpty(vLongitude)) {
                //vLongitude.setError("No Longitude");
                mLongitude = null;
            } else {
                mLongitude = vLongitude.getText().toString();
            }

            //RESTAURANT NAME
            if (isEmpty(vRestaurantName)) {
                vRestaurantName.setError("Enter restaurant name");
                mRestaurantName = null;
            } else {
                mRestaurantName = vRestaurantName.getText().toString();
            }

            //ADDRESS
            if (isEmpty(vAddress)) {
                vAddress.setError("Enter address");
                mAddress = null;
            } else {
                mAddress = vAddress.getText().toString();
            }

            //MOBILE NUMBER
            if (isEmpty(vMobile)) {
                vMobile.setError("Enter mobile no");
                mMobile = null;
            } else if (vMobile.getText().length() != 8) {
                vMobile.setError("Enter valid mobile no");
                mMobile = null;
            } else {
                mMobile = vMobile.getText().toString();
            }

            //EMAIL ID
            if (!isEmpty(vEmail)) {
                if (!isValidEmail(vEmail.getText())) {
                    vEmail.setError("Enter valid emailid");
                    mEmail = null;
                } else {
                    mEmail = vEmail.getText().toString();
                }
            } else if (isEmpty(vEmail)) {
                mEmail = vEmail.getText().toString();
            }


            //AREA NAME
            /*if (isEmpty(vAreaName)) {
                //  vAreaName.setError("Enter area name");
                mAreaName = null;
            } else {
                mAreaName = vMobile.getText().toString();
            }*/


        } catch (NullPointerException e) {
            Log.d("BasicDetailFragments", "Validate - null " + e.getMessage());
        }
        if (mLatitude != null &&
              mLongitude != null &&
                mRestaurantName != null &&
                        mAddress != null &&
                        mEmail != null &&
                        // mAreaName != null &&
                        mMobile != null
                ) {

            validate.onSuccess();
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isEmpty(EditText etText) {
        if (etText == null) {
            return false;
        } else if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_locate:
              //Open the dialog fragment here


                FragmentManager fm = getActivity().getFragmentManager();
                PickMapFragment mapFragment =  new PickMapFragment(new OnLocationSet(){

                    @Override
                    public void success(LatLng model) {
                        vLatitude.setText(model.latitude+"");
                        vLongitude.setText(model.longitude+"");

                        Log.e("LOCATION", model.latitude+","+model.longitude);
                    }
                });
                mapFragment.show(fm, "Pick Location");

            break;
            case R.id.btn_validate:
                validate();
                break;


        }

    }



    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(getActivity(), "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted, Now you can access location data.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validate.onCancel();
        validate();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
