package com.example.project_myqq2.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_myqq2.MainActivity;
import com.example.project_myqq2.R;

/**
 * Created by Administrator on 2017/3/1.
 */
public class AnimationActivity extends Activity{

    private ImageView ivRight,ivLeft;
    private TextView tvAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ivLeft = (ImageView) findViewById(R.id.iv_left);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        tvAnimation = (TextView) findViewById(R.id.tv_animation);

        //左门打开
        toLeft();
        //右门打开
        toRight();
        //文字渐变
        textChange();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AnimationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        },2300);

    }

    //文字渐变
    private void textChange() {
        AnimationSet anim2 = new AnimationSet(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation
                (1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setDuration(1000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0.0001f);
        alphaAnimation.setDuration(1500);
        anim2.addAnimation(scaleAnimation);
        anim2.addAnimation(alphaAnimation);
        anim2.setFillAfter(true);
        tvAnimation.startAnimation(anim2);
    }

    //右门打开
    private void toRight() {
        AnimationSet anim1 = new AnimationSet(true);

        TranslateAnimation translateAnimation1 = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF
                        ,+1f, Animation.RELATIVE_TO_SELF,0f, Animation.RELATIVE_TO_SELF,0f);
        //设置动画持续时间
        translateAnimation1.setDuration(2*1000);
        //开门开始时间
        anim1.setStartOffset(800);
        //添加动画
        anim1.addAnimation(translateAnimation1);
        //停留在最后位置
        anim1.setFillAfter(true);
        ivRight.startAnimation(anim1);

    }

    //左门打开
    private void toLeft() {
        AnimationSet anim = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation
                (Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF
                        ,-1f, Animation.RELATIVE_TO_SELF,0f, Animation.RELATIVE_TO_SELF,0f);
        //设置动画持续时间
        translateAnimation.setDuration(2*1000);
        //开门开始时间
        anim.setStartOffset(800);
        //添加动画
        anim.addAnimation(translateAnimation);
        //停留在最后位置
        anim.setFillAfter(true);
        ivLeft.startAnimation(anim);
    }
}
