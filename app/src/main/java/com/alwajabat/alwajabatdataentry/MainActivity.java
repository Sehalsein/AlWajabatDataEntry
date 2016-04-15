package com.alwajabat.alwajabatdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alwajabat.alwajabatdataentry.models.ConfirmationActivity;

import java.util.ArrayList;

import me.drozdzynski.library.steppers.OnCancelAction;
import me.drozdzynski.library.steppers.OnFinishAction;
import me.drozdzynski.library.steppers.SteppersItem;
import me.drozdzynski.library.steppers.SteppersView;


public class MainActivity extends AppCompatActivity {


    public ArrayList<SteppersItem> steps = new ArrayList<>();

    public SteppersItem stepFirst = new SteppersItem();
    public SteppersItem stepTwo = new SteppersItem();

    private PrimaryDetailsFragment primaryDetailsFragment;
    private SecondaryDetailsFragment secondaryDetailsFragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();


    }

    private void initView() {

        SteppersView.Config steppersViewConfig = new SteppersView.Config();
        steppersViewConfig.setOnFinishAction(new OnFinishAction() {
            @Override
            public void onFinish() {


                Toast.makeText(getApplication(), "Finished "+secondaryDetailsFragment.getModel().getRestaurantType().toArray().length, Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("primary_details", primaryDetailsFragment.getBasicDetails());
                intent.putExtra("secondary_details", secondaryDetailsFragment.getModel());
                startActivity(intent);
            }
        });

        steppersViewConfig.setOnCancelAction(new OnCancelAction() {
            @Override
            public void onCancel() {
                // Action when click cancel on one of steps

                Toast.makeText(getApplication(), "Back", Toast.LENGTH_SHORT).show();
            }
        });
        steppersViewConfig.setFragmentManager(getSupportFragmentManager());

        // Setup Support Fragment Manager for fragments in steps

        stepFirst.setLabel("Step 1");
        stepFirst.setSubLabel("Primary Details");

        stepTwo.setLabel("Step 2");
        stepTwo.setSubLabel("Secondary Details");









        Validate validatePrimary = new Validate() {

            @Override
            public void onCancel() {
                stepFirst.setPositiveButtonEnable(false);
            }

            @Override
            public void onSuccess() {
                stepFirst.setPositiveButtonEnable(true);
                Toast.makeText(MainActivity.this, "Validation complete", Toast.LENGTH_SHORT).show();
            }
        };

        Validate validateSecondary = new Validate() {

            @Override
            public void onCancel() {
                stepTwo.setPositiveButtonEnable(false);
            }

            @Override
            public void onSuccess() {
                stepTwo.setPositiveButtonEnable(true);
                Toast.makeText(MainActivity.this, "Secondary Validation complete", Toast.LENGTH_SHORT).show();
            }
        };



        primaryDetailsFragment =  new PrimaryDetailsFragment(validatePrimary);


        secondaryDetailsFragment = new SecondaryDetailsFragment(validateSecondary);
        stepFirst.setFragment(primaryDetailsFragment);
        stepTwo.setFragment(secondaryDetailsFragment);



        stepFirst.setPositiveButtonEnable(false);
        stepTwo.setPositiveButtonEnable(false);


        steps.add(stepFirst);
        steps.add(stepTwo);



        SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
        steppersView.setConfig(steppersViewConfig);
        steppersView.setItems(steps);
        steppersView.build();


    }


}
