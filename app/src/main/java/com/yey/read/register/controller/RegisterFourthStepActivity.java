package com.yey.read.register.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;

public class RegisterFourthStepActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fourth_step);
        Button nextStepButton = (Button)findViewById(R.id.btn_activity_nextstep4);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(RegisterFifthStepActivity.class);
            }
        });
    }

}
