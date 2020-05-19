package com.example.logika;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
import java.util.List;
import java.util.Random;

public class MainActivity<pinArray> extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

   private Boolean[] pinArray = {false, false, false, false};


   private Boolean[] colorButtonsArray = {false, false, false, false, false, false};

   private int attemptCounter;         //counter für textview

   private int[] userColorCode ={0,0,0,0};
   private int[] generatedColorCode = {0,0,0,0};

   private int attemptCountr;



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
        generate_ColorCodeOnStart();
        checkBoxA = findViewById(R.id.checkBoxAID);
        checkBoxB = findViewById(R.id.checkBoxBID);
        checkBoxC = findViewById(R.id.checkBoxCID);
        checkBoxD = findViewById(R.id.checkBoxDID);


        linearLayout = findViewById(R.id.linearLayout_checkboxen_ID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);


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


    /*generates color code on start. I used a 2. function which takes no View as parameter to call
    /in onCreate*/
    public void generate_ColorCodeOnStart(){
        int i = 0;
        List<String> givenList = Arrays.asList("red", "green", "blue", "yellow", "cyan", "magenta");
        while(i < 4) {
            Random rnd = new Random();
            int color = rnd.nextInt(givenList.size());
           generatedColorCode[i] = color;
            i++;

        }


    }


        public int setColorCodeForUser() {
        int colorChoice=0;
        for (int i = 0; i < colorButtonsArray.length; i++) {
            if (colorButtonsArray[i]) {
                colorChoice = i;
            }


        }
        return colorChoice;

        }

        public void checkColorCode () {
            int j = 0;
            for (int i = 0; i < 3; i++) {

                if (generatedColorCode[i] == userColorCode[i]) {
                    j++;
                    System.out.print(j);
                }
                if (j == 3) {
                    System.out.println("GEWONNNEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }

            }

        }

        //iterate through radiogroup and dissable all buttons/same with enable
        public void disableButtons () {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton) radioGroup.getChildAt(i)).setEnabled(false);
            }
        }

        public void enableButtons () {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton) radioGroup.getChildAt(i)).setEnabled(true);
            }
        }

        public void setColor () {

            //iterate throught radiogroup and check which button is clicked, set array accordingly
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                if (((CompoundButton) radioGroup.getChildAt(i)).isChecked()) {
                    colorButtonsArray[i] = true;
                    //setze hier welcher platz gemalt werden soll in UserColorArray.
                    //

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

                } else if (!((CompoundButton) linearLayout.getChildAt(0)).isChecked() &&
                        !((CompoundButton) linearLayout.getChildAt(1)).isChecked() &&
                        !((CompoundButton) linearLayout.getChildAt(2)).isChecked() &&
                        !((CompoundButton) linearLayout.getChildAt(3)).isChecked()) {

                    disableButtons();

                } else {
                    pinArray[i] = false;
                }

            }
        }


        //choose which color to color the pin(textviev v) given by function choosePin();
        public void chooseColor (TextView v){



                if (colorButtonsArray[0] == true) {
                    v.setBackgroundColor(getColor(R.color.red));
                } else if (colorButtonsArray[1]) {
                    v.setBackgroundColor(getColor(R.color.green));
                } else if (colorButtonsArray[2]) {
                    v.setBackgroundColor(getColor(R.color.blue));
                } else if (colorButtonsArray[3]) {
                    v.setBackgroundColor(getColor(R.color.yellow));
                } else if (colorButtonsArray[4]) {
                    v.setBackgroundColor(getColor(R.color.cyan));
                } else if (colorButtonsArray[5]) {
                    v.setBackgroundColor(getColor(R.color.magenta));

                } else {
                    //do something
                }




        }


        //color the corresponding pins based on pinArray bool values
        public void colorPins () {

            if (pinArray[0] == true) {
                chooseColor(a);
                //userColorCode[0] = 0;
                userColorCode[0] = setColorCodeForUser();
            }
            if (pinArray[1] == true) {
                chooseColor(b);
               // userColorCode[1] = 0;
                userColorCode[1] = setColorCodeForUser();

            }
            if (pinArray[2] == true) {
                chooseColor(c);
                //userColorCode[2] = 0;
                userColorCode[2] = setColorCodeForUser();
            }
            if (pinArray[3] == true) {
                chooseColor(d);
               // userColorCode[3] = 0;
                userColorCode[3] = setColorCodeForUser();
            }
            System.out.println("Users Code Guess" + Arrays.toString(userColorCode));

        }


        //only one color choice can be active at a time
        //therefore we clear all data except the inserted color in this moment.
        public void clearColorArray ( int value){
            for (int i = 0; i < 6; i++) {
                if (value != i) {
                    colorButtonsArray[i] = false;

                }
            }
        }

        //on click in checkboxes it updates game/screen
        @Override
        public void onClick (View v){

            checkPins();
            setColor();
            System.out.println(Arrays.toString(pinArray));
            System.out.println(Arrays.toString(colorButtonsArray));
            System.out.println("Gen.Code" + Arrays.toString(generatedColorCode));

            colorPins();
            checkColorCode();
        }

        //on change in radigroup(6buttons) it updates game/screen
        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){

            checkPins();
            setColor();
            System.out.println("Pin Array:" + Arrays.toString(pinArray));
            System.out.println("Color Array:" + Arrays.toString(colorButtonsArray));
            System.out.println("Gen.Code" + Arrays.toString(generatedColorCode));


            colorPins();
            checkColorCode();
        }

        //generates a color code everytime button is pressed
        public void generate_ColorCode (View view){
            int i = 0;
            List<String> givenList = Arrays.asList("red", "green", "blue", "yellow", "cyan", "magenta");
            while (i < 4) {
                Random rnd = new Random();
                int color = rnd.nextInt(givenList.size());
                generatedColorCode[i] = color;
                i++;

            }
            System.out.println("Gen.Code" + Arrays.toString(generatedColorCode));
        }
    }