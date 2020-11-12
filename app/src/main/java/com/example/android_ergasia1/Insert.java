package com.example.android_ergasia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
Kollias Anastasios
 */

public class Insert extends AppCompatActivity {

    dbHelper db;
    EditText e1,e2,e3,e4,e5,e6;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        db = new dbHelper(this);
        e1=(EditText)findViewById(R.id.title);
        e2=(EditText)findViewById(R.id.director);
        e3=(EditText)findViewById(R.id.duration);
        e4=(EditText)findViewById(R.id.genre);
        e5=(EditText)findViewById(R.id.pdyear);
        e6=(EditText)findViewById(R.id.rating);
        b1=(Button)findViewById(R.id.insertmov);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the empty fields.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkmovtitle = db.checkmovtitle(s1);
                    if (checkmovtitle==true)
                    {
                        Boolean insert = db.insertMovie(s1,s2,s3,s4,s5,s6);
                        if(insert==true){
                            Toast.makeText(getApplicationContext(),"Movie Inserted Successfully.",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Insert.this,Menu.class); //going to the menu activity
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Movie Insertion Failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"A movie with this title already exists.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
