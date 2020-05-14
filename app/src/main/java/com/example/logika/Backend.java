package com.example.logika;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.lang.reflect.Array;

public class Backend extends MainActivity {

    Boolean pinArray [] = {false, false, false, false};


    Boolean colorButtonsArray2[] = {false, false, false, false, false, false};


  //check funktion
    //check pins zuerst
    //wert änderung von bool 0->1  -> aktualisere array -> welche bool muss im array geändert werden

            // zeichen funktion(pinArray, colorButtonArray)
            // guckt welche farbe gezeichnet werden sollen durch auslesen von button position
                //male entsprechende farbe. -> picke aus colorlist????


     //checke welche pins gewählt sind und aktualisere array dementsprechend

    public void insertIntoPinArray(boolean state , int i) {
                      pinArray[i] = state;
    }



    public void checkPins2(CompoundButton pinButton) {
        if(pinButton.isChecked()){

            switch (pinButton.getId()){
                case R.id.checkBoxAID:
                    pinArray[0] = true;
                    Toast.makeText(this,"A clicked", Toast.LENGTH_SHORT).show();

                case R.id.checkBoxBID:
                    pinArray[1] = true;
                    Toast.makeText(this,"B clicked", Toast.LENGTH_SHORT).show();

                case R.id.checkBoxCID:
                    pinArray[2] = true;
                    Toast.makeText(this,"C clicked", Toast.LENGTH_SHORT).show();

                case R.id.checkBoxDID:
                    pinArray[3] = true;
                    Toast.makeText(this,"D clicked", Toast.LENGTH_SHORT).show();
            }


            //male



        }
    }
}
