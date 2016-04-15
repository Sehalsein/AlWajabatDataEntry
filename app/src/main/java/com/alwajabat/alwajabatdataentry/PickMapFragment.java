package com.alwajabat.alwajabatdataentry;

import android.app.DialogFragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.alwajabat.alwajabatdataentry.R;


import java.util.List;

/**
 * Created by deezdroid on 15/04/16.
 */
public class PickMapFragment extends DialogFragment implements MapWrapperLayout.OnDragListener{

    private GoogleMap googleMap;
    private CustomMapFragment mCustomMapFragment;

    private View mMarkerParentView;
    private ImageView mMarkerImageView;

    private int imageParentWidth = -1;
    private int imageParentHeight = -1;
    private int imageHeight = -1;
    private int centerX = -1;
    private int centerY = -1;

    private LatLng latLng;

    private Button setButton;

    private TextView mLocationTextView;
    private OnLocationSet onLocationSet;

    public PickMapFragment(OnLocationSet onLocationSet) {
        this.onLocationSet = onLocationSet;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment_layout, container, false);
        getDialog().setTitle("Point to location");


        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        mLocationTextView = (TextView) rootView.findViewById(R.id.location_text_view);
        mMarkerParentView = rootView.findViewById(R.id.marker_view_incl);
        mMarkerImageView = (ImageView) rootView.findViewById(R.id.marker_icon_view);
        setButton = (Button) rootView.findViewById(R.id.setLocation);

        setButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLocationSet.success(latLng);
                dismiss();
            }
        });

        return rootView;
    }




    private void initilizeMap() {
        if (googleMap == null) {
            mCustomMapFragment = ((CustomMapFragment) getFragmentManager()
                    .findFragmentById(R.id.map));

            mCustomMapFragment.setOnDragListener(this);
            googleMap = mCustomMapFragment.getMap();
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        googleMap.setBuildingsEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(26.0667,50.5577),
                11);
        googleMap.animateCamera(cameraUpdate);

    }

    @Override
    public void onDrag(MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Projection projection = (googleMap != null && googleMap
                    .getProjection() != null) ? googleMap.getProjection()
                    : null;
            //
            if (projection != null) {
                LatLng centerLatLng = projection.fromScreenLocation(new Point(
                        centerX, centerY));

                updateLocation(centerLatLng);
                latLng = centerLatLng;
            }
        }
    }

    private void updateLocation(LatLng centerLatLng) {
        if (centerLatLng != null) {
            Geocoder geocoder = new Geocoder(getActivity(),
                    Locale.getDefault());

            List<Address> addresses = new ArrayList<>();
            try {
                addresses = geocoder.getFromLocation(centerLatLng.latitude,
                        centerLatLng.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {

                String addressIndex0 = (addresses.get(0).getAddressLine(0) != null) ? addresses
                        .get(0).getAddressLine(0) : null;
                String addressIndex1 = (addresses.get(0).getAddressLine(1) != null) ? addresses
                        .get(0).getAddressLine(1) : null;
                String addressIndex2 = (addresses.get(0).getAddressLine(2) != null) ? addresses
                        .get(0).getAddressLine(2) : null;
                String addressIndex3 = (addresses.get(0).getAddressLine(3) != null) ? addresses
                        .get(0).getAddressLine(3) : null;

                String completeAddress = addressIndex0 + "," + addressIndex1;

                if (addressIndex2 != null) {
                    completeAddress += "," + addressIndex2;
                }
                if (addressIndex3 != null) {
                    completeAddress += "," + addressIndex3;
                }
                if (completeAddress != null) {
                    mLocationTextView.setText(completeAddress);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }

}
