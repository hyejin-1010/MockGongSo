package com.emirim.hyejin.mokgongso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;


public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*ImageView kermit = (ImageView) findViewById(R.id.gifView);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(kermit);
        Glide.with(this).load(R.drawable.kermit).into(gifImage);
        */

        try{
            Thread.sleep(3000);
            Intent gotomain = new Intent(this,MainActivity.class);
            startActivity(gotomain);
            finish();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
