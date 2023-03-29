package com.example.cs619fypm3mc180406254;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class SignUpAfter extends AppCompatActivity {
    dbAgent dba;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_after);
        dba = new dbAgent(this, null, null, 1);
        data = dba.getData();

        if (data.getCount() != 0) {
            StringBuffer databuffer = new StringBuffer();
            data.moveToLast();
            databuffer.append("Name: " + data.getString(3) + " " + data.getString(4) + "\n");
            databuffer.append("Role: " + data.getString(2) + "\n");
            databuffer.append("CNIC: " + data.getString(0) + "\n");
            databuffer.append("Resident ID: " + data.getString(5) + "\n");
            databuffer.append("Phone: " + data.getString(7) + "\n");
            databuffer.append("Address: " + data.getString(6) + "\n");
            databuffer.append("User ID / Email: " + data.getString(1) + "\n");
            TextView cnic = findViewById(R.id.phCNIC);

            cnic.setText(databuffer.toString());

            // on below line we are initializing variables with ids.
            Button exitBtn = findViewById(R.id.exitApp);

            // on below line we are adding click listener for our button
            exitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                }
            });
        }
    }
}