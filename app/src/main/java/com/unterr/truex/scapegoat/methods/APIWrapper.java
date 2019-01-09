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
import java.sql.Array;
import java.sql.Struct;

/*
 * APIWrapper:
 * methods for interacting with the Runescape web service
 */

public class APIWrapper {

    public static Boolean verifyUsername( String username ){

        //TODO: check if string is a valid Runescape username; if so return true

        return false;
    }

    public static Player pullPlayer( String username ){
        Player newPlayerObject = new Player();

        //TODO: pull character data from character api
        //Character API: (https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=)

        //TODO: split pulled data by commas and store data in array/hashmap


        //TODO: pull the herblore lvl from the API and return it

        return newPlayerObject;
    }

    //Method to pull all itemIDs from a given category from the OSRS API and return the itemIDs in an int[] array
    public static int[] pullCategory( String categoryID ){

        //TODO: pull itemID data from Runescape API and return it in an array
        int[] categoryArray = new int[30];

        return categoryArray;
    }

    public static Item pullItem( Double itemID ){
        Item newItemObject = new Item();

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

    }
}
