package com.example.ivan.control;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
//import android.icu.text.SimpleDateFormat;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Vacados extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SQLiteDatabase db;
    Button btn;
    int year, month, day;
    static final int DIALOG_ID = 0;
    //private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacados);
        final Bundle pantallant = getIntent().getExtras();

        /*  Calendario  */
        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month= cal.get(Calendar.MONTH);
        day  = cal.get(Calendar.DAY_OF_MONTH);

        /*  Spinners    */
        final Spinner diag = (Spinner) findViewById(R.id.diagnostic);
        ArrayAdapter<CharSequence> arraydiags = ArrayAdapter.createFromResource(this, R.array.diagnostico, android.R.layout.simple_spinner_item);
        diag.setAdapter(arraydiags);

        final Spinner est = (Spinner) findViewById(R.id.estadoprod);
        ArrayAdapter<CharSequence> arrayest = ArrayAdapter.createFromResource(this, R.array.estado, android.R.layout.simple_spinner_item);
        est.setAdapter(arrayest);

        final Spinner num = (Spinner) findViewById(R.id.numeros);
        ArrayAdapter<CharSequence> numeros = ArrayAdapter.createFromResource(this, R.array.numeros, android.R.layout.simple_spinner_item);
        num.setAdapter(numeros);

        final Spinner secado = (Spinner) findViewById(R.id.secado);
        ArrayAdapter<CharSequence> ssecado = ArrayAdapter.createFromResource(this, R.array.secado, android.R.layout.simple_spinner_item);
        secado.setAdapter(ssecado);

        /*  textos  */
        final TextView nanimal = (TextView) findViewById(R.id.nombreanimal);
        final TextView numnimal= (TextView) findViewById(R.id.numanimal);
        final TextView coments = (TextView) findViewById(R.id.observaciones);
        final TextView textView6=(TextView) findViewById(R.id.textView6);
        textView6.setVisibility(View.INVISIBLE);

        final EditText gestac  = (EditText) findViewById(R.id.dias);
        gestac.setVisibility(View.INVISIBLE);

        gestac.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {

                    if (Integer.parseInt(gestac.getText().toString()) < 30) {
                        gestac.setText("30");
                    } else if (Integer.parseInt(gestac.getText().toString()) > 285) {
                        gestac.setText("285");
                    }
                }
            }
        });

        diag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (diag.getSelectedItemPosition()==2){
                    gestac.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.VISIBLE);
                }else{
                    if (gestac.getVisibility()==View.VISIBLE){
                        gestac.setVisibility(View.INVISIBLE);
                    }
                    if (textView6.getVisibility()==View.VISIBLE){
                        textView6.setVisibility(View.INVISIBLE);
                    }
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DataBase datos =
                new DataBase(this, "Database",null,1);
        db = datos.getWritableDatabase();

        showDialogOnButtonClick();

        final Intent intent = new Intent(this, Titlescreen.class);

        Button fin = (Button) findViewById(R.id.fin);
        fin.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(numnimal.getText().toString().matches("") || diag.getSelectedItemPosition()== 0 || est.getSelectedItemPosition()== 0 || btn.getText().toString().matches("ESCOGER FECHA")){
                    Toast.makeText(Vacados.this, "Complete los campos restantes",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Vacados.this, pantallant.getString("total"),Toast.LENGTH_SHORT).show();
                }else{
                    if(gestac.hasFocus()) {
                        gestac.clearFocus();
                    }
                    String nombre = nanimal.getText().toString();
                    String numero = numnimal.getText().toString();
                    String datges = gestac.getText().toString();
                    String diagnos= diag.getSelectedItem().toString();
                    String estad  = est.getSelectedItem().toString();
                    String calif  = num.getSelectedItem().toString();
                    String secad  = secado.getSelectedItem().toString();
                    String coment = coments.getText().toString();

                    if (!coments.getText().toString().matches("")){
                        coment = coments.getText().toString();
                    }
                    String fecha  = btn.getText().toString();
                    String total;

                    String fparto;
                    String dabiert;
                    String secadovaca;

                    if (diag.getSelectedItemPosition()==1){
                        fparto      = "___";
                        dabiert     = DiasAbiertosVacia(btn.getText().toString());
                        secadovaca  = "___";
                    } else {
                        fparto  = Parto(gestac.getText().toString());
                        dabiert = diasAbiertosPrenada(btn.getText().toString(),fparto);
                        if (secado.getSelectedItemPosition() == 1){
                            secadovaca= SecProd(gestac.getText().toString());
                        } else {
                            secadovaca= SecGest(gestac.getText().toString());
                        }
                    }

                    if (!coments.getText().toString().matches("")){
                        total  = pantallant.getString("total")+",'"+nombre+"','"+numero+"','"+datges+"','"+diagnos+"','"+estad+"','"+calif+"','"+secad+"','"+fecha+"','"+coment+"'";
                    }
                    else {
                        total  = pantallant.getString("total")+",'"+nombre+"','"+numero+"','"+datges+"','"+diagnos+"','"+estad+"','"+calif+"','"+secad+"','"+fecha+"','Sin Comentarios'";
                    }

                    try{
                        db.execSQL("INSERT INTO vaca " +
                                "(motd, metd, finca, nomv, numv, dias, " +
                                "diag, estprod, cc, tiemposec, fecha, observ)" +
                                " VALUES ("+total+")");/**/
                        Toast.makeText(getApplicationContext(), total, Toast.LENGTH_LONG).show();
                        db.close();
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), ":(", Toast.LENGTH_LONG).show();
                    }
                    //intent.putExtra("pantallant",total);

                    //la fecha de parto la arroja cuando se escoja se escoja preñada
                    //la fecha de secado se arroja cuando se escoja en producción se escoja en producción
                    //Toast.makeText(getApplicationContext(),"Fecha aprox.de parto:\n" +fparto ,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Dias abiertos:\n" +dabiert,Toast.LENGTH_SHORT).show();
                    //
                }
            }
        });

    }
    public String Parto(String dias){
        Calendar act = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("d/MM/yyyy");
        Date date = new Date();
        act.setTime(date);
        act.add(Calendar.DATE, 285 - Integer.parseInt(dias));
        String fparto = fmt.format(act.getTime());
        return fparto;
    }

    public String SecProd(String dias){
        Calendar act = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("d/MM/yyyy");
        Date date = new Date();
        act.setTime(date);
        act.add(Calendar.DATE, 270 + 285 - Integer.parseInt(dias));
        String fparto = fmt.format(act.getTime());
        return fparto;
    }

    public String SecGest(String dias){
        Calendar act = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("d/MM/yyyy");
        Date date = new Date();
        act.setTime(date);
        act.add(Calendar.DATE, 210 - Integer.parseInt(dias));
        String fparto = fmt.format(act.getTime());
        return fparto;
    }
    /**/
    public String DiasAbiertosVacia(String fecha){
        //Ultimo Parto
        Calendar ultpart = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("d/MM/yyyy");
        try {
            ultpart.setTime(fmt.parse(fecha));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Hoy
        Calendar act = Calendar.getInstance();
        java.text.SimpleDateFormat fmi = new java.text.SimpleDateFormat("d/MM/yyyy");
        Date date = new Date();
        act.setTime(date);

        long diff = Math.abs(ultpart.getTimeInMillis() - act.getTimeInMillis());
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        /*Toast.makeText(getApplicationContext(),"UlTIMO PARTO\n" + ultpart.getTime().toString()
                +"\n" + "FECHA HOY\n" + act.getTime().toString()
                +"\n"+"DIFERENCIA DE FECHAS\n"+ (int) dayCount,Toast.LENGTH_LONG).show();*/

        return ("" + (int) dayCount);


    }

    public String diasAbiertosPrenada(String fecha,String fgest){
        Calendar ultpart = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("d/MM/yyyy");
        try {
            ultpart.setTime(fmt.parse(fecha));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar gestaction = Calendar.getInstance();
        try {
            gestaction.setTime(fmt.parse(fgest));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = Math.abs(ultpart.getTimeInMillis() - gestaction.getTimeInMillis());
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        /*Toast.makeText(getApplicationContext(),"UlTIMO PARTO\n" + ultpart.getTime().toString()
                +"\n" + "FECHA Gestacion\n" + gestaction.getTime().toString()
                +"\n"+"DIFERENCIA DE FECHAS\n"+ (int) dayCount,Toast.LENGTH_LONG).show();
                */
        return (""+(int) dayCount);

    }

    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.date);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, year, month, day);//year, month, day);
        } else {
            return null;
        }
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1+1;
            day = i2;
            btn.setText(day +"/"+month+"/"+year);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
