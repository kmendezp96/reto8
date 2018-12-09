package com.example.pc.reto8.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pc.reto8.model.Empresa;

import java.util.ArrayList;
import java.util.List;

public class CompanyOperations {

    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            CompanyDBHandler.COLUMN_ID,
            CompanyDBHandler.COLUMN_NAME,
            CompanyDBHandler.COLUMN_WEB_PAGE,
            CompanyDBHandler.COLUMN_PHONE,
            CompanyDBHandler.COLUMN_EMAIL,
            CompanyDBHandler.COLUMN_TYPE,
            CompanyDBHandler.COLUMN_PRODUCTS

    };

    public CompanyOperations(Context context){
        dbhandler = new CompanyDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Empresa addCompany(Empresa empresa){
        ContentValues values  = new ContentValues();
        values.put(CompanyDBHandler.COLUMN_NAME, empresa.getNombre());
        values.put(CompanyDBHandler.COLUMN_WEB_PAGE, empresa.getUrl());
        values.put(CompanyDBHandler.COLUMN_PHONE, empresa.getNumero());
        values.put(CompanyDBHandler.COLUMN_EMAIL, empresa.getEmail());
        values.put(CompanyDBHandler.COLUMN_TYPE, empresa.getClasificacion());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS, empresa.getProductosYServicios());
        long insertid = database.insert(CompanyDBHandler.TABLE_COMPANY,null,values);
        empresa.setEmpresaId(insertid);
        return empresa;

    }

    // Getting single Employee
    public Empresa getCompany(long id) {
        open();
        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANY,allColumns,CompanyDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Empresa e = new Empresa(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return Employee
        return e;
    }

    public List<Empresa> getAllCompanies() {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANY,allColumns,null,null,null, null, null);

        List<Empresa> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Empresa empresa = new Empresa();
                empresa.setEmpresaId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                empresa.setNombre(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                empresa.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                empresa.setNumero(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                empresa.setClasificacion(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_WEB_PAGE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                companies.add(empresa);
            }
        }
        // return All Employees
        return companies;
    }

    public List<Empresa> getAllCompaniesFilterByType(String type) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANY,allColumns,CompanyDBHandler.COLUMN_TYPE + "=?",new String[]{type},null, null, null);

        List<Empresa> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Empresa empresa = new Empresa();
                empresa.setEmpresaId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                empresa.setNombre(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                empresa.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                empresa.setNumero(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                empresa.setClasificacion(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_WEB_PAGE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                companies.add(empresa);
            }
        }
        // return All Employees
        return companies;
    }

    public List<Empresa> getAllCompaniesFilterByName(String name) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANY,allColumns,CompanyDBHandler.COLUMN_NAME + " LIKE?",new String[]{"%"+name+ "%"},null, null, null);

        List<Empresa> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Empresa empresa = new Empresa();
                empresa.setEmpresaId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                empresa.setNombre(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                empresa.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                empresa.setNumero(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                empresa.setClasificacion(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_WEB_PAGE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                companies.add(empresa);
            }
        }
        // return All Employees
        return companies;
    }

    public List<Empresa> getAllCompaniesFilterByTypeAndName(String name, String type) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANY,allColumns,CompanyDBHandler.COLUMN_NAME + " LIKE?" + " and " +CompanyDBHandler.COLUMN_TYPE + "=?",new String[]{"%"+name+ "%",type},null, null, null);

        List<Empresa> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Empresa empresa = new Empresa();
                empresa.setEmpresaId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                empresa.setNombre(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                empresa.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                empresa.setNumero(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PHONE)));
                empresa.setClasificacion(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TYPE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_WEB_PAGE)));
                empresa.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS)));
                companies.add(empresa);
            }
        }
        // return All Employees
        return companies;
    }

    // Updating Employee
    public int updateCompany(Empresa empresa) {

        ContentValues values = new ContentValues();
        values.put(CompanyDBHandler.COLUMN_NAME, empresa.getNombre());
        values.put(CompanyDBHandler.COLUMN_EMAIL, empresa.getEmail());
        values.put(CompanyDBHandler.COLUMN_PHONE, empresa.getNumero());
        values.put(CompanyDBHandler.COLUMN_WEB_PAGE, empresa.getUrl());
        values.put(CompanyDBHandler.COLUMN_TYPE, empresa.getClasificacion().toString());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS, empresa.getProductosYServicios().toString());

        // updating row
        return database.update(CompanyDBHandler.TABLE_COMPANY, values,
                CompanyDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(empresa.getEmpresaId())});
    }

    // Deleting Employee
    public void removeCompany(Empresa empresa) {

        database.delete(CompanyDBHandler.TABLE_COMPANY, CompanyDBHandler.COLUMN_ID + "=" + empresa.getEmpresaId(), null);
    }

}
