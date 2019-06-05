package com.unterr.truex.scapegoat.models;

import java.util.Locale;

public class MoneyProcess {

    //**Terminology Reference**
    // per = result from one action (ex: cleaning one herb)
    // input = item that needs to be purchased to perform the MoneyProcess (ex: one unclean herb)
    // product = item that is the result of the MoneyProcess to be sold (ex: one clean herb)
    // total = result from completing the MoneyProcess over time or completing a recurring MoneyProcess (Recurring/PerHR depending on categoryID)

    //itemID of the item that needs to be used (called in the constructor method)
    public Double inputID;

    //itemID of the item that will be the final product to be sold (called in the constructor method)
    public Double productID;


    //**Variables that are passed as parameters in the constructor method**
    //categoryID determines how certain methods will calculate results and is also used to pull specific MoneyProcess Objects to populate the RecyclerView (called in constructor method)
    public int categoryID;

    //**Category IDs**
    // 1 - (Herblore) Cleaning Herbs
    // 2 - (Herblore) Making Unfinished Potions
    // 3 - (Farming) Growing Saplings
    // 4 - (Fletching)Cutting Bolt Tips
    // **5 - (Fletching) Cutting Bows
    // **6 - (Fletching) Stringing Bows
    // 7 - (Smithing) Dart Tips

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

    public String iconUrl;



    public MoneyProcess(){}

    public MoneyProcess(Item _inputItem, Item _productItem, int _categoryID, Double _reqLvl, Double _xpPer){
        this.inputID = getItemID (_inputItem);
        this.productID = getItemID (_productItem);
        this.categoryID = _categoryID;
        this.reqLvl = _reqLvl;
        this.xpPer = _xpPer;
        this.name = getItemName (_inputItem, _productItem, _categoryID);
        this.inputTradePrice = getItemTradePrice (_inputItem);
        this.productTradePrice = getItemTradePrice (_productItem);
        this.profitPer = getProfitPer (_inputItem, _productItem, _categoryID);
        this.outputTotal = getOutputTotal (_categoryID);
        this.profitTotal = getProfitTotal (_categoryID, _inputItem, _productItem);
        this.xpTotal = getXpTotal (_categoryID, _xpPer);
        this.ifMemberOnly = getIfMemberOnly (_inputItem, _productItem);
        //Alter ReqLvlMet
        this.reqLvlMet = false;
        this.iconUrl = getIconUrl (_inputItem, _productItem, _categoryID);
    }

    public MoneyProcess(Item _inputItem, Item _productItem, int _categoryID, Double _reqLvl, Double _xpPer, Player _player){
        this.inputID = getItemID (_inputItem);
        this.productID = getItemID (_productItem);
        this.categoryID = _categoryID;
        this.reqLvl = _reqLvl;
        this.xpPer = _xpPer;
        this.name = getItemName (_inputItem, _productItem, _categoryID);
        this.inputTradePrice = getItemTradePrice (_inputItem);
        this.productTradePrice = getItemTradePrice (_productItem);
        this.profitPer = getProfitPer (_inputItem, _productItem, _categoryID);
        this.outputTotal = getOutputTotal (_categoryID);
        this.profitTotal = getProfitTotal (_categoryID, _inputItem, _productItem);
        this.xpTotal = getXpTotal (_categoryID, _xpPer);
        this.ifMemberOnly = getIfMemberOnly (_inputItem, _productItem);
        //Alter ReqLvlMet
        this.reqLvlMet = setReqLvlMet (_player);
        this.iconUrl = getIconUrl (_inputItem, _productItem, _categoryID);
    }

    //Constructor used when player skill lvl is factored into profit (ex: Barrows Repair)
    //TODO: Review constructor to see if it is the most efficient
    public MoneyProcess(Item _inputItem, Item _productItem, int _categoryID, Double _reqLvl, Player _player){
        this.inputID = getItemID (_inputItem);
        this.productID = getItemID (_productItem);
        this.categoryID = _categoryID;
        this.reqLvl = _reqLvl;
        this.xpPer = 0.0;
        this.name = getItemName (_inputItem, _productItem, _categoryID);
        this.inputTradePrice = getItemTradePrice (_inputItem);
        this.productTradePrice = getItemTradePrice (_productItem);
        this.profitPer = getProfitPer (_inputItem, _productItem, _categoryID, _player);
        this.outputTotal = getOutputTotal (_categoryID);
        this.profitTotal = this.profitPer;
        this.xpTotal = 0.0;
        this.ifMemberOnly = getIfMemberOnly (_inputItem, _productItem);
        //Alter ReqLvlMet
        this.reqLvlMet = setReqLvlMet (_player);
        this.iconUrl = getIconUrl (_inputItem, _productItem, _categoryID);
    }

    //Check if this constructor is necessary
    public MoneyProcess(Item _inputItem, Item _inputItem2, Item _productItem, int _categoryID, Double _reqLvl, Double _xpPer, Double _outputTotal, Player _player){
        this.inputID = getItemID (_inputItem);
        this.productID = getItemID (_productItem);
        this.categoryID = _categoryID;
        this.reqLvl = _reqLvl;
        this.xpPer = _xpPer;
        this.name = getItemName (_inputItem, _productItem, _categoryID);
        this.inputTradePrice = getItemTradePrice (_inputItem);
        this.productTradePrice = getItemTradePrice (_productItem);
        this.profitPer = getProfitPer (_inputItem, _inputItem2, _productItem, _categoryID);
        this.outputTotal = _outputTotal;
        this.profitTotal = getProfitTotal (_categoryID, _inputItem, _productItem);
        this.xpTotal = getXpTotal (_categoryID, _xpPer);
        this.ifMemberOnly = getIfMemberOnly (_inputItem, _productItem);
        //Alter ReqLvlMet
        this.reqLvlMet = setReqLvlMet (_player);
        this.iconUrl = getIconUrl (_inputItem, _productItem, _categoryID);
    }

    public MoneyProcess(Item _inputItem, Item _inputItem2, Item _productItem, int _categoryID, Double _reqLvl, Double _xpPer, Player _player){
        this.inputID = getItemID (_inputItem);
        this.productID = getItemID (_productItem);
        this.categoryID = _categoryID;
        this.reqLvl = _reqLvl;
        this.xpPer = _xpPer;
        this.name = getItemName (_inputItem, _productItem, _categoryID);
        this.inputTradePrice = getItemTradePrice (_inputItem);
        this.productTradePrice = getItemTradePrice (_productItem);
        this.profitPer = getProfitPer (_inputItem, _inputItem2, _productItem, _categoryID);
        this.outputTotal = getOutputTotal (_categoryID);
        this.profitTotal = getProfitTotal (_categoryID, _inputItem, _inputItem2, _productItem);
        this.xpTotal = getXpTotal (_categoryID, _xpPer);
        this.ifMemberOnly = getIfMemberOnly (_inputItem, _productItem);
        //Alter ReqLvlMet
        this.reqLvlMet = setReqLvlMet (_player);
        this.iconUrl = getIconUrl (_inputItem, _productItem, _categoryID);
    }
    //TODO: create other constructor methods with no _inputItem (mining, woodcutting)

    //TODO: create constructor methods that calls update() method based on parameters

    //TODO: create getName(productID) method that returns the name of the corresponding Item object (used to set this.name = getName(productID))

    public double getItemID (Item _item){
        return _item.getItemID ();
    }

    public String getItemName (Item _inputItem, Item _productItem, int _categoryID){
        if(_categoryID == 16 || _categoryID == 17 || _categoryID == 18){
            return _inputItem.getName();
        }else{
            return _productItem.getName ();
        }
    }

    public Double getItemTradePrice (Item _item){
        return _item.getTradePrice ();
    }

    public Double getProfitPer (Item _inputItem, Item _productItem, int _categoryID){
        if (_categoryID == 2){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 5.0));
        }if (_categoryID == 4){
            return (((_productItem.getTradePrice ())* 12.0) - (_inputItem.getTradePrice ()));
        }if (_categoryID == 7){
            return ((_productItem.getTradePrice () * 10.0) - (_inputItem.getTradePrice ()));
        }if (_categoryID == 15 || _productItem.getItemID () == 1741.0){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 1.0));
        }if (_categoryID == 15 || _productItem.getItemID () == 1743.0){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 3.0));
        }if (_categoryID == 15){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 20.0));
        }if (_categoryID == 16){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice ()* 4.0));
        }if (_categoryID == 17){
            return ((_productItem.getTradePrice ()) - Math.round((_inputItem.getTradePrice ()/2.0)* 4.0));
        }if (_categoryID == 18){
            return ((_productItem.getTradePrice ()) - Math.round((_inputItem.getTradePrice ()/3.0)* 4.0));
        }if (_categoryID == 23){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 12.4));
        }else{
            return (_productItem.getTradePrice ()) - (_inputItem.getTradePrice ());
        }

    }

    //TODO: Review this method to see if it is the most efficient
    public Double getProfitPer (Item _inputItem, Item _productItem, int _categoryID, Player _player){
        if (_categoryID == 19 || _categoryID == 20 || _categoryID == 21){
            return ((_productItem.getTradePrice ()) - Math.round((_inputItem.getTradePrice ()) + getBarrowsCalc(_player, _categoryID)));
        }else{
            return (_productItem.getTradePrice ()) - (_inputItem.getTradePrice ());
        }

    }

    public Double getProfitPer (Item _inputItem, Item _inputItem2, Item _productItem, int _categoryID){
        if (_categoryID == 6){
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + _inputItem2.getTradePrice ()));
        }if (_categoryID == 10){
            return (Math.round(_productItem.getTradePrice ()*8.8) - (_inputItem.getTradePrice () + _inputItem2.getTradePrice ()));
        }if (_categoryID == 14){
            if (_inputItem.getItemID () == 1511.0){
                return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 100.0 + Math.round(_inputItem2.getTradePrice ()/34.0)));
            }else if (_inputItem.getItemID () == 1521.0){
                return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 250.0 + Math.round (_inputItem2.getTradePrice ()/34.0)));
            }else if (_inputItem.getItemID () == 6333.0){
                return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 500.0 + Math.round(_inputItem2.getTradePrice ()/34.0)));
            }else if (_inputItem.getItemID () == 6332.0){
                return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 1500.0 + Math.round(_inputItem2.getTradePrice ()/34.0)));
            }
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + 100.0 + Math.round(_inputItem2.getTradePrice ()/34.0)));
        }if (_categoryID == 22){
            //Calculations take into account the ammount of coal ore needed for each bar as well as the amount of GP spent per bar based on the 72k cost per hour
            return ((_productItem.getTradePrice ()) - (_inputItem.getTradePrice () + _inputItem2.getTradePrice () + 13.3));
        }if (_categoryID == 24){
            return ((_productItem.getTradePrice ()) - ((_inputItem.getTradePrice () * 2.0) + _inputItem2.getTradePrice () + 20.0));
        }if (_categoryID == 25){
            return ((_productItem.getTradePrice ()) - ((_inputItem.getTradePrice () * 3.0) + _inputItem2.getTradePrice () + 26.6));
        }if (_categoryID == 26){
            return ((_productItem.getTradePrice ()) - ((_inputItem.getTradePrice () * 4.0) + _inputItem2.getTradePrice () + 33.5));
        }else{
            return (_productItem.getTradePrice ()) - (_inputItem.getTradePrice ());
        }

    }

    public Double getOutputTotal (int _categoryID){
        if(_categoryID == 1){
            //OutputTotal (PerHr) for cleaning herbs
            return 5000.0;
        } if(_categoryID == 2){
            //OutputTotal (PerHr) for making unfinished potions
            //OutputTotal (Buy Limit) for decanting potions
            return 2000.0;
        }if(_categoryID == 3){
            //OutputTotal (PerHr) for growing saplings
            return 1700.0;
        }if(_categoryID == 4){
            //OutputTotal (PerHr) for making bolt tips
            return 1400.0;
        }if(_categoryID == 5){
            //OutputTotal (PerHr) for fletching bows
            return 1500.0;
        }if (_categoryID == 6){
            //OutputTotal (PerHr) for stringing bows
            return 1700.0;
        }if(_categoryID == 7){
            //OutputTotal (PerHr) for smithing dart tips
            return 950.0;
        }if (_categoryID == 8 && getXpPer () == 12.5){
            return 4000.0;
        }if (_categoryID == 8 && getXpPer () == 17.5){
            return 4600.0;
        }if (_categoryID == 10){
            return 7.0;
        }if (_categoryID == 11){
            return 1100.0;
        }if (_categoryID == 14){
            return 2028.0;
        }if (_categoryID == 15){
            return 2800.0;
        }if (_categoryID == 16){
            return 500.0;
        }if (_categoryID == 17){
            return 1000.0;
        }if (_categoryID == 18){
            return 1500.0;
        }if (_categoryID == 22){
            return 5400.0;
        }if (_categoryID == 23){
            return 5800.0;
        }if (_categoryID == 24){
            return 3600.0;
        }if (_categoryID == 25){
            return 2700.0;
        }if (_categoryID == 26){
            return 2150.0;
        }else{
            return 1.0;
        }
    }

    public Double getProfitTotal (int _categoryID, Item _inputItem, Item _productItem){
        return ((getOutputTotal (_categoryID)) * (getProfitPer (_inputItem, _productItem, _categoryID)));
    }

    public Double getProfitTotal (int _categoryID, Item _inputItem, Item _inputItem2, Item _productItem){
        return ((getOutputTotal (_categoryID)) * (getProfitPer (_inputItem, _inputItem2, _productItem, _categoryID)));
    }

    public Double getXpTotal(int _categoryID, Double _xpPer){
        return (getOutputTotal (_categoryID)) * (_xpPer);
    }

    public Boolean getIfMemberOnly(Item _inputItem, Item _productItem){
        if(_inputItem.getIfMemberOnly () || _productItem.getIfMemberOnly ()){
            return true;
        } else{
            return false;
        }
    }

    //TODO: Alter method to check if player level is high enough
    public Boolean setReqLvlMet(Player _player){
        Double skillLvl = 0.0;
        if (this.categoryID == 1 || this.categoryID == 2 || this.categoryID == 16 || this.categoryID == 17 || this.categoryID == 18){
            skillLvl = _player.getHerbLvl();
        }if (this.categoryID == 3 || this.categoryID == 10){
            skillLvl = _player.getFarmingLvl();
        }if (this.categoryID == 4 || this.categoryID == 5 || this.categoryID == 6){
            skillLvl = _player.getFletchingLvl();
        }if (this.categoryID == 7 || this.categoryID == 8 || this.categoryID == 22 || this.categoryID == 23 || this.categoryID == 24 || this.categoryID == 25 || this.categoryID == 26){
            skillLvl = _player.getSmithingLvl();
        }if (this.categoryID == 9){
            skillLvl = _player.getFishingLvl ();
        }if (this.categoryID == 11){
            skillLvl = _player.getCookingLvl ();
        }if (this.categoryID == 14){
            skillLvl = _player.getFiremakingLvl ();
        }if (this.categoryID == 15){
            skillLvl = _player.getCraftingLvl ();
        }if (this.categoryID == 19 || this.categoryID == 20 || this.categoryID == 21){
            skillLvl = _player.getConstructionLvl();
        }

        if (skillLvl >= this.reqLvl){
            return true;
        } else{
            return false;
        }
    }


    public String getIconUrl(Item _inputItem, Item _productItem, int _categoryID){
        if(_categoryID == 3 || _categoryID == 16 || _categoryID == 17 || _categoryID == 18){
            return _inputItem.getIconURL ();
        }else{
            return _productItem.getIconURL ();
        }
    }

    public Double getBarrowsCalc(Player _player, int _categoryID){
        Double smithingLvl = 1.0;
        smithingLvl = _player.getSmithingLvl();
        Double barrowsCalc = 0.9;

        if (_categoryID == 19){
            //CategoryID 19 is Barrows repair for Helm
            barrowsCalc = ((1.0 - (smithingLvl/200.0)) * 60000.0);
        } else if (_categoryID == 20){
            //CategoryID 20 is Barrows repair for Body
            barrowsCalc = ((1.0 - (smithingLvl/200.0)) * 90000.0);
        } else if (_categoryID == 21){
            //CategoryID 21 is Barrows repair for Legs
            barrowsCalc = ((1.0 - (smithingLvl/200.0)) * 80000.0);
        }

        return barrowsCalc;
    }



    //Getter Methods:
    public Double getInputID() {return inputID;}

    public Double getProductID() {return productID;}

    public int getCategoryID() {return categoryID;}

    public Double getReqLvl() {return reqLvl;}

    public Double getXpPer() {return xpPer;}

    public String getProcessName() {return name;}

    public Double getInputTradePrice() {return inputTradePrice;}

    public Double getProductTradePrice() {return productTradePrice;}

    public Double getProfitPer() {return profitPer;}

    public Double getOutputTotal() {return outputTotal;}

    public Double getProfitTotal() {return profitTotal;}

    public Double getXpTotal() {return xpTotal;}

    public Boolean getIfMemberOnly() {return ifMemberOnly;}

    public Boolean getReqLvlMet() {return reqLvlMet;}

    public String getIconUrl() {return iconUrl;}


    //Setter Methods:
    public void setInputID(Double inputID) {
        this.inputID = inputID;
    }

    public void setProductID(Double productID) {
        this.productID = productID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setReqLvl(Double reqLvl) {
        this.reqLvl = reqLvl;
    }

    public void setXpPer(Double xpPer) {
        this.xpPer = xpPer;
    }

    public void setProcessName(String name) {
        this.name = name;
    }

    public void setInputTradePrice(Double inputTradePrice) {
        this.inputTradePrice = inputTradePrice;
    }

    public void setProductTradePrice(Double productTradePrice) {
        this.productTradePrice = productTradePrice;
    }

    public void setProfitPer(Double profitPer) {
        this.profitPer = profitPer;
    }

    public void setOutputTotal(Double outputTotal) {
        this.outputTotal = outputTotal;
    }

    public void setProfitTotal(Double profitTotal) {
        this.profitTotal = profitTotal;
    }

    public void setXpTotal(Double xpTotal) {
        this.xpTotal = xpTotal;
    }

    public void setIfMemberOnly(Boolean ifMemberOnly) {
        this.ifMemberOnly = ifMemberOnly;
    }

    public void setReqLvlMet(Boolean reqLvlMet) {
        this.reqLvlMet = reqLvlMet;
    }

    public void setIconUrl(String iconUrl){
        this.iconUrl = iconUrl;
    }


    public String toString() {
        if(this.isNull())
            return "null";

        return "Input ID = " + this.inputID +
                "\n| Product ID = " + this.productID +
                "\n| Required Level = " + this.reqLvl +
                "\n| XP Per = " + this.xpPer +
                "\n| Name = " + this.name +
                "\n| Input Trade Price = " + this.inputTradePrice +
                "\n| Product Trade Price = " + this.productTradePrice +
                "\n| Profit Per = " + this.profitPer +
                "\n| Output Total = " + this.outputTotal +
                "\n| Profit Total = " + this.profitTotal +
                "\n| XP Total = " + this.xpTotal +
                "\n| If Member Only = " + this.ifMemberOnly +
                "\n| Required Level Met = " + this.reqLvlMet;
    }

    private boolean isNull(){
        return      inputID == null ||
                    productID == null ||
                    reqLvl == null ||
                    xpPer == null ||
                    name == null ||
                    inputTradePrice == null ||
                    productTradePrice == null ||
                    profitPer == null ||
                    outputTotal == null ||
                    profitTotal == null ||
                    xpTotal == null ||
                    ifMemberOnly == null ||
                    reqLvlMet == null ||
                    iconUrl == null;
    }

    //TODO: create getInputTradePrice(inputID) method that returns the tradePrice of the corresponding Item object (used to set this.inputTradePrice = getInputTradePrice(inputID))

    //TODO: create getProductTradePrice(productID) method that returns the tradePrice of the corresponding Item object (used to set this.inputTradePrice = getInputTradePrice(productID))

    //TODO: create getProfitPer(inputID, productID) method that returns the profitPer (productTradePrice - inputTradePrice) X(Could be calculated in update/constructor)

    //TODO: create getOutputTotal(categoryID) method that returns the outputTotal (currently only using constants)

    //TODO: create getProfitTotal(outputTotal, profitPer) method that returns profitTotal (outputTotal * profitPer) X(Could be calculated in update/constructor)

    //TODO: create getXpTotal(outputTotal, xpPer) method that returns xpTotal (outputTotal * xpPer) X(Could be calculated in update/constructor)

    //TODO: create getIfMemberOnly(categoryID, inputID, productID) method that returns T/F by calling ifMemberOnly of corresponding inputID, productID Item Objects

    //TODO: create getReqLvlMet(reqLvl) (categoryID, inputID, productID) method that determines the skill type (from categoryID) and returns T/F by calling get(Skill)Lvl() of corresponding inputID, productID Item Objects

    //TODO: create update() method that calls all other calculation methods

    //TODO: create helper method to populate the RecyclerView


}
