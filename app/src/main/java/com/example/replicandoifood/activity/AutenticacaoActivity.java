package com.example.replicandoifood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.replicandoifood.R;
import com.example.replicandoifood.helper.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class AutenticacaoActivity extends AppCompatActivity {

    private AppCompatButton btnAcessar;
    private EditText campoEmail;
    private EditText campoSenha;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch tipoAcesso;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizacao);
        getSupportActionBar().hide();

        inicializaComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        setupListeners();
        checkLoggedInUser();

    }

    private void setupListeners() {

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        //Verifica o stado do Switch
                        if (tipoAcesso.isChecked()) {//cadastro
                            autenticacao.createUserWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        setupHome();

                                    } else {
                                        String erroExcecao = "";

                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            erroExcecao = "Digite uma senha mais forte";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            erroExcecao = "Por favor, digite um email válido";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            erroExcecao = "Esta conta já esta cadastrada";
                                        } catch (Exception e) {
                                            erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {//login

                            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Logado com sucesso",
                                                Toast.LENGTH_SHORT).show();
                                        setupHome();

                                    } else {
                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Erro ao fazer login : " + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    } else {
                        Toast.makeText(AutenticacaoActivity.this,
                                "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AutenticacaoActivity.this,
                            "Preencha o E-mail!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLoggedInUser(){
        FirebaseUser currentUser = autenticacao.getCurrentUser();
        if (currentUser != null){
            setupHome();
        }
    }

    private void setupHome() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    private void inicializaComponentes() {
        btnAcessar = findViewById(R.id.btn_acessar_auth);
        campoEmail = findViewById(R.id.et_email_auth);
        campoSenha = findViewById(R.id.et_senha_auth);
        tipoAcesso = findViewById(R.id.sw_tipo_acesso_auth);
    }
}