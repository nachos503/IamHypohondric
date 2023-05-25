package com.example.loginapp;

import static com.example.loginapp.Registration.editTextPassword;
import static com.example.loginapp.Registration.editTextUsername;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class Login extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private dataBaseManage databaseManage;

    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.btn_llogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            };
        });
    }
// Метод для переключения экрана на другой activity
    private void loginUser() {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}





