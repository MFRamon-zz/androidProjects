package com.rmanrique.notes;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.rmanrique.notes.Adapters.SliderAdapter;


public class IntroductionActivity extends AppCompatActivity {

    public ViewPager _slideViewPager;
    public LinearLayout _dotsLayout;
    public SliderAdapter sliderAdapter;
    public Button _btnNext;
    public TextView[] mDots;
    public int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        RelativeLayout relativeLayout = findViewById(R.id.RelativeLayoutIntroduction);
      /*  AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();*/

        _btnNext = findViewById(R.id.btnNext);
        _slideViewPager = findViewById(R.id.slideViewPager);
        _dotsLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        _slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        _slideViewPager.addOnPageChangeListener(viewListener);

        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                startActivity(intent);

            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        _dotsLayout.removeAllViews();
        for (int i=0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            _dotsLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;
            if(i == 0){
                _btnNext.setEnabled(false);
                _btnNext.setVisibility(View.INVISIBLE);
            }
            if(i == 1){
                _btnNext.setEnabled(false);
                _btnNext.setVisibility(View.INVISIBLE);
            }else{
                _btnNext.setEnabled(true);
                _btnNext.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
