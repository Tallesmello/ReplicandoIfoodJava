package com.example.replicandoifood.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import com.example.replicandoifood.R;

public class AutorizacaoActivity extends AppCompatActivity {

    private AppCompatButton btnAcessar;
    private EditText campoEmail;
    private EditText campoSenha;
    private Switch tipoAcesso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizacao);
        getSupportActionBar().hide();

        inicializaComponentes();
    }

    private void inicializaComponentes(){
        btnAcessar = findViewById(R.id.btn_acessar_auth);
        campoEmail = findViewById(R.id.et_email_auth);
        campoSenha = findViewById(R.id.et_senha_auth);
        tipoAcesso = findViewById(R.id.sw_tipo_acesso_auth);
    }
}