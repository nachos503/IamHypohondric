package com.example.loginapp;

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

import com.example.loginapp.databinding.ActivityMainScreenBinding;

public class MainScreen extends  AppCompatActivity{
    private AppBarConfiguration appBarConfiguration;

    private Button pillTrackerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        pillTrackerButton = findViewById(R.id.pill_tracker);

    //Обрабатываем нажатие на кнопку первого сервиса
        Intent intent = new Intent(this, pill_tracker.class);

        pillTrackerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}
