package com.example.ivan.control;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by RokkuX on 04/07/2016.
 */
public class Reports extends AppCompatActivity{

    SQLiteDatabase db;
    Button src;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBase datos =
                new DataBase(this, "DataBase",null,1);
        db = datos.getWritableDatabase();


        Button but = (Button) findViewById(R.id.retinit);
        final Intent reinicio = new Intent(this,Titlescreen.class);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(reinicio);
            }
        });

    }


}
