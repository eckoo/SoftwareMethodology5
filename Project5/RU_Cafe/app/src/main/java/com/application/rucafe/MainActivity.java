package com.application.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.rucafe.Model.OrderModel;

import java.util.ArrayList;

/**
 * This Class, the launcher class where all the button will be display.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Creates donut Object of type Button.
     * Creates coffee Object of type Button.
     * Creates cart Object of type Button.
     * Creates store Object of type Button.
     */
    Button donut,coffee,cart,store;
    
    /**
     * Creates arrayList Object of type OrderModel ArrayList.
     */
    public static ArrayList<OrderModel> arrayList = new ArrayList<>();

    /**
     * onCreate is the initialization method.
     * @param savedInstanceState Object of type Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        donut = (Button) findViewById(R.id.donut);
        coffee = (Button) findViewById(R.id.coffee);
        cart = (Button) findViewById(R.id.cart);
        store = (Button) findViewById(R.id.store);

        //on click listeners
        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Donuts.class));
            }
        });
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Coffees.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Cart.class));
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Store.class));
            }
        });


    }


}