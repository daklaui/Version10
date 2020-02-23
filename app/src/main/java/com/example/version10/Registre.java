package com.example.version10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import Model.USER;
import Model.USER_PROFILE;

public class Registre extends AppCompatActivity {

    EditText Nom,Prenom,Date_naissence,Taille,Poids,Email,Password;
    CheckBox Homme,Femme;
    Button Register_btn;
    String Sexe="";
    DB_Projetc db_projetc;
    SharedPreferences sharedpreferences;
    DatePickerDialog.OnDateSetListener dateSetListener;
    USER user;
    public static final String mypreference = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        db_projetc=new DB_Projetc(this);
        Nom=(EditText)findViewById(R.id.Nom);
        Prenom=(EditText)findViewById(R.id.Prenom);
        Date_naissence=(EditText)findViewById(R.id.Date_Nais);
        Taille=(EditText)findViewById(R.id.Taille);
        Poids=(EditText)findViewById(R.id.Poids);
        Email=(EditText)findViewById(R.id.Email);
        Password=(EditText)findViewById(R.id.Password);
        Homme=(CheckBox) findViewById(R.id.H);
        Femme=(CheckBox)findViewById(R.id.F);
        Register_btn=(Button) findViewById(R.id.Register);

/*******************************CheckBox***********************************************/
        Homme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sexe="H";
                if(Homme.isChecked()){
                    Homme.setChecked(true);
                    Femme.setEnabled(false);
                }else{
                    Homme.setChecked(false);
                    Femme.setEnabled(true);
                }
            }
        });

        Femme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sexe="F";
                if(Femme.isChecked()){
                    Femme.setChecked(true);
                    Homme.setEnabled(false);
                }else{
                    Femme.setChecked(false);
                    Homme.setEnabled(true);
                }
            }
        });
/************************************FIN****************************************************/

/*******************************************Date*************************************************/
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        Date_naissence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registre.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day
                );

                datePickerDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT)
                );
                datePickerDialog.show();
            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String day_1="";
                String Month_1="";
                if(dayOfMonth<10)
                {
                    day_1="0"+dayOfMonth;
                }
                else
                {
                    day_1=String.valueOf(dayOfMonth);
                }
                if(month<10)
                {
                    Month_1="0"+month;
                }
                else
                {
                    Month_1=String.valueOf(month);
                }
                String date=day_1+"-"+Month_1+"-"+year;
                Date_naissence.setText(date);
            }
        };
/**********************************************Fin**********************************************/


        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=new USER();
                user.setEmail(Email.getText().toString());
                user.setPassword(Password.getText().toString());
                long id_user=db_projetc.insert_new_user(user);
                USER_PROFILE user_profile = new USER_PROFILE();
                user_profile.setNom(Nom.getText().toString());
                user_profile.setPrenom(Prenom.getText().toString());
                user_profile.setDate_Naissence(Date_naissence.getText().toString());
                user_profile.setPoids(Float.parseFloat(Poids.getText().toString()));
                user_profile.setTaille(Float.parseFloat(Taille.getText().toString()));
                user_profile.setId_user(id_user);
                user_profile.setSexe(Sexe);
                if(db_projetc.insert_new_profile(user_profile))
                {
                    sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Is_Inscrip","1");
                    editor.commit();
                    Intent in = new Intent(Registre.this,MainActivity.class);
                    Log.e("mdp",user.getPassword());
                    startActivity(in);

                }
                else
                {
                    Toast.makeText(Registre.this,"Merci de verifier",Toast.LENGTH_LONG).show();

                }
            }
        });



    }
}
