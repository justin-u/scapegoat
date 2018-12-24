package com.unterr.truex.scapegoat.methods;
import android.graphics.drawable.Drawable;

import  com.unterr.truex.scapegoat.models.Item;
import  com.unterr.truex.scapegoat.models.Player;

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
