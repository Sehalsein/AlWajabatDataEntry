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

                Toast.makeText(getApplication(), "Completed", Toast.LENGTH_SHORT).show();
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


        Validate validate = new Validate() {
            @Override
            public void onSuccess() {
                stepFirst.setPositiveButtonEnable(true);
                Toast.makeText(MainActivity.this, "Validation complete", Toast.LENGTH_SHORT).show();
            }
        };

        stepFirst.setFragment(new BasicDetailsFragment(validate));
        stepTwo.setFragment(new RestaurantDetails());
        stepFirst.setPositiveButtonEnable(true);
        stepTwo.setPositiveButtonEnable(false);

        steps.add(stepFirst);
        steps.add(stepTwo);

        SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
        steppersView.setConfig(steppersViewConfig);
        steppersView.setItems(steps);
        steppersView.build();


    }


}
