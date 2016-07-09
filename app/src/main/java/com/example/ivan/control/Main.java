package com.example.ivan.control;

import android.app.Activity;

//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class Main extends Activity implements AdapterView.OnItemSelectedListener {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText tarjprof = (EditText) findViewById(R.id.TarjProf);
        tarjprof.setVisibility(View.INVISIBLE);

        final EditText name = (EditText) findViewById(R.id.Nametext);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> profesiones = ArrayAdapter.createFromResource(this, R.array.Profesiones_Arr, android.R.layout.simple_spinner_item);
        spinner.setOnItemSelectedListener(this);

        profesiones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(profesiones);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinner.getSelectedItemPosition()){
                    case 0:
                        if (tarjprof.getVisibility() == View.VISIBLE){
                            tarjprof.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case 4:
                        if (tarjprof.getVisibility() == View.VISIBLE){
                            tarjprof.setVisibility(View.INVISIBLE);
                        }
                        break;
                    default:
                        tarjprof.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DataBase datos =
                new DataBase(this, "Database",null,1);
        db = datos.getWritableDatabase();

        final CharSequence advert = "Considere usar esta app como Veterinario o Zootecnista";
        Button next = (Button) findViewById(R.id.Next);
        final Button tofnc= (Button) findViewById(R.id.buttonfnc);
        final Context context = this;
        final Intent intent = new Intent(context, Titlescreen.class);//
        final Intent fnc = new Intent(this,Finca.class);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().matches("")) { //no name
                }else{ //name
                    if (spinner.getSelectedItemPosition()>0 && spinner.getSelectedItemPosition()<4){
                        if (tarjprof.getText().toString().matches("")){ //no
                            Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
                        }else {
                            String pantalla1 ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','"+tarjprof.getText().toString()+"',";
                            String pantallax ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','"+tarjprof.getText().toString()+"'";
                            db.execSQL("INSERT INTO usuario (nombre,prof,tarjprof) VALUES ("+pantallax+")");
                            intent.putExtra("pantalla1",pantalla1);
                            startActivity(intent);

                        }

                    }else {
                        if (spinner.getSelectedItemPosition()==4) {
                            //Toast.makeText(getApplicationContext(), advert, Toast.LENGTH_SHORT).show();
                            String pantalla1 ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','Tarjeta P:____'";
                            String pantallax ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','Tarjeta P:____'";
                            db.execSQL("INSERT INTO usuario (nombre,prof,tarjprof) VALUES ("+pantallax+")");
                            intent.putExtra("pantalla1",pantalla1);
                            startActivity(intent);
                        }
                    }
                }

            }
        });

        tofnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (name.getText().toString().matches("")) { //no name
                }else{ //name
                    if (spinner.getSelectedItemPosition()>0 && spinner.getSelectedItemPosition()<4){
                        if (tarjprof.getText().toString().matches("")){ //no
                            Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
                        }else {
                            String pantalla1 ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','"+tarjprof.getText().toString()+"',";
                            String pantallax ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"','"+tarjprof.getText().toString()+"'";
                            db.execSQL("INSERT INTO usuario ( nombre, prof,tarjprof) VALUES ("+pantallax+")");
                            intent.putExtra("pantalla1",pantalla1);
                            startActivity(fnc);

                        }

                    }else {
                        if (spinner.getSelectedItemPosition()==4) {
                            //Toast.makeText(getApplicationContext(), advert, Toast.LENGTH_SHORT).show();
                            String pantalla1 ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"',";
                            String pantallax ="'"+name.getText().toString()+ "','" +spinner.getSelectedItem().toString()+"'";
                            db.execSQL("INSERT INTO usuario (nombre,prof) VALUES ("+pantallax+")");
                            intent.putExtra("pantalla1",pantalla1);
                            startActivity(fnc);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
