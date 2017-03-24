package com.example.project_myqq2.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.project_myqq2.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class PagerActivity extends Activity {

    private ViewPager viewPager;
    private LayoutInflater inflater;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        inflater = LayoutInflater.from(PagerActivity.this);
        View view1 = inflater.inflate(R.layout.item_viewpager1,null);
        View view2 = inflater.inflate(R.layout.item_viewpager2,null);
        View view3 = inflater.inflate(R.layout.item_viewpager3,null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            //需要加载
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager)container).addView(viewList.get(position));
                return viewList.get(position);
            }

            //需要删除
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager)container).removeView(viewList.get(position));
            }
        });
    }

    //Button按钮进入动画页面
    public void startAnimation(View v){
        Intent intent = new Intent(PagerActivity.this,AnimationActivity.class);
        startActivity(intent);
    }

}
