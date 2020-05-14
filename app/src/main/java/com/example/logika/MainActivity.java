package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

public class MainActivity<pinArray> extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

   private Boolean[] pinArray = {false, false, false, false};


   private Boolean[] colorButtonsArray = {false, false, false, false, false, false};

   private int attemptCounter;         //counter für textview

   private Backend backend;

   private CheckBox[] chk;

   private LinearLayout linearLayout;
   private CheckBox checkBoxA;
   private CheckBox checkBoxB;
   private CheckBox checkBoxC;   //checkbox buttons
   private CheckBox checkBoxD;

   private RadioGroup radioGroup;
   private RadioButton radioButtonRed;
   private RadioButton radioButtonGreen;
   private RadioButton radioButtonBlue;
   private RadioButton radioButtonYellow;
   private RadioButton radioButtonCyan;
   private RadioButton radioButtonMagenta;

   private FloatingActionButton mailButton;
   private RadioButton radioButton;

   private TextView a;
   private TextView b;
   private TextView c;
   private TextView d;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxA = findViewById(R.id.checkBoxAID);
        checkBoxB = findViewById(R.id.checkBoxBID);
        checkBoxC = findViewById(R.id.checkBoxCID);
        checkBoxD = findViewById(R.id.checkBoxDID);


        linearLayout = findViewById(R.id.linearLayout_checkboxen_ID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);

        radioButtonRed = findViewById(R.id.radioButtonRED_ID);
        radioButtonGreen = findViewById(R.id.radioButtonGREEN_ID);
        radioButtonBlue = findViewById(R.id.radioButtonBLUE_ID);
        radioButtonYellow = findViewById(R.id.radioButtonYELLOW_ID);
        radioButtonCyan = findViewById(R.id.radioButtonCYAN_ID);
        radioButtonMagenta = findViewById(R.id.radioButtonMAGENTA_ID);

        a = findViewById(R.id.textViewA_ID);
        b = findViewById(R.id.textViewB_ID);
        c = findViewById(R.id.textViewC_ID);
        d = findViewById(R.id.textViewD_ID);

        checkBoxA.setOnClickListener(this);
        checkBoxB.setOnClickListener(this);
        checkBoxC.setOnClickListener(this);
        checkBoxD.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(this);
        disableButtons();


    }

    //iterate through radiogroup and dissable all buttons/same with enable
    public void disableButtons() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setEnabled(false);
        }
    }

    public void enableButtons() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setEnabled(true);
        }
    }

    public void setColor() {

        //iterate throught radiogroup and check which button is clicked, set array accordingly
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (((CompoundButton) radioGroup.getChildAt(i)).isChecked()) {
                colorButtonsArray[i] = true;
            } else {
                colorButtonsArray[i] = false;
            }
        }

        }


        //interate thoough the linear layout and check pins and set the pinArray accordingly
        public void checkPins () {



        for (int i = 0; i < linearLayout.getChildCount(); i++) {

                if (((CompoundButton) linearLayout.getChildAt(i)).isChecked()) {
                    pinArray[i] = true;
                    enableButtons();

                }
                else if(((CompoundButton) linearLayout.getChildAt(0)).isChecked() == false ||
                        ((CompoundButton) linearLayout.getChildAt(1)).isChecked()== false  ||
                        ((CompoundButton) linearLayout.getChildAt(2)).isChecked()== false  ||
                        ((CompoundButton) linearLayout.getChildAt(3)).isChecked()== false){

                    disableButtons();

                }

                else {
                    pinArray[i] = false;
                }

            }
        }




        //choose which color to color the pin(textviev v) given by function choosePin();
        public void chooseColor (TextView v){

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
//color the corresponding pins based on pinArray bool values
        public void colorPins () {

                if (pinArray[0] == true) {
                    chooseColor(a);
                }
                if (pinArray[1] == true) {
                    chooseColor(b);
                }
                if (pinArray[2] == true) {
                    chooseColor(c);
                }
                if (pinArray[3] == true) {
                    chooseColor(d);
                }

        }


        //only one color choice can be active at a time
        //therefore we clear all data except the inserted color in this moment.
        public void clearColorArray ( int value){
            for (int i = 0; i < 6; i++) {
                if (value == i) {
                    //do nothing
                } else
                    colorButtonsArray[i] = false;
            }
        }


        //on click on checkbox update screen
        @Override
        public void onClick (View v){

            checkPins();
            setColor();
            System.out.println(Arrays.toString(pinArray));
            System.out.println(Arrays.toString(colorButtonsArray));
            colorPins();
        }


        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){

            checkPins();
            setColor();
            System.out.println(Arrays.toString(pinArray));
            System.out.println(Arrays.toString(colorButtonsArray));
            colorPins();
        }


    }