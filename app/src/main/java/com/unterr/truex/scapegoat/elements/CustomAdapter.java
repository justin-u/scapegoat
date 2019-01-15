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

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<MoneyProcess> data;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public final View view;
        public final TextView name;
        public final TextView inputPrice;
        public final TextView productPrice;
        public final TextView profitPer;
        public final TextView xpPer;
        public final TextView profitPerHr;
        public final ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById (R.id.name);
            inputPrice = view.findViewById (R.id.inputPrice);
            productPrice = view.findViewById (R.id.productPrice);
            profitPer = view.findViewById (R.id.profitPer);
            xpPer = view.findViewById (R.id.xpPer);
            profitPerHr = view.findViewById (R.id.profitPerHr);
            image = view.findViewById (R.id.image);
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

        MoneyProcess process = data.get(position);

        if (process.getProfitPer () < 0){
            holder.profitPer.setTextColor (Color.rgb (124,27,16));
        } else{
            holder.profitPer.setTextColor (Color.rgb(33, 132, 38));
        }

        holder.name.setText (process.getProcessName ());
        holder.inputPrice.setText (process.getInputTradePrice ().toString ());
        holder.productPrice.setText (process.getProductTradePrice ().toString ());
        holder.profitPer.setText (process.getProfitPer ().toString ());
        holder.xpPer.setText (process.getXpPer ().toString ());
        holder.profitPerHr.setText (process.getProfitTotal ().toString ());
        Picasso.get().load(process.getIconUrl()).into(holder.image);

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
