package com.example.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Person_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "persons";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_persons_table=String.format("create table %s(%s integer primary key autoincrement, %s text)",TABLE_NAME,KEY_ID,KEY_NAME);
        db.execSQL(create_persons_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_persons_table=String.format("drop table if exists $s", TABLE_NAME);
        db.execSQL(drop_persons_table);
        onCreate(db);

    }
    public void addPerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,person.getName());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public void addPersonName(String PersonName) {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,PersonName);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<Person> getPersonsName() {
        ArrayList<Person> personList=new ArrayList<>();
        String query = "select "+ KEY_NAME +" from " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Person person = new Person(cursor.getString(0));
            personList.add(person);
            cursor.moveToNext();
        }
        return personList;
    }
    public void deletePeson(String pesonName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + " = ?", new String[] { String.valueOf(pesonName) });
        db.close();
    }
}
