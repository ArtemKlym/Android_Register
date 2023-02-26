package com.example.challenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private static final String TAG = "MyActivity";
    private Button btnRegister, btnPickImage;
    private EditText editTextName, editTextEmail, editTextPassword, editTextRePassword;
    private CheckBox checkBoxAgree;
    private RadioGroup radioGroupGender;
    private ArrayList<Contact> contacts;
    private TextView txtWarningName, txtWarningEmail;

    private ArrayList<String> countries;

    private Spinner spinnerCountries;
    private RelativeLayout parent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initArrayLists();

        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                countries
        );

        spinnerCountries.setAdapter(countriesAdapter);


        btnPickImage.setOnClickListener(view -> Toast.makeText(MainActivity.this,
                                "Image has been selected",
                                Toast.LENGTH_SHORT).show());

        btnRegister.setOnClickListener(this);
    }

    private void initArrayLists() {
        contacts = new ArrayList<>();
        countries = new ArrayList<>();

        countries.add("Germany");
        countries.add("Ukraine");
        countries.add("USA");
        countries.add("India");
    }

    private void initViews () {
        Log.d(TAG, "initViews: Started");
        btnRegister = findViewById(R.id.btnRegister);
        btnPickImage = findViewById(R.id.btnImage);
        editTextName = findViewById(R.id.editTxtName);
        editTextEmail = findViewById(R.id.editTxtEmail);
        editTextPassword = findViewById(R.id.editTxtPassword);
        editTextRePassword = findViewById(R.id.editTxtRePassword);
        checkBoxAgree = findViewById(R.id.checkboxAgreemant);
        radioGroupGender = findViewById(R.id.radioGroup);
        spinnerCountries = findViewById(R.id.spinnerCountry);
        txtWarningEmail = findViewById(R.id.txtWarningEmail);
        txtWarningName = findViewById(R.id.txtWarningName);
        parent = findViewById(R.id.parent);
    }

    public void clearFields(){
        Log.d(TAG,"clearFields: Started");
        editTextName.getText().clear();
        editTextEmail.getText().clear();
        editTextPassword.getText().clear();
        editTextRePassword.getText().clear();
        radioGroupGender.clearCheck();
        checkBoxAgree.setChecked(false);
    }

    @Override
    public void onClick(View view) {
        if(validateData()) {
            txtWarningEmail.setVisibility(View.INVISIBLE);
            txtWarningName.setVisibility(View.INVISIBLE);

            contacts.add(new Contact(editTextName.getText().toString(),
                    editTextEmail.getText().toString(),
                    editTextPassword.getText().toString(),
                    getGender(radioGroupGender),
                    spinnerCountries.getSelectedItem().toString()));
            clearFields();
        }
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        if(editTextName.getText().toString().equals("")){
            txtWarningName.setVisibility(View.VISIBLE);
            return false;
        }
        if(editTextEmail.getText().toString().equals("")){
            txtWarningEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if(!comparePasswords(editTextPassword, editTextRePassword)) {
            Snackbar.make(parent,"Password doesn't match",Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", view -> {
                        editTextRePassword.getText().clear();
                        editTextPassword.getText().clear();
                    }).show();
            return false;
        }
        return true;
    }

    public String getGender(RadioGroup radioGroup){
        int selectedID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = findViewById(selectedID);

        String gender = radioButton.getText().toString();

        return gender.toLowerCase();
    }
    public boolean comparePasswords(EditText pass1, EditText pass2){
        return (pass1.getText().toString().equals(pass2.getText().toString())) && (!pass1.getText().toString().equals("") && !pass2.getText().toString().equals(""));
    }
}