package com.example.challenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister, btnPickImage;
    private EditText editTextName, editTextEmail, editTextPassword, editTextRePassword;
    private CheckBox checkBoxAgree;
    private RadioGroup radioGroupGender;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnPickImage = findViewById(R.id.btnImage);
        editTextName = findViewById(R.id.editTxtName);
        editTextEmail = findViewById(R.id.editTxtEmail);
        editTextPassword = findViewById(R.id.editTxtPassword);
        editTextRePassword = findViewById(R.id.editTxtRePassword);

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Image has been selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean comparePasswords(EditText pass1, EditText pass2){
        if(pass1.getText().equals(pass2.getText()))
            return true;
        return false;
    }
}