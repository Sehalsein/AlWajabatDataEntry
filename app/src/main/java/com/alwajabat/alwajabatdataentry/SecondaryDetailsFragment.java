package com.alwajabat.alwajabatdataentry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondaryDetailsFragment extends Fragment implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {


    CheckBox vDelivery;
    EditText vMinOrder, vDeliveryCharge;
    TextView vOpenTime, vCloseTime;
    Button vValidate;

    int openTimeHours = -1, openTimeMinutes = -1;
    int closeTimeHours = -1, closeTimeMinutes = -1;


    public SecondaryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_restaurant_details, container, false);

        String[] arrayCusine = {"Arabic", "Indian", "Mexican", "Chineese", "Fast Food", "Italian", "labaneese", "Continantal", "Spanish"};
        MultiSelectionSpinner vCusine = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_cusine);
        vCusine.setItems(arrayCusine);
        vCusine.setListener(this);

        String[] arrayPayment = {"Debit Card", "Credit Card", "Cash", "Cheque"};
        MultiSelectionSpinner vPayment = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_payment_method);
        vPayment.setItems(arrayPayment);
        vPayment.setListener(this);

        String[] arrayType = {"Breakfast", "Lunch", "Snacks", "Dinner"};
        MultiSelectionSpinner vType = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_restaurant_type);
        vType.setItems(arrayType);
        vType.setListener(this);

        String[] arrayAmmenity = {"Halal", "WiFi", "Outdoor Seating", "Parking", "Valet Parking", "Smoking Area", "Indoor Smoking",
                "Play Area", "Take Away", "Wheel Chair Accessible", "Alcohol", "Sports Screen"};
        MultiSelectionSpinner vAmmenity = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_amenity);
        vAmmenity.setItems(arrayAmmenity);
        vAmmenity.setListener(this);

        vDelivery = (CheckBox) layout.findViewById(R.id.cb_delivery);
        vMinOrder = (EditText) layout.findViewById(R.id.et_min_order);
        vDeliveryCharge = (EditText) layout.findViewById(R.id.et_delivery_charge);
        vOpenTime = (TextView) layout.findViewById(R.id.tv_open);
        vCloseTime = (TextView) layout.findViewById(R.id.tv_close);
        vValidate = (Button) layout.findViewById(R.id.btn_validate);

        vMinOrder.setEnabled(false);
        vDeliveryCharge.setEnabled(false);
        vDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vMinOrder.setEnabled(true);
                    vDeliveryCharge.setEnabled(true);
                } else {
                    vMinOrder.setEnabled(false);
                    vDeliveryCharge.setEnabled(false);
                }
            }
        });

        vOpenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                                Toast.makeText(getActivity(), "Restaurant opens at " + hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();
                                vOpenTime.setText(hourOfDay + " : " + minute);
                            }
                        })
                        .setStartTime(10, 10)
                        .setDoneText("Set Time")
                        .setCancelText("Cancel")
                        .setThemeDark(false);
                rtpd.show(getActivity().getSupportFragmentManager(), "Pick Opening Time");

            }
        });

        vCloseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                                Toast.makeText(getActivity(), "Restaurant closes at " + hourOfDay + " : " + minute, Toast.LENGTH_SHORT).show();
                                vCloseTime.setText(hourOfDay + " : " + minute);
                            }
                        })
                        .setStartTime(10, 10)
                        .setDoneText("Set Time")
                        .setCancelText("Cancel")
                        .setThemeDark(false);
                rtpd.show(getActivity().getSupportFragmentManager(), "Pick Closing Time");

            }
        });


        return layout;
    }

    private void validate() {

        if (vDelivery.isChecked()) {
            if (isEmpty(vDeliveryCharge)) {
                vDeliveryCharge.setError("ASD");
            }
            if (isEmpty(vMinOrder)) {
                vMinOrder.setError("ASD");
            }
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
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

    }
}
