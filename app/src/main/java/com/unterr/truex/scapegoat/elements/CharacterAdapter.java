package com.unterr.truex.scapegoat.elements;

import android.content.Context;
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

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CustomViewHolder> {
    private ArrayList<String> data;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public final View view;
        public final TextView name;
        public final TextView skillLevel;
        public final ImageView imagePlayerSkill;
        public final Context context;



        public CustomViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById (R.id.name);
            skillLevel = view.findViewById (R.id.playerSkillLvl);
            imagePlayerSkill = view.findViewById (R.id.imageCharSkill);
            context = imagePlayerSkill.getContext();
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CharacterAdapter(ArrayList<String> _data) {
        this.data = _data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
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

        String[] nameArray = {"Attack:", "Defence:", "Strength:", "Hitpoints:", "Ranged:", "Prayer:", "Magic:", "Cooking:", "Woodcutting:", "Fletching:", "Fishing:", "Firemaking:",
                "Crafting:", "Smithing:", "Mining:", "Herblore:", "Agility:", "Thieving:", "Slayer:", "Farming:", "Runecrafting:", "Hunter:", "Construction:"};

        //String[] drawableArray = {"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility", "thieving", "slayer", "farming", "runecrafting", "hunter", "construction"};


        String level = data.get(position);
        //level = String.format("%.0f", level);
        String skill = nameArray[position];
        if (level != null && level.length () > 2){
            level = level.substring (0, level.length () - 2);
        }
        holder.skillLevel.setText (level);
        holder.name.setText (skill);

        //Temporary solution. Icon needs to be added to drawable and called
        if (position == 0) {
            holder.imagePlayerSkill.setImageResource(R.drawable.attack);
        }if (position == 1) {
            holder.imagePlayerSkill.setImageResource(R.drawable.defence);
        }if (position == 2) {
            holder.imagePlayerSkill.setImageResource(R.drawable.strength);
        }if (position == 3) {
            holder.imagePlayerSkill.setImageResource(R.drawable.hitpoints);
        }if (position == 4) {
            holder.imagePlayerSkill.setImageResource(R.drawable.ranged);
        }if (position == 5) {
            holder.imagePlayerSkill.setImageResource(R.drawable.prayer);
        }if (position == 6) {
            holder.imagePlayerSkill.setImageResource(R.drawable.magic);
        }if (position == 7) {
            holder.imagePlayerSkill.setImageResource(R.drawable.cooking);
        }if (position == 8) {
            holder.imagePlayerSkill.setImageResource(R.drawable.woodcutting);
        }if (position == 9) {
            holder.imagePlayerSkill.setImageResource(R.drawable.fletching);
        }if (position == 10) {
            holder.imagePlayerSkill.setImageResource(R.drawable.fishing);
        }if (position == 11) {
            holder.imagePlayerSkill.setImageResource(R.drawable.firemaking);
        }if (position == 12) {
            holder.imagePlayerSkill.setImageResource(R.drawable.crafting);
        }if (position == 13) {
            holder.imagePlayerSkill.setImageResource(R.drawable.smithing);
        }if (position == 14) {
            holder.imagePlayerSkill.setImageResource(R.drawable.mining);
        }if (position == 15) {
            holder.imagePlayerSkill.setImageResource(R.drawable.herblore);
        }if (position == 16) {
            holder.imagePlayerSkill.setImageResource(R.drawable.agility);
        }if (position == 17) {
            holder.imagePlayerSkill.setImageResource(R.drawable.thieving);
        }if (position == 18) {
            holder.imagePlayerSkill.setImageResource(R.drawable.slayer);
        }if (position == 19) {
            holder.imagePlayerSkill.setImageResource(R.drawable.farming);
        }if (position == 20) {
            holder.imagePlayerSkill.setImageResource(R.drawable.runecrafting);
        }if (position == 21) {
            holder.imagePlayerSkill.setImageResource(R.drawable.hunter);
        }if (position == 22) {
            holder.imagePlayerSkill.setImageResource(R.drawable.construction);
        }

        //String[] drawableArray = {"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility", "thieving", "slayer", "farming", "runecrafting", "hunter", "construction"};

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

