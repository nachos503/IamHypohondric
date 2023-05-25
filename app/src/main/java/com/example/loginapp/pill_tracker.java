package com.example.loginapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.loginapp.databinding.ActivityPillTrackerBinding;

import java.util.ArrayList;
import java.util.List;

public class pill_tracker extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPillTrackerBinding binding;

    private CalendarView calendarView;
    private TextView tvTablets;
    private ListView lvTablets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_tracker);

        calendarView = findViewById(R.id.calendarView);
        tvTablets = findViewById(R.id.tvTablets);
        lvTablets = findViewById(R.id.lvTablets);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Получение информации о таблетках для выбранного дня
                List<String> tablets = getTabletsForDay(year, month, dayOfMonth);

                // Отображение информации о таблетках
                if (tablets.isEmpty()) {
                    tvTablets.setText("Список таблеток пуст на выбранный день");
                    lvTablets.setVisibility(View.GONE);
                } else {
                    tvTablets.setText("Таблетки на выбранный день:");
                    lvTablets.setAdapter(new ArrayAdapter<>(pill_tracker.this, android.R.layout.simple_list_item_1, tablets));
                    lvTablets.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private List<String> getTabletsForDay(int year, int month, int dayOfMonth) {
        // Здесь необходимо реализовать логику получения информации о таблетках для выбранного дня
        // Например, можно использовать базу данных или файл для хранения информации о таблетках
        // В данном примере просто возвращается фиктивный список таблеток
        List<String> tablets = new ArrayList<>();
        if (dayOfMonth % 2 == 0) {
            tablets.add("Супрастинчик");
        }
        if (dayOfMonth % 3 == 0) {
            tablets.add("Витамишки");
        }
        if (dayOfMonth % 5 == 0) {
            tablets.add("Фенибут");
        }
        return tablets;
    }
}
