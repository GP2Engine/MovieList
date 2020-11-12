package com.example.android_ergasia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/*
Kollias Anastasios MPSP19018
Ergasia 1 - Android
 */

public class titleRes extends AppCompatActivity {

    dbHelper db;
    TextView t1,t2,t3,t4,t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_res);
        db = new dbHelper(this);
        t1=(TextView)findViewById(R.id.titleres);
        t2=(TextView)findViewById(R.id.directorres);
        t3=(TextView)findViewById(R.id.durationres);
        t4=(TextView)findViewById(R.id.genreres);
        t5=(TextView)findViewById(R.id.pdyearres);
        t6=(TextView)findViewById(R.id.ratingres);
        String title = getIntent().getStringExtra("transfer"); //getting the title from the previous activity

        Boolean checkmovtitle = db.checkmovtitle(title);
        if(checkmovtitle==false)
        {
            Cursor c = db.moviereader(title);
            c.moveToNext();
            t1.setText(c.getString(c.getColumnIndex("title")));
            t2.setText(c.getString(c.getColumnIndex("director")));
            t3.setText(c.getString(c.getColumnIndex("duration")));
            t4.setText(c.getString(c.getColumnIndex("genre")));
            t5.setText(c.getString(c.getColumnIndex("pdyear")));
            t6.setText(c.getString(c.getColumnIndex("rating")));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"There is not such movie with this title.",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(titleRes.this,Search.class); //going to the search activity
            startActivity(i);
        }
    }
}
