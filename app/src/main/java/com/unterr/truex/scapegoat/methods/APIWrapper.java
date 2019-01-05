package com.unterr.truex.scapegoat.methods;
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


        //TODO: pull the herblore lvl from the API and return it

        return newPlayerObject;
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

                    Log.e("JSONException","JSON Downkiad Error:" + e.getMessage());
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

        DownloadItem task = new DownloadItem();
        task.execute("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + itemID.toString());

        //TODO: pull item info from the API and return

        return newItemObject;
    }

    public static Drawable pullIcon( String URL ){

        //TODO: load drawable from icon URL

        return null;
    }

    public static Drawable pullIconLarge( String URL ){

        //TODO: load drawable from iconLarge URL

        return null;
    }
}
