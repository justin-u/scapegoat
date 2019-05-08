package com.unterr.truex.scapegoat.methods;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import  com.unterr.truex.scapegoat.models.Item;
import  com.unterr.truex.scapegoat.models.Player;

//Import statements to handle JSON data and JSON exceptions
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/*
 * APIWrapper:
 * methods for interacting with the Runescape web service
 */

public class APIWrapper {

    public static Boolean verifyUsername( String username ){

        //TODO: Fix verifyUsername to return Boolean
        Boolean verify = false;

        class DownloadPlayer extends AsyncTask<String, Void, Boolean> {

            @Override
            protected Boolean doInBackground(String... urls) {

                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int data = reader.read();

                    while (data != -1) {

                        char current = (char) data;

                        result += current;

                        data = reader.read();

                    }
                    return true;

                } catch (Exception e) {

                    Log.e("JSONException","JSON Download Error:" + e.getMessage());
                }

                return false;
            }
        }

        try{
            verify = new DownloadPlayer().execute("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username).get();

        }catch(InterruptedException e){
            Log.e("AsyncException","Interrupted Exception:" + e.getMessage());
        }catch (ExecutionException e){
            Log.e("AsyncException","Execution Exception:" + e.getMessage());
        }

        return verify;
    }

    public static Player pullPlayer( String username ){

        String rawUser = "";


        class DownloadPlayer extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... urls) {

                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int data = reader.read();

                    while (data != -1) {

                        char current = (char) data;

                        result += current;

                        data = reader.read();

                    }
                    return result;

                } catch (Exception e) {

                    Log.e("JSONException","JSON Download Error:" + e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i("PlayerData","User Data:" + result);

            }
        }

        try{
            rawUser = new DownloadPlayer().execute("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username).get();
        }catch(InterruptedException e){
            Log.e("AsyncException","Interrupted Exception:" + e.getMessage());
        }catch (ExecutionException e){
            Log.e("AsyncException","Execution Exception:" + e.getMessage());
        }
        //TODO: Use loop that pulls the data every 2 indices of the array.

        Log.i("UserData", "User data" + rawUser);
        String[] userArray = rawUser.split(",");

        Double _attackLvl = Double.parseDouble (userArray[3]);
        Log.i("SpecData", _attackLvl.toString ());
        Double _defenceLvl = Double.parseDouble (userArray[5]);
        Log.i("SpecData", _defenceLvl.toString ());
        Double _strengthLvl = Double.parseDouble (userArray[7]);
        Log.i("SpecData", _strengthLvl.toString ());
        Double _hitpointsLvl = Double.parseDouble (userArray[9]);
        Log.i("SpecData", _hitpointsLvl.toString ());
        Double _rangedLvl = Double.parseDouble (userArray[11]);
        Log.i("SpecData", _rangedLvl.toString ());
        Double _prayerLvl = Double.parseDouble (userArray[13]);
        Log.i("SpecData", _prayerLvl.toString ());
        Double _magicLvl = Double.parseDouble (userArray[15]);
        Log.i("SpecData", _magicLvl.toString ());
        Double _cookingLvl = Double.parseDouble (userArray[17]);
        Log.i("SpecData", _cookingLvl.toString ());
        Double _woodcuttingLvl = Double.parseDouble (userArray[19]);
        Log.i("SpecData", _woodcuttingLvl.toString ());
        Double _fletchingLvl = Double.parseDouble (userArray[21]);
        Log.i("SpecData", _fletchingLvl.toString ());
        Double _fishingLvl = Double.parseDouble (userArray[23]);
        Log.i("SpecData", _fishingLvl.toString ());
        Double _firemakingLvl = Double.parseDouble (userArray[25]);
        Log.i("SpecData", _firemakingLvl.toString ());
        Double _craftingLvl = Double.parseDouble (userArray[27]);
        Log.i("SpecData", _craftingLvl.toString ());
        Double _smithingLvl = Double.parseDouble (userArray[29]);
        Log.i("SpecData", _smithingLvl.toString ());
        Double _miningLvl = Double.parseDouble (userArray[31]);
        Log.i("SpecData", _miningLvl.toString ());
        Double _herbLvl = Double.parseDouble (userArray[33]);
        Log.i("SpecData", _herbLvl.toString ());
        Double _agilityLvl = Double.parseDouble (userArray[35]);
        Log.i("SpecData", _agilityLvl.toString ());
        Double _thievingLvl = Double.parseDouble (userArray[37]);
        Log.i("SpecData", _thievingLvl.toString ());
        Double _slayerLvl = Double.parseDouble (userArray[39]);
        Log.i("SpecData", _slayerLvl.toString ());
        Double _farmingLvl = Double.parseDouble (userArray[41]);
        Log.i("SpecData", _farmingLvl.toString ());
        Double _runecraftingLvl = Double.parseDouble (userArray[43]);
        Log.i("SpecData", _runecraftingLvl.toString ());
        Double _hunterLvl = Double.parseDouble (userArray[45]);
        Log.i("SpecData", _hunterLvl.toString ());
        Double _constructionLvl = Double.parseDouble (userArray[47]);
        Log.i("SpecData", _constructionLvl.toString ());

        Player newPlayerObject = new Player(username, _attackLvl, _defenceLvl, _strengthLvl, _hitpointsLvl, _rangedLvl,
                _prayerLvl, _magicLvl, _cookingLvl, _woodcuttingLvl, _fletchingLvl, _fishingLvl,
                _firemakingLvl, _craftingLvl, _smithingLvl, _miningLvl, _herbLvl, _agilityLvl,
                _thievingLvl, _slayerLvl, _farmingLvl, _runecraftingLvl, _hunterLvl, _constructionLvl);


        //task.execute("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username);

        return newPlayerObject;
    }

    public static String pullAWSJson(){
        String JsonData = "";

        class DownloadItem extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... urls) {

                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);

                    int data = reader.read();

                    while (data != -1) {

                        char current = (char) data;

                        result += current;

                        data = reader.read();

                    }

                    return result;

                } catch (Exception e) {

                    Log.e("JSONException","JSON Download Error:" + e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


            }
        }

        try{
            JsonData = new DownloadItem().execute("https://0ennap82fi.execute-api.us-east-2.amazonaws.com/stable/getAll").get();
        }catch(InterruptedException e){
            Log.e("AsyncException","Interrupted Exception:" + e.getMessage());
            return null;
        }catch (ExecutionException e){
            Log.e("AsyncException","Execution Exception:" + e.getMessage());
            return null;
        }

        return JsonData;
    }


    //TODO: Alter parameters to only include itemID. JsonData can be pulled from public variable
    public static Item pullItem(final Double itemID, String JsonData){

        String _iconURL = new String();
        String _iconLargeURL = new String();
        Double _itemID = 0.0;
        Boolean _memberOnly = false;
        String _name = new String();
        Double _tradePrice = 0.0;

        try {
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

    public static Drawable pullIcon( String iconURL ){

        Bitmap x;
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(iconURL).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            x = BitmapFactory.decodeStream(input);
        } catch (Exception e){
            Log.e("HTTPError","HTTP Connection Error:" + e.getMessage());
            return null;
        }
        //TODO: alter pullIcon BitmapDrawable to non deprecated method
        return new BitmapDrawable(x);

        //TODO: debug pullIcon method
    }

    public static Drawable pullIconLarge( String iconURL ){

        Bitmap x;
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(iconURL).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            x = BitmapFactory.decodeStream(input);
        } catch (Exception e){
            Log.e("HTTPError","HTTP Connection Error:" + e.getMessage());
            return null;
        }
        //TODO: alter pullIconLarge BitmapDrawable to non deprecated method
        return new BitmapDrawable(x);

        //TODO: debug pullIconLarge method

    }
}
