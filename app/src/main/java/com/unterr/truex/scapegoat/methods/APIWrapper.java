package com.unterr.truex.scapegoat.methods;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.unterr.truex.scapegoat.activities.MainActivity;
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

    //Method to pull all itemIDs from a given category from the OSRS API and return the itemIDs in an int[] array
    public static int[] pullCategory( String categoryID ){

        //Could look through and pull from a specific category for each starting letter
        //Method could prove to be more of a hassle than useful (categories are very broad)

        //TODO: pull itemID data from Runescape API and return it in an array
        //User array or arraylist?
        int[] categoryArray = new int[30];

        return categoryArray;
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
