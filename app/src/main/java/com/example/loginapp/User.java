package com.example.loginapp;

public class User {
    private String username;
    private String password;
    private String email;
    private String sex;

    private int day;

    private int year;

    private int month;
    private String phoneNumber;

    public User(String username, String password, String email, String sex, int day, int month, int year, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.day = day;
        this.month = month;
        this.year = year;
        this.phoneNumber = phoneNumber;
    }

    // геттеры и сеттеры для всех полей
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {this.year = year;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
