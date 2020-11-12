package com.example.android_ergasia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
Kollias Anastasios
 */

public class listResDirector extends AppCompatActivity {

    dbHelper db;
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_res_director);
        db = new dbHelper(this);
        l1=(ListView) findViewById(R.id.listview1);
        String director = getIntent().getStringExtra("transfer"); //getting the director from the previous activity

        Boolean checkdirector = db.checkdirector(director);
        if(checkdirector==true)
        {
            ArrayList<String> alist = new ArrayList<>();
            Cursor c = db.searchdirector(director);
            while(c.moveToNext()){
                alist.add(c.getString(c.getColumnIndex("title")));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alist);
                l1.setAdapter(listAdapter);
            }
            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  String clickinput = ((TextView) view).getText().toString();
                    Intent i = new Intent(listResDirector.this,titleRes.class); //going to the title results activity
                    i.putExtra("transfer",clickinput); //sending the title to the next activity so it can process the search operation
                    startActivity(i);
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No movies with this director name exist.",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(listResDirector.this,Search.class); //going to the search activity
            startActivity(i);
        }

    }
}
