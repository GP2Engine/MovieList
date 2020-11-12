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

public class listResTitle extends AppCompatActivity {

    dbHelper db;
    ListView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_res_title);
        db = new dbHelper(this);
        l1=(ListView) findViewById(R.id.listview2);
        String title = getIntent().getStringExtra("transfer"); //getting the title from the previous activity

        ArrayList<String> alist = new ArrayList<>();
        Cursor c = db.searchparttitle(title);
        if(c.getCount() == 0){
            Toast.makeText(this, "No movies matching a part of this title name exist.",Toast.LENGTH_LONG).show();
            Intent i = new Intent(listResTitle.this,Search.class); //going to the search activity
            startActivity(i);
        }
        else
        {
            while(c.moveToNext()){
                alist.add(c.getString(c.getColumnIndex("title")));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alist);
                l1.setAdapter(listAdapter);
            }
            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String clickinput = ((TextView) view).getText().toString();
                    Intent i = new Intent(listResTitle.this,titleRes.class); //going to the title results activity
                    i.putExtra("transfer",clickinput); //sending the title to the next activity so it can process the search operation
                    startActivity(i);
                }
            });
        }
    }
}
