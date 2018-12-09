package com.example.pc.reto8;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jage.sqliteapp.R;
import com.example.pc.reto8.db.CompanyOperations;

public class MainActivity extends AppCompatActivity {

    private Button addCompanyButton;
    private Button deleteCompanyButton;
    private Button viewAllCompanyButton;
    private CompanyOperations companyOps;
    private static final String EXTRA_EMP_ID = "com.androidtutorialpoint.empId";
    private static final String EXTRA_ADD_UPDATE = "com.androidtutorialpoint.add_update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addCompanyButton = (Button) findViewById(R.id.button_add_company);
        deleteCompanyButton = (Button) findViewById(R.id.button_delete_company);
        viewAllCompanyButton = (Button)findViewById(R.id.button_view_companys);

        companyOps = new CompanyOperations(MainActivity.this);

        addCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddUpdateCompany.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });

        deleteCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmpIdAndRemoveEmp();
            }
        });
        viewAllCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewAllCompanies.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.company_menu, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.menu_item_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    public void getEmpIdAndRemoveEmp(){

        LayoutInflater li = LayoutInflater.from(this);
        View getEmpIdView = li.inflate(R.layout.dialog_get_emp_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set dialog_get_emp_id.xml to alertdialog builder
        alertDialogBuilder.setView(getEmpIdView);

        final EditText userInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        View getEmpIdView = li.inflate(R.layout.dialog_confirm_delete, null);

                        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(MainActivity.this);
                        // set dialog_get_emp_id.xml to alertdialog builder
                        final String delete_id =  userInput.getText().toString();
                        alertDialogBuilder2.setView(getEmpIdView);
                        alertDialogBuilder2
                                .setCancelable(true)
                                .setPositiveButton("Delete",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        companyOps = new CompanyOperations(MainActivity.this);
                                        companyOps.removeCompany(companyOps.getCompany(Long.parseLong(delete_id)));
                                        Toast t = Toast.makeText(MainActivity.this,"Empresa removed successfully!",Toast.LENGTH_SHORT);
                                        t.show();
                                    }
                                }).create()
                                .show();
                    }
                }).create()
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        companyOps.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        companyOps.close();

    }
    
}
