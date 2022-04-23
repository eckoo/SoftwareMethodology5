package com.application.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.rucafe.Adapter.Donut_Flavour_Adapter;
import com.application.rucafe.Model.OrderModel;

/**
 * This class holds properties of the Donut object, such as type, flavor,
 * quantity.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Donuts extends AppCompatActivity {

    /**
     * Creates Minus Object of type ImageButton.
     * Creates Add Object of type ImageButton.
     */
    ImageButton Minus, Add;

    /**
     * Creates counter Object of type TextView.
     * Creates price Object of type TextView.
     */
    TextView counter, price;

    /**
     * Creates selecttype Object of type Spinner.
     */
    Spinner selecttype;

    /**
     * Creates rv Object of type RecyclerView.
     */
    RecyclerView rv;

    /**
     * Creates adapter Object of type Donut_Flavour_Adapater.
     */
    Donut_Flavour_Adapter adapter;

    /**
     * Creates AddtoCart Object of type Button.
     */
    Button AddtoCart;

    /**
     * Invalid selected position
     */
    private final int INVALID_POSITION = -1;

    /**
     * Valid counter
     */
    private final String COUNTER_1 = "1";

    /**
     * Donut type: Yeast
     */
    private final String YEAST_DONUT = "Yeast Donut";

    /**
     * Donut type: Cake
     */
    private final String CAKE_DONUT = "Cake Donut";

    /**
     * Donut type: Holes
     */
    private final String DONUT_HOLES = "Donut Holes";


    /**
     * onCreate is the initialization method.
     *
     * @param savedInstanceState Object of type Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);

        //Initialize
        price = findViewById(R.id.price);
        AddtoCart = findViewById(R.id.addtocart);
        rv = findViewById(R.id.rv);
        Minus = findViewById(R.id.imgbtn1);
        Add = findViewById(R.id.imgbtn2);
        counter = findViewById(R.id.counter);
        selecttype = findViewById(R.id.selecttype);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(gridLayoutManager);

        //select type
        selecttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = selecttype.getSelectedItem().toString();

                if (type.equals(YEAST_DONUT)) {
                    adapter = new Donut_Flavour_Adapter(Donuts.this, getResources()
                            .getStringArray(R.array.Yeast));
                    rv.setAdapter(adapter);
                    price.setText(getResources().getString(R.string.yeastprice));
                    counter.setText(getResources().getString(R.string.temp));
                }
                if (type.equals(CAKE_DONUT)) {
                    adapter = new Donut_Flavour_Adapter(Donuts.this, getResources()
                            .getStringArray(R.array.Cake));
                    rv.setAdapter(adapter);
                    price.setText(getResources().getString(R.string.cakeprice));
                    counter.setText(getResources().getString(R.string.temp));

                }
                if (type.equals(DONUT_HOLES)) {
                    adapter = new Donut_Flavour_Adapter(Donuts.this, getResources()
                            .getStringArray(R.array.Holes));
                    rv.setAdapter(adapter);
                    price.setText(getResources().getString(R.string.holeprice));
                    counter.setText(getResources().getString(R.string.temp));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Add QTY
        Add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String add = counter.getText().toString();
                int temp = Integer.parseInt(add);
                temp++;
                counter.setText(String.valueOf(temp));

                String type = selecttype.getSelectedItem().toString();
                if (type.equals(YEAST_DONUT)) {
                    float f = temp * Float.parseFloat(getResources().getString(R.string.yeastprice));
                    price.setText(String.format("%.2f", f));
                }
                if (type.equals(CAKE_DONUT)) {
                    float f = temp * Float.parseFloat(getResources().getString(R.string.cakeprice));
                    price.setText(String.format("%.2f", f));
                }
                if (type.equals(DONUT_HOLES)) {
                    float f = temp * Float.parseFloat(getResources().getString(R.string.holeprice));
                    price.setText(String.format("%.2f", f));
                }

            }
        });

        //Minus QTY
        Minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (!counter.getText().toString().equals(COUNTER_1)) {
                    String add = counter.getText().toString();
                    int temp = Integer.parseInt(add);
                    temp--;
                    counter.setText(String.valueOf(temp));

                    String type = selecttype.getSelectedItem().toString();
                    if (type.equals(YEAST_DONUT)) {
                        float f = temp * Float.parseFloat(getResources().getString(R.string.yeastprice));

                        price.setText(String.format("%.2f", f));
                    }
                    if (type.equals(CAKE_DONUT)) {
                        float f = temp * Float.parseFloat(getResources().getString(R.string.cakeprice));
                        price.setText(String.format("%.2f", f));
                    }
                    if (type.equals(DONUT_HOLES)) {
                        float f = temp * Float.parseFloat(getResources().getString(R.string.holeprice));
                        price.setText(String.format("%.2f", f));
                    }

                }
            }
        });

        //add to cart
        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Donut_Flavour_Adapter.selectedPosition == INVALID_POSITION) {
                    Toast.makeText(Donuts.this, "Please Select Flavour",
                            Toast.LENGTH_SHORT).show();
                } else {
                    PlaceOrder();
                }
            }
        });

    }

    /**
     * This Method Places Order into your cart.
     */
    private void PlaceOrder() {

        OrderModel model = new OrderModel();
        model.setType(selecttype.getSelectedItem().toString());
        model.setPrice(price.getText().toString());
        model.setQty(counter.getText().toString());
        model.setFlavour(Donut_Flavour_Adapter.selectedflavour);
        model.setSize("");
        model.setName(getResources().getString(R.string.donut));

        MainActivity.arrayList.add(model);

        Toast.makeText(Donuts.this, getResources().getString(R.string.addtocartsuccess), Toast.LENGTH_SHORT).show();

    }
}