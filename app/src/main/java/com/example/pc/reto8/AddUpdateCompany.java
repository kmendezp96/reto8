package com.example.pc.reto8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jage.sqliteapp.R;
import com.example.pc.reto8.db.CompanyOperations;
import com.example.pc.reto8.model.Empresa;

public class AddUpdateCompany  extends AppCompatActivity {

    private static final String EXTRA_EMP_ID = "com.androidtutorialpoint.empId";
    private static final String EXTRA_ADD_UPDATE = "com.androidtutorialpoint.add_update";
    private static final String DIALOG_DATE = "DialogDate";
    private RadioGroup radioGroup;
    private RadioButton consultRadioButton,softRadioButton,fabRadioButton;
    private EditText firstNameEditText;
    private EditText emailEditText;
    private EditText websiteEditText;
    private EditText phoneEditText;
    private EditText productsEditText;
    private Button addUpdateButton;
    private Empresa newEmpresa;
    private Empresa oldEmpresa;
    private String mode;
    private long empId;
    private CompanyOperations employeeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);
        newEmpresa = new Empresa();
        oldEmpresa = new Empresa();
        firstNameEditText = (EditText)findViewById(R.id.edit_text_name);
        emailEditText = (EditText)findViewById(R.id.edit_text_email);
        phoneEditText = (EditText) findViewById(R.id.edit_text_phone);
        productsEditText = (EditText) findViewById(R.id.edit_text_products);
        radioGroup = (RadioGroup) findViewById(R.id.radio_type);
        consultRadioButton = (RadioButton) findViewById(R.id.radio_1);
        softRadioButton = (RadioButton) findViewById(R.id.radio_2);
        fabRadioButton = (RadioButton) findViewById(R.id.radio_3);
        websiteEditText = (EditText)findViewById(R.id.edit_text_website);
        addUpdateButton = (Button)findViewById(R.id.button_add_update_employee);
        employeeData = new CompanyOperations(this);
        employeeData.open();


        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if(mode.equals("Update")){

            addUpdateButton.setText("Update Empresa");
            empId = getIntent().getLongExtra(EXTRA_EMP_ID,0);

            initializeCompany(empId);

        } else {
            newEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.CONSULTORIA.toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.radio_1) {
                    newEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.CONSULTORIA.toString());
                    if(mode.equals("Update")){
                        oldEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.CONSULTORIA.toString());
                    }
                } else if (checkedId == R.id.radio_2) {
                    newEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.DESAROLLO_A_LA_MEDIDA.toString());
                    if(mode.equals("Update")){
                        oldEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.DESAROLLO_A_LA_MEDIDA.toString());
                    }
                } else if (checkedId == R.id.radio_3) {
                    newEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.FABRICA_DE_SOFTWARE.toString());
                    if(mode.equals("Update")){
                        oldEmpresa.setClasificacion(Empresa.ClasificacionEmpresa.FABRICA_DE_SOFTWARE.toString());
                    }
                }
            }

        });

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode.equals("Add")) {
                    newEmpresa.setNombre(firstNameEditText.getText().toString());
                    newEmpresa.setEmail(emailEditText.getText().toString());
                    newEmpresa.setNumero(phoneEditText.getText().toString());
                    newEmpresa.setProductosYServicios(productsEditText.getText().toString());
                    newEmpresa.setUrl(websiteEditText.getText().toString());
                    employeeData.addCompany(newEmpresa);
                    Toast t = Toast.makeText(AddUpdateCompany.this, "Empresa "+ newEmpresa.getNombre() + "has been added successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateCompany.this,MainActivity.class);
                    startActivity(i);
                }else {
                    oldEmpresa.setNombre(firstNameEditText.getText().toString());
                    oldEmpresa.setEmail(emailEditText.getText().toString());
                    oldEmpresa.setNumero(phoneEditText.getText().toString());
                    oldEmpresa.setProductosYServicios(productsEditText.getText().toString());
                    oldEmpresa.setUrl(websiteEditText.getText().toString());
                    employeeData.updateCompany(oldEmpresa);
                    Toast t = Toast.makeText(AddUpdateCompany.this, "Empresa "+ oldEmpresa.getNombre() + " has been updated successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateCompany.this,viewCompany.class);
                    startActivity(i);

                }


            }
        });


    }

    private void initializeCompany(long empId) {
        oldEmpresa = employeeData.getCompany(empId);
        firstNameEditText.setText(oldEmpresa.getNombre());
        emailEditText.setText(oldEmpresa.getEmail());
        phoneEditText.setText(oldEmpresa.getNumero());
        productsEditText.setText(oldEmpresa.getProductosYServicios());
        if(oldEmpresa.getClasificacion().equals(Empresa.ClasificacionEmpresa.CONSULTORIA.toString())){
            radioGroup.check(R.id.radio_1);
        } else if(oldEmpresa.getClasificacion().equals(Empresa.ClasificacionEmpresa.DESAROLLO_A_LA_MEDIDA.toString())){
            radioGroup.check(R.id.radio_2);
        } else if (oldEmpresa.getClasificacion().equals(Empresa.ClasificacionEmpresa.FABRICA_DE_SOFTWARE.toString())){
            radioGroup.check(R.id.radio_3);
        }


        websiteEditText.setText(oldEmpresa.getUrl());
    }

}
