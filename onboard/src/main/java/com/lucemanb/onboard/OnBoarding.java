package com.lucemanb.onboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnNext, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_boarding);
        if (getSupportActionBar()!=null)getSupportActionBar().hide();

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btn_next);
        btnBack = findViewById(R.id.btn_previous);

        btnNext.setText(OnBoard.nextText);
        addBottomDots(0);

        mViewPagerAdapter adapter = new mViewPagerAdapter();
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                if (position == OnBoard.layouts.length - 1) {
                    btnNext.setText(OnBoard.startText);
                } else {
                    btnNext.setText(OnBoard.nextText);
                }

                if (position!=0)btnBack.setVisibility(View.VISIBLE);
                else btnBack.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < OnBoard.layouts.length) {
                    viewPager.setCurrentItem(current);
                }else {
                    startActivity(new Intent(OnBoarding.this, OnBoard.aClass));
                    finish();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = viewPager.getCurrentItem() - 1;
                if (current > 0) {
                    viewPager.setCurrentItem(current);
                }
            }
        });

    }
    public class mViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        private mViewPagerAdapter() {
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(OnBoard.layouts[position]);
            return OnBoard.layouts[position];
        }

        @Override
        public int getCount() {
            return OnBoard.layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[OnBoard.layouts.length];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(50);
            dots[i].setTextColor(OnBoard.colorInactive);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(OnBoard.colorActive);
    }

}
