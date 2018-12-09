package com.example.pc.reto8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jage.sqliteapp.R;
import com.example.pc.reto8.db.CompanyOperations;
import com.example.pc.reto8.model.Empresa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewAllCompanies extends AppCompatActivity {

    private CompanyOperations companyOps;
    List<Empresa> companies;
    private StableArrayAdapter adapter;
    private ArrayList<String> list;
    private SharedPreferences mPrefs;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_companies);
        listview = (ListView) findViewById(R.id.list);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        refreshList();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                startActivityForResult(new Intent(this, Settings.class), 0);
                return true;
            case R.id.clear_fiter:
                SharedPreferences.Editor ed = mPrefs.edit();
                ed.putString("filter_by_type", "Not filter");
                ed.putString("filter_by_name","");
                ed.commit();
                refreshList();
                return true;
        }
        return false;
    }

    public void refreshList(){
        list = new ArrayList<String>();
        companyOps = new CompanyOperations(this);
        String mFilterT = mPrefs.getString("filter_by_type","Not filter");
        String mFilterN = mPrefs.getString("filter_by_name","");

        companyOps.open();
        if(!mFilterT.equals("Not filter")){
            if(!mFilterN.equals("")){
                companies = companyOps.getAllCompaniesFilterByTypeAndName(mFilterN,mFilterT);
            }else {
            companies = companyOps.getAllCompaniesFilterByType(mFilterT);
            }
        } else if(!mFilterN.equals("")){
            companies = companyOps.getAllCompaniesFilterByName(mFilterN);
        } else {
            companies = companyOps.getAllCompanies();
        }

        companyOps.close();
        if(companies.size() > 0) {
            for (Empresa comp : companies) {
                list.add(comp.getEmpresaId() + "- N: "
                        + comp.getNombre()+ " T: " +comp.getClasificacion());
            }
        }
        adapter = new StableArrayAdapter(this,
                R.xml.company_item, R.id.firstLine, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if (item != null) {
                    String[] parts = item.split("\\-"); // String array, each element is text between dots
                    String beforeFirstDot = parts[0];    // Text before the first dot

                    SharedPreferences.Editor ed = mPrefs.edit();
                    ed.putLong("company_id", Long.valueOf(beforeFirstDot));
                    ed.commit();
                    startActivityForResult(new Intent(ViewAllCompanies.this, viewCompany.class), 0);
                    list.clear();
                }
            }

        });

    }
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  int textViewResourceId2,
                                  List<String> objects) {
            super(context, textViewResourceId, textViewResourceId2, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
