package com.unterr.truex.scapegoat.models;

import com.unterr.truex.scapegoat.methods.APIWrapper;

import android.graphics.drawable.Drawable;

public class Item {

    private String  iconURL;
    private String  iconLargeURL;
    private Double  itemID;
    private Boolean ifMemberOnly;
    private String  name;
    private Double  tradePrice;

    //TODO: Remove iconURL String and all uses of it throughout the project
    public Item(){}

    //Constructor method to call the pullItem method (APIWrapper) that pulls JSON data and uses the second constructor to create a new Item object.
    /*
    public Item( Double _itemID ){
        Item _item = APIWrapper.pullItem( _itemID );
        if( _item != null ){ this.update(_item); }
    }
    */

    //Second constructor method with every parameter to be called by the pullItem method (APIWrapper) in order to create an item object with the pulled JSON data
    public Item(String _iconURL, String _iconLargeURL, Double _itemID, Boolean _ifMemberOnly, String _name, Double _tradePrice){
        this.iconURL = _iconURL;
        this.iconLargeURL = _iconLargeURL;
        this.itemID = _itemID;
        this.ifMemberOnly = _ifMemberOnly;
        this.name = _name;
        this.tradePrice = _tradePrice;
    }

    private void setIconURL( String _iconURL ){
        this.iconURL = _iconURL;
    }

    private void setIconLargeURL( String _iconLargeURL ){
        this.iconLargeURL = _iconLargeURL;
    }

    private void setItemID( Double _itemID ){
        this.itemID = _itemID;
    }

    private void setIfMemberOnly( Boolean _ifMemberOnly ){
        this.ifMemberOnly = _ifMemberOnly;
    }

    private void setName( String _name ){
        this.name = _name;
    }

    private void setTradePrice( Double _tradePrice ){
        this.tradePrice = _tradePrice;
    }

    public Drawable getIcon(){
        return APIWrapper.pullIcon(this.iconURL);
    }

    public Drawable getIconLarge(){
        return APIWrapper.pullIconLarge(this.iconLargeURL);
    }

    public String getIconURL(){return this.iconLargeURL;}

    public double getItemID(){
        return this.itemID;
    }

    public boolean getIfMemberOnly(){
        return this.ifMemberOnly;
    }

    public String getName(){
        return this.name;
    }

    public String toString() {
        if(this.isNull())
            return "null";

        return "Name = " + this.name + " | Item ID:" + this.itemID + " | TradePrice:" + this.tradePrice;
    }

    public Double getTradePrice(){
        return this.tradePrice;
    }

    /*
    public boolean update(){

        return this.update( APIWrapper.pullItem( this.itemID ) );
    }
    */

    private boolean update( Item _item ){

        if(  _item             == null        ){ return false; }
        //Might be cause of .toString() returning null
        if(  _item       .equals(this)        ){ return false; }
        if( !_item.itemID.equals(this.itemID) ){ return false; }

        this.iconURL        = _item.iconURL;
        this.iconLargeURL   = _item.iconLargeURL;
        this.itemID         = _item.itemID;
        this.ifMemberOnly   = _item.ifMemberOnly;
        this.name           = _item.name;
        this.tradePrice     = _item.tradePrice;

        return true;
    }

    private boolean isNull(){
        return      iconURL == null ||
                    iconLargeURL == null ||
                    itemID == null ||
                    ifMemberOnly == null ||
                    name == null ||
                    tradePrice == null;
    }
}
