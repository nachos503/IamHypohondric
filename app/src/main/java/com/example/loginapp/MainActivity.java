package com.example.loginapp;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.DatePicker;
import android.widget.Button;
import android.view.View;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextEmail, editTextPhoneNumber;
    private RadioGroup radioGroupSex;
    private DatePicker datePickerBirthday;
    private Button buttonRegister;

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Создаем базу данных
        database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);

        // Создаем таблицу пользователей, если она еще не существует
        database.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT, phone_number TEXT, sex TEXT, birthday_day INTEGER, birthday_month INTEGER, birthday_year INTEGER)");

        // Находим все поля по их идентификаторам
        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPhoneNumber = findViewById(R.id.edittext_phonenumber);
        radioGroupSex = findViewById(R.id.radiogroup_sex);
        datePickerBirthday = findViewById(R.id.datepicker_birthday);
        buttonRegister = findViewById(R.id.button_register);

        // Обрабатываем нажатие на кнопку регистрации
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }

        });
    }

    private void registerUser() {
        // Получаем значения всех полей
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        int sexId = radioGroupSex.getCheckedRadioButtonId();
        String sex = sexId == R.id.radiobutton_male ? "male" : "female";
        int day = datePickerBirthday.getDayOfMonth();
        int month = datePickerBirthday.getMonth() + 1; // Добавляем 1, так как месяцы начинаются с 0
        int year = datePickerBirthday.getYear();

        // Проверяем, заполнены ли все поля
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            editTextPhoneNumber.setError("Please enter phone number");
            editTextPhoneNumber.requestFocus();
            return;
        }

        // Создаем объект пользователя с полученными значениями
        User user = new User(username, password, email, sex, day, month, year, phoneNumber);

        // Добавляем пользователя в базу данных
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        values.put("phone_number", user.getPhoneNumber());
        values.put("sex", user.getSex());
        values.put("birthday_day", user.getDay());
        values.put("birthday_month", user.getMonth());
        values.put("birthday_year", user.getYear());
        long result = database.insert("users", null, values);

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}