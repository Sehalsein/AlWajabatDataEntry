package com.alwajabat.alwajabatdataentry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import me.drozdzynski.library.steppers.OnCancelAction;
import me.drozdzynski.library.steppers.OnFinishAction;
import me.drozdzynski.library.steppers.SteppersItem;
import me.drozdzynski.library.steppers.SteppersView;

public class MainActivity extends AppCompatActivity {


    public ArrayList<SteppersItem> steps = new ArrayList<>();

    public SteppersItem stepFirst = new SteppersItem();
    public SteppersItem stepTwo = new SteppersItem();
    public SteppersItem stepThree = new SteppersItem();


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

                Toast.makeText(getApplication(), "Finished", Toast.LENGTH_SHORT).show();
            }
        });

        steppersViewConfig.setOnCancelAction(new OnCancelAction() {
            @Override
            public void onCancel() {
                // Action when click cancel on one of steps
                stepFirst.setPositiveButtonEnable(true);
                Toast.makeText(getApplication(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        steppersViewConfig.setFragmentManager(getSupportFragmentManager());

        // Setup Support Fragment Manager for fragments in steps

        stepFirst.setLabel("Step 1");
        stepFirst.setSubLabel("Primary Details");

        stepTwo.setLabel("Step 2");
        stepTwo.setSubLabel("Secondary Details");

        stepThree.setLabel("Step 3");
        stepThree.setSubLabel("Amenities");

        Validate validate = new Validate() {
            @Override
            public void onSuccess() {
                stepFirst.setPositiveButtonEnable(true);
                Toast.makeText(MainActivity.this, "Validation complete", Toast.LENGTH_SHORT).show();
            }
        };

        stepFirst.setFragment(new BasicDetailsFragment(validate));
        stepTwo.setFragment(new RestaurantDetails());
        stepThree.setFragment(new AmmenetyDetails());
        stepFirst.setPositiveButtonEnable(false);
        stepTwo.setPositiveButtonEnable(true);
        stepThree.setPositiveButtonEnable(true);

        steps.add(stepFirst);
        steps.add(stepTwo);
        steps.add(stepThree);

        SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
        steppersView.setConfig(steppersViewConfig);
        steppersView.setItems(steps);
        steppersView.build();


    }


}
