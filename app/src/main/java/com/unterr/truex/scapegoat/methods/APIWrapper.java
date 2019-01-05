package com.unterr.truex.scapegoat.methods;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

        class DownloadTask extends AsyncTask<String, Void, String> {

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


                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {

                    String message = "";

                    JSONObject jsonObject = new JSONObject(result);

                    String itemInfo = jsonObject.getString("item");

                    Log.i("Item Data", itemInfo);

                    JSONArray arr = new JSONArray(itemInfo);

                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject jsonPart = arr.getJSONObject(i);

                        String main = "";
                        String description = "";

                        main = jsonPart.getString("current");
                        description = jsonPart.getString("description");

                        if (main != "" && description != "") {

                            message += main + ": " + description + "\r\n";

                        }

                    }

                    if (message != "") {



                    } else {

                    }


                } catch (JSONException e) {


                }



            }
        }

        DownloadTask task = new DownloadTask();
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
