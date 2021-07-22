package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button login;

    //Переменная для работы с БД
    private DBHelper mDBHelper;             //DBHelper myDB;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        username = (EditText) findViewById( R.id.username);
        password = (EditText) findViewById( R.id.password);
        login=(Button) findViewById( R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Вы не заполнили данные!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result = mDBHelper.CheckUsernamePassword(user,pass);
                    if ( result ==true)
                    {
                        Toast.makeText(MainActivity.this, "Ну типа вошёл!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Нихуя не верно!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}