package com.unterr.truex.scapegoat.elements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.unterr.truex.scapegoat.R;
import com.unterr.truex.scapegoat.models.Item;
import com.unterr.truex.scapegoat.models.MoneyProcess;

import org.w3c.dom.Text;

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
        public final TextView description;
        public final ImageView image;

        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById (R.id.name);
            description = view.findViewById (R.id.description);
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

        holder.name.setText (process.getProcessName ());
        holder.description.setText (process.getProfitTotal ().toString ());
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
