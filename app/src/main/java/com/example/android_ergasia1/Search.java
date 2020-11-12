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

public class Search extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        e1=(EditText)findViewById(R.id.titlesrch);
        e2=(EditText)findViewById(R.id.parttitlesrch);
        e3=(EditText)findViewById(R.id.directorsrch);
        b1=(Button)findViewById(R.id.searchtitle);
        b2=(Button)findViewById(R.id.searchtitlepart);
        b3=(Button)findViewById(R.id.searchdirector);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                if (s1.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the movie's title.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Search.this,titleRes.class); //going to the title results activity
                    i.putExtra("transfer",s1); //sending the title to the next activity so it can process the search operation
                    startActivity(i);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s2 = e2.getText().toString();
                if (s2.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill a part of the movie's title.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Search.this, listResTitle.class); //going to the list results activity
                    i.putExtra("transfer",s2); //sending the part of the movie title to the next activity so it can process the search operation
                    startActivity(i);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s3 = e3.getText().toString();
                if (s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the movie's director.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Search.this, listResDirector.class); //going to the list results activity
                    i.putExtra("transfer",s3); //sending the director to the next activity so it can process the search operation
                    startActivity(i);
                }
            }
        });
    }
}
