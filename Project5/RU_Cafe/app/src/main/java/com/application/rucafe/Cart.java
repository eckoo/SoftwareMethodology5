package com.application.rucafe;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.rucafe.Adapter.Cart_Adapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class is for the current order menu and allows for storing
 * and manipulation of the user's order of Coffee and/or Donuts.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Cart extends AppCompatActivity implements DeleteFromCart{

    /**
     * Creates rv Object of type RecyclerView.
     */
    RecyclerView rv;

    /**
     * Creates adapaer Object of type Cart_Adapter.
     */
    Cart_Adapter adapter;

    /**
     * Creates subtotal Object of type TextView.
     * Creates total Object of type TextView.
     * Creates tax Object of type TextView.
     */
    TextView subtotal,total,tax;

    /**
     * Creates array Object of type String ArrayList.
     */
    ArrayList<String> array;

    /**
     * Creates placeorder Object of type Button.
     */
    Button placeorder;

    /**
     * Creates NULL_VALUE Object of type String.
     */
    private final String NULL_VALUE = "null";

    /**
     * Creates a TAX Object of type double.
     */
    private final double TAX = 6.625;

    /**
     * Creates PERCENT Object of type double.
     */
    private final double PERCENT = 100.0;

    /**
     * onCreate is the initialization method.
     * @param savedInstanceState Object of type Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        array = new ArrayList<>();
        placeorder = findViewById(R.id.placeorder);
        subtotal = findViewById(R.id.subtotal);
        total = findViewById(R.id.total);
        tax = findViewById(R.id.tax);

        rv = findViewById(R.id.rv);
        LinearLayoutManager gridLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(gridLayoutManager);
        adapter = new Cart_Adapter(Cart.this,this);
        rv.setAdapter(adapter);

        //calculating price
        PricingTotal();

        //button click listener
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.arrayList.isEmpty()){
                    Toast.makeText(Cart.this, getResources().getString(R.string.noorder), Toast.LENGTH_SHORT).show();
                }else {
                    saveArrayList(getResources().getString(R.string.key));
                }
            }
        });
    }

    /**
     * Creates and updates the current object's price.
     */
    @SuppressLint("DefaultLocale")
    public void PricingTotal(){
        double p = 0.00;
        for (int i=0 ; i < MainActivity.arrayList.size() ; i++){
            p = p + Float.parseFloat(MainActivity.arrayList.get(i).getPrice());
        }
        subtotal.setText(String.format("%.2f", p));
        double res = (p / PERCENT) * TAX;
        tax.setText(String.format("%.2f", res));
        double totally = p+res;
        total.setText(String.format("%.2f", totally));

    }


    /**
     * Interface which call from Adapter. When we delete any item from cart
     */
    @Override
    public void onclick() {
        PricingTotal();
    }


    /**
     * Save your cart into your mobile device through Shared Preferences.
     * @param key Object of type String.
     */
    public void saveArrayList(String key){

        String Order="";
        for (int i=0; i< MainActivity.arrayList.size() ; i++){
            if (MainActivity.arrayList.get(i).getName().equals(getResources().getString(R.string.donut))){
                Order = Order.concat(MainActivity.arrayList.get(i).getType()
                        +" ("+MainActivity.arrayList.get(i).getQty()+") "
                        +MainActivity.arrayList.get(i).getPrice()+"$\n");
            }
            else {
                Order = Order.concat(MainActivity.arrayList.get(i).getName()
                        +" ("+MainActivity.arrayList.get(i).getQty()+") "
                        +MainActivity.arrayList.get(i).getPrice()
                        +"$ ["+MainActivity.arrayList.get(i).getSize()+"]"
                        +"["+MainActivity.arrayList.get(i).getFlavour()+"]"
                        +"\n");
            }
        }

        Order = Order.concat("Order Total: "+total.getText().toString()
                +"$ (subtotal: "+subtotal.getText().toString()+"$, tax: "+tax.getText().toString()+"$)\n\n");
        Order = "Order Number: "+System.currentTimeMillis()+"\n"+Order;

        // get previous list
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson1 = new Gson();
        String json1 = prefs.getString(key, NULL_VALUE);
        if (!json1.equals(NULL_VALUE)) {
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            array = gson1.fromJson(json1, type);
        }

        array.add(Order);

        //Save Order
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!

        Toast.makeText(Cart.this, "Your Order will be Placed", Toast.LENGTH_SHORT).show();
        MainActivity.arrayList.clear();
        finish();
    }

}