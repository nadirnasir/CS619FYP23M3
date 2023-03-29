package com.example.cs619fypm3mc180406254;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    dbAgent dba;
    TextInputEditText cnic, fname, lname, address, phone, rid, email;
    Switch role;
    String roleName = "Resident";
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dba = new dbAgent(this, null, null, 1);

        cnic = findViewById(R.id.cnic);
        fname = findViewById(R.id.FirstName);
        lname = findViewById(R.id.LastName);
        address = findViewById(R.id.Address);
        phone = findViewById(R.id.Phone);
        rid = findViewById(R.id.ResidentID);
        email = findViewById(R.id.Email);
        role = findViewById(R.id.Type);
        register = findViewById(R.id.Register);

        role.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    roleName = "Service Provider";
                } else {
                    roleName = "Resident";
                }
                Toast.makeText(SignUpActivity.this, roleName, Toast.LENGTH_LONG).show();
            }
        });

        Button cancel = findViewById(R.id.btnCancel);

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(main);
                    }
                }
        );
        register();
    }

    public void register() {
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = dba.insertData(
                                cnic.getText().toString(),
                                email.getText().toString(),
                                roleName,
                                fname.getText().toString(),
                                lname.getText().toString(),
                                rid.getText().toString(),
                                address.getText().toString(),
                                phone.getText().toString()

                        );
                        if (isInserted) {
                            Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Intent signupAfter = new Intent(getApplicationContext(), SignUpAfter.class);
                            startActivity(signupAfter);
                        } else
                            Toast.makeText(SignUpActivity.this, "Failure", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}