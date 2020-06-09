package com.example.logika;
//Anton Leonov 1274411
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

   private Boolean[] pinArray = {false, false, false, false}; //4 checkboxes array


   private Boolean[] colorButtonsArray = {false, false, false, false, false, false}; //6 colorpin array

   private int attemptCounter;         //counter für textview
    static int[] userColorCode ={0,0,0,0}; //code for user -> same as colorpins, red = 0, blue = 1, etc...
     static int[] generatedColorCode = {0,0,0,0}; //generated random code from 0-5(red-magenta)





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

   private LinearLayout checkBoxLayout;
    private LinearLayout colorTextViewLayout;

   private FloatingActionButton mailButton;
   private RadioButton radioButton;
   private TextView attemptsView;

   private TextView a;
   private TextView b;
   private TextView c;
   private TextView d;

   private Boolean ableToCheck = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate_ColorCodeOnStart();
        checkBoxA = findViewById(R.id.checkBoxAID);
        checkBoxB = findViewById(R.id.checkBoxBID);
        checkBoxC = findViewById(R.id.checkBoxCID);
        checkBoxD = findViewById(R.id.checkBoxDID);
        attemptsView = findViewById(R.id.counterview_ID);

        linearLayout = findViewById(R.id.linearLayout_checkboxen_ID);
        colorTextViewLayout = findViewById(R.id.colorViewsLayout_ID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        mailButton = findViewById(R.id.checkScreenButton);
        mailButton.setOnClickListener(this);

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
        //mailButton.setEnabled(false);
    }

    /*generates color code on start. I used a 2. function which takes no View as parameter to call
    /in onCreate*/
    public void generate_ColorCodeOnStart(){
        int i = 0;
        List<String> givenList = Arrays.asList("red", "green", "blue", "yellow", "cyan", "magenta");
        while (i < 4) {
            Random rnd = new Random();
            int color = rnd.nextInt(givenList.size());
            generatedColorCode[i] = color;
            userColorCode[i] = 9;
            i++;
        }
    }

        //checks colorbutton array, which button is picked, is then
        //
        public int setColorCodeForUser() {
        int colorChoice=0;
        for (int i = 0; i < colorButtonsArray.length; i++) {
            if (colorButtonsArray[i]) {
                colorChoice = i;
            }


        }
        return colorChoice;
    }




        //checks how many colors are in right place, if all allign, user gets instant win message!
        public void checkColorCode () {
            int j = 0;
            for (int i = 0; i < 4; i++) {

                if (generatedColorCode[i] == userColorCode[i]) {
                    j++;
                    System.out.println("Current Correct Colors:"+ j);
                }
                if (j == 4) {
                    Toast.makeText(this, "Congratulations! YOU WIN!", Toast.LENGTH_LONG).show();
                }


            }

        }

        //disbale checkboxes and clear color after generating new color code
        public void dissableCheckBoxesAndColors(){
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                ((CheckBox) linearLayout.getChildAt(i)).setChecked(false);
            }
            for(int i = 0; i < colorTextViewLayout.getChildCount();i++){
                colorTextViewLayout.getChildAt(i).setBackgroundColor(getColor(R.color.lightgrey));

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

            /*iterate throught radiogroup and check which button is clicked, set array accordingly,
            so we know what color to use for actually checked pins
             */

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


        //interate thoough the linear layout and check pins if checked and set the pinArray accordingly
        public void checkPins () {


            for (int i = 0; i < linearLayout.getChildCount(); i++) {

                if (((CompoundButton) linearLayout.getChildAt(i)).isChecked()) {
                    pinArray[i] = true;
                    enableButtons();


                    //disable color buttons when no box is chcked
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


        //choose which color to color the pin(textviev v)
        public void chooseColor (TextView v){



                if (colorButtonsArray[0]) {
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
        checkForCompleteSolution();
        switch (v.getId()) {
            case R.id.checkScreenButton:

                if (ableToCheck == true) {
                    Intent intent = new Intent(this, CheckScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please color all 4 Blocks!", Toast.LENGTH_SHORT);
                }
            buttonFunctions();
        }


        }


        public void buttonFunctions(){
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
            attemptCounter++;
            attemptsView.setText(String.valueOf(attemptCounter));
            System.out.println("Users Code Guess" + Arrays.toString(userColorCode));
            checkForCompleteSolution();
        }

        //generates a color code everytime button is pressed and "restarts" the UI

        public void generate_ColorCode (View view){
            generate_ColorCodeOnStart();


            dissableCheckBoxesAndColors();
            checkPins();
            clearColorArray(9);
            ableToCheck=false;

            attemptCounter=0;
            attemptsView.setText(String.valueOf(attemptCounter));
            System.out.println("Gen.Code" + Arrays.toString(generatedColorCode));
        System.out.println("User.Code" + Arrays.toString(userColorCode));
        }


        //checks for completion and enables button if true
        public void checkForCompleteSolution(){
        int count = 0;
        for(int i = 0; i < 4; i++){
            if(userColorCode[0] != 9){
                count++;

            }
            System.out.print("COUNT:"+ count);
            if(count == 3){
                ableToCheck= true;
            }

        }}






}