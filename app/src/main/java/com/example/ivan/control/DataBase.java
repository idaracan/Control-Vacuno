package com.example.ivan.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by ivan on 3/07/16.
 */
public class DataBase extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE usuario (nombre STRING, prof STRING, tarjprof STRING)";
    String sqlCreate2 = "CREATE TABLE finca (nom STRING, dueño STRING, ubic STRING, tr STRING, sr STRING)";
    /*String sqlCreate3 = "CREATE TABLE vaca " +
            "( motD STRING, metD STRING, finca STRING, nomv STRING, numv STRING, fecha STRING, dias STRING," +
            " cc STRING, diag STRING, estprod STRING, tiemposec STRING, observ STRING)";*/
    String sqlCreate3 = "CREATE TABLE vaca (" +
            "  idvaca INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1," +
            "  motD VARCHAR(45) NULL DEFAULT NULL," +
            "  metD VARCHAR(45) NULL DEFAULT NULL," +
            "  nomv VARCHAR(45) NULL DEFAULT NULL," +
            "  numv VARCHAR(45) NULL DEFAULT NULL," +
            "  fecha VARCHAR(45) NULL DEFAULT NULL," +
            "  dias VARCHAR(45) NULL DEFAULT NULL," +
            "  cc VARCHAR(45) NULL DEFAULT NULL," +
            "  diag VARCHAR(45) NULL DEFAULT NULL," +
            "  estprod VARCHAR(45) NULL DEFAULT NULL," +
            "  tiemposec VARCHAR(45) NULL DEFAULT NULL," +
            "  finca VARCHAR(45) NULL DEFAULT NULL," +
            "  observ VARCHAR(45) NULL DEFAULT 'Sin Observaciones')";


    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS finca");
        db.execSQL("DROP TABLE IF EXISTS vaca");
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
    }
}
