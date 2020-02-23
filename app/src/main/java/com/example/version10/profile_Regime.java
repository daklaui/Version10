package com.example.version10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import Model.REGIME;
import Model.USER;
import Model.USER_PROFILE;

public class profile_Regime extends AppCompatActivity {
    Button confirmer;
    EditText nouveauPoids;
    CheckBox c1, c2, c3, c4;
    REGIME regime;
    String Type_Poid="";
    DB_Projetc db_projetc;
    double Nb_kilo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__regime);
        db_projetc=new DB_Projetc(this);
        confirmer = findViewById(R.id.Confirmer);
        nouveauPoids = findViewById(R.id.NvPoids);
        c1 = findViewById(R.id.Case1);
        c2 = findViewById(R.id.Case2);
        c3 = findViewById(R.id.Case3);
        c4 = findViewById(R.id.Case4);


        /*******************************CheckBox***********************************************/
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type_Poid = "Case1";
                Nb_kilo=0.25;
                if (c1.isChecked()) {
                    c1.setChecked(true);
                    c2.setEnabled(false);
                    c3.setEnabled(false);
                    c4.setEnabled(false);
                } else {
                    c1.setChecked(false);
                    c2.setEnabled(true);
                    c3.setEnabled(true);
                    c4.setEnabled(true);
                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type_Poid = "Case2";
                Nb_kilo=0.5;
                if (c2.isChecked()) {
                    c2.setChecked(true);
                    c1.setEnabled(false);
                    c3.setEnabled(false);
                    c4.setEnabled(false);
                } else {
                    c2.setChecked(false);
                    c1.setEnabled(true);
                    c3.setEnabled(true);
                    c4.setEnabled(true);
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type_Poid = "Case3";
                Nb_kilo=0.75;
                if (c3.isChecked()) {
                    c3.setChecked(true);
                    c1.setEnabled(false);
                    c2.setEnabled(false);
                    c4.setEnabled(false);
                } else {
                    c3.setChecked(false);
                    c1.setEnabled(true);
                    c2.setEnabled(true);
                    c4.setEnabled(true);
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Type_Poid = "Case4";
                Nb_kilo=1;
                if (c4.isChecked()) {
                    c4.setChecked(true);
                    c1.setEnabled(false);
                    c2.setEnabled(false);
                    c3.setEnabled(false);
                } else {
                    c4.setChecked(false);
                    c1.setEnabled(true);
                    c2.setEnabled(true);
                    c3.setEnabled(true);
                }
            }
        });
/************************************FIN****************************************************/
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regime=new REGIME();
                regime.setType_Regime(Type_Poid);
                regime.setNb_Kilo_Regime(Nb_kilo);
                  regime.setDate_Creation(year+"-"+(month+1)+"-"+day);
                  regime.setPeriode_Regime(1);
                  regime.setPoids_Final(Double.parseDouble(nouveauPoids.getText().toString()));
                if(db_projetc.insert_regime(regime))
                {

                    Toast.makeText(profile_Regime.this,"bien enregistrer",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(profile_Regime.this,"Merci de verifier",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
