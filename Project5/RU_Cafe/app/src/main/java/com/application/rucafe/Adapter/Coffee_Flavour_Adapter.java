package com.application.rucafe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rucafe.DeleteFromCart;
import com.application.rucafe.R;

import java.util.ArrayList;

/**
 * This class provides the functionality of adapter of Coffee Flavour.
 * Show Flavour of Coffee.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Coffee_Flavour_Adapter extends RecyclerView.Adapter<Coffee_Flavour_Adapter.ViewHolder>{
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
     * Creates data Object of type DeleteFromCart.
     */
    DeleteFromCart data;
    
    /**
     * Creates selectedflavour Object of type String ArrayList.
     */
    public static ArrayList<String> selectedflavour = new ArrayList<>();

    /**
     * Coffee_Flavour_Adapter constructor method.
     * @param context Object of type Context.
     * @param array Object of type String[].
     * @param data Object of type DeleteFromCart.
     */
    public Coffee_Flavour_Adapter(Context context, String[] array, DeleteFromCart data) {
        this.context=context;
        this.array=array;
        this.data=data;
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

        //Item View
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

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    selectedflavour.add(array[position]);
                }else {
                    selectedflavour.remove(array[position]);
                }
                data.onclick();
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
