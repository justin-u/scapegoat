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

    public Item(){}


    //Public constructor method might be needed for the API wrapper to easily return a new Item object. Set methods could also be made public

    public Item( Double _itemID ){
        Item _item = APIWrapper.pullItem( _itemID );
        if( _item != null ){ this.update(_item); }
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

        return this.name + " | " + this.itemID + " | " + this.tradePrice;
    }

    public Double getTradePrice(){
        return this.tradePrice;
    }

    public boolean update(){

        return this.update( APIWrapper.pullItem( this.itemID ) );
    }

    private boolean update( Item _item ){

        if(  _item             == null        ){ return false; }
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
