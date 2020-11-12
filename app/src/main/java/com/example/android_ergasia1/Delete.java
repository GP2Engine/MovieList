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

public class Delete extends AppCompatActivity {

    dbHelper db;
    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db = new dbHelper(this);
        e1=(EditText)findViewById(R.id.titledel);
        b1=(Button)findViewById(R.id.delmov);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                if (s1.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the movie's title.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkmovtitle = db.checkmovtitle(s1);
                    if (checkmovtitle==false){
                        Boolean deletemov = db.deletemovie(s1);
                        if(deletemov==true){
                            Toast.makeText(getApplicationContext(),"Movie Deleted Successfully.",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Delete.this,Menu.class); //going to the menu activity
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Movie Deletion Failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"There is not such movie with this title.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
