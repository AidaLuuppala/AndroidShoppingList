package com.example.aida.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShoppingList extends AppCompatActivity {

    public static ArrayList<String> shoppings = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    public static int firstTime = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAddShopping:
                Intent siirryLisaamaanMenusta = new Intent(ShoppingList.this, NewShoppingItem.class);
                this.startActivity(siirryLisaamaanMenusta);
                return true;
            case R.id.mnInstructions:
                Intent siirryOhjeisiinMenusta = new Intent(ShoppingList.this, Instructions.class);
                this.startActivity(siirryOhjeisiinMenusta);
                //showToast("PUHELIN PAINETTU");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // NOTIFIKAATIO TOAST, TOAST ON POPUP IKKUNA JOKA NÄKYY NOIN SEKUNNI APUMETODI
    public void showToast(String teksti) {
        int aika = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, teksti, aika);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //LUODAAN TOOLBAR
        Toolbar menuToolbar =(Toolbar) findViewById(R.id.menuToolbar);
        setSupportActionBar(menuToolbar);

        ListView nameList = (ListView)findViewById(R.id.nameList);

        if (firstTime == 1) {
            lataaSharedPreferences();
            firstTime = 0;
        }

        if(shoppings != null && shoppings.size()>0) {
            //ADAPTERIN KÄYYYÖ KYTKETTÄESSÄ ARRAYLIST NÄYTÖN LIST LISTAAN
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    shoppings.toArray(new String[1]));
            nameList.setAdapter(adapter);
        }

        //LUODAAN BUTTON PAINIKE
        Button btAdd;
        //HAETAAN XML-TIEDOSTOSTA VIITTAUS BUTTONIIN
        btAdd = (Button) findViewById(R.id.btAdd);
        //LUODAAN TAPAHTUMAN KUUNTELIJA BUTTONILLE
        btAdd.setOnClickListener(new View.OnClickListener() {

            //ONCLICK ETODISSA MÄÄRITETÄÄN MITÄ TAPAHTUU KUN PAINIKETTA PAINETAAN
            @Override
            public void onClick(View v) {
                //LUODAAN INTENT OLIO
                Intent siirrySeuraavaan = new Intent(ShoppingList.this, NewShoppingItem.class);
                // KÄYNNISTETÄÄN TOINEN ACTIVITY
                startActivity(siirrySeuraavaan);
            }
        });
    }

    //METODI, JOKA TALLENTAA SHAREDPREFERENCEEN ELI TIEDOSTOON
    private void lataaSharedPreferences() {
        SharedPreferences omaSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String shoppingsString = omaSharedPreferences.getString("shoppings", "");
        StringTokenizer tokenizer = new StringTokenizer(shoppingsString, "|");
        while(tokenizer.hasMoreTokens()) {
            shoppings.add(tokenizer.nextToken());
        }
    }
    //METODI, JOKA LUKE SHAREDPREFENCESTÄ ELI TIEDOSTOSTA
    private void tallennaSharedPreferences() {
        SharedPreferences omaSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String shoppingsString = "";
        for(int i = 0; i < shoppings.size(); i++) {
            shoppingsString += shoppings.get(i) + "|";
        }
        SharedPreferences.Editor editor = omaSharedPreferences.edit();
        editor.putString("shoppings", shoppingsString);
        editor.apply();
    }
    //ELINKAARI ONDESTROY TOTEUTETAAN, JOSSA TALLENNETAAN OSTOKSET
    @Override
    protected void onDestroy() {
        super.onDestroy();
        tallennaSharedPreferences();
    }

}
