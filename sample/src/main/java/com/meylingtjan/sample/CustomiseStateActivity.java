package com.meylingtjan.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.meylingtjan.stepview.StepView;
import com.meylingtjan.stepview.model.Step;
import com.shuhart.stepview.sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomiseStateActivity extends AppCompatActivity {
    private int currentStep = 0;
    private StepView stepView;
    private List<Step> steps = new ArrayList<>();
    private List<String> listStepTitle = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise_state);
        stepView = findViewById(R.id.step_view);
        setListStepView();
        setupStepView();
    }

    private void setListStepView(){
        listStepTitle = Arrays.asList(getResources().getStringArray(R.array.steps));

        for (int i = 0; i < listStepTitle.size(); i++) {
            steps.add(new Step(listStepTitle.get(i)));
        }

        //customise the state for completed step
        steps.get(2).setState(Step.State.COMPLETED);
        steps.get(4).setState(Step.State.COMPLETED);

        stepView.setListSteps(steps, listStepTitle);
    }

    private void setupStepView() {

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(CustomiseStateActivity.this, "Step " + step, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < stepView.getStepCount() - 1) {
                    steps.get(currentStep).setState(Step.State.COMPLETED);
                    currentStep++;
                    stepView.go(currentStep, true);
                } else {
                    stepView.done(true);
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 0) {
                    steps.get(currentStep).setState(Step.State.COMPLETED);
                    currentStep--;
                }
                stepView.done(false);
                stepView.go(currentStep, true);
            }
        });
    }
}
