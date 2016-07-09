package com.example.ivan.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Titlescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlescreen);
        /*  LINKS TO OTHER VIEWS    */
        final Intent tousrset = new Intent(this, Main.class);
        final Intent tofncset = new Intent(this, Finca.class);
        final Intent tocowset = new Intent(this, Vaca.class);
        final Intent toreps   = new Intent(this, ReportsX.class);

        /*  Buttons */
        Button tousr = (Button) findViewById(R.id.tousr);
        Button tofnc = (Button) findViewById(R.id.tofnc);
        Button tocow = (Button) findViewById(R.id.tocow);
        Button torep = (Button) findViewById(R.id.toreportes);

        tousr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tousrset);
            }
        });
        torep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(toreps);
            }
        });

        tofnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tofncset);
            }
        });
        tocow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tocowset);
            }
        });

    }

}
