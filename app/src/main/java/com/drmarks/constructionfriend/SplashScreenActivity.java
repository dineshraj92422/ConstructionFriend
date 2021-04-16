package com.drmarks.constructionfriend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActivityCompat.requestPermissions(this,new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET},PackageManager.PERMISSION_GRANTED);
        Animation logoAnimation = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        Animation textAnimation = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        ImageView imgVi =findViewById(R.id.imageView);
        TextView textv=findViewById(R.id.cftext);
        imgVi.startAnimation(logoAnimation);
        textv.startAnimation(textAnimation);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreenActivity.this,IndustrialSectorActivity.class);
                startActivity(intent);
                finish();
            }
            },4000);
    }
}