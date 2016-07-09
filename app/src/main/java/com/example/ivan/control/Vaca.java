package com.example.ivan.control;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Vaca extends AppCompatActivity {


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBase datos =
                new DataBase(this, "Database",null,1);
        db = datos.getWritableDatabase();

        String[] fincas = new String[] {};
        Cursor c = db.rawQuery("Select nom from finca",fincas);
        int i = 1;
        String nombresfincas = "Seleccionar Opción, ";
        if (c.moveToFirst()) {
            do {
                String nombre = c.getString(0);
                nombresfincas = nombresfincas + nombre+",";
            } while (c.moveToNext());
        }
        fincas = nombresfincas.split(",");

        setContentView(R.layout.activity_vaca);
        //final Bundle pantallant = getIntent().getExtras();
        //Toast.makeText(getApplicationContext(),pantallant.getString("pantallant"),Toast.LENGTH_SHORT).show();
        /*  Spinners    */
        final Spinner metodos = (Spinner) findViewById(R.id.metodos);
        ArrayAdapter<CharSequence> arraymetodos = ArrayAdapter.createFromResource(this, R.array.metodos_array, android.R.layout.simple_spinner_item);
        metodos.setAdapter(arraymetodos);

        final Spinner motivos = (Spinner) findViewById(R.id.motivos);
        ArrayAdapter<CharSequence> arraymotivos = ArrayAdapter.createFromResource(this, R.array.motivos_array, android.R.layout.simple_spinner_item);
        motivos.setAdapter(arraymotivos);

        final Spinner finca = (Spinner) findViewById(R.id.selectorfinca);
        ArrayAdapter<String> arrayfincas = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fincas);
        arrayfincas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finca.setAdapter(arrayfincas);
        //ArrayAdapter<CharSequence>
        /*  Botón   */
        Button sig = (Button) findViewById(R.id.pg5);
        final Intent siguiente = new Intent(this, Vacados.class);
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (metodos.getSelectedItemPosition() == 0 || motivos.getSelectedItemPosition() == 0 || finca.getSelectedItemPosition()==0) {
                    Toast.makeText(getApplicationContext(), "Invalid selection", Toast.LENGTH_SHORT).show();
                } else {
                    String total ="'"+ motivos.getSelectedItem().toString() + "','" +metodos.getSelectedItem().toString()+"','"+finca.getSelectedItem().toString()+"'";
                    //db.execSQL("INSERT INTO vaca (motd,metd,finca) VALUES ("+total+")");
                    siguiente.putExtra("total",total);
                    startActivity(siguiente);

                }
            }

        });
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


}
