package com.example.replicandoifood.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth referenciaAutenticacao;
    private static StorageReference referenciaStorage;

    private static String getIdUsuario(){
        FirebaseAuth autenticacao = getFirebaseAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    //retorna a referencia do database
    public static DatabaseReference getFirebase(){
        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    //retorna a intancia do FirebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){
        if (referenciaAutenticacao == null){
            referenciaAutenticacao =referenciaAutenticacao.getInstance();
        }
        return referenciaAutenticacao;
    }

    //retorna a intancia do FirebaseStorage
    public static StorageReference getFirebaseStorage(){
        if (referenciaStorage == null){
            referenciaStorage = FirebaseStorage.getInstance().getReference();
        }
        return referenciaStorage;
    }
}
