package com.unterr.truex.scapegoat.models;

/*
 * Player:
 * Stores all relevant player information of the current user
 */

import com.unterr.truex.scapegoat.methods.APIWrapper;

public class Player {

    private String username;
    private Double attackLvl;
    private Double defenceLvl;
    private Double strengthLvl;
    private Double hitpointsLvl;
    private Double rangedLvl;
    private Double prayerLvl;
    private Double magicLvl;
    private Double cookingLvl;
    private Double woodcuttingLvl;
    private Double fletchingLvl;
    private Double fishingLvl;
    private Double firemakingLvl;
    private Double craftingLvl;
    private Double smithingLvl;
    private Double miningLvl;
    private Double herbLvl;
    private Double agilityLvl;
    private Double thievingLvl;
    private Double slayerLvl;
    private Double farmingLvl;
    private Double runecraftingLvl;
    private Double hunterLvl;
    private Double constructionLvl;

    public Player(){}

    //Constructor method to call the pulPlayer method (APIWrapper) that pulls API data and uses the second constructor to create a new Item object.
    public Player( String _username ){
        Player _player = APIWrapper.pullPlayer( _username );
        if( _player != null ){ this.update(_player); }
    }

    //Second constructor method with every parameter to be called by the pullPlayer method (APIWrapper) in order to create a player object with the pulled API data
    public Player(String _username, Double _attackLvl, Double _defenceLvl, Double _strengthLvl, Double _hitpointsLvl, Double _rangedLvl,
                  Double _prayerLvl, Double _magicLvl, Double _cookingLvl, Double _woodcuttingLvl, Double _fletchingLvl, Double _fishingLvl,
                  Double _firemakingLvl, Double _craftingLvl, Double _smithingLvl, Double _miningLvl, Double _herbLvl, Double _agilityLvl,
                  Double _thievingLvl, Double _slayerLvl, Double _farmingLvl, Double _runecraftingLvl, Double _hunterLvl, Double _constructionLvl){

        this.username = _username;
        this.attackLvl = _attackLvl;
        this.defenceLvl = _defenceLvl;
        this.strengthLvl = _strengthLvl;
        this.hitpointsLvl = _hitpointsLvl;
        this.rangedLvl = _rangedLvl;
        this.prayerLvl = _prayerLvl;
        this.magicLvl = _magicLvl;
        this.cookingLvl = _cookingLvl;
        this.woodcuttingLvl = _woodcuttingLvl;
        this.fletchingLvl = _fletchingLvl;
        this.fishingLvl = _fishingLvl;
        this.firemakingLvl = _firemakingLvl;
        this.craftingLvl = _craftingLvl;
        this.smithingLvl = _smithingLvl;
        this.miningLvl = _miningLvl;
        this.herbLvl = _herbLvl;
        this.agilityLvl = _agilityLvl;
        this.thievingLvl = _thievingLvl;
        this.slayerLvl = _slayerLvl;
        this.farmingLvl = _farmingLvl;
        this.runecraftingLvl = _runecraftingLvl;
        this.hunterLvl = _hunterLvl;
        this.constructionLvl = _constructionLvl;

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
