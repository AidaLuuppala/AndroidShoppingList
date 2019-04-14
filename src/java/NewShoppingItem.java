package com.example.aida.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewShoppingItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_shopping_item);
        //LUODAAN BUTTON PAINIKE
        Button addButton;
        //HAETAAN XML-TIEDOSTOSTA VIITTAUS BUTTONIIN
        addButton = (Button) findViewById(R.id.addButton);
        //HAE VIITTAUS XML-TIEDOSTON EDITTECT KENTTÄÄN r:N KAUTTA
        final EditText etName = (EditText)findViewById(R.id.etName);
        //LUODAAN TAPAHTUMAN KUUNTELIJA BUTTONILLE
        addButton.setOnClickListener(new View.OnClickListener() {

            //ONCLICK ETODISSA MÄÄRITETÄÄN MITÄ TAPAHTUU KUN PAINIKETTA PAINETAAN
            @Override
            public void onClick(View v) {
                //LUODAAN EDITTEXTIIN KÄYTTÄJÄN KIRJOITTAMA TEKSTI STRING-MUUTTUJAAN
                String name = etName.getText().toString();
                //HEITETÄÄN TOAST, JOSSA KÄYTTÄJÄN KIRJOITTAMA NIMI
                //showToast(name);
                ShoppingList.shoppings.add(name);
                //SIIRRYTÄÄN LISTAN NÄYTTÖÖN
                Intent seuraavaNaytto = new Intent(NewShoppingItem.this, ShoppingList.class);
                // KÄYNNISTETÄÄN TOINEN ACTIVITY
                startActivity(seuraavaNaytto);
            }
        });
    }

    public void showToast(String teksti) {
        int aika = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, teksti, aika);
        toast.show();
    }
}