package com.unterr.truex.scapegoat.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.elements.CustomAdapter;
import com.unterr.truex.scapegoat.methods.APIWrapper;
import com.unterr.truex.scapegoat.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Android Elements
    private Toolbar                     toolbar;
    private RecyclerView                recyclerView;
    private RecyclerView.Adapter        adapter;
    private RecyclerView.LayoutManager  layoutManager;
    private ArrayList<Item>             data;

    // Objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =       findViewById(R.id.toolbar);
        recyclerView =  findViewById(R.id.lst);

        data = new ArrayList<Item>();
        for(int i = 0; i < 30; i++){
            data.add(new Item());
        }

        layoutManager = new LinearLayoutManager(this);
        adapter       = new CustomAdapter(data);

        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // set action for floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Test for APIWrapper
        Double testItem = new Double(111);
        APIWrapper.pullItem (testItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
