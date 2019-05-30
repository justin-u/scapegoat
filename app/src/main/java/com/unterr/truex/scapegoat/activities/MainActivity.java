package com.unterr.truex.scapegoat.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.FrameLayout;

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
    public Player testPlayer = APIWrapper.pullPlayer ("Jtruezie");



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



        recyclerView = (RecyclerView) findViewById (R.id.lst);
        layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //TODO: Ensure that JsonData value is not null when making pullItem calls (onCreate)
        JsonData = APIWrapper.pullAWSJson();
        Log.d ("AWS Data", JsonData);
        MoneyProcess cleaningGuam = new MoneyProcess (pullItem(199.0), pullItem(249.0), 1, 3.0, 2.5, testPlayer);
        Log.d("AWS Data", cleaningGuam.toString ());

        //JsonData = APIWrapper.pullAWSJson ();




        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
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
                        mDrawer.closeDrawers();

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
            JSONObject itemObj = reader.getJSONObject ("\"" + urlItemID + "\"");

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

        MoneyProcess cleaningGuam = new MoneyProcess (pullItem(199.0), pullItem(249.0), 1, 3.0, 2.5, testPlayer);
        MoneyProcess cleaningMarrentil = new MoneyProcess (pullItem(201.0), pullItem(251.0), 1, 5.0, 3.75, testPlayer);
        MoneyProcess cleaningTarromin = new MoneyProcess (pullItem(203.0), pullItem(253.0), 1, 11.0, 5.0, testPlayer);
        MoneyProcess cleaningHarralander = new MoneyProcess (pullItem(205.0), pullItem(255.0), 1, 20.0, 6.25, testPlayer);
        MoneyProcess cleaningRanarr = new MoneyProcess (pullItem(207.0), pullItem(257.0), 1, 25.0, 7.5, testPlayer);
        MoneyProcess cleaningToadflax = new MoneyProcess (pullItem(3049.0), pullItem(2998.0), 1, 30.0, 8.0, testPlayer);
        MoneyProcess cleaningIrit = new MoneyProcess (pullItem(209.0), pullItem(259.0), 1, 40.0, 8.75, testPlayer);
        MoneyProcess cleaningAvantoe = new MoneyProcess (pullItem(211.0), pullItem(261.0), 1, 48.0, 10.0, testPlayer);
        MoneyProcess cleaningKwuarm = new MoneyProcess (pullItem(213.0), pullItem(263.0), 1, 54.0, 11.25, testPlayer);
        MoneyProcess cleaningSnapdragon = new MoneyProcess (pullItem(3051.0), pullItem(3000.0), 1, 59.0, 11.75, testPlayer);
        MoneyProcess cleaningCadantine = new MoneyProcess (pullItem(215.0), pullItem(265.0), 1, 65.0, 12.5, testPlayer);
        MoneyProcess cleaningLantadyme = new MoneyProcess (pullItem(2485.0), pullItem(2481.0), 1, 67.0, 13.125, testPlayer);
        MoneyProcess cleaningDwarfWeed = new MoneyProcess (pullItem(217.0), pullItem(267.0), 1, 70.0, 13.75, testPlayer);
        MoneyProcess cleaningTorstol = new MoneyProcess (pullItem (219.0), pullItem (269.0), 1, 75.0, 15.0, testPlayer);

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

        MoneyProcess unfGuam = new MoneyProcess (pullItem(249.0), pullItem(91.0), 2, 3.0, 0.0, testPlayer);
        MoneyProcess unfMarrentil = new MoneyProcess (pullItem(251.0), pullItem(93.0), 2, 5.0, 0.0, testPlayer);
        MoneyProcess unfTarromin = new MoneyProcess (pullItem(253.0), pullItem(95.0), 2, 11.0, 0.0, testPlayer);
        MoneyProcess unfHarralander = new MoneyProcess (pullItem(255.0), pullItem(97.0), 2, 20.0, 0.0, testPlayer);
        MoneyProcess unfRanarr = new MoneyProcess (pullItem(257.0), pullItem(99.0), 2, 25.0, 0.0, testPlayer);
        MoneyProcess unfToadflax = new MoneyProcess (pullItem(2998.0), pullItem(3002.0), 2, 30.0, 0.0, testPlayer);
        MoneyProcess unfIrit = new MoneyProcess (pullItem(259.0), pullItem(101.0), 2, 40.0, 0.0, testPlayer);
        MoneyProcess unfAvantoe = new MoneyProcess (pullItem(261.0), pullItem(103.0), 2, 48.0, 0.0, testPlayer);
        MoneyProcess unfKwuarm = new MoneyProcess (pullItem(263.0), pullItem(105.0), 2, 54.0, 0.0, testPlayer);
        MoneyProcess unfSnapdragon = new MoneyProcess (pullItem(3000.0), pullItem(3004.0), 2, 59.0, 0.0, testPlayer);
        MoneyProcess unfCadantine = new MoneyProcess (pullItem(265.0), pullItem(107.0), 2, 65.0, 0.0, testPlayer);
        MoneyProcess unfLantadyme = new MoneyProcess (pullItem(2481.0), pullItem(2483.0), 2, 67.0, 0.0, testPlayer);
        MoneyProcess unfDwarfWeed = new MoneyProcess (pullItem(267.0), pullItem(109.0), 2, 70.0, 0.0, testPlayer);
        MoneyProcess unfTorstol = new MoneyProcess (pullItem (269.0), pullItem (111.0), 2, 75.0, 0.0, testPlayer);

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

        MoneyProcess sapOak = new MoneyProcess (pullItem(5312.0), pullItem(5370.0), 3, 15.0, 0.0, testPlayer);
        MoneyProcess sapApple = new MoneyProcess (pullItem(5283.0), pullItem(5496.0), 3, 27.0, 0.0, testPlayer);
        MoneyProcess sapWillow = new MoneyProcess (pullItem(5313.0), pullItem(5371.0), 3, 30.0, 0.0, testPlayer);
        MoneyProcess sapBanana = new MoneyProcess (pullItem(5284.0), pullItem(5497.0),  3, 33.0, 0.0, testPlayer);
        MoneyProcess sapTeak = new MoneyProcess (pullItem(21486.0), pullItem(21477.0),  3, 35.0, 0.0, testPlayer);
        MoneyProcess sapOrange = new MoneyProcess (pullItem(5285.0), pullItem(5498.0), 3, 39.0, 0.0, testPlayer);
        MoneyProcess sapCurry = new MoneyProcess (pullItem(5286.0), pullItem(5499.0),  3, 42.0, 0.0, testPlayer);
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
        MoneyProcess cuttingOpalTips = new MoneyProcess (pullItem(1609.0), pullItem(45.0), 4, 11.0, 1.6, testPlayer);
        MoneyProcess cuttingJadeTips = new MoneyProcess (pullItem(1611.0), pullItem(9187.0), 4, 26.0, 2.4, testPlayer);
        MoneyProcess cuttingRedTopazTips = new MoneyProcess (pullItem(1613.0), pullItem(9188.0), 4, 48.0, 4.0, testPlayer);
        MoneyProcess cuttingSapphireTips = new MoneyProcess (pullItem(1607.0), pullItem(9189.0), 4, 56.0, 4.0, testPlayer);
        MoneyProcess cuttingEmeraldTips = new MoneyProcess (pullItem(1605.0), pullItem(9190.0), 4, 58.0, 5.5, testPlayer);
        MoneyProcess cuttingRubyTips = new MoneyProcess (pullItem(1603.0), pullItem(9191.0), 4, 63.0, 6.0, testPlayer);
        MoneyProcess cuttingDiamondTips = new MoneyProcess (pullItem(1601.0), pullItem(9192.0), 4, 65.0, 7.0, testPlayer);

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
        MoneyProcess fletchSB = new MoneyProcess (pullItem(1511.0), pullItem(50.0), 5, 5.0, 5.0, testPlayer);
        MoneyProcess fletchLB = new MoneyProcess (pullItem(1511.0), pullItem(48.0), 5, 10.0, 10.0, testPlayer);
        MoneyProcess fletchOakSB = new MoneyProcess (pullItem(1521.0), pullItem(54.0), 5, 20.0, 16.5, testPlayer);
        MoneyProcess fletchOakLB = new MoneyProcess (pullItem(1521.0), pullItem(56.0), 5, 25.0, 25.0, testPlayer);
        MoneyProcess fletchWillowSB = new MoneyProcess (pullItem(1519.0), pullItem(60.0), 5, 35.0, 33.3, testPlayer);
        MoneyProcess fletchWillowLB = new MoneyProcess (pullItem(1519.0), pullItem(58.0), 5, 40.0, 41.5, testPlayer);
        MoneyProcess fletchMapleSB = new MoneyProcess (pullItem(1517.0), pullItem(64.0), 5, 50.0, 50.0, testPlayer);
        MoneyProcess fletchMapleLB = new MoneyProcess (pullItem(1517.0), pullItem(62.0), 5, 55.0, 58.3, testPlayer);
        MoneyProcess fletchYewSB = new MoneyProcess (pullItem(1515.0), pullItem(68.0), 5, 65.0, 67.5, testPlayer);
        MoneyProcess fletchYewLB = new MoneyProcess (pullItem(1515.0), pullItem(66.0), 5, 70.0, 75.5, testPlayer);
        MoneyProcess fletchMagicSB = new MoneyProcess (pullItem(1513.0), pullItem(72.0), 5, 80.0, 83.3, testPlayer);
        MoneyProcess fletchMagicLB = new MoneyProcess (pullItem(1513.0), pullItem(70.0), 5, 85.0, 91.5, testPlayer);


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
        Item bowString = pullItem (1777.0);
        MoneyProcess stringSB = new MoneyProcess (pullItem(50.0), bowString, pullItem(841.0), 5, 5.0, 5.0, testPlayer);
        MoneyProcess stringLB = new MoneyProcess (pullItem(48.0), bowString, pullItem(839.0), 5, 10.0, 10.0, testPlayer);
        MoneyProcess stringOakSB = new MoneyProcess (pullItem(54.0), bowString, pullItem(843.0), 5, 20.0, 16.5, testPlayer);
        MoneyProcess stringOakLB = new MoneyProcess (pullItem(56.0), bowString, pullItem(845.0), 5, 25.0, 25.0, testPlayer);
        MoneyProcess stringWillowSB = new MoneyProcess (pullItem(60.0), bowString, pullItem(849.0), 5, 35.0, 33.3, testPlayer);
        MoneyProcess stringWillowLB = new MoneyProcess (pullItem(58.0), bowString, pullItem(847.0), 5, 40.0, 41.5, testPlayer);
        MoneyProcess stringMapleSB = new MoneyProcess (pullItem(64.0), bowString, pullItem(853.0), 5, 50.0, 50.0, testPlayer);
        MoneyProcess stringMapleLB = new MoneyProcess (pullItem(62.0), bowString, pullItem(859.0), 5, 55.0, 58.3, testPlayer);
        MoneyProcess stringYewSB = new MoneyProcess (pullItem(68.0), bowString, pullItem(857.0), 5, 65.0, 67.5, testPlayer);
        MoneyProcess stringYewLB = new MoneyProcess (pullItem(66.0), bowString, pullItem(855.0), 5, 70.0, 75.5, testPlayer);
        MoneyProcess stringMagicSB = new MoneyProcess (pullItem(72.0), bowString, pullItem(861.0), 5, 80.0, 83.3, testPlayer);
        MoneyProcess stringMagicLB = new MoneyProcess (pullItem(70.0), bowString, pullItem(859.0), 5, 85.0, 91.5, testPlayer);


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

        MoneyProcess smithBronzeTips = new MoneyProcess (pullItem(2349.0), pullItem(819.0), 7, 4.0, 12.5, testPlayer);
        MoneyProcess smithIronTips = new MoneyProcess (pullItem(2351.0), pullItem(820.0), 7, 19.0, 25.0, testPlayer);
        MoneyProcess smithSteelTips = new MoneyProcess (pullItem(2353.0), pullItem(821.0), 7, 34.0, 37.5, testPlayer);
        MoneyProcess smithMithrilTips = new MoneyProcess (pullItem(2359.0), pullItem(822.0), 7, 54.0, 50.0, testPlayer);
        MoneyProcess smithAdamantTips = new MoneyProcess (pullItem(2361.0), pullItem(823.0), 7, 74.0, 62.5, testPlayer);
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

        Item ultracompost = pullItem (21483.0);

        MoneyProcess farmingGuam = new MoneyProcess (pullItem(5291.0), ultracompost, pullItem(199.0), 10, 9.0, 12.5, testPlayer);
        MoneyProcess farmingMarrentil = new MoneyProcess (pullItem(5292.0), ultracompost, pullItem(201.0), 10, 14.0, 15.0, testPlayer);
        MoneyProcess farmingTarromin = new MoneyProcess (pullItem(5293.0), ultracompost, pullItem(203.0), 10, 19.0, 18.0, testPlayer);
        MoneyProcess farmingHarralander = new MoneyProcess (pullItem(5294.0), ultracompost, pullItem(205.0), 10, 26.0, 24.0, testPlayer);
        //Causes Exceptions
        //MoneyProcess farmingRanarr = new MoneyProcess (pullItem(5295.0), ultracompost, pullItem(207.0), 10, 32.0, 30.5, testPlayer);
        MoneyProcess farmingToadflax = new MoneyProcess (pullItem(5296.0), ultracompost, pullItem(3049.0), 10, 38.0, 38.5, testPlayer);
        MoneyProcess farmingIrit = new MoneyProcess (pullItem(5297.0), ultracompost, pullItem(209.0), 10, 44.0, 48.5, testPlayer);
        MoneyProcess farmingAvantoe = new MoneyProcess (pullItem(5298.0), ultracompost, pullItem(211.0), 10, 50.0, 61.5, testPlayer);
        MoneyProcess farmingKwuarm = new MoneyProcess (pullItem(5299.0), ultracompost, pullItem(213.0), 10, 56.0, 78.0, testPlayer);
        //Caises Exceptions
        //MoneyProcess farmingSnapdragon = new MoneyProcess (pullItem(5300.0), ultracompost, pullItem(3051.0), 10, 62.0, 98.5, testPlayer);
        MoneyProcess farmingCadantine = new MoneyProcess (pullItem(5301.0), ultracompost, pullItem(215.0), 10, 67.0, 120.0, testPlayer);
        MoneyProcess farmingLantadyme = new MoneyProcess (pullItem(5302.0), ultracompost, pullItem(2485.0), 10, 73.0, 151.5, testPlayer);
        MoneyProcess farmingDwarfWeed = new MoneyProcess (pullItem(5303.0), ultracompost, pullItem(217.0), 10, 79.0, 192.0, testPlayer);
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

        MoneyProcess cookingShrimp = new MoneyProcess (pullItem(317.0), pullItem(315.0), 11, 1.0, 30.0, testPlayer);
        MoneyProcess cookingSardine = new MoneyProcess (pullItem(327.0), pullItem(325.0), 11, 1.0, 40.0, testPlayer);
        MoneyProcess cookingAnchovies = new MoneyProcess (pullItem(321.0), pullItem(319.0), 11, 1.0, 30.0, testPlayer);
        MoneyProcess cookingHerring = new MoneyProcess (pullItem(345.0), pullItem(347.0), 11, 5.0, 50.0, testPlayer);
        MoneyProcess cookingMackerel = new MoneyProcess (pullItem(353.0), pullItem(355.0), 11, 10.0, 60.0, testPlayer);
        MoneyProcess cookingTrout = new MoneyProcess (pullItem(335.0), pullItem(333.0), 11, 15.0, 70.0, testPlayer);
        MoneyProcess cookingCod = new MoneyProcess (pullItem(341.0), pullItem(339.0), 11, 18.0, 75.0, testPlayer);
        MoneyProcess cookingPike = new MoneyProcess (pullItem(349.0), pullItem(351.0), 11, 20.0, 80.0, testPlayer);
        MoneyProcess cookingSalmon = new MoneyProcess (pullItem(331.0), pullItem(329.0), 11, 25.0, 90.0, testPlayer);
        MoneyProcess cookingSlimyEel = new MoneyProcess (pullItem(3379.0), pullItem(3381.0), 11, 28.0, 95.0, testPlayer);
        MoneyProcess cookingTuna = new MoneyProcess (pullItem(359.0), pullItem(361.0), 11, 30.0, 100.0, testPlayer);
        MoneyProcess cookingRainbowFish = new MoneyProcess (pullItem(10138.0), pullItem(10136.0), 11, 35.0, 110.0, testPlayer);
        MoneyProcess cookingCaveEel = new MoneyProcess (pullItem(5001.0), pullItem(5003.0), 11, 38.0, 115.0, testPlayer);
        MoneyProcess cookingLobster = new MoneyProcess (pullItem (377.0), pullItem (379.0), 11, 40.0, 120.0, testPlayer);
        MoneyProcess cookingBass = new MoneyProcess (pullItem (363.0), pullItem (365.0), 11, 43.0, 130.0, testPlayer);
        MoneyProcess cookingSwordfish = new MoneyProcess (pullItem (371.0), pullItem (373.0), 11, 45.0, 140.0, testPlayer);
        MoneyProcess cookingMonkfish = new MoneyProcess (pullItem (7944.0), pullItem (7946.0), 11, 62.0, 150.0, testPlayer);
        MoneyProcess cookingKarambwan = new MoneyProcess (pullItem (3142.0), pullItem (3144.0), 11, 30.0, 190.0, testPlayer);
        MoneyProcess cookingShark = new MoneyProcess (pullItem (383.0), pullItem (385.0), 11, 80.0, 210.0, testPlayer);
        MoneyProcess cookingSeaTurtle = new MoneyProcess (pullItem (395.0), pullItem (397.0), 11, 82.0, 212.0, testPlayer);
        MoneyProcess cookingMantaRay = new MoneyProcess (pullItem (389.0), pullItem (391.0), 11, 91.0, 216.0, testPlayer);
        MoneyProcess cookingAnglerfish = new MoneyProcess (pullItem (13439.0), pullItem (13441.0), 11, 84.0, 230.0, testPlayer);
        MoneyProcess cookingDarkCrab = new MoneyProcess (pullItem (11934.0), pullItem (11936.0), 11, 90.0, 215.0, testPlayer);

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
        Item ringOfDueling = pullItem (2552.0);

        MoneyProcess makingPlank = new MoneyProcess (pullItem(1511.0), ringOfDueling, pullItem(960.0), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingOakPlank = new MoneyProcess (pullItem(1521.0), ringOfDueling, pullItem(8778.0), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingTeakPlank = new MoneyProcess (pullItem(6333.0), ringOfDueling, pullItem(8780.0), 14, 50.0, 0.0, testPlayer);
        MoneyProcess makingMahoganyPlank = new MoneyProcess (pullItem(6332.0), ringOfDueling, pullItem(8782.0), 14, 50.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataMakingPlanks = new ArrayList<MoneyProcess>();

        dataMakingPlanks.add (makingPlank);
        dataMakingPlanks.add (makingOakPlank);
        dataMakingPlanks.add (makingTeakPlank);
        dataMakingPlanks.add (makingMahoganyPlank);


        return(dataMakingPlanks);
    }

    //CategoryID = 15 (Tanning Leather)
    public ArrayList<MoneyProcess> dataTanningLeather(){

        MoneyProcess tanningLeather = new MoneyProcess (pullItem(1739.0), pullItem(1741.0), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningHardLeather = new MoneyProcess (pullItem(1739.0), pullItem(1743.0), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningGreenDragon = new MoneyProcess (pullItem(1753.0), pullItem(1745.0), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningBlueDragon = new MoneyProcess (pullItem(1751.0), pullItem(2505.0), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningRedDragon = new MoneyProcess (pullItem(1749.0), pullItem(2507.0), 15, 1.0, 0.0, testPlayer);
        MoneyProcess tanningBlackDragon = new MoneyProcess (pullItem(1747.0), pullItem(2509.0), 15, 1.0, 0.0, testPlayer);

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

        Item rangingPotion = pullItem (2444.0);
        MoneyProcess decantRanging1 = new MoneyProcess (pullItem(173.0), rangingPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantRanging2 = new MoneyProcess (pullItem(171.0), rangingPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantRanging3 = new MoneyProcess (pullItem(169.0), rangingPotion, 18, 1.0, 0.0, testPlayer);

        Item guthixRestPotion = pullItem (4417.0);
        MoneyProcess decantGuthixRest1 = new MoneyProcess (pullItem(4423.0), guthixRestPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixRest2 = new MoneyProcess (pullItem(4421.0), guthixRestPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixRest3 = new MoneyProcess (pullItem(4419.0), guthixRestPotion, 18, 1.0, 0.0, testPlayer);

        Item guthixBalancePotion = pullItem (7660.0);
        MoneyProcess decantGuthixBalance1 = new MoneyProcess (pullItem(7666.0), guthixBalancePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixBalance2 = new MoneyProcess (pullItem(7664.0), guthixBalancePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantGuthixBalance3 = new MoneyProcess (pullItem(7662.0), guthixBalancePotion, 18, 1.0, 0.0, testPlayer);

        Item energyPotion = pullItem (3008.0);
        MoneyProcess decantEnergy1 = new MoneyProcess (pullItem(3014.0), energyPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantEnergy2 = new MoneyProcess (pullItem(3012.0), energyPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantEnergy3 = new MoneyProcess (pullItem(3010.0), energyPotion, 18, 1.0, 0.0, testPlayer);

        Item superEnergyPotion = pullItem (3016.0);
        MoneyProcess decantSuperEnergy1 = new MoneyProcess (pullItem(3022.0), superEnergyPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperEnergy2 = new MoneyProcess (pullItem(3020.0), superEnergyPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperEnergy3 = new MoneyProcess (pullItem(3018.0), superEnergyPotion, 18, 1.0, 0.0, testPlayer);

        /*
        Item prayerPotion = pullItem (2434.0);
        MoneyProcess decantPrayer1 = new MoneyProcess (pullItem(143.0), prayerPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantPrayer2 = new MoneyProcess (pullItem(141.0), prayerPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantPrayer3 = new MoneyProcess (pullItem(139.0), prayerPotion, 18, 1.0, 0.0, testPlayer);
        */

        Item attackPotion = pullItem (2428.0);
        MoneyProcess decantAttack1 = new MoneyProcess (pullItem(125.0), attackPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAttack2 = new MoneyProcess (pullItem(123.0), attackPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAttack3 = new MoneyProcess (pullItem(121.0), attackPotion, 18, 1.0, 0.0, testPlayer);

        Item superAttackPotion = pullItem (2436.0);
        MoneyProcess decantSuperAttack1 = new MoneyProcess (pullItem(149.0), superAttackPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAttack2 = new MoneyProcess (pullItem(147.0), superAttackPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAttack3 = new MoneyProcess (pullItem(145.0), superAttackPotion, 18, 1.0, 0.0, testPlayer);

        Item superAntipoisonPotion = pullItem (2448.0);
        MoneyProcess decantSuperAntipoison1 = new MoneyProcess (pullItem(185.0), superAntipoisonPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAntipoison2 = new MoneyProcess (pullItem(183.0), superAntipoisonPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperAntipoison3 = new MoneyProcess (pullItem(181.0), superAntipoisonPotion, 18, 1.0, 0.0, testPlayer);

        Item superStrengthPotion = pullItem (2440.0);
        MoneyProcess decantSuperStrength1 = new MoneyProcess (pullItem(161.0), superStrengthPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperStrength2 = new MoneyProcess (pullItem(159.0), superStrengthPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperStrength3 = new MoneyProcess (pullItem(157.0), superStrengthPotion, 18, 1.0, 0.0, testPlayer);
        /*
        Item superRestorePotion = pullItem (3024.0);
        MoneyProcess decantSuperRestore1 = new MoneyProcess (pullItem(3030.0), superRestorePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperRestore2 = new MoneyProcess (pullItem(3028.0), superRestorePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperRestore3 = new MoneyProcess (pullItem(3026.0), superRestorePotion, 18, 1.0, 0.0, testPlayer);
        */

        Item superDefencePotion = pullItem (2442.0);
        MoneyProcess decantSuperDefence1 = new MoneyProcess (pullItem(167.0), superDefencePotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperDefence2 = new MoneyProcess (pullItem(165.0), superDefencePotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSuperDefence3 = new MoneyProcess (pullItem(163.0), superDefencePotion, 18, 1.0, 0.0, testPlayer);

        Item zamorakBrew = pullItem (2450.0);
        MoneyProcess decantZamorakBrew1 = new MoneyProcess (pullItem(193.0), zamorakBrew, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantZamorakBrew2 = new MoneyProcess (pullItem(191.0), zamorakBrew, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantZamorakBrew3 = new MoneyProcess (pullItem(189.0), zamorakBrew, 18, 1.0, 0.0, testPlayer);

        Item saradominBrew = pullItem (6685.0);
        MoneyProcess decantSaradominBrew1 = new MoneyProcess (pullItem(6691.0), saradominBrew, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantSaradominBrew2 = new MoneyProcess (pullItem(6689.0), saradominBrew, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantSaradominBrew3 = new MoneyProcess (pullItem(6687.0), saradominBrew, 18, 1.0, 0.0, testPlayer);

        Item antiVenom = pullItem (12905.0);
        MoneyProcess decantAntiVenom1 = new MoneyProcess (pullItem(12911.0), antiVenom, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenom2 = new MoneyProcess (pullItem(12909.0), antiVenom, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenom3 = new MoneyProcess (pullItem(12907.0), antiVenom, 18, 1.0, 0.0, testPlayer);

        /*
        Item antiVenomPlus = pullItem (12913.0);
        MoneyProcess decantAntiVenomPlus1 = new MoneyProcess (pullItem(12919.0), antiVenomPlus, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenomPlus2 = new MoneyProcess (pullItem(12917.0), antiVenomPlus, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantAntiVenomPlus3 = new MoneyProcess (pullItem(12915.0), antiVenomPlus, 18, 1.0, 0.0, testPlayer);
        */

        Item staminaPotion = pullItem (12625.0);
        MoneyProcess decantStamina1 = new MoneyProcess (pullItem(12631.0), staminaPotion, 16, 1.0, 0.0, testPlayer);
        MoneyProcess decantStamina2 = new MoneyProcess (pullItem(12629.0), staminaPotion, 17, 1.0, 0.0, testPlayer);
        MoneyProcess decantStamina3 = new MoneyProcess (pullItem(12627.0), staminaPotion, 18, 1.0, 0.0, testPlayer);



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

    //TODO: Finish BarrowsRepair ArrayList
    public ArrayList<MoneyProcess> dataBarrowsRepair(){

        MoneyProcess repairDharokHelm = new MoneyProcess (pullItem(4884.0), pullItem(4716.0), 19, 1.0, 0.0, testPlayer);
        MoneyProcess repairDharokBody = new MoneyProcess (pullItem(4896.0), pullItem(4720.0), 20, 1.0, 0.0, testPlayer);
        MoneyProcess repairDharokLegs = new MoneyProcess (pullItem(4902.0), pullItem(4722.0), 21, 1.0, 0.0, testPlayer);

        ArrayList<MoneyProcess> dataBarrowsRepair = new ArrayList<MoneyProcess>();


        dataBarrowsRepair.add (repairDharokHelm);
        dataBarrowsRepair.add (repairDharokBody);
        dataBarrowsRepair.add (repairDharokLegs);


        return(dataBarrowsRepair);
    }

}
