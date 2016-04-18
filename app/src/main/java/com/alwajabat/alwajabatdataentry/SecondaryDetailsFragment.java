package com.alwajabat.alwajabatdataentry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alwajabat.alwajabatdataentry.model.AmmenitityModel;
import com.alwajabat.alwajabatdataentry.model.CuisineModel;
import com.alwajabat.alwajabatdataentry.model.PaymentModel;
import com.alwajabat.alwajabatdataentry.model.SecondaryModel;
import com.alwajabat.alwajabatdataentry.model.TypeModel;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondaryDetailsFragment extends Fragment implements MultiSelectionSpinner.OnMultipleItemsSelectedListener, TextWatcher {


    private CheckBox vDelivery;
    private EditText vMinOrder, vDeliveryCharge, vCostForTwo;
    private TextView vOpenTime, vCloseTime;

    private MultiSelectionSpinner vCuisine, vPayment,vAmmenity,vType;
    private TextView errCuisine, errType, errPayment, errTime;
    private Validate validate;



    int openTimeHours = -1, openTimeMinutes = -1;
    int closeTimeHours = -1, closeTimeMinutes = -1;

    private SecondaryModel model ;


    public SecondaryDetailsFragment(Validate validate) {
        this.validate = validate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_restaurant_details, container, false);

        String[] arrayCusine = {"Arabic", "Indian", "Mexican", "Chineese", "Fast Food", "Italian", "labaneese", "Continantal", "Spanish"};
        vCuisine = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_cusine);
        vCuisine.setItems(arrayCusine);
        vCuisine.setListener(this);

        String[] arrayPayment = {"Debit Card", "Credit Card", "Cash", "Cheque"};
         vPayment = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_payment_method);
        vPayment.setItems(arrayPayment);
        vPayment.setSelection(2);
        vPayment.setListener(this);

        String[] arrayType = {"Breakfast", "Lunch", "Snacks", "Dinner"};
         vType = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_restaurant_type);
        vType.setItems(arrayType);
        vType.setListener(this);

        String[] arrayAmmenity = {"Halal", "WiFi", "Outdoor Seating", "Parking", "Valet Parking", "Smoking Area", "Indoor Smoking",
                "Play Area", "Take Away", "Wheel Chair Accessible", "Alcohol", "Sports Screen"};
         vAmmenity = (MultiSelectionSpinner) layout.findViewById(R.id.spinner_amenity);
        vAmmenity.setItems(arrayAmmenity);
        vAmmenity.setListener(this);

        vDelivery = (CheckBox) layout.findViewById(R.id.cb_delivery);
        vMinOrder = (EditText) layout.findViewById(R.id.et_min_order);
        vDeliveryCharge = (EditText) layout.findViewById(R.id.et_delivery_charge);
        vCostForTwo = (EditText) layout.findViewById(R.id.et_cost_for_two);
        vOpenTime = (TextView) layout.findViewById(R.id.tv_open);
        vCloseTime = (TextView) layout.findViewById(R.id.tv_close);

        errCuisine = (TextView) layout.findViewById(R.id.err_cuisine);
        errType = (TextView) layout.findViewById(R.id.err_type);
        errPayment = (TextView) layout.findViewById(R.id.err_payment);
        errTime = (TextView) layout.findViewById(R.id.err_time);

        vCostForTwo.addTextChangedListener(this);
        vMinOrder.addTextChangedListener(this);
        vDeliveryCharge.addTextChangedListener(this);

        vMinOrder.setEnabled(false);
        vDeliveryCharge.setEnabled(false);
        vDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validate();
                if (isChecked) {
                    vMinOrder.setEnabled(true);
                    vDeliveryCharge.setEnabled(true);
                } else {
                    vMinOrder.setEnabled(false);
                    vDeliveryCharge.setEnabled(false);
                    vMinOrder.setError(null);
                    vDeliveryCharge.setError(null);
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

                                vOpenTime.setText(hourOfDay + " : " + minute);
                                openTimeHours = hourOfDay;
                                openTimeMinutes = minute;
                                validate();
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

                                vCloseTime.setText(hourOfDay + " : " + minute);
                                closeTimeHours = hourOfDay;
                                closeTimeMinutes = minute;
                                validate();
                            }
                        })
                        .setStartTime(10, 10)
                        .setDoneText("Set Time")
                        .setCancelText("Cancel")
                        .setThemeDark(false);
                rtpd.show(getActivity().getSupportFragmentManager(), "Pick Closing Time");

            }
        });

        validate();

        return layout;
    }

    private void validate() {

        boolean cusineFlag = false;
        boolean paymentFlag = false;
        boolean restaurantFlag = false;
        boolean costForTwoFlag = false;
        boolean timingFlag = false;
        boolean deliveryFlag = false;



        if (vCuisine.getSelectedIndices().size()<=0){
            errCuisine.setVisibility(View.VISIBLE);

        }else{
            errCuisine.setVisibility(View.GONE);
            cusineFlag=true;
        }

        if (vPayment.getSelectedIndices().size()<=0){
            errPayment.setVisibility(View.VISIBLE);
        }else{
            errPayment.setVisibility(View.GONE);
            paymentFlag = true;

        }

        if (vType.getSelectedIndices().size()<=0){
            errType.setVisibility(View.VISIBLE);
        }else{
           errType.setVisibility(View.GONE);
            restaurantFlag = true;
        }


        if (isEmpty(vCostForTwo)) {
            vCostForTwo.setError("Enter Average Cost for Two");

        } else {
            costForTwoFlag = true;
        }

        if(openTimeMinutes <0 || openTimeHours < 0 || closeTimeMinutes < 0 || closeTimeHours<0){
            errTime.setVisibility(View.VISIBLE);
        }else {
            errTime.setVisibility(View.GONE);
            timingFlag = true;
        }

        if(vDelivery.isChecked()){
            boolean orderFlag = false, chargeFlag = false;
            if(isEmpty(vMinOrder)){
                vMinOrder.setError("Minimum Order For Delivery");
            }
            else{
                orderFlag = true;
                vMinOrder.setError(null);
            }

            if(isEmpty(vDeliveryCharge)){
                vDeliveryCharge.setError("Additional Delivery Charges");
            }
            else{
                chargeFlag = true;
                vDeliveryCharge.setError(null);
            }

            if(orderFlag && chargeFlag){
                deliveryFlag = true;
            }else {
                deliveryFlag = false;
            }


        }else {
            deliveryFlag = true;
        }

        if(cusineFlag && paymentFlag && restaurantFlag && costForTwoFlag &&
                timingFlag && deliveryFlag){
            validate.onSuccess();

            makeModel();


        }else {
            validate.onCancel();
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
        validate();
    }

    @Override
    public void selectedStrings(List<String> strings) {

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

    private void makeModel(){

        model =  new SecondaryModel();

        //For Cuisine
        if(vCuisine.getSelectedIndices().size()>0)
        for(int i=0; i<vCuisine.getSelectedIndices().size(); i++){
            model.getCuisines().add(i, new CuisineModel(i+"", vCuisine.getSelectedStrings().get(i)));
        }

        //For Payement
        if(vPayment.getSelectedIndices().size()>0)
        for(int i=0; i<vPayment.getSelectedIndices().size(); i++){
            model.getPaymentModes().add(i, new PaymentModel(i+"", vPayment.getSelectedStrings().get(i)));
        }

        //For Ammenities
        if(vAmmenity.getSelectedIndices().size()>0)
        for(int i=0; i<vAmmenity.getSelectedIndices().size(); i++){
            model.getAmmenities().add(i, new AmmenitityModel(i+"", vAmmenity.getSelectedStrings().get(i)));
        }

        //For Type
        if(vType.getSelectedIndices().size()>0)
        for(int i=0; i<vType.getSelectedIndices().size(); i++){
            model.getRestaurantType().add(i, new TypeModel(i+"", vType.getSelectedStrings().get(i)));
        }

        model.setDelivery(vDelivery.isChecked());

        model.setCostForTwo(Integer.parseInt(vCostForTwo.getText().toString()));

        if(vDelivery.isChecked()){
            model.setMinimumOrder(Integer.parseInt(vMinOrder.getText().toString()));
            model.setDeliveryCharge(Integer.parseInt(vDeliveryCharge.getText().toString()));
        }else {
            model.setMinimumOrder(0);
            model.setDeliveryCharge(0);
        }

        model.setOpenHour(openTimeHours);
        model.setCloseHour(closeTimeHours);
        model.setOpenMinute(openTimeMinutes);
        model.setCloseMinute(closeTimeMinutes);


    }

    public SecondaryModel getModel(){
        return model;
    }
}
