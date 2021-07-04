package com.example.travelcove.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.travelcove.HelperClasses.SliderAdapter;
import com.example.travelcove.R;
import com.example.travelcove.User.Login;
import com.example.travelcove.User.Profile;
import com.example.travelcove.User.Welcome_page;
import com.google.firebase.auth.FirebaseAuth;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPos;

    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);

    }



    public void skip(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void start(View view) {
        startActivity(new Intent(this, Login.class));
    }


    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
        currentPos++;

        if (currentPos == 2) {

            animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
            letsGetStarted.setAnimation(animation);
            letsGetStarted.setVisibility(View.VISIBLE);
        }

    }

    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(38);

            dotsLayout.addView(dots[i]);

            if(dots.length > 0) dots[position].setTextColor(getResources().getColor(R.color.black));
        }


    }


}