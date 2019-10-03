package com.unterr.truex.scapegoat.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.elements.CharacterAdapter;
import com.unterr.truex.scapegoat.elements.CustomAdapter;
import com.unterr.truex.scapegoat.methods.APIWrapper;
import com.unterr.truex.scapegoat.models.Item;
import com.unterr.truex.scapegoat.models.MoneyProcess;
import com.unterr.truex.scapegoat.models.Player;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    // Android Elements
    private Toolbar                     toolbar;
    private DrawerLayout                mDrawer;
    private NavigationView              navigationView;

    private RecyclerView                recyclerView;
    private RecyclerView.Adapter        adapter;
    private RecyclerView.LayoutManager  layoutManager;
    public String JsonData;

    // Objects
    //TODO: Add
    //public Player testPlayer = APIWrapper.pullPlayer ("Jtruezie");
    public Player testPlayer = new Player("Guest", 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
            1.0, 1.0, 1.0, 1.0, 1.0, 1.0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById (R.id.toolBar);
        setSupportActionBar (toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        //Used for setupDrawerContent
        navigationView = findViewById (R.id.nav_view);
        //setupDrawerContent (navigationView);


        recyclerView = findViewById (R.id.lst);
        layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        String SharedUser = PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "default");
        //SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        showUserDialog (MainActivity.this);

        /*
        if(SharedUser == "default"){


            String userName = testPlayer.getUsername ();
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("USERNAME", userName).apply();

            //TODO: Alter myStringToSave to the username String
        } else{
            testPlayer = APIWrapper.pullPlayer (SharedUser);
        }
        */


        //TODO: Ensure that JsonData value is not null when making pullItem calls (onCreate)
        //JsonData = APIWrapper.pullAWSJson();
        //Log.d ("AWS Data", JsonData);
        //MoneyProcess cleaningGuam = new MoneyProcess (pullItem(199.0), pullItem(249.0), 1, 3.0, 2.5, testPlayer);
        //Log.d("AWS Data", cleaningGuam.toString ());

        //JsonData = APIWrapper.pullAWSJson ();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawer.closeDrawers();
                        //TODO: Fix Progress Bar Animation (circular_progress_bar.xml)
                        //ProgressBar pgsBar = findViewById(R.id.pBar);
                        //pgsBar.setVisibility(View.VISIBLE);
                        // set item as selected to persist highlight
                        switch (menuItem.getItemId ()){
                            case R.id.nav_character:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CharacterAdapter (getPlayerArray ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getUsername () != null){
                                    setToolbar (testPlayer.getUsername ());
                                }
                                break;
                            }case R.id.nav_herbCleaning:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataHerbCleaning ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getHerbLvl () != null){
                                    setToolbar ("Cleaning Grimy Herbs" + " (lvl:" + (String.format("%.0f", testPlayer.getHerbLvl ())) + ")");
                                }else{
                                    setToolbar ("Cleaning Grimy Herbs");
                                }
                                break;
                            }case R.id.nav_unfPotions:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataHerbUnfinished ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getHerbLvl () != null){
                                    setToolbar ("Making Unf Potions" + " (lvl:" + (String.format("%.0f", testPlayer.getHerbLvl ())) + ")");
                                }else{
                                    setToolbar ("Making Unf Potions");
                                }
                                break;
                            }case R.id.nav_decantPotions:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataDecantPotions ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getHerbLvl () != null){
                                    setToolbar ("Decanting Potions" + " (lvl:" + (String.format("%.0f", testPlayer.getHerbLvl ())) + ")");
                                }else{
                                    setToolbar ("Decanting Potions");
                                }
                                break;
                            }case R.id.nav_growingSaplings:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataSaplings ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFarmingLvl () != null){
                                    setToolbar ("Growing Saplings" + " (lvl:" + (String.format("%.0f", testPlayer.getFarmingLvl ())) + ")");
                                }else{
                                    setToolbar ("Growing Saplings");
                                }
                                break;
                            }case R.id.nav_farmingHerbs:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataHerbFarming ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFarmingLvl () != null){
                                    setToolbar ("Farming Herbs" + " (lvl:" + (String.format("%.0f", testPlayer.getFarmingLvl ())) + ")");
                                }else{
                                    setToolbar ("Farming Herbs");
                                }
                                break;
                            }case R.id.nav_fletchingBoltTips:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataBoltTips ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFletchingLvl () != null){
                                    setToolbar ("Cutting Bolt Tips" + " (lvl:" + (String.format("%.0f", testPlayer.getFletchingLvl ())) + ")");
                                }else{
                                    setToolbar ("Cutting Bolt Tips");
                                }
                                break;
                            }case R.id.nav_fletchingBows:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataFletchBows ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFletchingLvl () != null){
                                    setToolbar ("Fletching Bows" + " (lvl:" + (String.format("%.0f", testPlayer.getFletchingLvl ())) + ")");
                                }else{
                                    setToolbar ("Fletching Bows");
                                }
                                break;
                            }case R.id.nav_stringingBows:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataStringBows ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFletchingLvl () != null){
                                    setToolbar ("Stringing Bows" + " (lvl:" + (String.format("%.0f", testPlayer.getFletchingLvl ())) + ")");
                                }else{
                                    setToolbar ("Stringing Bows");
                                }
                                break;
                            }case R.id.nav_smithingDartTips:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataSmithDarts ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getSmithingLvl () != null){
                                    setToolbar ("Smithing Dart Tips" + " (lvl:" + (String.format("%.0f", testPlayer.getSmithingLvl ())) + ")");
                                }else{
                                    setToolbar ("Smithing Dart Tips");
                                }
                                break;
                            }case R.id.nav_barrowsRepair:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataBarrowsRepair ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getCraftingLvl () != null){
                                    setToolbar ("Barrows Repair" + " (lvl:" + (String.format("%.0f", testPlayer.getSmithingLvl ())) + ")");
                                }else{
                                    setToolbar ("Barrows Repair");
                                }
                                break;
                            }case R.id.nav_blastFurnace:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataBlastFurnace ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getCraftingLvl () != null){
                                    setToolbar ("Blast Furnace" + " (lvl:" + (String.format("%.0f", testPlayer.getSmithingLvl ())) + ")");
                                }else{
                                    setToolbar ("Blast Furnace");
                                }
                                break;
                            }case R.id.nav_cookingFish:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataCookingFish ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getCookingLvl () != null){
                                    setToolbar ("Cooking Fish" + " (lvl:" + (String.format("%.0f", testPlayer.getCookingLvl ())) + ")");
                                }else{
                                    setToolbar ("Cooking Fish");
                                }
                                break;
                            }case R.id.nav_makingPlanks:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataMakingPlanks ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getFiremakingLvl () != null){
                                    setToolbar ("Making Planks" + " (lvl:" + (String.format("%.0f", testPlayer.getFiremakingLvl ())) + ")");
                                }else{
                                    setToolbar ("Making Planks");
                                }
                                break;
                            }case R.id.nav_tanningLeather:{
                                mDrawer.closeDrawer (GravityCompat.START);
                                adapter = new CustomAdapter (dataTanningLeather ());
                                recyclerView.setAdapter(adapter);
                                if (testPlayer.getCraftingLvl () != null){
                                    setToolbar ("Tanning Leather" + " (lvl:" + (String.format("%.0f", testPlayer.getCraftingLvl ())) + ")");
                                }else{
                                    setToolbar ("Tanning Leather");
                                }
                                break;
                            }
                        }

                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        //pgsBar.setVisibility(View.GONE);

                        return false;
                    }
                });



        //recyclerView =  findViewById(R.id.lst);


        /*
        for(int i = 0; i < 30; i++){
            data.add(new MoneyProcess ());
        }
        */

        //**CustomAdapter calls one of the temporary data() methods below to create new MoneyProcess classes and populate the RecyclerView**
        //1 - dataHerbCleaning() is complete and can be called without exceptions
        //2 - dataHerbUnfinished() is complete and can be called without exceptions
        //3 - dataSaplings() is not complete but can be called without exceptions
        //4 - dataBoltTips() is not complete but can be called without exceptions
        //5 - dataFletchBows() is complete and can be called without exceptions
        //6 - dataStringBows() is complete and can be called without exceptions
        //7 - dataSmithDarts() is not complete and cannot be called without exceptions
        /*
        try{

        } catch (Exception e){
            Log.i ("AdapterException", e.getMessage ());
        }
        */





        // set action for floating action button
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    public void showUserDialog(Context c){
        final EditText taskEditText = new EditText(c);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Enter your username:");
            alertDialogBuilder.setView(taskEditText);
            alertDialogBuilder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if(!APIWrapper.verifyUsername (String.valueOf (taskEditText.getText ()))){
                        Toast.makeText(MainActivity.this,"Username Not Found",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Username Found! Go to settings to change your current Username.",Toast.LENGTH_LONG).show();
                        testPlayer = APIWrapper.pullPlayer (String.valueOf (taskEditText.getText ()));
                        //TODO: Save username String to SharedPreferences
                        //finish();
                    }
             }});

        alertDialogBuilder.setNegativeButton("Skip",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"No Username added. Go to settings to add a Username.",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void setToolbar(String heading) {
        toolbar.setTitle(heading);
    }

    /*
    public void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener (
                new NavigationView.OnNavigationItemSelectedListener () {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem (menuItem);
                        return true;
                    }
                });
    }

    //Could be used to access fragments based on the nav_menu item selected
    public void selectDrawerItem(MenuItem menuItem){
        switch(menuItem.getItemId ()){
            case R.id.nav_herbCleaning:
                mDrawer.closeDrawer (GravityCompat.START);
                adapter = new CustomAdapter (dataHerbCleaning ());
                recyclerView.setAdapter(adapter);
            case R.id.nav_unfPotions:
                mDrawer.closeDrawer (GravityCompat.START);
                adapter = new CustomAdapter (dataHerbUnfinished ());
                recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId ()){
            case android.R.id.home:
                mDrawer.openDrawer (GravityCompat.START);
                return true;

            case R.id.nav_herbCleaning:
                adapter       = new CustomAdapter(dataStringBows ());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

        }

        return super.onOptionsItemSelected (item);
    }
    */

    //Player Array to be called and used by the CharacterAdapter
    public ArrayList<String> getPlayerArray(){
        ArrayList<String> array = new ArrayList<> ();

        array.add(testPlayer.getAttackLvl ().toString ());
        array.add(testPlayer.getDefenceLvl ().toString ());
        array.add(testPlayer.getStrengthLvl ().toString ());
        array.add(testPlayer.getHitpointsLvl ().toString ());
        array.add(testPlayer.getRangedLvl ().toString ());
        array.add(testPlayer.getPrayerLvl ().toString ());
        array.add(testPlayer.getMagicLvl ().toString ());
        array.add(testPlayer.getCookingLvl ().toString ());
        array.add(testPlayer.getWoodcuttingLvl ().toString ());
        array.add(testPlayer.getFletchingLvl ().toString ());
        array.add(testPlayer.getFishingLvl ().toString ());
        array.add(testPlayer.getFiremakingLvl ().toString ());
        array.add(testPlayer.getCraftingLvl ().toString ());
        array.add(testPlayer.getSmithingLvl ().toString ());
        array.add(testPlayer.getMiningLvl ().toString ());
        array.add(testPlayer.getHerbLvl ().toString ());
        array.add(testPlayer.getAgilityLvl ().toString ());
        array.add(testPlayer.getThievingLvl ().toString ());
        array.add(testPlayer.getSlayerLvl ().toString ());
        array.add(testPlayer.getFarmingLvl ().toString ());
        array.add(testPlayer.getRunecraftingLvl ().toString ());
        array.add(testPlayer.getHunterLvl ().toString ());
        array.add(testPlayer.getConstructionLvl ().toString ());

        return(array);
    }

    public Item pullItem(final Double itemID){

        String _iconURL = new String();
        String _iconLargeURL = new String();
        Double _itemID = 0.0;
        Boolean _memberOnly = false;
        String _name = new String();
        Double _tradePrice = 0.0;

        try {
            //TODO: Alter AWSJson data to a universal variable
            JSONObject reader = new JSONObject(JsonData);
            String urlItemID = String.format("%.0f", itemID);
            JSONObject itemObj = reader.getJSONObject (urlItemID);

            _iconURL = itemObj.getString ("icon");
            Log.i("ItemDataReturn","Icon Url:" + _iconURL);

            _iconLargeURL = itemObj.getString ("icon_large");
            Log.i("ItemDataReturn","Large Icon Url:" + _iconLargeURL);

            _itemID = Double.parseDouble(itemObj.getString ("id"));
            Log.i("ItemDataReturn","Item ID:" + _itemID.toString ());

            _memberOnly = Boolean.valueOf(itemObj.getString ("isMember"));
            Log.i("ItemDataReturn","Members Only:" + _memberOnly.toString ());

            _name = itemObj.getString ("name");
            Log.i("ItemDataReturn","Item Name:" + _name);

            String tradePrice = itemObj.getString ("currentPrice");
            tradePrice = tradePrice.replace (",","");
            _tradePrice = Double.parseDouble (tradePrice);
            Log.i("ItemDataReturn","Trade Price:" + _tradePrice.toString ());

        } catch (final JSONException e) {
            Log.e("JSONException","JSON Parsing Error:" + e.getMessage());

        }

        Item newItemObject = new Item(_iconURL, _iconLargeURL, _itemID, _memberOnly, _name, _tradePrice);

        //TODO: Create check to validate if the given (perameter) itemID matches the itemID pulled from the ge API

        //Double itemID converted to String urlItemID with formatting to remove the decimal point and decimal values. (decimal point causes url to not return data)
        //String urlItemID = String.format("%.0f", itemID);

        //DownloadItem task = new DownloadItem();
        //task.execute("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + urlItemID);

        //TODO: create new Item object with the pulled JSON data

        return newItemObject;
    }

    public Item pullItem(final Double itemID, String json){

        String _iconURL = new String();
        String _iconLargeURL = new String();
        Double _itemID = 0.0;
        Boolean _memberOnly = false;
        String _name = new String();
        Double _tradePrice = 0.0;

        try {
            //TODO: Alter AWSJson data to a universal variable
            JSONObject reader = new JSONObject(json);
            String urlItemID = String.format("%.0f", itemID);
            JSONObject itemObj = reader.getJSONObject (urlItemID);

            _iconURL = itemObj.getString ("icon");
            Log.i("ItemDataReturn","Icon Url:" + _iconURL);

            _iconLargeURL = itemObj.getString ("icon_large");
            Log.i("ItemDataReturn","Large Icon Url:" + _iconLargeURL);

            _itemID = Double.parseDouble(itemObj.getString ("id"));
            Log.i("ItemDataReturn","Item ID:" + _itemID.toString ());

            _memberOnly = Boolean.valueOf(itemObj.getString ("isMember"));
            Log.i("ItemDataReturn","Members Only:" + _memberOnly.toString ());

            _name = itemObj.getString ("name");
            Log.i("ItemDataReturn","Item Name:" + _name);

            String tradePrice = itemObj.getString ("currentPrice");
            tradePrice = tradePrice.replace (",","");
            _tradePrice = Double.parseDouble (tradePrice);
            Log.i("ItemDataReturn","Trade Price:" + _tradePrice.toString ());

        } catch (final JSONException e) {
            Log.e("JSONException","JSON Parsing Error:" + e.getMessage());

        }

        Item newItemObject = new Item(_iconURL, _iconLargeURL, _itemID, _memberOnly, _name, _tradePrice);

        //TODO: Create check to validate if the given (perameter) itemID matches the itemID pulled from the ge API

        //Double itemID converted to String urlItemID with formatting to remove the decimal point and decimal values. (decimal point causes url to not return data)
        //String urlItemID = String.format("%.0f", itemID);

        //DownloadItem task = new DownloadItem();
        //task.execute("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + urlItemID);

        //TODO: create new Item object with the pulled JSON data

        return newItemObject;
    }

    //CategoryID = 1 (Cleaning Herbs)
    public ArrayList<MoneyProcess> dataHerbCleaning(){
        String j = APIWrapper.pullAWSJson("199,249,201,251,203,253,205,255,207,257,3049,2998,209,259,211,261,213,263,3051,3000,215,265,2485,2481,217,267,219,269");

        MoneyProcess cleaningGuam = new MoneyProcess (pullItem(199.0,j), pullItem(249.0,j), 1, 3.0, 2.5, testPlayer);
        MoneyProcess cleaningMarrentil = new MoneyProcess (pullItem(201.0,j), pullItem(251.0,j), 1, 5.0, 3.75, testPlayer);
        MoneyProcess cleaningTarromin = new MoneyProcess (pullItem(203.0,j), pullItem(253.0,j), 1, 11.0, 5.0, testPlayer);
        MoneyProcess cleaningHarralander = new MoneyProcess (pullItem(205.0,j), pullItem(255.0,j), 1, 20.0, 6.25, testPlayer);
        MoneyProcess cleaningRanarr = new MoneyProcess (pullItem(207.0,j), pullItem(257.0,j), 1, 25.0, 7.5, testPlayer);
        MoneyProcess cleaningToadflax = new MoneyProcess (pullItem(3049.0,j), pullItem(2998.0,j), 1, 30.0, 8.0, testPlayer);
        MoneyProcess cleaningIrit = new MoneyProcess (pullItem(209.0,j), pullItem(259.0,j), 1, 40.0, 8.75, testPlayer);
        MoneyProcess cleaningAvantoe = new MoneyProcess (pullItem(211.0,j), pullItem(261.0,j), 1, 48.0, 10.0, testPlayer);
        MoneyProcess cleaningKwuarm = new MoneyProcess (pullItem(213.0,j), pullItem(263.0,j), 1, 54.0, 11.25, testPlayer);
        MoneyProcess cleaningSnapdragon = new MoneyProcess (pullItem(3051.0,j), pullItem(3000.0,j), 1, 59.0, 11.75, testPlayer);
        MoneyProcess cleaningCadantine = new MoneyProcess (pullItem(215.0,j), pullItem(265.0,j), 1, 65.0, 12.5, testPlayer);
        MoneyProcess cleaningLantadyme = new MoneyProcess (pullItem(2485.0,j), pullItem(2481.0,j), 1, 67.0, 13.125, testPlayer);
        MoneyProcess cleaningDwarfWeed = new MoneyProcess (pullItem(217.0,j), pullItem(267.0,j), 1, 70.0, 13.75, testPlayer);
        MoneyProcess cleaningTorstol = new MoneyProcess (pullItem (219.0,j), pullItem (269.0,j), 1, 75.0, 15.0, testPlayer);

        ArrayList<MoneyProcess> dataCleaning = new ArrayList<MoneyProcess>();

        dataCleaning.add (cleaningGuam);
        dataCleaning.add (cleaningMarrentil);
        dataCleaning.add (cleaningTarromin);
        dataCleaning.add (cleaningHarralander);
        dataCleaning.add (cleaningRanarr);
        dataCleaning.add (cleaningToadflax);
        dataCleaning.add (cleaningIrit);
        dataCleaning.add (cleaningAvantoe);
        dataCleaning.add (cleaningKwuarm);
        dataCleaning.add (cleaningSnapdragon);
        dataCleaning.add (cleaningCadantine);
        dataCleaning.add (cleaningLantadyme);
        dataCleaning.add (cleaningDwarfWeed);
        dataCleaning.add (cleaningTorstol);

        return(dataCleaning);
    }

    //Could add data for using unclean herbs
    //CategoryID = 2 (Making Unfinished Potions)
    public ArrayList<MoneyProcess> dataHerbUnfinished(){

        String j = APIWrapper.pullAWSJson("91,93,95,97,99,3002,101,103,105,3004,107,2483,109,111,249,251,253,253,255,257,2998,259,261,263,3000,265,2481,267,269");

        MoneyProcess unfGuam = new MoneyProcess (pullItem(249.0,j), pullItem(91.0,j), 2, 3.0, 0.0, testPlayer);
        MoneyProcess unfMarrentil = new MoneyProcess (pullItem(251.0,j), pullItem(93.0,j), 2, 5.0, 0.0, testPlayer);
        MoneyProcess unfTarromin = new MoneyProcess (pullItem(253.0,j), pullItem(95.0,j), 2, 11.0, 0.0, testPlayer);
        MoneyProcess unfHarralander = new MoneyProcess (pullItem(255.0,j), pullItem(97.0,j), 2, 20.0, 0.0, testPlayer);
        MoneyProcess unfRanarr = new MoneyProcess (pullItem(257.0,j), pullItem(99.0,j), 2, 25.0, 0.0, testPlayer);
        MoneyProcess unfToadflax = new MoneyProcess (pullItem(2998.0,j), pullItem(3002.0,j), 2, 30.0, 0.0, testPlayer);
        MoneyProcess unfIrit = new MoneyProcess (pullItem(259.0,j), pullItem(101.0,j), 2, 40.0, 0.0, testPlayer);
        MoneyProcess unfAvantoe = new MoneyProcess (pullItem(261.0,j), pullItem(103.0,j), 2, 48.0, 0.0, testPlayer);
        MoneyProcess unfKwuarm = new MoneyProcess (pullItem(263.0,j), pullItem(105.0,j), 2, 54.0, 0.0, testPlayer);
        MoneyProcess unfSnapdragon = new MoneyProcess (pullItem(3000.0,j), pullItem(3004.0,j), 2, 59.0, 0.0, testPlayer);
        MoneyProcess unfCadantine = new MoneyProcess (pullItem(265.0,j), pullItem(107.0,j), 2, 65.0, 0.0, testPlayer);
        MoneyProcess unfLantadyme = new MoneyProcess (pullItem(2481.0,j), pullItem(2483.0,j), 2, 67.0, 0.0, testPlayer);
        MoneyProcess unfDwarfWeed = new MoneyProcess (pullItem(267.0,j), pullItem(109.0,j), 2, 70.0, 0.0, testPlayer);
        MoneyProcess unfTorstol = new MoneyProcess (pullItem (269.0,j), pullItem (111.0,j), 2, 75.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataHerbUnfinished = new ArrayList<MoneyProcess>();

        dataHerbUnfinished.add (unfGuam);
        dataHerbUnfinished.add (unfMarrentil);
        dataHerbUnfinished.add (unfTarromin);
        dataHerbUnfinished.add (unfHarralander);
        dataHerbUnfinished.add (unfRanarr);
        dataHerbUnfinished.add (unfToadflax);
        dataHerbUnfinished.add (unfIrit);
        dataHerbUnfinished.add (unfAvantoe);
        dataHerbUnfinished.add (unfKwuarm);
        dataHerbUnfinished.add (unfSnapdragon);
        dataHerbUnfinished.add (unfCadantine);
        dataHerbUnfinished.add (unfLantadyme);
        dataHerbUnfinished.add (unfDwarfWeed);
        dataHerbUnfinished.add (unfTorstol);

        return(dataHerbUnfinished);
    }

    //CategoryID = 3 (Growing Saplings)
    //Pulling data for sapMaple, sapYew, sapPalm, and sapMagic causes exceptions and forces the app to close
    //TODO: Include noted money processes in API pull
    public ArrayList<MoneyProcess> dataSaplings(){

        String j = APIWrapper.pullAWSJson("5312,5370,5283,5496,5313,5371,5284,5497,21486,21477,5285,5498,5286,5499,5314,5372,5287,5500,21488,21480,5288,5501,5315,5373,5289,5502,5290,5503,5316,5374");


        MoneyProcess sapOak = new MoneyProcess (pullItem(5312.0,j), pullItem(5370.0,j), 3, 15.0, 0.0, testPlayer);
        MoneyProcess sapApple = new MoneyProcess (pullItem(5283.0,j), pullItem(5496.0,j), 3, 27.0, 0.0, testPlayer);
        MoneyProcess sapWillow = new MoneyProcess (pullItem(5313.0,j), pullItem(5371.0,j), 3, 30.0, 0.0, testPlayer);
        MoneyProcess sapBanana = new MoneyProcess (pullItem(5284.0,j), pullItem(5497.0,j),  3, 33.0, 0.0, testPlayer);
        MoneyProcess sapTeak = new MoneyProcess (pullItem(21486.0,j), pullItem(21477.0,j),  3, 35.0, 0.0, testPlayer);
        MoneyProcess sapOrange = new MoneyProcess (pullItem(5285.0,j), pullItem(5498.0,j), 3, 39.0, 0.0, testPlayer);
        MoneyProcess sapCurry = new MoneyProcess (pullItem(5286.0,j), pullItem(5499.0,j),  3, 42.0, 0.0, testPlayer);
        //Causes Exceptions
        //MoneyProcess sapMaple = new MoneyProcess (pullItem(5314.0), pullItem(5372.0), 3, 45.0, 0.0, testPlayer);
        //Log.i ("DataSapling", sapMaple.toString ());


        //MoneyProcess sapPineapple = new MoneyProcess (pullItem(5287.0), pullItem(5500.0), 3, 51.0, 0.0, testPlayer);
        //MoneyProcess sapMahogany = new MoneyProcess (pullItem(21488.0), pullItem(21480.0),  3, 55.0, 0.0, testPlayer);
        //MoneyProcess sapPapaya = new MoneyProcess (pullItem(5288.0), pullItem(5501.0), 3, 57.0, 0.0, testPlayer);


        //Causes Exceptions
        //MoneyProcess sapYew = new MoneyProcess (pullItem(5315.0), pullItem(5373.0),  3, 60.0, 0.0, testPlayer);
        //MoneyProcess sapPalm = new MoneyProcess (pullItem(5289.0), pullItem(5502.0),  3, 68.0, 0.0, testPlayer);

        //MoneyProcess sapCalquat = new MoneyProcess (pullItem (5290.0), pullItem (5503.0),  3, 72.0, 0.0, testPlayer);

        //Causes Exceptions
        //MoneyProcess sapMagic = new MoneyProcess (pullItem (5316.0), pullItem (5374.0),  3, 75.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataSaplings = new ArrayList<MoneyProcess>();

        dataSaplings.add (sapOak);
        dataSaplings.add (sapApple);
        dataSaplings.add (sapWillow);
        dataSaplings.add (sapBanana);
        dataSaplings.add (sapTeak);
        dataSaplings.add (sapOrange);
        dataSaplings.add (sapCurry);

        //Causes Exceptions
        //dataSaplings.add (sapMaple);

        /*
        dataSaplings.add (sapPineapple);
        dataSaplings.add (sapMahogany);
        dataSaplings.add (sapPapaya);
        */

        //Causes Exceptions
        //dataSaplings.add (sapYew);
        //dataSaplings.add (sapPalm);

        //dataSaplings.add (sapCalquat);

        //Causes Exceptions
        //dataSaplings.add (sapMagic);


        return(dataSaplings);
    }

    //CategoryID = 4 (Making Bolt Tips)
    //Pulling data for cuttingDragonTips and cuttingOnyxTips causes exceptions and forces the app to close
    //TODO: Do not include noted MoneyProcesses until MoneyProcess is altered to half their amount per input
    public ArrayList<MoneyProcess> dataBoltTips(){

        String j = APIWrapper.pullAWSJson("1609,45,1611,9187,1613,9188,1607,9189,1605,9190,1603,9191,1601,9192,1615,9193,6573,9194");


        MoneyProcess cuttingOpalTips = new MoneyProcess (pullItem(1609.0,j), pullItem(45.0,j), 4, 11.0, 1.6, testPlayer);
        MoneyProcess cuttingJadeTips = new MoneyProcess (pullItem(1611.0,j), pullItem(9187.0,j), 4, 26.0, 2.4, testPlayer);
        MoneyProcess cuttingRedTopazTips = new MoneyProcess (pullItem(1613.0,j), pullItem(9188.0,j), 4, 48.0, 4.0, testPlayer);
        MoneyProcess cuttingSapphireTips = new MoneyProcess (pullItem(1607.0,j), pullItem(9189.0,j), 4, 56.0, 4.0, testPlayer);
        MoneyProcess cuttingEmeraldTips = new MoneyProcess (pullItem(1605.0,j), pullItem(9190.0,j), 4, 58.0, 5.5, testPlayer);
        MoneyProcess cuttingRubyTips = new MoneyProcess (pullItem(1603.0,j), pullItem(9191.0,j), 4, 63.0, 6.0, testPlayer);
        MoneyProcess cuttingDiamondTips = new MoneyProcess (pullItem(1601.0,j), pullItem(9192.0,j), 4, 65.0, 7.0, testPlayer);

        //TODO
        //cuttingDragonTips and cuttingOnyxTips causes the app to crash
        //MoneyProcess cuttingDragonTips = new MoneyProcess (pullItem(1615.0), pullItem(9193.0), 4, 71.0, 8.2, testPlayer);
        //MoneyProcess cuttingOnyxTips = new MoneyProcess (pullItem(6573.0), pullItem(9194.0), 4, 73.0, 9.4, testPlayer);


        //OnyxTips produce double the amount per input compared to other bolt tips
        //cuttingOnyxTips.setProfitPer (cuttingOnyxTips.profitPer * 2.0);
        //cuttingOnyxTips.setProfitTotal (cuttingOnyxTips.profitTotal * 2.0);

        //Amethyst Bolts require crafting and not fletching
        //MoneyProcess cuttingAmethystTips = new MoneyProcess (pullItem(21347.0), pullItem(21338.0), 4, 83.0, 60.0, testPlayer);

        ArrayList<MoneyProcess> dataBoltTips = new ArrayList<MoneyProcess>();

        dataBoltTips.add (cuttingOpalTips);
        dataBoltTips.add (cuttingJadeTips);
        dataBoltTips.add (cuttingRedTopazTips);
        dataBoltTips.add (cuttingSapphireTips);
        dataBoltTips.add (cuttingEmeraldTips);
        dataBoltTips.add (cuttingRubyTips);
        dataBoltTips.add (cuttingDiamondTips);
        //dataBoltTips.add (cuttingDragonTips);
        //dataBoltTips.add (cuttingOnyxTips);

        return(dataBoltTips);
    }

    //CategoryID = 5 (Fletching Bows)
    //TODO: Change method so it stores the logs in an item so it doesn't call each log type twice
    public ArrayList<MoneyProcess> dataFletchBows(){

        String j = APIWrapper.pullAWSJson("1511,1521,1519,1517,1515,1513,50,48,54,56,60,58,64,62,68,66,72,70");

        MoneyProcess fletchSB = new MoneyProcess (pullItem(1511.0,j), pullItem(50.0,j), 5, 5.0, 5.0, testPlayer);
        MoneyProcess fletchLB = new MoneyProcess (pullItem(1511.0,j), pullItem(48.0,j), 5, 10.0, 10.0, testPlayer);
        MoneyProcess fletchOakSB = new MoneyProcess (pullItem(1521.0,j), pullItem(54.0,j), 5, 20.0, 16.5, testPlayer);
        MoneyProcess fletchOakLB = new MoneyProcess (pullItem(1521.0,j), pullItem(56.0,j), 5, 25.0, 25.0, testPlayer);
        MoneyProcess fletchWillowSB = new MoneyProcess (pullItem(1519.0,j), pullItem(60.0,j), 5, 35.0, 33.3, testPlayer);
        MoneyProcess fletchWillowLB = new MoneyProcess (pullItem(1519.0,j), pullItem(58.0,j), 5, 40.0, 41.5, testPlayer);
        MoneyProcess fletchMapleSB = new MoneyProcess (pullItem(1517.0,j), pullItem(64.0,j), 5, 50.0, 50.0, testPlayer);
        MoneyProcess fletchMapleLB = new MoneyProcess (pullItem(1517.0,j), pullItem(62.0,j), 5, 55.0, 58.3, testPlayer);
        MoneyProcess fletchYewSB = new MoneyProcess (pullItem(1515.0,j), pullItem(68.0,j), 5, 65.0, 67.5, testPlayer);
        MoneyProcess fletchYewLB = new MoneyProcess (pullItem(1515.0,j), pullItem(66.0,j), 5, 70.0, 75.5, testPlayer);
        MoneyProcess fletchMagicSB = new MoneyProcess (pullItem(1513.0,j), pullItem(72.0,j), 5, 80.0, 83.3, testPlayer);
        MoneyProcess fletchMagicLB = new MoneyProcess (pullItem(1513.0,j), pullItem(70.0,j), 5, 85.0, 91.5, testPlayer);


        ArrayList<MoneyProcess> dataFletchBows = new ArrayList<MoneyProcess>();

        dataFletchBows.add (fletchSB);
        dataFletchBows.add (fletchLB);
        dataFletchBows.add (fletchOakSB);
        dataFletchBows.add (fletchOakLB);
        dataFletchBows.add (fletchWillowSB);
        dataFletchBows.add (fletchWillowLB);
        dataFletchBows.add (fletchMapleSB);
        dataFletchBows.add (fletchMapleLB);
        dataFletchBows.add (fletchYewSB);
        dataFletchBows.add (fletchYewLB);
        dataFletchBows.add (fletchMagicSB);
        dataFletchBows.add (fletchMagicLB);


        return(dataFletchBows);
    }

    //CategoryID = 6 (Stringing Bows)
    public ArrayList<MoneyProcess> dataStringBows(){

        String j = APIWrapper.pullAWSJson("841,839,843,845,849,847,853,859,857,855,861,859,50,48,54,56,60,58,64,62,68,66,72,70");

        Item bowString = pullItem (1777.0);

        MoneyProcess stringSB = new MoneyProcess (pullItem(50.0,j), bowString, pullItem(841.0,j), 5, 5.0, 5.0, testPlayer);
        MoneyProcess stringLB = new MoneyProcess (pullItem(48.0,j), bowString, pullItem(839.0,j), 5, 10.0, 10.0, testPlayer);
        MoneyProcess stringOakSB = new MoneyProcess (pullItem(54.0,j), bowString, pullItem(843.0,j), 5, 20.0, 16.5, testPlayer);
        MoneyProcess stringOakLB = new MoneyProcess (pullItem(56.0,j), bowString, pullItem(845.0,j), 5, 25.0, 25.0, testPlayer);
        MoneyProcess stringWillowSB = new MoneyProcess (pullItem(60.0,j), bowString, pullItem(849.0,j), 5, 35.0, 33.3, testPlayer);
        MoneyProcess stringWillowLB = new MoneyProcess (pullItem(58.0,j), bowString, pullItem(847.0,j), 5, 40.0, 41.5, testPlayer);
        MoneyProcess stringMapleSB = new MoneyProcess (pullItem(64.0,j), bowString, pullItem(853.0,j), 5, 50.0, 50.0, testPlayer);
        MoneyProcess stringMapleLB = new MoneyProcess (pullItem(62.0,j), bowString, pullItem(859.0,j), 5, 55.0, 58.3, testPlayer);
        MoneyProcess stringYewSB = new MoneyProcess (pullItem(68.0,j), bowString, pullItem(857.0,j), 5, 65.0, 67.5, testPlayer);
        MoneyProcess stringYewLB = new MoneyProcess (pullItem(66.0,j), bowString, pullItem(855.0,j), 5, 70.0, 75.5, testPlayer);
        MoneyProcess stringMagicSB = new MoneyProcess (pullItem(72.0,j), bowString, pullItem(861.0,j), 5, 80.0, 83.3, testPlayer);
        MoneyProcess stringMagicLB = new MoneyProcess (pullItem(70.0,j), bowString, pullItem(859.0,j), 5, 85.0, 91.5, testPlayer);


        ArrayList<MoneyProcess> dataStringBows = new ArrayList<MoneyProcess>();

        dataStringBows.add (stringSB);
        dataStringBows.add (stringLB);
        dataStringBows.add (stringOakSB);
        dataStringBows.add (stringOakLB);
        dataStringBows.add (stringWillowSB);
        dataStringBows.add (stringWillowLB);
        dataStringBows.add (stringMapleSB);
        dataStringBows.add (stringMapleLB);
        dataStringBows.add (stringYewSB);
        dataStringBows.add (stringYewLB);
        dataStringBows.add (stringMagicSB);
        dataStringBows.add (stringMagicLB);


        return(dataStringBows);
    }

    //CategoryID = 7 (Smithing Dart Tips)
    //Pulling data for smithRuneTips causes exceptions and forces the app to close
    public ArrayList<MoneyProcess> dataSmithDarts(){

        String j = APIWrapper.pullAWSJson("2349,819,2351,820,2351,821,2359,822,2361,823,2363,824");

        MoneyProcess smithBronzeTips = new MoneyProcess (pullItem(2349.0,j), pullItem(819.0,j), 7, 4.0, 12.5, testPlayer);
        MoneyProcess smithIronTips = new MoneyProcess (pullItem(2351.0,j), pullItem(820.0,j), 7, 19.0, 25.0, testPlayer);
        MoneyProcess smithSteelTips = new MoneyProcess (pullItem(2353.0,j), pullItem(821.0,j), 7, 34.0, 37.5, testPlayer);
        MoneyProcess smithMithrilTips = new MoneyProcess (pullItem(2359.0,j), pullItem(822.0,j), 7, 54.0, 50.0, testPlayer);
        MoneyProcess smithAdamantTips = new MoneyProcess (pullItem(2361.0,j), pullItem(823.0,j), 7, 74.0, 62.5, testPlayer);
        //MoneyProcess smithRuneTips = new MoneyProcess (pullItem(2363.0), pullItem(824.0), 7, 89.0, 75.0, testPlayer);

        ArrayList<MoneyProcess> dataSmithDarts = new ArrayList<MoneyProcess>();

        dataSmithDarts.add (smithBronzeTips);
        dataSmithDarts.add (smithIronTips);
        dataSmithDarts.add (smithSteelTips);
        dataSmithDarts.add (smithMithrilTips);
        dataSmithDarts.add (smithAdamantTips);
        //dataSmithDarts.add (smithRuneTips);


        return(dataSmithDarts);
    }

    //CategoryID = 10 (Farming Herbs)
    //Pulling data for farmingRanarr, farmingSnapdragon, and farmingTorstol causes exceptions and forces the app to close
    public ArrayList<MoneyProcess> dataHerbFarming(){

        String j = APIWrapper.pullAWSJson("5291,5292,5293,5294,5295,5296,5297,5298,5299,5300,5301,5302,5303,5304,199,201,203,205,207,3049,209,211,213,3051,215,2485,217,219");

        Item ultracompost = pullItem (21483.0);

        MoneyProcess farmingGuam = new MoneyProcess (pullItem(5291.0,j), ultracompost, pullItem(199.0,j), 10, 9.0, 12.5, testPlayer);
        MoneyProcess farmingMarrentil = new MoneyProcess (pullItem(5292.0,j), ultracompost, pullItem(201.0,j), 10, 14.0, 15.0, testPlayer);
        MoneyProcess farmingTarromin = new MoneyProcess (pullItem(5293.0,j), ultracompost, pullItem(203.0,j), 10, 19.0, 18.0, testPlayer);
        MoneyProcess farmingHarralander = new MoneyProcess (pullItem(5294.0,j), ultracompost, pullItem(205.0,j), 10, 26.0, 24.0, testPlayer);
        //Causes Exceptions
        //MoneyProcess farmingRanarr = new MoneyProcess (pullItem(5295.0), ultracompost, pullItem(207.0), 10, 32.0, 30.5, testPlayer);
        MoneyProcess farmingToadflax = new MoneyProcess (pullItem(5296.0,j), ultracompost, pullItem(3049.0,j), 10, 38.0, 38.5, testPlayer);
        MoneyProcess farmingIrit = new MoneyProcess (pullItem(5297.0,j), ultracompost, pullItem(209.0,j), 10, 44.0, 48.5, testPlayer);
        MoneyProcess farmingAvantoe = new MoneyProcess (pullItem(5298.0,j), ultracompost, pullItem(211.0,j), 10, 50.0, 61.5, testPlayer);
        MoneyProcess farmingKwuarm = new MoneyProcess (pullItem(5299.0,j), ultracompost, pullItem(213.0,j), 10, 56.0, 78.0, testPlayer);
        //Caises Exceptions
        //MoneyProcess farmingSnapdragon = new MoneyProcess (pullItem(5300.0), ultracompost, pullItem(3051.0), 10, 62.0, 98.5, testPlayer);
        MoneyProcess farmingCadantine = new MoneyProcess (pullItem(5301.0,j), ultracompost, pullItem(215.0,j), 10, 67.0, 120.0, testPlayer);
        MoneyProcess farmingLantadyme = new MoneyProcess (pullItem(5302.0,j), ultracompost, pullItem(2485.0,j), 10, 73.0, 151.5, testPlayer);
        MoneyProcess farmingDwarfWeed = new MoneyProcess (pullItem(5303.0,j), ultracompost, pullItem(217.0,j), 10, 79.0, 192.0, testPlayer);
        //Causes Exceptions
        //MoneyProcess farmingTorstol = new MoneyProcess (pullItem (5304.0), ultracompost, pullItem (219.0), 10, 85.0, 224.5, testPlayer);

        ArrayList<MoneyProcess> dataHerbFarming = new ArrayList<MoneyProcess>();

        dataHerbFarming.add (farmingGuam);

        dataHerbFarming.add (farmingMarrentil);
        dataHerbFarming.add (farmingTarromin);
        dataHerbFarming.add (farmingHarralander);
        //Causes Exceptions
        //dataHerbFarming.add (farmingRanarr);
        dataHerbFarming.add (farmingToadflax);
        dataHerbFarming.add (farmingIrit);
        dataHerbFarming.add (farmingAvantoe);
        dataHerbFarming.add (farmingKwuarm);
        //Causes Exceptions
        //dataHerbFarming.add (farmingSnapdragon);
        dataHerbFarming.add (farmingCadantine);
        dataHerbFarming.add (farmingLantadyme);
        dataHerbFarming.add (farmingDwarfWeed);
        //Causes Exceptions
        //dataHerbFarming.add (farmingTorstol);

        return(dataHerbFarming);
    }

    //CategoryID = 11 (Cooking Fish)
    public ArrayList<MoneyProcess> dataCookingFish(){

        String j = APIWrapper.pullAWSJson("317,315,327,325,321,319,345,347,353,355,335,333,341,339,349,351,331,329,3379,3381,359,361,10138,10136,5001,5003,377,379,363,365,371,373,7944,7946,3142,3144,383,385,395,397,389,391,13439,13441,11934,11936");

        MoneyProcess cookingShrimp = new MoneyProcess (pullItem(317.0,j), pullItem(315.0,j), 11, 1.0, 30.0, testPlayer);
        MoneyProcess cookingSardine = new MoneyProcess (pullItem(327.0,j), pullItem(325.0,j), 11, 1.0, 40.0, testPlayer);
        MoneyProcess cookingAnchovies = new MoneyProcess (pullItem(321.0,j), pullItem(319.0,j), 11, 1.0, 30.0, testPlayer);
        MoneyProcess cookingHerring = new MoneyProcess (pullItem(345.0,j), pullItem(347.0,j), 11, 5.0, 50.0, testPlayer);
        MoneyProcess cookingMackerel = new MoneyProcess (pullItem(353.0,j), pullItem(355.0,j), 11, 10.0, 60.0, testPlayer);
        MoneyProcess cookingTrout = new MoneyProcess (pullItem(335.0,j), pullItem(333.0,j), 11, 15.0, 70.0, testPlayer);
        MoneyProcess cookingCod = new MoneyProcess (pullItem(341.0,j), pullItem(339.0,j), 11, 18.0, 75.0, testPlayer);
        MoneyProcess cookingPike = new MoneyProcess (pullItem(349.0,j), pullItem(351.0,j), 11, 20.0, 80.0, testPlayer);
        MoneyProcess cookingSalmon = new MoneyProcess (pullItem(331.0,j), pullItem(329.0,j), 11, 25.0, 90.0, testPlayer);
        MoneyProcess cookingSlimyEel = new MoneyProcess (pullItem(3379.0,j), pullItem(3381.0,j), 11, 28.0, 95.0, testPlayer);
        MoneyProcess cookingTuna = new MoneyProcess (pullItem(359.0,j), pullItem(361.0,j), 11, 30.0, 100.0, testPlayer);
        MoneyProcess cookingRainbowFish = new MoneyProcess (pullItem(10138.0,j), pullItem(10136.0,j), 11, 35.0, 110.0, testPlayer);
        MoneyProcess cookingCaveEel = new MoneyProcess (pullItem(5001.0,j), pullItem(5003.0,j), 11, 38.0, 115.0, testPlayer);
        MoneyProcess cookingLobster = new MoneyProcess (pullItem (377.0,j), pullItem (379.0,j), 11, 40.0, 120.0, testPlayer);
        MoneyProcess cookingBass = new MoneyProcess (pullItem (363.0,j), pullItem (365.0,j), 11, 43.0, 130.0, testPlayer);
        MoneyProcess cookingSwordfish = new MoneyProcess (pullItem (371.0,j), pullItem (373.0,j), 11, 45.0, 140.0, testPlayer);
        MoneyProcess cookingMonkfish = new MoneyProcess (pullItem (7944.0,j), pullItem (7946.0,j), 11, 62.0, 150.0, testPlayer);
        MoneyProcess cookingKarambwan = new MoneyProcess (pullItem (3142.0,j), pullItem (3144.0,j), 11, 30.0, 190.0, testPlayer);
        MoneyProcess cookingShark = new MoneyProcess (pullItem (383.0,j), pullItem (385.0,j), 11, 80.0, 210.0, testPlayer);
        MoneyProcess cookingSeaTurtle = new MoneyProcess (pullItem (395.0,j), pullItem (397.0,j), 11, 82.0, 212.0, testPlayer);
        MoneyProcess cookingMantaRay = new MoneyProcess (pullItem (389.0,j), pullItem (391.0,j), 11, 91.0, 216.0, testPlayer);
        MoneyProcess cookingAnglerfish = new MoneyProcess (pullItem (13439.0,j), pullItem (13441.0,j), 11, 84.0, 230.0, testPlayer);
        MoneyProcess cookingDarkCrab = new MoneyProcess (pullItem (11934.0,j), pullItem (11936.0,j), 11, 90.0, 215.0, testPlayer);

        ArrayList<MoneyProcess> dataCookingFish = new ArrayList<MoneyProcess>();

        dataCookingFish.add (cookingShrimp);
        dataCookingFish.add (cookingSardine);
        dataCookingFish.add (cookingAnchovies);
        dataCookingFish.add (cookingHerring);
        dataCookingFish.add (cookingMackerel);
        dataCookingFish.add (cookingTrout);
        dataCookingFish.add (cookingCod);
        dataCookingFish.add (cookingPike);
        dataCookingFish.add (cookingSalmon);
        dataCookingFish.add (cookingSlimyEel);
        dataCookingFish.add (cookingTuna);
        dataCookingFish.add (cookingRainbowFish);
        dataCookingFish.add (cookingCaveEel);
        dataCookingFish.add (cookingLobster);
        dataCookingFish.add (cookingBass);
        dataCookingFish.add (cookingSwordfish);
        dataCookingFish.add (cookingMonkfish);
        dataCookingFish.add (cookingKarambwan);
        dataCookingFish.add (cookingShark);
        dataCookingFish.add (cookingSeaTurtle);
        dataCookingFish.add (cookingMantaRay);
        dataCookingFish.add (cookingAnglerfish);
        dataCookingFish.add (cookingDarkCrab);

        return(dataCookingFish);
    }

    //CategoryID = 14 (Making Planks)
    public ArrayList<MoneyProcess> dataMakingPlanks(){

        String j = APIWrapper.pullAWSJson("1511,960,1521,8778,6333,8780,6332,8782");

        Item ringOfDueling = pullItem (2552.0);

        MoneyProcess makingPlank = new MoneyProcess (pullItem(1511.0,j), ringOfDueling, pullItem(960.0,j), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingOakPlank = new MoneyProcess (pullItem(1521.0,j), ringOfDueling, pullItem(8778.0,j), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingTeakPlank = new MoneyProcess (pullItem(6333.0,j), ringOfDueling, pullItem(8780.0,j), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingMahoganyPlank = new MoneyProcess (pullItem(6332.0,j), ringOfDueling, pullItem(8782.0,j), 14, 50.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataMakingPlanks = new ArrayList<MoneyProcess>();

        dataMakingPlanks.add (makingPlank);
        dataMakingPlanks.add (makingOakPlank);
        dataMakingPlanks.add (makingTeakPlank);
        dataMakingPlanks.add (makingMahoganyPlank);


        return(dataMakingPlanks);
    }

    //CategoryID = 15 (Tanning Leather)
    public ArrayList<MoneyProcess> dataTanningLeather(){

        String j = APIWrapper.pullAWSJson("1739,1741,1739,1743,1753,1745,1751,2505,1749,2507,1747,2509");

        MoneyProcess tanningLeather = new MoneyProcess (pullItem(1739.0,j), pullItem(1741.0,j), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningHardLeather = new MoneyProcess (pullItem(1739.0,j), pullItem(1743.0,j), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningGreenDragon = new MoneyProcess (pullItem(1753.0,j), pullItem(1745.0,j), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningBlueDragon = new MoneyProcess (pullItem(1751.0,j), pullItem(2505.0,j), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningRedDragon = new MoneyProcess (pullItem(1749.0,j), pullItem(2507.0,j), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningBlackDragon = new MoneyProcess (pullItem(1747.0,j), pullItem(2509.0,j), 15, 1.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataTanningLeather = new ArrayList<MoneyProcess>();

        dataTanningLeather.add (tanningLeather);
        dataTanningLeather.add (tanningHardLeather);
        dataTanningLeather.add (tanningGreenDragon);
        dataTanningLeather.add (tanningBlueDragon);
        dataTanningLeather.add (tanningRedDragon);
        dataTanningLeather.add (tanningBlackDragon);


        return(dataTanningLeather);
    }

    //CategoryID = 16, 17, 18 (Decanting Potions)
    //Pulling data for decantPrayer, decantSuperRestore, and decantAntivenomPlus causes exceptions and forces the app to close
    public ArrayList<MoneyProcess> dataDecantPotions(){

        String j = APIWrapper.pullAWSJson("2444,173,171,169,4417,4423,4421,4419,7660,7666,7664,7662,3008,3014,3012,3010,3016,3022,3020,3018,2434,143,141,139,2428,125,123,121,2436,149,147,145,2448,185,183,181,2440,161,159,157,3024,3030,3028,3026,2442,167,165,163,2450,193,191,189,6685,6691,6689,6687,12905,12911,12909,12907,12913,12919,12917,12915,12625,12631,12629,12627");

        Item rangingPotion = pullItem (2444.0,j);
        MoneyProcess decantRanging1 = new MoneyProcess (pullItem(173.0,j), rangingPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantRanging2 = new MoneyProcess (pullItem(171.0,j), rangingPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantRanging3 = new MoneyProcess (pullItem(169.0,j), rangingPotion, 18, 1.0, 0.0, testPlayer);

        Item guthixRestPotion = pullItem (4417.0,j);
        MoneyProcess decantGuthixRest1 = new MoneyProcess (pullItem(4423.0,j), guthixRestPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixRest2 = new MoneyProcess (pullItem(4421.0,j), guthixRestPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixRest3 = new MoneyProcess (pullItem(4419.0,j), guthixRestPotion, 18, 1.0, 0.0, testPlayer);

        Item guthixBalancePotion = pullItem (7660.0,j);
        MoneyProcess decantGuthixBalance1 = new MoneyProcess (pullItem(7666.0,j), guthixBalancePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixBalance2 = new MoneyProcess (pullItem(7664.0,j), guthixBalancePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixBalance3 = new MoneyProcess (pullItem(7662.0,j), guthixBalancePotion, 18, 1.0, 0.0, testPlayer);

        Item energyPotion = pullItem (3008.0,j);
        MoneyProcess decantEnergy1 = new MoneyProcess (pullItem(3014.0,j), energyPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantEnergy2 = new MoneyProcess (pullItem(3012.0,j), energyPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantEnergy3 = new MoneyProcess (pullItem(3010.0,j), energyPotion, 18, 1.0, 0.0, testPlayer);

        Item superEnergyPotion = pullItem (3016.0,j);
        MoneyProcess decantSuperEnergy1 = new MoneyProcess (pullItem(3022.0,j), superEnergyPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperEnergy2 = new MoneyProcess (pullItem(3020.0,j), superEnergyPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperEnergy3 = new MoneyProcess (pullItem(3018.0,j), superEnergyPotion, 18, 1.0, 0.0, testPlayer);

        /*
        Item prayerPotion = pullItem (2434.0);
        MoneyProcess decantPrayer1 = new MoneyProcess (pullItem(143.0), prayerPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantPrayer2 = new MoneyProcess (pullItem(141.0), prayerPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantPrayer3 = new MoneyProcess (pullItem(139.0), prayerPotion, 18, 1.0, 0.0, testPlayer);
        */

        Item attackPotion = pullItem (2428.0,j);
        MoneyProcess decantAttack1 = new MoneyProcess (pullItem(125.0,j), attackPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAttack2 = new MoneyProcess (pullItem(123.0,j), attackPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAttack3 = new MoneyProcess (pullItem(121.0,j), attackPotion, 18, 1.0, 0.0, testPlayer);

        Item superAttackPotion = pullItem (2436.0,j);
        MoneyProcess decantSuperAttack1 = new MoneyProcess (pullItem(149.0,j), superAttackPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAttack2 = new MoneyProcess (pullItem(147.0,j), superAttackPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAttack3 = new MoneyProcess (pullItem(145.0,j), superAttackPotion, 18, 1.0, 0.0, testPlayer);

        Item superAntipoisonPotion = pullItem (2448.0,j);
        MoneyProcess decantSuperAntipoison1 = new MoneyProcess (pullItem(185.0,j), superAntipoisonPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAntipoison2 = new MoneyProcess (pullItem(183.0,j), superAntipoisonPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAntipoison3 = new MoneyProcess (pullItem(181.0,j), superAntipoisonPotion, 18, 1.0, 0.0, testPlayer);

        Item superStrengthPotion = pullItem (2440.0,j);
        MoneyProcess decantSuperStrength1 = new MoneyProcess (pullItem(161.0,j), superStrengthPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperStrength2 = new MoneyProcess (pullItem(159.0,j), superStrengthPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperStrength3 = new MoneyProcess (pullItem(157.0,j), superStrengthPotion, 18, 1.0, 0.0, testPlayer);
        /*
        Item superRestorePotion = pullItem (3024.0);
        MoneyProcess decantSuperRestore1 = new MoneyProcess (pullItem(3030.0), superRestorePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperRestore2 = new MoneyProcess (pullItem(3028.0), superRestorePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperRestore3 = new MoneyProcess (pullItem(3026.0), superRestorePotion, 18, 1.0, 0.0, testPlayer);
        */

        Item superDefencePotion = pullItem (2442.0,j);
        MoneyProcess decantSuperDefence1 = new MoneyProcess (pullItem(167.0,j), superDefencePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperDefence2 = new MoneyProcess (pullItem(165.0,j), superDefencePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperDefence3 = new MoneyProcess (pullItem(163.0,j), superDefencePotion, 18, 1.0, 0.0, testPlayer);

        Item zamorakBrew = pullItem (2450.0,j);
        MoneyProcess decantZamorakBrew1 = new MoneyProcess (pullItem(193.0,j), zamorakBrew, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantZamorakBrew2 = new MoneyProcess (pullItem(191.0,j), zamorakBrew, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantZamorakBrew3 = new MoneyProcess (pullItem(189.0,j), zamorakBrew, 18, 1.0, 0.0, testPlayer);

        Item saradominBrew = pullItem (6685.0,j);
        MoneyProcess decantSaradominBrew1 = new MoneyProcess (pullItem(6691.0,j), saradominBrew, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSaradominBrew2 = new MoneyProcess (pullItem(6689.0,j), saradominBrew, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSaradominBrew3 = new MoneyProcess (pullItem(6687.0,j), saradominBrew, 18, 1.0, 0.0, testPlayer);

        Item antiVenom = pullItem (12905.0,j);
        MoneyProcess decantAntiVenom1 = new MoneyProcess (pullItem(12911.0,j), antiVenom, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenom2 = new MoneyProcess (pullItem(12909.0,j), antiVenom, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenom3 = new MoneyProcess (pullItem(12907.0,j), antiVenom, 18, 1.0, 0.0, testPlayer);

        /*
        Item antiVenomPlus = pullItem (12913.0);
        MoneyProcess decantAntiVenomPlus1 = new MoneyProcess (pullItem(12919.0), antiVenomPlus, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenomPlus2 = new MoneyProcess (pullItem(12917.0), antiVenomPlus, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenomPlus3 = new MoneyProcess (pullItem(12915.0), antiVenomPlus, 18, 1.0, 0.0, testPlayer);
        */

        Item staminaPotion = pullItem (12625.0,j);
        MoneyProcess decantStamina1 = new MoneyProcess (pullItem(12631.0,j), staminaPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantStamina2 = new MoneyProcess (pullItem(12629.0,j), staminaPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantStamina3 = new MoneyProcess (pullItem(12627.0,j), staminaPotion, 18, 1.0, 0.0, testPlayer);



        ArrayList<MoneyProcess> dataDecantPotions = new ArrayList<MoneyProcess>();


        dataDecantPotions.add (decantRanging1);
        dataDecantPotions.add (decantRanging2);
        dataDecantPotions.add (decantRanging3);
        dataDecantPotions.add (decantGuthixRest1);
        dataDecantPotions.add (decantGuthixRest2);
        dataDecantPotions.add (decantGuthixRest3);
        dataDecantPotions.add (decantGuthixBalance1);
        dataDecantPotions.add (decantGuthixBalance2);
        dataDecantPotions.add (decantGuthixBalance3);
        dataDecantPotions.add (decantEnergy1);
        dataDecantPotions.add (decantEnergy2);
        dataDecantPotions.add (decantEnergy3);
        dataDecantPotions.add (decantSuperEnergy1);
        dataDecantPotions.add (decantSuperEnergy2);
        dataDecantPotions.add (decantSuperEnergy3);

        /*
        //Causes Exception
        dataDecantPotions.add (decantPrayer1);
        dataDecantPotions.add (decantPrayer2);
        dataDecantPotions.add (decantPrayer3);
        */
        dataDecantPotions.add (decantAttack1);
        dataDecantPotions.add (decantAttack2);
        dataDecantPotions.add (decantAttack3);
        dataDecantPotions.add (decantSuperAttack1);
        dataDecantPotions.add (decantSuperAttack2);
        dataDecantPotions.add (decantSuperAttack3);
        dataDecantPotions.add (decantSuperAntipoison1);
        dataDecantPotions.add (decantSuperAntipoison2);
        dataDecantPotions.add (decantSuperAntipoison3);
        dataDecantPotions.add (decantSuperStrength1);
        dataDecantPotions.add (decantSuperStrength2);
        dataDecantPotions.add (decantSuperStrength3);
        /*
        //Causes Exception
        dataDecantPotions.add (decantSuperRestore1);
        dataDecantPotions.add (decantSuperRestore2);
        dataDecantPotions.add (decantSuperRestore3);
        */
        dataDecantPotions.add (decantSuperDefence1);
        dataDecantPotions.add (decantSuperDefence2);
        dataDecantPotions.add (decantSuperDefence3);
        dataDecantPotions.add (decantZamorakBrew1);
        dataDecantPotions.add (decantZamorakBrew2);
        dataDecantPotions.add (decantZamorakBrew3);
        dataDecantPotions.add (decantSaradominBrew1);
        dataDecantPotions.add (decantSaradominBrew2);
        dataDecantPotions.add (decantSaradominBrew3);
        dataDecantPotions.add (decantAntiVenom1);
        dataDecantPotions.add (decantAntiVenom2);
        dataDecantPotions.add (decantAntiVenom3);
        /*
        //Causes Exception
        dataDecantPotions.add (decantAntiVenomPlus1);
        dataDecantPotions.add (decantAntiVenomPlus2);
        dataDecantPotions.add (decantAntiVenomPlus3);
        */
        dataDecantPotions.add (decantStamina1);
        dataDecantPotions.add (decantStamina2);
        dataDecantPotions.add (decantStamina3);

        return(dataDecantPotions);
    }

    //CategoryID = 19, 20, 21 (Barrows Repair)
    public ArrayList<MoneyProcess> dataBarrowsRepair(){

        String j = APIWrapper.pullAWSJson("4884,4716,4896,4720,4902,4722,4956,4745,4968,4749,4974,4751,4908,4724,4920,4728,4926,4730,4980,4753,4992,4755,4998,4757,4932,4732,4944,4736,4950,4738,4860,4708,4860,4708,4872,4712,4878,4714");

        //Required level for Barrows repair is 55 construction
        MoneyProcess repairDharokHelm = new MoneyProcess (pullItem(4884.0,j), pullItem(4716.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairDharokBody = new MoneyProcess (pullItem(4896.0,j), pullItem(4720.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairDharokLegs = new MoneyProcess (pullItem(4902.0,j), pullItem(4722.0,j), 21, 55.0, testPlayer);

        MoneyProcess repairToragHelm = new MoneyProcess (pullItem(4956.0,j), pullItem(4745.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairToragBody = new MoneyProcess (pullItem(4968.0,j), pullItem(4749.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairToragLegs = new MoneyProcess (pullItem(4974.0,j), pullItem(4751.0,j), 21, 55.0, testPlayer);

        MoneyProcess repairGuthanHelm = new MoneyProcess (pullItem(4908.0,j), pullItem(4724.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairGuthanBody = new MoneyProcess (pullItem(4920.0,j), pullItem(4728.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairGuthanLegs = new MoneyProcess (pullItem(4926.0,j), pullItem(4730.0,j), 21, 55.0, testPlayer);

        MoneyProcess repairVeracHelm = new MoneyProcess (pullItem(4980.0,j), pullItem(4753.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairVeracBody = new MoneyProcess (pullItem(4992.0,j), pullItem(4755.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairVeracLegs = new MoneyProcess (pullItem(4998.0,j), pullItem(4757.0,j), 21, 55.0, testPlayer);

        MoneyProcess repairKarilHelm = new MoneyProcess (pullItem(4932.0,j), pullItem(4732.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairKarilBody = new MoneyProcess (pullItem(4944.0,j), pullItem(4736.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairKarilLegs = new MoneyProcess (pullItem(4950.0,j), pullItem(4738.0,j), 21, 55.0, testPlayer);

        MoneyProcess repairAhrimHelm = new MoneyProcess (pullItem(4860.0,j), pullItem(4708.0,j), 19, 55.0, testPlayer);
        MoneyProcess repairAhrimBody = new MoneyProcess (pullItem(4872.0,j), pullItem(4712.0,j), 20, 55.0, testPlayer);
        MoneyProcess repairAhrimLegs = new MoneyProcess (pullItem(4878.0,j), pullItem(4714.0,j), 21, 55.0, testPlayer);

        ArrayList<MoneyProcess> dataBarrowsRepair = new ArrayList<MoneyProcess>();

        dataBarrowsRepair.add (repairDharokHelm);
        dataBarrowsRepair.add (repairDharokBody);
        dataBarrowsRepair.add (repairDharokLegs);
        dataBarrowsRepair.add (repairToragHelm);
        dataBarrowsRepair.add (repairToragBody);
        dataBarrowsRepair.add (repairToragLegs);
        dataBarrowsRepair.add (repairGuthanHelm);
        dataBarrowsRepair.add (repairGuthanBody);
        dataBarrowsRepair.add (repairGuthanLegs);
        dataBarrowsRepair.add (repairVeracHelm);
        dataBarrowsRepair.add (repairVeracBody);
        dataBarrowsRepair.add (repairVeracLegs);
        dataBarrowsRepair.add (repairKarilHelm);
        dataBarrowsRepair.add (repairKarilBody);
        dataBarrowsRepair.add (repairKarilLegs);
        dataBarrowsRepair.add (repairAhrimHelm);
        dataBarrowsRepair.add (repairAhrimBody);
        dataBarrowsRepair.add (repairAhrimLegs);

        return(dataBarrowsRepair);
    }

    //CategoryID = 22, 23, 24, 25, 26
    //Alter MoneyProcesses to make sure that they are listed as MemberOnly
    //TODO: Fix BlastFurnace profit calculation methods
    public ArrayList<MoneyProcess> dataBlastFurnace(){

        String j = APIWrapper.pullAWSJson("453,440,444,2357,447,449,451,2353,2359,2361,2363");

        Item coalOre = pullItem (453.0,j);

        MoneyProcess blastSteel = new MoneyProcess (coalOre, pullItem(440.0,j), pullItem (2353.0,j), 22, 30.0, 17.5, testPlayer);
        MoneyProcess blastGold = new MoneyProcess (pullItem(444.0,j), pullItem (2357.0,j), 23, 40.0, 56.2, testPlayer);
        MoneyProcess blastMithril = new MoneyProcess (coalOre, pullItem(447.0,j), pullItem (2359.0,j), 24, 50.0, 30.0, testPlayer);
        MoneyProcess blastAdamantite = new MoneyProcess (coalOre, pullItem(449.0,j), pullItem (2361.0,j), 25, 70.0, 37.5, testPlayer);
        MoneyProcess blastRunite = new MoneyProcess (coalOre, pullItem(451.0,j), pullItem (2363.0,j), 26, 85.0, 50.0, testPlayer);

        blastSteel.setIfMemberOnly (true);
        blastGold.setIfMemberOnly (true);
        blastMithril.setIfMemberOnly (true);
        blastAdamantite.setIfMemberOnly (true);
        blastRunite.setIfMemberOnly (true);

        ArrayList<MoneyProcess> dataBlastFurnace = new ArrayList<MoneyProcess>();

        dataBlastFurnace.add (blastSteel);
        dataBlastFurnace.add(blastGold);
        dataBlastFurnace.add (blastMithril);
        dataBlastFurnace.add (blastAdamantite);
        dataBlastFurnace.add (blastRunite);

        return(dataBlastFurnace);
    }

    //CategoryID = 27
    //TODO: Test HighAlch method
    public ArrayList<MoneyProcess> dataHighAlch(){
        Item natureRune = pullItem (561.0);

        MoneyProcess alchBlackDHide = new MoneyProcess (natureRune, pullItem (2353.0), 27, 55.0, 65.0, testPlayer);

        ArrayList<MoneyProcess> dataHighAlch = new ArrayList<> ();

        return(dataHighAlch);
    }

}
