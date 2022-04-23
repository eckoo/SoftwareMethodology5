package com.application.rucafe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rucafe.DeleteFromCart;
import com.application.rucafe.MainActivity;
import com.application.rucafe.R;

/**
 * This class provides the functionality of adapter of cart.
 * Show orders of Coffee or Donut.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder>{
    //declaration
	/**
	 * Creates context Object of type Context.
	 */
    Context context;
    
    /**
     * Creates inter Object of type DeleteFromCart.
     */
    DeleteFromCart inter;

    /**
     * Cart adapter constructor method.
     * @param context Object of type Context.
     * @param inter Object of type DeleteFromCart
     */
    public Cart_Adapter(Context context, DeleteFromCart inter) {
        //getting from Cart Activity
        this.context=context;
        this.inter = inter;
    }


    /**
     * Set view of card - item view.
     * @param parent Object of type ViewGroup.
     * @param viewType Object of type int.
     * @return view - the new current ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //set View of Card(item View)
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cartcard,parent,false);
        return new ViewHolder(view);
    }


    /**
     * Set values.
     * @param holder Object of type ViewHolder.
     * @param position Object of type int.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView")
            int position) {

        //set Data on ItemView
        holder.name.setText(MainActivity.arrayList.get(position).getType()
                .concat(" (")
                .concat(MainActivity.arrayList.get(position).getQty())
                .concat(")")
        );

        holder.price.setText(MainActivity.arrayList.get(position).getPrice()
                .concat(context.getResources().getString(R.string.dollar)));
        holder.category.setText(MainActivity.arrayList.get(position).getFlavour()
                .concat("\n")
                .concat(MainActivity.arrayList.get(position).getSize())
        );

        // Delete From Cart
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        context);

// Setting Dialog Title
                alertDialog2.setTitle("Confirm Delete...");

// Setting Dialog Message
                alertDialog2.setMessage("Are you sure you want delete this product?");

// Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                MainActivity.arrayList.remove(position);
                                inter.onclick();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

// Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

// Showing Alert Dialog
                alertDialog2.show();


            }
        });
    }


    /**
     * getItemCount returns the current number of orders in the cart.
     * @return size of current orders in cart.
     */
    @Override
    public int getItemCount() {
        return MainActivity.arrayList.size();
    }

    /**
     * Declaration and initiation.
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,category,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tv_subcat_title);
            price=itemView.findViewById(R.id.tv_subcat_price);
            category=itemView.findViewById(R.id.category);
            delete = itemView.findViewById(R.id.delete);

        }
    }

}
