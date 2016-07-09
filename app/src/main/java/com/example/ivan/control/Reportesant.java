package com.example.ivan.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Reportesant extends AppCompatActivity {


    SQLiteDatabase db;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportesant);
        final Bundle pantallant = getIntent().getExtras();

        BaseD registro =
                new BaseD(this, "BaseD", null, 1);
        db = registro.getWritableDatabase();

        String[] resource = pantallant.getString("pantallant").split(",");
        if (resource.length == 18) {
            db.execSQL("INSERT INTO registro (name,prof,tarjprof,finca,prop,ubic,tr,sr,motdiag,metdiag,nanimal,nunimal,gestac,diag,est,num,sec,fecha) VALUES (" + pantallant.getString("pantallant") + ")");
        } else {
            db.execSQL("INSERT INTO registro (name,prof,tarjprof,finca,prop,ubic,tr,sr,motdiag,metdiag,nanimal,nunimal,gestac,diag,est,num,sec,coment,fecha) VALUES (" + pantallant.getString("pantallant") + ")");
        }


        String pant = pantallant.getString("pantallant").toString().replace(",", "\n");
        String fpant = pant.replace("'", " ");
        String xpant = "Datos registrados: \n" + fpant;

        textView = (TextView) findViewById(R.id.textView9);
        textView.setText(xpant);
        Button btn = (Button) findViewById(R.id.retinit);


        final Intent reinicio = new Intent(this, Main.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(reinicio);
            }
        });


       //Button but = (Button) findViewById(R.id.but);
       // final Intent repus = new Intent(this,Repus.class);
        //but.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        startActivity(repus);
        //    }
        //});


    }


}
