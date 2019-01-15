package com.unterr.truex.scapegoat.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.elements.CustomAdapter;
import com.unterr.truex.scapegoat.methods.APIWrapper;
import com.unterr.truex.scapegoat.models.Item;
import com.unterr.truex.scapegoat.models.MoneyProcess;
import com.unterr.truex.scapegoat.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Android Elements
    private Toolbar                     toolbar;
    private RecyclerView                recyclerView;
    private RecyclerView.Adapter        adapter;
    private RecyclerView.LayoutManager  layoutManager;
    private ArrayList<MoneyProcess>     data;

    // Objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =       findViewById(R.id.toolbar);
        recyclerView =  findViewById(R.id.lst);

        //Double grimyGuamItemID = new Double(199);
        //Item grimyGuam = APIWrapper.pullItem(grimyGuamItemID);
        //Double guamItemID = new Double(249);
        //Item guam = APIWrapper.pullItem(guamItemID);
        MoneyProcess cleaningGuam = new MoneyProcess (APIWrapper.pullItem(199.0), APIWrapper.pullItem(249.0), 1, 3.0, 2.5);
        MoneyProcess cleaningMarrentil = new MoneyProcess (APIWrapper.pullItem(201.0), APIWrapper.pullItem(251.0), 1, 5.0, 3.75);
        MoneyProcess cleaningTarromin = new MoneyProcess (APIWrapper.pullItem(203.0), APIWrapper.pullItem(253.0), 1, 11.0, 5.0);
        MoneyProcess cleaningHarralander = new MoneyProcess (APIWrapper.pullItem(205.0), APIWrapper.pullItem(255.0), 1, 20.0, 6.25);
        MoneyProcess cleaningRanarr = new MoneyProcess (APIWrapper.pullItem(207.0), APIWrapper.pullItem(257.0), 1, 25.0, 7.5);
        MoneyProcess cleaningToadflax = new MoneyProcess (APIWrapper.pullItem(3049.0), APIWrapper.pullItem(2998.0), 1, 30.0, 8.0);
        MoneyProcess cleaningIrit = new MoneyProcess (APIWrapper.pullItem(209.0), APIWrapper.pullItem(259.0), 1, 40.0, 8.75);
        MoneyProcess cleaningAvantoe = new MoneyProcess (APIWrapper.pullItem(211.0), APIWrapper.pullItem(261.0), 1, 48.0, 10.0);
        MoneyProcess cleaningKwuarm = new MoneyProcess (APIWrapper.pullItem(213.0), APIWrapper.pullItem(263.0), 1, 54.0, 11.25);
        MoneyProcess cleaningSnapdragon = new MoneyProcess (APIWrapper.pullItem(3051.0), APIWrapper.pullItem(3000.0), 1, 59.0, 11.75);
        MoneyProcess cleaningCadantine = new MoneyProcess (APIWrapper.pullItem(215.0), APIWrapper.pullItem(265.0), 1, 65.0, 12.5);
        MoneyProcess cleaningLantadyme = new MoneyProcess (APIWrapper.pullItem(2485.0), APIWrapper.pullItem(2481.0), 1, 67.0, 13.125);
        MoneyProcess cleaningDwarfWeed = new MoneyProcess (APIWrapper.pullItem(217.0), APIWrapper.pullItem(267.0), 1, 70.0, 13.75);

        Double grimyTorstolItemID = new Double(219);
        Item grimyTorstol = APIWrapper.pullItem(grimyTorstolItemID);
        Log.i("TestItemData:", grimyTorstol.toString());
        Double torstolItemID = new Double(269);
        Item torstol = APIWrapper.pullItem(torstolItemID);
        Log.i("TestItemData:", torstol.toString());
        MoneyProcess cleaningTorstol = new MoneyProcess (grimyTorstol, torstol, 1, 75.0, 15.0);
        Log.i("TestItemData:", cleaningTorstol.toString());


        data = new ArrayList<MoneyProcess>();
        data.add (cleaningGuam);
        data.add (cleaningMarrentil);
        data.add (cleaningTarromin);
        data.add (cleaningHarralander);
        data.add (cleaningRanarr);
        data.add (cleaningToadflax);
        data.add (cleaningIrit);
        data.add (cleaningAvantoe);
        data.add (cleaningKwuarm);
        data.add (cleaningSnapdragon);
        data.add (cleaningCadantine);
        data.add (cleaningLantadyme);
        data.add (cleaningDwarfWeed);
        data.add (cleaningTorstol);

        /*
        for(int i = 0; i < 30; i++){
            data.add(new MoneyProcess ());
        }
        */

        //layoutManager = new LinearLayoutManager(this);
        adapter       = new CustomAdapter(data);

        //setSupportActionBar(toolbar);

        this.recyclerView = (RecyclerView) findViewById (R.id.lst);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager (this);
        this.recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(layoutManager);
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

        //Test Variables:
        Double testItemID = new Double(111);
        String testUsername = new String("Jtruezie");

        //Tests for APIWrapper pullItem:
        //APIWrapper.pullItem (testItemID);
        //Item testItem = APIWrapper.pullItem(testItemID);
        //Log.i("TestItemData:", testItem.toString());


        //Tests for APIWrapper pullPlayer:
        //APIWrapper.pullPlayer (testUsername);
        Player testPlayer = APIWrapper.pullPlayer (testUsername);
        Log.i("PlayerData:", testPlayer.toString());


        //Tests for APIWrapper verifyUsername:
        Log.i("VerifyTest:", APIWrapper.verifyUsername (testUsername).toString ());

        //Tests for MoneyProcess:


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
