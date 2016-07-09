package com.example.ivan.control;

/**
 * Created by RokkuX on 23/06/2016.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Repus extends AppCompatActivity implements View.OnClickListener {



    EditText editText;
    SQLiteDatabase db;

    TextView textView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Button btn = (Button) findViewById(R.id.retinit);
        final Intent reinicio = new Intent(this, Main.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(reinicio);
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        String[] args = new String[]{editText.getText().toString()};
        Cursor c = db.rawQuery(" SELECT * FROM registro WHERE nombre=? ", args);
        if(c.moveToFirst()){
            textView.setText(""); // Vacio el textview
            //Recorremos el cursor hasta que no haya más registros
            int i = 1;
            String kat = "";
            do {
                String name = c.getString(0);
                String prof = c.getString(1);
                String tarjprof = c.getString(2);
                String finca = c.getString(3);
                String prop = c.getString(4);
                String ubic = c.getString(5);
                String tr = c.getString(6);
                String sr = c.getString(7);
                String motdiag = c.getString(8);
                String metdiag = c.getString(9);
                String nanimal = c.getString(10);
                String nunimal = c.getString(11);
                String gestac = c.getString(12);
                String diag = c.getString(13);
                String est = c.getString(14);
                String num = c.getString(15);
                String sec = c.getString(16);
                String coment = c.getString(17);
                String fecha = c.getString(18);
                String kot = ("Registro: " + i + name + "\n" + prof + "\n" + tarjprof + "\n" + finca + "\n" + prop + "\n" + ubic + "\n" + tr + "\n" + sr + "\n" + motdiag + "\n" + metdiag + "\n" + nanimal + "\n" + nunimal + "\n" + gestac + "\n" + diag + "\n" + est + "\n" + num + "\n" + sec + "\n" + coment + "\n" + fecha + "\n");
                kat = (kat + kot);
            } while (c.moveToNext());
            textView.append(kat);
        }


    }

    @Override
    public void onClick(View view) {

    }
}
