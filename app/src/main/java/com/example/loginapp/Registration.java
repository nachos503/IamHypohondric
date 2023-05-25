package com.example.loginapp;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.DatePicker;
import android.widget.Button;
import android.view.View;
import android.text.TextUtils;
import android.widget.TextView;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class Registration extends AppCompatActivity {


    protected static EditText editTextUsername;
    protected static EditText editTextPassword;
    protected EditText editTextEmail;
    protected EditText editTextPhoneNumber;
    private RadioGroup radioGroupSex;
    private DatePicker datePickerBirthday;
    private Button buttonRegister;

    private TextView passwordRequire;

    private SQLiteDatabase database;
    private dataBaseManage databaseManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Создаем экземпляр базы данных
        databaseManage = new dataBaseManage(this);

        // Создаем базу данных
        database = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        // Создаем таблицу пользователей, если она еще не существует
        database.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT, phone_number TEXT, sex TEXT, birth_day INTEGER, birth_month INTEGER, birth_year INTEGER)");

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

    // Обработка иключения, чтобы пароль соответствовал заданным требованиям
    public boolean validatePassword(String password) {
        // Минимальная длина пароля
        int minLength = 8;

        // Максимальная длина пароля
        int maxLength = 20;

        // Проверка длины пароля
        if (password.length() < minLength || password.length() > maxLength) {
            return false;
        }

        // Проверка наличия строчных и прописных букв
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }

            // Если найдены оба типа букв, можно прекратить проверку
            if (hasLowerCase && hasUpperCase) {
                break;
            }
        }

        // Если нет обоих типов букв, пароль не удовлетворяет требованиям
        if (!hasLowerCase || !hasUpperCase) {
            passwordRequire = findViewById(R.id.passwordRequire);
            passwordRequire.setVisibility(View.VISIBLE);
            return false;

        }

        // Если все проверки пройдены успешно, пароль соответствует требованиям
        return true;
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

        //Добавляем пользователя в базу данных
        // Получение базы данных в режиме записи
        SQLiteDatabase db = databaseManage.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dataBaseManage.COLUMN_USERNAME, username);
        values.put(dataBaseManage.COLUMN_PASSWORD, password);
        values.put(dataBaseManage.COLUMN_EMAIL, email);
        values.put(dataBaseManage.COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(dataBaseManage.COLUMN_SEX, sex);
        values.put(dataBaseManage.COLUMN_BIRTH_DAY, day);
        values.put(dataBaseManage.COLUMN_BIRTH_MONTH, month);
        values.put(dataBaseManage.COLUMN_BIRTH_YEAR, year);

        // Вставка данных в таблицу
        db.insert(dataBaseManage.TABLE_NAME, null, values);

        // Закрытие базы данных
        db.close();

        // Проверяем, заполнены ли все поля
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Введите username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Введите пароль");
            editTextPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Введите email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            editTextPhoneNumber.setError("Введите номер телефона");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (!validatePassword(password)){
            editTextPassword.setError("Пароль не соответствует заданным требованиям.");
            editTextPassword.requestFocus();
            return;
        };

        // Создаем объект пользователя с полученными значениями
        User user = new User(username, password, email, sex, day, month, year, phoneNumber);



        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}