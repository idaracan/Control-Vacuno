package com.example.ivan.control;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class ReportsX extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportesant);

        DataBase datos =
                new DataBase(this, "DataBase",null,1);
        db = datos.getReadableDatabase();

        final EditText editText2 = (EditText) findViewById(R.id.search);
        final TextView textView = (TextView) findViewById(R.id.textView9);

        Button but = (Button) findViewById(R.id.retinit);
        final Intent reinicio = new Intent(this,Titlescreen.class);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(reinicio);
                String a = "'a','b','c','d','123|','f','g','h','i','j','k','l'";
                db.execSQL("INSERT INTO vaca (motd, metd, finca, nomv, numv, dias, diag, estprod, cc, tiemposec, fecha, observ)" +
                        " VALUES ("+a+")");
            }
        });

        Button src = (Button) findViewById(R.id.src);
        src.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"No entra al if",Toast.LENGTH_SHORT).show();

                String[] args = new String[] {editText2.getText().toString()};
                Cursor c = db.rawQuery("SELECT * FROM finca", null);
                Toast.makeText(ReportsX.this, ""+ (int) c.getCount() , Toast.LENGTH_SHORT).show();

                if (c.moveToFirst()) {
                    //textView.setText("");  Vacio el textview
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String motd= c.getString(0);
                        String metd = c.getString(1);
                        String finca = c.getString(2);
                        String nomv = c.getString(3);
                        String numv = c.getString(4);
                        /*String fecha = c.getString(5);
                        String dias = c.getString(6);
                        String cc = c.getString(7);
                        String diag = c.getString(8);
                        String estprod = c.getString(9);
                        String tiemposec = c.getString(10);
                        String observ = c.getString(11);
                        textView.setText("Nombre de vaca: "+nomv+"\n"+"Número de Vaca: "+numv+"\nMotivo de Diagnóstico: "+motd+"\nMétodo de Diagnóstico"+metd+"\nPertenece a la Finca: "+finca+
                                "\nFecha de último Parto: "+fecha+"\nDías de Gestación: "+dias+"\nEstado Corporal: "+cc+"\nDiagnóstico: "+diag+"\nEstado Productivo: "+estprod+
                                "\nTiempo de Secado"+tiemposec+"Observaciones/Comentarios: "+observ);*/
                        textView.setText(""+motd+"\n" +
                                "Método de Diagnóstico"+metd+"\n" +
                                "Pertenece a la Finca: "+finca);

                    } while(c.moveToNext());

                }
            }
        });



    }


}
