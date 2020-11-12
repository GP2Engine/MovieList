package com.example.android_ergasia1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/*
Kollias Anastasios MPSP19018
Ergasia 1 - Android
 */

public class Menu extends AppCompatActivity {

    dbHelper db;
    Button b1,b2,b3,b4,b5,b6;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db = new dbHelper(this);
        b1=(Button)findViewById(R.id.insert);
        b2=(Button)findViewById(R.id.search);
        b3 =(Button)findViewById(R.id.delete);
        b4 =(Button)findViewById(R.id.update);
        b5 =(Button)findViewById(R.id.showall);
        b6 =(Button)findViewById(R.id.logout);
        t1=(TextView)findViewById(R.id.weltext);
        t1.append(", "+Login.unamewel);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,Insert.class); //going to the insert activity
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public EditText editTextTitle;
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,Search.class); //going to the search activity
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,Delete.class); //going to the delete activity
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,Update.class); //going to the update activity
                startActivity(i);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> movtitles = db.getmovies();
                if(movtitles.size()>0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                    if(movtitles.size()==1)
                    {
                        builder.setTitle(movtitles.size()+" movie was found with title:");
                    }
                    else
                    {
                        builder.setTitle(movtitles.size()+" movies were found with titles:");
                    }
                    builder.setItems(movtitles.toArray(new String[0]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No movies were found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this,MainActivity.class); //going to the main activity
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
