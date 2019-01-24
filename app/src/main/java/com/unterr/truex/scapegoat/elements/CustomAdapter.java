package com.unterr.truex.scapegoat.elements;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.models.MoneyProcess;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<MoneyProcess> data;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public final View view;
        public final TextView name;
        public final TextView reqLvl;
        public final TextView inputPrice;
        public final TextView productPrice;
        public final TextView profitPer;
        public final TextView xpPer;
        public final TextView profitPerHr;
        public final ImageView image;
        public final ImageView imageSkill;

        public final TextView labelProfitPer;
        public final TextView labelProfitPerHr;


        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById (R.id.name);
            reqLvl = view.findViewById (R.id.reqLvl);
            inputPrice = view.findViewById (R.id.inputPrice);
            productPrice = view.findViewById (R.id.productPrice);
            profitPer = view.findViewById (R.id.profitPer);
            xpPer = view.findViewById (R.id.xpPer);
            profitPerHr = view.findViewById (R.id.profitPerHr);
            image = view.findViewById (R.id.image);
            imageSkill = view.findViewById (R.id.imageSkill);

            labelProfitPer = view.findViewById (R.id.labelProfitPer);
            labelProfitPerHr = view.findViewById (R.id.labelProfitPerHr);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapter(ArrayList<MoneyProcess> _data) {
        this.data = _data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_process, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //Temporary solution. Icon needs to be added to drawable

        //String skillIcon = ("herblore");
        //Drawable myDrawable = getResources().getDrawable(R.drawable.icon.herblore);

        MoneyProcess process = data.get(position);

        NumberFormat numberFormat = NumberFormat.getNumberInstance (Locale.US);

        if (process.getProfitPer () < 0){
            holder.profitPer.setTextColor (Color.rgb (124,27,16));
            holder.profitPerHr.setTextColor (Color.rgb (124,27,16));
        } else{
            holder.profitPer.setTextColor (Color.rgb(33, 132, 38));
            holder.profitPerHr.setTextColor (Color.rgb(33, 132, 38));
        }

        if (process.getReqLvlMet () == false){
            holder.reqLvl.setTextColor (Color.rgb (124,27,16));
            holder.name.setTextColor (Color.rgb (124,27,16));
        } else{
            holder.reqLvl.setTextColor (Color.rgb(33, 132, 38));
            holder.name.setTextColor (Color.rgb(33, 132, 38));
        }

        if (process.getCategoryID () == 16 || process.getCategoryID () == 17 || process.getCategoryID () == 18){
            holder.labelProfitPerHr.setText ("Buy Limit Profit(2000):");
        }if (process.getCategoryID () == 10){
            holder.labelProfitPer.setText ("Profit Per Patch:");
            holder.labelProfitPerHr.setText ("Total Run Profit (7):");
        }

        holder.name.setText (process.getProcessName ());
        holder.reqLvl.setText (String.format("%.0f", process.getReqLvl ()));
        holder.inputPrice.setText (numberFormat.format(process.getInputTradePrice ()) + "gp");
        holder.productPrice.setText (numberFormat.format(process.getProductTradePrice ()) + "gp");
        holder.profitPer.setText (numberFormat.format(process.getProfitPer ()) + "gp");
        holder.xpPer.setText (process.getXpPer ().toString ());
        holder.profitPerHr.setText (numberFormat.format(process.getProfitTotal ()) + "gp");
        Picasso.get().load(process.getIconUrl()).into(holder.image);

        //Temporary solution. Icon needs to be added to drawable and called
        if (process.categoryID == 1 || process.categoryID == 2 || process.categoryID == 16 || process.categoryID == 17 || process.categoryID == 18){
            holder.imageSkill.setImageResource(R.drawable.herblore);
        }if (process.categoryID == 3 || process.categoryID == 10){
            holder.imageSkill.setImageResource(R.drawable.farming);
        }if (process.categoryID == 4 || process.categoryID == 5 || process.categoryID == 6){
            holder.imageSkill.setImageResource(R.drawable.fletching);
        }if (process.categoryID == 7){
            holder.imageSkill.setImageResource(R.drawable.smithing);
        }if (process.categoryID == 11){
            holder.imageSkill.setImageResource (R.drawable.cooking);
        }if (process.categoryID == 14){
            holder.imageSkill.setImageResource (R.drawable.firemaking);
        }if (process.categoryID == 15){
            holder.imageSkill.setImageResource (R.drawable.crafting);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        } else {
            return 0;
        }
    }
}
