package com.example.version10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.USER;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    EditText Email,Password;
    Button Login;
    DB_Projetc db_projetc;
    USER user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email=(EditText)findViewById(R.id.Login);
        Password=(EditText)findViewById(R.id.MDP);
        Login=(Button)findViewById(R.id.cirLoginButton) ;
        db_projetc=new DB_Projetc(this);
sharedpreferences=getSharedPreferences(mypreference,
        Context.MODE_PRIVATE);

        if(!sharedpreferences.getString("Is_Inscrip","").contains("1"))
        {
            startActivity(new Intent(MainActivity.this,Registre.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new USER();
                user.setEmail(Email.getText().toString());
                user.setPassword(Password.getText().toString());
                if(db_projetc.getData(user).getCount()>0)
                {
                    startActivity(new Intent(MainActivity.this,Profile.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Merci de verifier votre login ou mot de passe",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
