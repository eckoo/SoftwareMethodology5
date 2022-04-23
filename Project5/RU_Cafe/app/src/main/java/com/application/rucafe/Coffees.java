package com.application.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.rucafe.Adapter.Coffee_Flavour_Adapter;
import com.application.rucafe.Adapter.Donut_Flavour_Adapter;
import com.application.rucafe.Model.OrderModel;

/**
 * This class holds properties of Coffee objects like add-ins, size, quantity
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Coffees extends AppCompatActivity implements DeleteFromCart{

    /**
     * Creates Minus Object of type ImageButton.
     * Creates Add Object of type ImageButton.
     */
    ImageButton Minus,Add;

    /**
     * Creates counter Object of type TextView.
     * Creates price Object of type TextView.
     */
    TextView counter,price;

    /**
     * Creates selecttype Object of type Spinner.
     */
    Spinner selecttype;

    /**
     * Creates AddtoCart Object of type Button.
     */
    Button AddtoCart;

    /**
     * Creates rv Object of type RecyclerView.
     */
    RecyclerView rv;

    /**
     * Creates adapter Object of type Coffee_Flavour_Adapater.
     */
    Coffee_Flavour_Adapter adapter;

    /**
     * Basic price for Coffee
     */
    private final double BASIC_PRICE = 1.69;

    /**
     * Price for large size
     */
    private final double EXTRA_PRICE = 0.40;

    /**
     * Flavor price percent
     */

    private final double FLAVOR_PRICE = 0.30;

    /**
     * Valid counter 1
     */
    private final String COUNTER_1 = "1";

    /**
     * Coffee Size: Short
     */
    private final String TYPE_SHORT = "Short";
    /**
     * Coffee Size: Tall
     */
    private final String TYPE_TALL = "Tall";
    /**
     * Coffee Size: Grande
     */
    private final String TYPE_GRANDE = "Grande";
    /**
     * Coffee Size: Venti
     */
    private final String TYPE_VENTI = "Venti";

    /**
     * onCreate is the initialization method.
     * @param savedInstanceState Object of type Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffees);

        //Initialize
        price = findViewById(R.id.price);
        AddtoCart = findViewById(R.id.addtocart);
        Minus = findViewById(R.id.imgbtn1);
        Add = findViewById(R.id.imgbtn2);
        counter = findViewById(R.id.counter);
        selecttype = findViewById(R.id.selecttype);

        rv = findViewById(R.id.rv);

        LinearLayoutManager gridLayoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(gridLayoutManager);
        adapter = new Coffee_Flavour_Adapter(Coffees.this, getResources().getStringArray
                (R.array.CofeeFlavour),this);
        rv.setAdapter(adapter);

        //select type
        selecttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                PriceCalculator();

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
                String add=counter.getText().toString();
                int temp=Integer.parseInt(add);
                temp++;
                counter.setText(String.valueOf(temp));

                PriceCalculator();
            }
        });

        //Minus QTY
        Minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (!counter.getText().toString().equals(COUNTER_1)) {
                    String add=counter.getText().toString();
                    int temp=Integer.parseInt(add);
                    temp--;
                    counter.setText(String.valueOf(temp));

                    PriceCalculator();

                }
            }
        });

        //Add into Cart
        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Coffee_Flavour_Adapter.selectedflavour.isEmpty()){
                    Toast.makeText(Coffees.this, "Please Select Flavour of your Coffee",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    PlaceOrder();
                }
            }
        });

    }

    /**
     * Order will be placed through this function.
     */
    private void PlaceOrder() {

        String ff = "";
        for (int i=0 ; i<Coffee_Flavour_Adapter.selectedflavour.size() ; i++){
            if (TextUtils.isEmpty(ff)){
                ff = Coffee_Flavour_Adapter.selectedflavour.get(i);
            }else {
                ff = ff.concat(","+Coffee_Flavour_Adapter.selectedflavour.get(i));
            }
        }

        OrderModel model = new OrderModel();
        model.setType(getResources().getString(R.string.coffee));
        model.setPrice(price.getText().toString());
        model.setQty(counter.getText().toString());
        model.setSize(selecttype.getSelectedItem().toString());
        model.setName(getResources().getString(R.string.coffee));

        model.setFlavour(ff);

        MainActivity.arrayList.add(model);

        Toast.makeText(Coffees.this, getResources().getString(R.string.addtocartsuccess), Toast.LENGTH_SHORT).show();

    }

    /**
     * Interface for calculate price when something change
     */
    @Override
    public void onclick() {
        PriceCalculator();
    }

    /**
     * Calculate price of Coffee
     */
    @SuppressLint("DefaultLocale")
    private void PriceCalculator() {

        String type = selecttype.getSelectedItem().toString();

        double typeprice = 0.00;
        double flavourprice = 0.00;

        if (type.equals(TYPE_SHORT)){
            typeprice = BASIC_PRICE;
        }
        if (type.equals(TYPE_TALL)){
            typeprice = BASIC_PRICE+EXTRA_PRICE;
        }
        if (type.equals(TYPE_GRANDE)){
            typeprice = BASIC_PRICE+EXTRA_PRICE+EXTRA_PRICE;
        }
        if (type.equals(TYPE_VENTI)){
            typeprice = BASIC_PRICE+EXTRA_PRICE+EXTRA_PRICE+EXTRA_PRICE;
        }

        if (Coffee_Flavour_Adapter.selectedflavour.size()>0){
            flavourprice = Coffee_Flavour_Adapter.selectedflavour.size()*FLAVOR_PRICE;
        }

        double subtotal = typeprice+flavourprice;
        double total = Integer.parseInt(counter.getText().toString())*subtotal;
        price.setText(String.format("%.2f", total));

    }
}