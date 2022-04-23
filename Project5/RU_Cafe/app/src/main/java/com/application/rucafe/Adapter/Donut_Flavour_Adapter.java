package com.application.rucafe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rucafe.Donuts;
import com.application.rucafe.R;

import java.util.ArrayList;

/**
 * This class provides the functionality of adapter of Donut Flavour.
 * Show Flavour of Donut.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Donut_Flavour_Adapter extends RecyclerView.Adapter<Donut_Flavour_Adapter.ViewHolder>{
    //declaration
	/**
	 * Creates context Object of type Context.
	 */
    Context context;
    
    /**
     * Creates array Object of type String[].
     */
    String[] array;
    
    /**
     * Creates selectedPosition Object of type int.
     */
    public static int selectedPosition;
    
    /**
     * Creates selectedflavour Object of type String.
     */
    public static String selectedflavour = "null";

    /**
     * Donut_Flavour_Adapter constructor method.
     * @param context Object of type Context.
     * @param array Object of type String[].
     */
    public Donut_Flavour_Adapter(Context context, String[] array) {
        this.context=context;
        this.array=array;
        selectedPosition = -1;
    }

    /**
     * Set view of card - item view.
     * @param parent Object of type ViewGroup.
     * @param viewType Object of type int.
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.donutypecard,parent,false);
        return new ViewHolder(view);
    }

    /**
     * Set values.
     * @param holder Object of type ViewHolder.
     * @param position Object of type int.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(array[position]);

        holder.checkbox.setChecked(selectedPosition == position);

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                selectedflavour = array[position];
                notifyDataSetChanged();
            }
        });

    }

    /**
     * getItemCount returns the current number of orders in the cart.
     * @return size of current orders in cart.
     */
    @Override
    public int getItemCount() {
        return array.length;
    }

    /**
     * Declaration and initialization.
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CheckBox checkbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            checkbox = itemView.findViewById(R.id.checkbox);

        }
    }
}
