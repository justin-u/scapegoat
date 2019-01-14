package com.unterr.truex.scapegoat.elements;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.models.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<Item> data;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView textView;
        public CustomViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.crd);
            textView = v.findViewById(R.id.tv_gp2);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapter(ArrayList<Item> _data) {
        data = _data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_textview, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textView.setText(data.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}
