package com.example.pc.reto8.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompanyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMPANY = "company";
    public static final String COLUMN_ID = "companyID";
    public static final String COLUMN_NAME = "companyName";
    public static final String COLUMN_WEB_PAGE = "webPage";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_TYPE= "type";
    public static final String COLUMN_PRODUCTS= "products";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_COMPANY + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_WEB_PAGE + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_PRODUCTS + " TEXT" +
                    ")";


    public CompanyDBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COMPANY);
        db.execSQL(TABLE_CREATE);
    }
}
