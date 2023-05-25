package com.example.loginapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class dataBaseManage extends SQLiteOpenHelper {
    class databasemanage {}
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_BIRTH_DAY = "birth_day";
    public static final String COLUMN_BIRTH_MONTH = "birth_month";
    public static final String COLUMN_BIRTH_YEAR = "birth_year";

    private static databasemanage instance;



    public dataBaseManage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // Удаляем таблицу, если она существует
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Создание таблицы
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_BIRTH_DAY + " INTEGER, " +
                COLUMN_BIRTH_MONTH + " INTEGER, " +
                COLUMN_BIRTH_YEAR + " INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Обновление базы данных при изменении версии
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Метод для проверки совпадения введенных username и password с теми, которые есть в БД
    public boolean check_login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{username, password}, null, null, null);

        boolean result = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return result;
    }

    // Метод для удаления таблицы
    public void deleteTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }
}
