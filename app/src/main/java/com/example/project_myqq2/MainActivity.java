package com.example.project_myqq2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.project_myqq2.HomeActivity.ContactFragment;
import com.example.project_myqq2.HomeActivity.MessageFragment;
import com.example.project_myqq2.HomeActivity.ZoneFragment;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{

    private FragmentManager manager;
    private RadioGroup rg_home;
    private RadioButton rb_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        //初始化
        manager = getSupportFragmentManager();
        //设置默认选中
        rb_message.setChecked(true);

        rg_home.setOnCheckedChangeListener(this);
        //切换不同fragment
        changeFragment(new MessageFragment(),false);


    }

    //RadioGroup点击事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_message://消息
                changeFragment(new MessageFragment(), true);
                break;
            case R.id.rb_contact://联系人
                changeFragment(new ContactFragment(),true);
                break;
            case R.id.rb_zone://动态
                changeFragment(new ZoneFragment(), true);
                break;
            default:
                break;

        }
    }
    //切换不同fragment
    public void changeFragment(Fragment fragment, Boolean isInit){
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frag_home,fragment);

        if (!isInit){
            transaction.addToBackStack(null);
        }

        transaction.commit();

    }


    //初始化
    private void initView() {
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rg_home = (RadioGroup) findViewById(R.id.rg_home);
    }


}
