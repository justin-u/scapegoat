package com.unterr.truex.scapegoat.models;

/*
 * Player:
 * Stores all relevant player information of the current user
 */

import com.unterr.truex.scapegoat.methods.APIWrapper;

public class Player {

    private String username;
    private Double herbLvl;

    public Player(){}

    //Constructor method to call the pulPlayer method (APIWrapper) that pulls API data and uses the second constructor to create a new Item object.
    public Player( String _username ){
        Player _player = APIWrapper.pullPlayer( _username );
        if( _player != null ){ this.update(_player); }
    }

    //Second constructor method with every parameter to be called by the pullPlayer method (APIWrapper) in order to create a player object with the pulled API data
    public Player(String _username, Double _herbLvl){
        this.username = _username;
        this.herbLvl = _herbLvl;
    }

    private void setUsername( String _username ){
        this.username = _username;
    }

    private void setHerbLvl( Double _herbLvl ){
        this.herbLvl = _herbLvl;
    }

    public String getUsername(){
        return this.username;
    }

    public Double getherbLvl(){
        return this.herbLvl;
    }

    private boolean update(){
        if( this.username == null ){ return false; }
        Player _player = APIWrapper.pullPlayer( this.username );
        if( _player != null ){ this.update(_player); }
        return true;
    }

    private boolean update( Player _player ){

        if(  _player              == null           ){ return false; }
        if(  _player         .equals(this)          ){ return false; }
        if( !_player.username.equals(this.username) ){ return false; }

        this.username = _player.username;
        this.herbLvl  = _player.herbLvl;

        return true;
    }
}
