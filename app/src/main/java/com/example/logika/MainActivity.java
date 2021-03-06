package com.example.logika;
//Anton Leonov 1274411
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, RadioGroup.OnCheckedChangeListener {

   private Boolean[] pinArray = {false, false, false, false}; //4 checkboxes array


   private Boolean[] colorButtonsArray = {false, false, false, false, false, false}; //6 colorpin array

   private int attemptCounter = 0;         //counter für textview
    private int[] userColorCode ={0,0,0,0}; //code for user -> same as colorpins, red = 0, blue = 1, etc...
    private int[] generatedColorCode = {0,0,0,0}; //generated random code from 0-5(red-magenta)





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
   private int blackboxes = 0;
   private int whiteboxes = 0;
   private Boolean firststart = false;

    public MainActivity() {
    }

    //calls methods when acitivity is visible again. Here we display our win message or the correct pin message.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firststart = true;
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



    }

    /**
     *  runs code everytime we return from checkscreen
     *  passing the pin values with intent did not work. I tried 2 methods as seen below
     *  with the methods "getDataFromCheckScreen() or onActivityResult() but it did not work
     *  in the end i simply declared 2 static variables in check screen, so atleast it works.
     */


    @Override
    protected void onResume() {

        super.onResume();

        if(!firststart){
            //getDataFromCheckScreen(); or onActivityResult() <- dont work..

            checkColorCode();
        }


        firststart = false;

    }






    //does also not work, only prints default values.
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                blackboxes = data.getIntExtra("blackboxesDATA", 4);
                whiteboxes = data.getIntExtra("whiteboxesDATA", 3);
            }
        }
    }

    //gets data from second activity. Does not work.
    public void getDataFromCheckScreen() {

       Bundle extras = getIntent().getExtras();
       blackboxes = extras.getInt("blackboxes");
       whiteboxes = extras.getInt("whiteboxes");



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
            blackboxes = CheckScreen.blackBoxes;
            whiteboxes = CheckScreen.whiteBoxes;
            for (int i = 0; i < 4; i++) {

                if (generatedColorCode[i] == userColorCode[i]) {
                    j++;
                    System.out.println("Current Correct Colors:"+ j);
                }
                if (j == 4) {
                    Toast.makeText(this, this.getString(R.string.winMessage), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,blackboxes +  this.getString(R.string.zeropinscorrect) + whiteboxes +  this.getString(R.string.correctcolorbutwrongpos)
                            +"\n" + this.getString(R.string.tryagain), Toast.LENGTH_LONG).show();
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

                userColorCode[0] = setColorCodeForUser();
            }
            if (pinArray[1] == true) {
                chooseColor(b);

                userColorCode[1] = setColorCodeForUser();

            }
            if (pinArray[2] == true) {
                chooseColor(c);

                userColorCode[2] = setColorCodeForUser();
            }
            if (pinArray[3] == true) {
                chooseColor(d);

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


        switch (v.getId()) {
            case R.id.checkScreenButton:

                checkForCompleteSolution();


                if (ableToCheck == true) {


                    Intent intent = new Intent(MainActivity.this, CheckScreen.class);
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("usercode", userColorCode);
                    bundle.putIntArray("generatedcode", generatedColorCode);
                    intent.putExtras(bundle);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivityForResult(intent,1);

                } else {
                    Toast.makeText(this,this.getString(R.string.colorALl4Blocks), Toast.LENGTH_SHORT).show();
                }


            case R.id.checkBoxAID:
                checkPins();

                //buttonFunctions();
            case R.id.checkBoxBID:
                checkPins();

                //buttonFunctions();
            case R.id.checkBoxCID:
                checkPins();

                //buttonFunctions();
            case R.id.checkBoxDID :
                checkPins();

                //buttonFunctions();
        }


        }


        public void buttonFunctions(){
            setColor();
            System.out.println(Arrays.toString(pinArray));
            System.out.println(Arrays.toString(colorButtonsArray));
            System.out.println("Gen.Code" + Arrays.toString(generatedColorCode));
            colorPins();

        }
        //on change in radigroup(6buttons) it updates game/screen
        @Override
        public void onCheckedChanged (RadioGroup group,int checkedId){

            checkPins();
            buttonFunctions();

            System.out.println("Users Code Guess" + Arrays.toString(userColorCode));

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
            Toast.makeText(this, this.getString(R.string.newColorCodeGenerated) , Toast.LENGTH_SHORT).show();

        }


        //checks for completion and enables button if true
        public void checkForCompleteSolution(){
       int count;
        for(  count = 0; count < 4; count++){
            if(userColorCode[count] == 9){
                break;
            }
            System.out.print("COUNT:"+ count);
            if(count == 3){
                ableToCheck= true;
                attemptCounter++;
                attemptsView.setText(String.valueOf(attemptCounter));

            }

        }}





}