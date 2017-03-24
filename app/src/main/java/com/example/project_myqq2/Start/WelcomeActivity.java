package com.example.project_myqq2.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.project_myqq2.R;


/**
 * Created by Administrator on 2017/3/1.
 */

public class WelcomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,PagerActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        },2300);
    }
}
