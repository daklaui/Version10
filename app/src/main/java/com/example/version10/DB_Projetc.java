package com.example.version10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.REGIME;
import Model.USER;
import Model.USER_PROFILE;

public class DB_Projetc extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BD.db";
    /*************************Table USER *****************************/
    public static final String USER_TABLE_NAME = "USER";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_EMAIL = "Email";
    public static final String USER_COLUMN_MDP = "Password";
    /*************************Fin Table USER *****************************/

    /*************************Table USER PROFILE *****************************/
    public static final String USER_PROFILE_TABLE_NAME = "USER_PROFILE";
    public static final String USER_PROFILE_COLUMN_ID = "id";
    public static final String USER_PROFILE_NOM = "Nom";
    public static final String USER_PROFILE_COLUMN_PRENOM= "Prenom";
    public static final String USER_PROFILE_COLUMN_DATE_DE_NAISSENCE= "Date_De_Naissence";
    public static final String USER_PROFILE_COLUMN_SEXE= "Sexe";
    public static final String USER_PROFILE_COLUMN_TAILLE= "Taille";
    public static final String USER_PROFILE_COLUMN_POIDS= "Poids";

    public DB_Projetc(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*************************Fin Table USER PROFILE  *****************************/

    /*************************Table REGIME *****************************/
    public static final String REGIME_TABLE_NAME = "REGIME";
    public static final String REGIME_COLUMN_ID = "id";
    public static final String TYPE_REGIME_COLUMN_EMAIL = "Type_Regime";
    public static final String DATE_CREATION_COLUMN_EMAIL = "Date_Creation";
    public static final String PERIODE_COLUMN_MDP = "Periode_Regime";
    public static final String NBKILO_COLUMN_MDP = "Nb_Kilo_Regime";
    public static final String POIDS_FINAL_COLUMN_MDP = "Poids_Final";
    /*************************Fin Table REGIME *****************************/



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+USER_TABLE_NAME +
                        " ( "+USER_COLUMN_ID+" integer primary key AUTOINCREMENT, "+
                              USER_COLUMN_EMAIL+" text, "+
                              USER_COLUMN_MDP+" text)"
        );

        db.execSQL(
                "create table "+USER_PROFILE_TABLE_NAME +
                        " ( "+USER_PROFILE_COLUMN_ID+" integer primary key AUTOINCREMENT,"+
                        USER_PROFILE_NOM+" text,"+
                        USER_PROFILE_COLUMN_PRENOM+" text,"+
                        USER_PROFILE_COLUMN_DATE_DE_NAISSENCE+" text,"+
                        USER_PROFILE_COLUMN_SEXE+" text,"+
                        USER_PROFILE_COLUMN_TAILLE+" REAL,"+
                        USER_PROFILE_COLUMN_POIDS+" REAL)"
        );

        db.execSQL(
                "create table "+REGIME_TABLE_NAME +
                        " ( "+REGIME_COLUMN_ID+" integer primary key AUTOINCREMENT,"+
                        TYPE_REGIME_COLUMN_EMAIL+" text,"+
                        DATE_CREATION_COLUMN_EMAIL+" text,"+
                        PERIODE_COLUMN_MDP+" integer,"+
                        NBKILO_COLUMN_MDP+" REAL,"+
                        POIDS_FINAL_COLUMN_MDP+" REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+USER_PROFILE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+REGIME_TABLE_NAME);
        onCreate(db);
    }

    public boolean insert_new_profile (USER_PROFILE user_profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_PROFILE_NOM, user_profile.getNom());
        contentValues.put(USER_PROFILE_COLUMN_PRENOM, user_profile.getPrenom());
        contentValues.put(USER_PROFILE_COLUMN_DATE_DE_NAISSENCE, user_profile.getDate_Naissence());
        contentValues.put(USER_PROFILE_COLUMN_SEXE, user_profile.getSexe());
        contentValues.put(USER_PROFILE_COLUMN_TAILLE, user_profile.getTaille());
        contentValues.put(USER_PROFILE_COLUMN_POIDS, user_profile.getPoids());
        db.insert(USER_PROFILE_TABLE_NAME, null, contentValues);
        return true;
    }

    public long insert_new_user (USER user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_EMAIL, user.getEmail());
        contentValues.put(USER_COLUMN_MDP, user.getPassword());
        return db.insert(USER_TABLE_NAME, null, contentValues);

    }


    public Cursor getData(USER user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+USER_TABLE_NAME +" where "+USER_COLUMN_EMAIL+" = '"+user.getEmail()+"' and  "+USER_COLUMN_MDP+" = '"+user.getPassword()+"'", null );
        return res;
    }


    public boolean insert_regime(REGIME regime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE_REGIME_COLUMN_EMAIL, regime.getType_Regime());
        contentValues.put(DATE_CREATION_COLUMN_EMAIL, regime.getDate_Creation());
        contentValues.put(PERIODE_COLUMN_MDP, regime.getPeriode_Regime());
        contentValues.put(NBKILO_COLUMN_MDP, regime.getNb_Kilo_Regime());
        contentValues.put(POIDS_FINAL_COLUMN_MDP, regime.getPoids_Final());
        db.insert(REGIME_TABLE_NAME, null, contentValues);
        return true;
    }

}
