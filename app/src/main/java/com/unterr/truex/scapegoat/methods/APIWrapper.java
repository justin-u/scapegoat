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

        class DownloadUser extends AsyncTask<String, Void, Boolean> {

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

                } catch (Exception e) {
                    Log.e("JSONException","JSON Download Error:" + e.getMessage());
                    return false;
                }

                if (result != ""){
                    Log.i("VerifyUser","User Verified:" + result);
                    return true;
                } else{
                    Log.i("VerifyUser","User Not Found");
                    return false;
                }
            }
        }

        return false;

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
                //delegate.processFinish(result);

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
        Double _strengthLvl = Double.parseDouble (userArray[7]);
        Double _hitpointsLvl = Double.parseDouble (userArray[9]);
        Double _rangedLvl = Double.parseDouble (userArray[11]);
        Double _prayerLvl = Double.parseDouble (userArray[13]);
        Double _magicLvl = Double.parseDouble (userArray[15]);
        Double _cookingLvl = Double.parseDouble (userArray[17]);
        Double _woodcuttingLvl = Double.parseDouble (userArray[19]);
        Double _fletchingLvl = Double.parseDouble (userArray[21]);
        Double _fishingLvl = Double.parseDouble (userArray[23]);
        Double _firemakingLvl = Double.parseDouble (userArray[25]);
        Double _craftingLvl = Double.parseDouble (userArray[27]);
        Double _smithingLvl = Double.parseDouble (userArray[29]);
        Double _miningLvl = Double.parseDouble (userArray[31]);
        Double _herbLvl = Double.parseDouble (userArray[33]);
        Double _agilityLvl = Double.parseDouble (userArray[35]);
        Double _thievingLvl = Double.parseDouble (userArray[37]);
        Double _slayerLvl = Double.parseDouble (userArray[39]);
        Double _farmingLvl = Double.parseDouble (userArray[41]);
        Double _runecraftingLvl = Double.parseDouble (userArray[43]);
        Double _hunterLvl = Double.parseDouble (userArray[45]);
        Log.i("SpecData", _hunterLvl.toString ());
        Double _constructionLvl = Double.parseDouble (userArray[47]);

        Player newPlayerObject = new Player(username, _attackLvl, _defenceLvl, _strengthLvl, _hitpointsLvl, _rangedLvl,
                _prayerLvl, _magicLvl, _cookingLvl, _woodcuttingLvl, _fletchingLvl, _fishingLvl,
                _firemakingLvl, _craftingLvl, _smithingLvl, _miningLvl, _herbLvl, _agilityLvl,
                _thievingLvl, _slayerLvl, _farmingLvl, _runecraftingLvl, _hunterLvl, _constructionLvl);


        //task.execute("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username);

        return newPlayerObject;
    }

    //Method to pull all itemIDs from a given category from the OSRS API and return the itemIDs in an int[] array
    public static int[] pullCategory( String categoryID ){

        //Could look through and pull from a specific category for each starting letter
        //Method could prove to be more of a hassle than useful (categories are very broad)

        //TODO: pull itemID data from Runescape API and return it in an array
        //User array or arraylist?
        int[] categoryArray = new int[30];

        return categoryArray;
    }

    public static Item pullItem( Double itemID ){
        Item newItemObject = new Item();

        String rawItem = "";

        //TODO: return item data from AsyncTask (array?) and then call Item constructor method

        //Have AsyncTask return Item object instead of String
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

                try {

                    JSONObject reader = new JSONObject(result);
                    JSONObject itemObj = reader.getJSONObject ("item");
                    String _iconURL = itemObj.getString ("icon");
                    Log.i("ItemDataReturn","Icon Url:" + _iconURL);
                    String _iconLargeURL = itemObj.getString ("icon_large");
                    Log.i("ItemDataReturn","Large Icon Url:" + _iconLargeURL);
                    String _itemID = itemObj.getString ("id");
                    Log.i("ItemDataReturn","Item ID:" + _itemID);
                    String _ifMembersOnly = itemObj.getString ("members");
                    Log.i("ItemDataReturn","Members Only:" + _ifMembersOnly);
                    String _name = itemObj.getString ("name");
                    Log.i("ItemDataReturn","Item Name:" + _name);

                    JSONObject currentObj = reader.getJSONObject ("current");
                    String _tradePrice = currentObj.getString ("price");
                    Log.i("ItemDataReturn","Trade Price:" + _tradePrice);

                } catch (final JSONException e) {
                    Log.e("JSONException","JSON Parsing Error:" + e.getMessage());

                }

            }
        }

        //TODO: Create check to validate if the given (perameter) itemID matches the itemID pulled from the ge API

        //Double itemID converted to String urlItemID with formatting to remove the decimal point and decimal values. (decimal point causes url to not return data)
        String urlItemID = String.format("%.0f", itemID);

        DownloadItem task = new DownloadItem();
        task.execute("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + urlItemID);

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
