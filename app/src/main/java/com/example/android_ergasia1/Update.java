package com.example.android_ergasia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
Kollias Anastasios MPSP19018
Ergasia 1 - Android
 */

public class Update extends AppCompatActivity {

    dbHelper db;
    EditText e1,e2,e3,e4,e5,e6;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new dbHelper(this);
        e1=(EditText)findViewById(R.id.titleupd);
        e2=(EditText)findViewById(R.id.directorupd);
        e3=(EditText)findViewById(R.id.durationupd);
        e4=(EditText)findViewById(R.id.genreupd);
        e5=(EditText)findViewById(R.id.pdyearupd);
        e6=(EditText)findViewById(R.id.ratingupd);
        b1=(Button)findViewById(R.id.searchupd);
        b2=(Button)findViewById(R.id.updatemov);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                if (s1.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the movie's title.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkmovtitle = db.checkmovtitle(s1);
                    if(checkmovtitle==false)
                    {
                        Cursor c = db.moviereader(s1);
                        c.moveToNext();
                        e2.setText(c.getString(c.getColumnIndex("director")));
                        e3.setText(c.getString(c.getColumnIndex("duration")));
                        e4.setText(c.getString(c.getColumnIndex("genre")));
                        e5.setText(c.getString(c.getColumnIndex("pdyear")));
                        e6.setText(c.getString(c.getColumnIndex("rating")));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"There is not such movie with this title.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
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
                else
                {
                    Boolean checkmovtitle = db.checkmovtitle(s1);
                    if (checkmovtitle==false)
                    {
                        Boolean updatemov = db.updatemovie(s1,s2,s3,s4,s5,s6);
                        if(updatemov==true){
                            Toast.makeText(getApplicationContext(),"Movie Updated Successfully.",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Update.this,Menu.class); //going to the menu activity
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Movie Updating Failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"There is not such movie with this title.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
