package com.example.ivan.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Finca extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBase datos =
                new DataBase(this, "Database",null,1);
        db = datos.getWritableDatabase();

        setContentView(R.layout.activity_finca);
        /*  Variables Previas*/
        final Bundle pantalla1 = getIntent().getExtras();
//        final String selpant1 = pantalla1.getString("pantallant");
        //Toast.makeText(getApplicationContext(),selpant1,Toast.LENGTH_SHORT).show();
        /*  Texts       */
        final TextView finca = (TextView) findViewById(R.id.finca);
        final TextView dueno = (TextView) findViewById(R.id.propietario);
        final TextView ubicacion = (TextView) findViewById(R.id.ubicacion);
        /*  Spinners    */
        final Spinner rep = (Spinner) findViewById(R.id.tiporep);
        ArrayAdapter<CharSequence> tiprep = ArrayAdapter.createFromResource(this,R.array.Reproduccion,android.R.layout.simple_spinner_item);
        rep.setAdapter(tiprep);
        final Spinner prod= (Spinner) findViewById(R.id.sisprod);
        ArrayAdapter<CharSequence> sprod  = ArrayAdapter.createFromResource(this,R.array.produccion,android.R.layout.simple_spinner_item);
        prod.setAdapter(sprod);
        final Button vca = (Button) findViewById(R.id.vaca);
        final Intent tovaca = new Intent(this, Vaca.class);
        vca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finca.getText().toString().matches("") || dueno.getText().toString().matches("") || ubicacion.getText().toString().matches("") || rep.getSelectedItemPosition()== 0 || prod.getSelectedItemPosition()== 0){
                    Toast.makeText(getApplicationContext(),"Complete los campos restantes",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Finca Añadida", Toast.LENGTH_SHORT).show();
                    String valfinca = finca.getText().toString();
                    String valdeno  = dueno.getText().toString();
                    String valubic  = ubicacion.getText().toString();
                    String valrep   = rep.getSelectedItem().toString();
                    String valsist  = prod.getSelectedItem().toString();
                    String total = valfinca + "','" + valdeno+ "','" +valubic+ "','" +valrep+ "','"+valsist;
                    db.execSQL("INSERT INTO finca (nom,dueño, ubic,tr,sr) VALUES ('"+total+"')");
                    startActivity(tovaca);

                }
            }
        });
        Button nxt = (Button) findViewById(R.id.sig);
        final Intent siguiente  = new Intent(this, Titlescreen.class);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finca.getText().toString().matches("") || dueno.getText().toString().matches("") || ubicacion.getText().toString().matches("") || rep.getSelectedItemPosition()== 0 || prod.getSelectedItemPosition()== 0){
                    Toast.makeText(getApplicationContext(),"Complete los campos restantes",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Finca Añadida", Toast.LENGTH_SHORT).show();
                    String valfinca = finca.getText().toString();
                    String valdeno  = dueno.getText().toString();
                    String valubic  = ubicacion.getText().toString();
                    String valrep   = rep.getSelectedItem().toString();
                    String valsist  = prod.getSelectedItem().toString();
                    String total =valfinca + "','" + valdeno+ "','" +valubic+ "','" +valrep+ "','" +valsist;
                    db.execSQL("INSERT INTO finca (nom,dueño, ubic,tr,sr) VALUES ('"+total+"')");
                    startActivity(siguiente);

                }
            }
        });
    }
}
