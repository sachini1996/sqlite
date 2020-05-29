package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

import static android.os.Build.ID;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";
    public static final String TABLE_NAME = "Users_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                " CREATE TABLE " + UserMaster.Users.TABLE_NAME+"("+
                        UserMaster.Users._ID + "INTEGER PRIMARY KEY,"+
                        UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT,"+
                        UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(db);
    }
    public void addInfo(String UserName, String Password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues Values = new ContentValues();
       Values.put(UserMaster.Users.COLUMN_NAME_USERNAME, UserName;
       Values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, Password);


        long newRowId = db.insert(UserMaster.Users.TABLE_NAME, null, Values);
        //if (result == -1)
            //return false;
        //else
            //return true;
    }
    public List ReadAllInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,

        };
        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME+"DECS";
        Cursor cursor = db.query{
            UserMaster.Users.TABLE_NAME,
            projection,
            null,null,null,null,sortOrder);

        }
        public Integer deleteInfo(String ID){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TABLE_NAME, "ID = ?",new String[] {ID});
        }

    }
    public boolean updateInfo(String Id, String UserName, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Id);
        contentValues.put(COL_2,UserName;
        contentValues.put(COL_3,Password);

        db.update(TABLE_NAME, contentValues, "Id = ?",new String[] {Id} );
        return true;
    }
}
