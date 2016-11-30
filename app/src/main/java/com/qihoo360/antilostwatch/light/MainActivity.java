package com.qihoo360.antilostwatch.light;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qihoo360.antilostwatch.light.view.viewpagerindicator.TabPageIndicator;
import com.qihoo360.antilostwatch.light.view.viewpagerindicator.TabPageIndicatorAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabPageIndicatorAdapter pageAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pageAdapter);

        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
    }
}
