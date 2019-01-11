package com.unterr.truex.scapegoat.models;

public class MoneyProcess {

    //**Terminology Reference**
    // per = result from one action (ex: cleaning one herb)
    // input = item that needs to be purchased to perform the MoneyProcess (ex: one unclean herb)
    // product = item that is the result of the MoneyProcess to be sold (ex: one clean herb)
    // total = result from completing the MoneyProcess over time or completing a recurring MoneyProcess (Recurring/PerHR depending on categoryID)

    //**Variables that are passed as parameters in the constructor method**
    //itemID of the item that needs to be used (called in the constructor method)
    public Double inputID;

    //itemID of the item that will be the final product to be sold (called in the constructor method)
    public Double productID;

    //categoryID determines how certain methods will calculate results and is also used to pull specific MoneyProcess Objects to populate the RecyclerView (called in constructor method)
    public int categoryID;

    //reqLvl is the skill requirement of the given MoneyProcess and is used to set reqLvlMet based on given Player Object
    public Double reqLvl;

    //xpPer determines the XP gain of the MoneyProcess on one action and is used to calculate xpTotal
    public Double xpPer;


    //**Variables that are calculated and then returned**
    //name is pulled from the Item Object (getName()) with the corresponding itemID as productID
    //name pulled by RecyclerView
    public String name;

    //input trade price is pulled from the Item Object (getTradePrice()) with the corresponding itemID as inputID
    //inputTradePrice pulled by RecyclerView
    public Double inputTradePrice;

    //input trade price is pulled from the Item Object (getTradePrice()) with the corresponding itemID as productID
    //productTradePrice pulled by RecyclerView
    public Double productTradePrice;

    //profitPer is the profit of the MoneyProcess on one action (productTradePrice - inputTradePrice)
    //profitPer pulled by RecyclerView
    public Double profitPer;

    //outputTotal is the result of completing the MoneyProcess over time (per hour) or completing a recurring MoneyProcess (ex: farming herbs)
    //outputTotal is calculated depending on the categoryID (currently just using constants for outputTotal but will allow for calculations in the future depending on )
    //outputTotal is used to calculate xpTotal and profitTotal
    public Double outputTotal;

    //profitTotal is the total amount of profit earned from completing the MoneyProcess over time (per hour) or completing a recurring MoneyProcess
    //profitTotal = (outputTotal * profitPer)
    //profitTotal pulled by RecyclerView
    public Double profitTotal;

    //xpTotal is the total xp gained from completing the MoneyProcess over time (per hour) or completing a recurring MoneyProcess
    //xpTotal = (outputTotal * xpPer)
    //xpTotal pulled by RecyclerView
    public Double xpTotal;

    //ifMemberOnly checks if the MoneyProcess is available only to members
    //ifMemberOnly is returned by checking inputID getIfMemberOnly() and productID getIfMemberOnly()
    //ifMemberOnly can be used to filter what MoneyProcess Objects are displayed in the RecyclerView
    public Boolean ifMemberOnly;

    //reqLvlMet checks if the corresponding Player skillLvl (specific skill determined by categoryID) is greater than or equal (>=) to the reqLvl variable
    //reqLvlMet calls Player.get(skill)Lvl to perform the check
    //reqLvlMet can be used to filter what MoneyProcess Objects are displayed (or how they're displayed) in the RecyclerView
    public Boolean reqLvlMet = false;


    public MoneyProcess(){}

    //TODO: create constructor methods that calls update() method based on parameters

    //TODO: create method to check if inputID and productID have corresponding Item Objects (creates new Item Objects if they do not exist)


    //TODO: create update() method that calls all other calculation methods

    //TODO: create helper method to populate the RecyclerView


}
