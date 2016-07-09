package com.example.ivan.control;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by ivan on 22/06/16.
 */
public class BaseD extends SQLiteOpenHelper{

    String sqlcreate = "CREATE TABLE registro (name TEXT, prof TEXT, tarjprof TEXT, finca TEXT, prop TEXT, ubic TEXT,  tr TEXT, sr TEXT, motdiag TEXT, metdiag TEXT, nanimal TEXT,nunimal TEXT, gestac TEXT, diag TEXT, est TEXT, num TEXT, sec TEXT, coment TEXT, fecha TEXT)";
    String sqlCreate2 = "CREATE TABLE datos (name TEXT, prof TEXT, tarjprof TEXT)";

    public BaseD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sqlcreate);
        db.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS registro");
        db.execSQL("DROP TABLE IF EXISTS datos");
        db.execSQL(sqlcreate);

    }
}
