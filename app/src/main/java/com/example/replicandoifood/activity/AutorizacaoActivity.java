package com.example.replicandoifood.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.replicandoifood.R;

public class AutorizacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizacao);
        getSupportActionBar().hide();
    }
}