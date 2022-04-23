package com.application.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.application.rucafe.Model.OrderModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class provides the functionality of managing multiple orders as well as
 * other features.
 *
 * @author Kiernan King and Ahmed Alghazwi
 */
public class Store extends AppCompatActivity {

    /**
     * Creates arrayList Object of type String ArrayList.
     */
    ArrayList<String> arrayList;
    
    /**
     * Creates lv Object of type ListView.
     */
    ListView lv;
    
    /**
     * Creates arrayAdapter Object of type String ArrayAdapter.
     */
    ArrayAdapter<String> arrayAdapter;

    /**
     * onCreate is the initialization method.
     * @param savedInstanceState Object of type Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //initialize
        arrayList = new ArrayList<>();
        arrayList = getArrayList(getResources().getString(R.string.key));
        lv = (ListView) findViewById(R.id.listview);

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listviewtextview,
                arrayList );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        Store.this);

// Setting Dialog Title
                alertDialog2.setTitle("Confirm Cancel...");

// Setting Dialog Message
                alertDialog2.setMessage("Are you sure you want cancel this order?");

// Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                arrayList.remove(i);
                                updateArraylist(getResources().getString(R.string.key),arrayList);
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

                return false;
            }
        });
    }


    /**
     * Gets your store order from saved data on mobile.
     * @param key Object of type String.
     * @return gson represetation of Json Object.
     */
    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, "null");
        if (json.equals("null")) {
            Toast.makeText(Store.this,"There is something error",Toast.LENGTH_LONG).show();
        }
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    /**
     * If an order is cancelled, the order will be updated from here.
     * @param key Object of type String.
     * @param updatelist Object of type String ArrayList.
     */
    public void updateArraylist(String key, ArrayList<String> updatelist){

        //Update Order
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(updatelist);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!

        arrayList.clear();
        arrayList = getArrayList(getResources().getString(R.string.key));

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.listviewtextview,
                arrayList);
        lv.setAdapter(arrayAdapter);

    }

}