package com.example.replicandoifood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.replicandoifood.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirAutorizacao();
            }
        }, 3000);
    }

    private void abrirAutorizacao(){
        Intent intent = new Intent(SplashActivity.this, AutorizacaoActivity.class);
        startActivity(intent);
        finish();
    }

}