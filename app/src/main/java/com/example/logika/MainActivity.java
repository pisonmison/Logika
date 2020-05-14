package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

public class MainActivity<pinArray> extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener {

    Boolean[] pinArray = {false, false, false, false};


    Boolean[] colorButtonsArray = {false, false, false, false, false, false};

    int attemptCounter;         //counter für textview

    Backend backend;

    CheckBox[] chk;


    CheckBox checkBoxA;
    CheckBox checkBoxB;
    CheckBox checkBoxC;   //checkbox buttons
    CheckBox checkBoxD;

    RadioGroup radioGroup;
    RadioButton radioButtonRed;
    RadioButton radioButtonGreen;
    RadioButton radioButtonBlue;
    RadioButton radioButtonYellow;
    RadioButton radioButtonCyan;
    RadioButton radioButtonMagenta;

    FloatingActionButton mailButton;

    TextView a;
    TextView b;
    TextView c;
    TextView d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxA = findViewById(R.id.checkBoxAID);
        checkBoxB = findViewById(R.id.checkBoxBID);
        checkBoxC = findViewById(R.id.checkBoxCID);
        checkBoxD = findViewById(R.id.checkBoxDID);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        radioButtonRed = findViewById(R.id.radioButton1);
        radioButtonGreen = findViewById(R.id.radioButton2);
        radioButtonBlue = findViewById(R.id.radioButton3);
        radioButtonYellow = findViewById(R.id.radioButton4);
        radioButtonCyan = findViewById(R.id.radioButton5);
        radioButtonMagenta = findViewById(R.id.radioButton6);

        a = findViewById(R.id.textViewA_ID);
        b = findViewById(R.id.textViewB_ID);
        c = findViewById(R.id.textViewC_ID);
        d = findViewById(R.id.textViewD_ID);

        checkBoxA.setOnClickListener(this);
        checkBoxB.setOnClickListener(this);
        checkBoxC.setOnClickListener(this);
        checkBoxD.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(this);


    }


    public void checkColor() {

        if (radioButtonRed.isChecked()) {
            colorButtonsArray[0] = true;
            clearColorArray(0);
        } else if (radioButtonGreen.isChecked()) {
            colorButtonsArray[1] = true;
            clearColorArray(1);
        } else if (radioButtonBlue.isChecked()) {
            colorButtonsArray[2] = true;
            clearColorArray(2);
        } else if (radioButtonYellow.isChecked()) {
            colorButtonsArray[3] = true;
            clearColorArray(3);
        } else if (radioButtonCyan.isChecked()) {
            colorButtonsArray[4] = true;
            clearColorArray(4);

        } else if (radioButtonMagenta.isChecked()) {
            colorButtonsArray[5] = true;
            clearColorArray(5);

        }

    }

    public void checkPins() {
        if (checkBoxA.isChecked()) {
            pinArray[0] = true;
        } else {
            pinArray[0] = false;
        }
        if (checkBoxB.isChecked()) {
            pinArray[1] = true;
        } else {
            pinArray[1] = false;
        }
        if (checkBoxC.isChecked()) {
            pinArray[2] = true;
        } else {
            pinArray[2] = false;
        }
        if (checkBoxD.isChecked()) {
            pinArray[3] = true;
        } else {
            pinArray[3] = false;
        }


    }


    public CheckBox checkPinArray() {
        for (int i = 0; i < 3; i++) {
            if (pinArray[0] == true) {
                return checkBoxA;
            }
        }
        return null;
    }

    public void chooseColor(TextView v){

            for (int j = 0; j < 5; j++) {
                if (colorButtonsArray[0] == true) {
                    v.setBackgroundColor(getResources().getColor(R.color.red));
                } else if (colorButtonsArray[1]) {
                    v.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (colorButtonsArray[2]) {
                    v.setBackgroundColor(getResources().getColor(R.color.blue));
                } else if (colorButtonsArray[3]) {
                    v.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else if (colorButtonsArray[4]) {
                    v.setBackgroundColor(getResources().getColor(R.color.cyan));
                } else if (colorButtonsArray[5]) {
                    v.setBackgroundColor(getResources().getColor(R.color.magenta));

                } else {
                    //do something
                }
            }


    }

    public void colorPins(){
        for(int i = 0; i < 3; i++){
            if(pinArray[0] == true){
                chooseColor(a);

            }
            if(pinArray[1] == true){
                chooseColor(b);

            }
            if(pinArray[2] == true){
                chooseColor(c);

            }
            if(pinArray[3] == true){
                chooseColor(d);

            }
        }
    }



        public void clearColorArray (int value){
            for (int i = 0; i < 6; i++) {
                if (value == i) {
                    //do nothing
                } else
                    colorButtonsArray[i] = false;
            }
        }



        @Override
        public void onClick (View v){
            checkPins();
            checkColor();
            System.out.println(Arrays.toString(pinArray));
            System.out.println(Arrays.toString(colorButtonsArray));
            colorPins();
        }

        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){
            switch (group.getId()) {
                case R.id.radioGroupID:

                    checkPins();
                    checkColor();
                    System.out.println(Arrays.toString(pinArray));
                    System.out.println(Arrays.toString(colorButtonsArray));
                    colorPins();



            }
        }

        @Override
        public void onCheckedChanged (CompoundButton buttonView ,boolean isChecked){
            switch (buttonView.getId()) {
                case R.id.checkBoxAID:

                    checkPins();
                    checkColor();
                    System.out.println(Arrays.toString(pinArray));
                    System.out.println(Arrays.toString(colorButtonsArray));

                    colorPins();


                case R.id.checkBoxBID:
                    checkPins();
                    checkColor();
                    System.out.println(Arrays.toString(pinArray));
                    System.out.println(Arrays.toString(colorButtonsArray));

                    colorPins();


                case R.id.checkBoxCID:
                    checkPins();
                    checkColor();
                    System.out.println(Arrays.toString(pinArray));
                    System.out.println(Arrays.toString(colorButtonsArray));

                    colorPins();


                case R.id.checkBoxDID:
                    checkPins();
                    checkColor();
                    System.out.println(Arrays.toString(pinArray));
                    System.out.println(Arrays.toString(colorButtonsArray));

                    colorPins();
            }
        }
    }
