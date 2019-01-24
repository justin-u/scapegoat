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

    private void setCookingLvl( Double _cookingLvl ){
        this.cookingLvl = _cookingLvl;
    }

    private void setCraftingLvl( Double _craftingLvl){
        this.craftingLvl = _craftingLvl;
    }

    private void setFishingLvl( Double _fishingLvl ){
        this.fishingLvl = _fishingLvl;
    }

    private void setFiremakingLvl( Double _firemakingLvl){
        this.firemakingLvl = _firemakingLvl;
    }

    private void setHerbLvl( Double _herbLvl ){
        this.herbLvl = _herbLvl;
    }

    private void setFletchingLvl ( Double _fletchingLvl) {
        this.fletchingLvl = _fletchingLvl;
    }

    private void setFarmingLvl ( Double _farmingLvl) {
        this.farmingLvl = _farmingLvl;
    }

    private void setSmithingLvl ( Double _smithingLvl){
        this.smithingLvl = _smithingLvl;
    }

    //TODO: create void set(Skill)Lvl methods for all skills

    public String getUsername(){
        return this.username;
    }

    public Double getCookingLvl() {
        return this.cookingLvl;
    }

    public Double getCraftingLvl(){
        return this.craftingLvl;
    }

    public Double getFishingLvl(){
        return this.fishingLvl;
    }

    public Double getFiremakingLvl(){
        return this.firemakingLvl;
    }

    public Double getHerbLvl(){
        return this.herbLvl;
    }

    public Double getFletchingLvl() {
        return this.fletchingLvl;
    }

    public Double getFarmingLvl(){
        return this.farmingLvl;
    }

    public Double getSmithingLvl() {
        return this.smithingLvl;
    }

    //TODO: create Double get(Skill)Lvl methods for all skills

    //TODO: Alter toString() to include all Player variables
    public String toString() {
        if(this.isNull()){
            return "null";
        }
        return "Username: "  + this.username +
                "\n | Attack Level: " + this.attackLvl.toString () +
                "\n | Defence Level: " + this.defenceLvl.toString () +
                "\n | Strength Level: " + this.strengthLvl.toString () +
                "\n | Hitpoints Level: " + this.hitpointsLvl.toString () +
                "\n | Ranged Level: " + this.rangedLvl.toString () +
                "\n | Prayer Level: " + this.prayerLvl.toString () +
                "\n | Magic Level: " + this.magicLvl.toString () +
                "\n | Cooking Level: " + this.cookingLvl.toString () +
                "\n | Woodcutting Level: " + this.woodcuttingLvl.toString () +
                "\n | Fletching Level: " + this.fletchingLvl.toString () +
                "\n | Fishing Level: " + this.fishingLvl.toString () +
                "\n | Firemaking Level: " + this.firemakingLvl.toString () +
                "\n | Crafting Level: " + this.craftingLvl.toString () +
                "\n | Smithing Level: " + this.smithingLvl.toString () +
                "\n | Mining Level: " + this.miningLvl.toString () +
                "\n | Herblore Level: " + this.herbLvl.toString () +
                "\n | Agility Level: " + this.agilityLvl.toString () +
                "\n | Thieving Level: " + this.thievingLvl.toString () +
                "\n | Slayer Level: " + this.slayerLvl.toString () +
                "\n | Farming Level: " + this.farmingLvl.toString () +
                "\n | Runecrafting Level: " + this.runecraftingLvl.toString () +
                "\n | Hunter Level: " + this.hunterLvl.toString () +
                "\n | Construction Level: " + this.constructionLvl.toString ();
    }

    private boolean update(){
        if( this.username == null ){ return false; }
        Player _player = APIWrapper.pullPlayer( this.username );
        if( _player != null ){ this.update(_player); }
        return true;
    }

    private boolean update( Player _player ){

        if(  _player              == null           ){ return false; }
        //Might be cause of .toString() returning null
        //if(  _player         .equals(this)          ){ return false; }
        if( !_player.username.equals(this.username) ){ return false; }


        this.username = _player.username;
        this.attackLvl = _player.attackLvl;
        this.defenceLvl = _player.defenceLvl;
        this.strengthLvl = _player.strengthLvl;
        this.hitpointsLvl = _player.hitpointsLvl;
        this.rangedLvl = _player.rangedLvl;
        this.prayerLvl = _player.prayerLvl;
        this.magicLvl = _player.magicLvl;
        this.cookingLvl = _player.cookingLvl;
        this.woodcuttingLvl = _player.woodcuttingLvl;
        this.fletchingLvl = _player.fletchingLvl;
        this.fishingLvl = _player.fishingLvl;
        this.firemakingLvl = _player.firemakingLvl;
        this.craftingLvl = _player.craftingLvl;
        this.smithingLvl = _player.smithingLvl;
        this.miningLvl = _player.miningLvl;
        this.herbLvl = _player.herbLvl;
        this.agilityLvl = _player.agilityLvl;
        this.thievingLvl = _player.thievingLvl;
        this.slayerLvl = _player.slayerLvl;
        this.farmingLvl = _player.farmingLvl;
        this.runecraftingLvl = _player.runecraftingLvl;
        this.hunterLvl = _player.hunterLvl;
        this.constructionLvl = _player.constructionLvl;

        return true;
    }

    private boolean isNull(){
        return  username == null ||
                attackLvl == null ||
                defenceLvl  == null ||
                strengthLvl  == null ||
                hitpointsLvl  == null ||
                rangedLvl  == null ||
                prayerLvl  == null ||
                magicLvl  == null ||
                cookingLvl  == null ||
                woodcuttingLvl  == null ||
                fletchingLvl  == null ||
                fishingLvl  == null ||
                firemakingLvl  == null ||
                craftingLvl  == null ||
                smithingLvl  == null ||
                miningLvl  == null ||
                herbLvl  == null ||
                agilityLvl  == null ||
                thievingLvl  == null ||
                slayerLvl  == null ||
                farmingLvl  == null ||
                runecraftingLvl  == null ||
                hunterLvl  == null ||
                constructionLvl  == null;

    }
}
