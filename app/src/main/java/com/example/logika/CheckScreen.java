package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

public class CheckScreen extends AppCompatActivity implements Button.OnClickListener{
    private TextView box1;
    private TextView box2;
    private TextView box3;
    private TextView box4;

    private TextView fullyCorrectPinsView;
    private TextView onlyColorCorrectPinView;
    private LinearLayout boxlayout;

    private Button dismissButton;
    private Boolean boxArray[] = {false, false, false, false};
    private Boolean boxOnlyColorRight[] = {false, false, false, false};

    private int[] userColorCode;
    private int[] generatedColorCode;
    private int boxCounter = 0;
    public static int whiteBoxes = 0;
    public static int blackBoxes = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_screen);

        box1 = findViewById(R.id.textView1BOX_ID);
        box2 = findViewById(R.id.textView2BOX_ID);
        box3 = findViewById(R.id.textView3BOX_ID);
        box4 = findViewById(R.id.textView4BOX_ID);

        boxlayout = findViewById(R.id.checkscreenBoxLayout_ID);


        fullyCorrectPinsView = findViewById(R.id.textViewFullyCorrectPins_ID);
        onlyColorCorrectPinView = findViewById(R.id.textViewOnlyColorCorrect_ID);

        dismissButton = findViewById(R.id.dismissButton);


        dismissButton.setOnClickListener(this);
        getDataFromMainActivity();

        //color the boxes everytime this activity is started or put to front
        colorBlackBoxes();
        colorWhiteBoxes();
        System.out.println("BoxArray:" + Arrays.toString(boxArray));

        //System.out.println("BoxColorArray:" + Arrays.toString(boxColorArray));


    }

    //gets the 2 arrays passed by main activity. Works fine.
    public void getDataFromMainActivity(){
        Bundle extras = getIntent().getExtras();
        userColorCode = extras.getIntArray("usercode");
        generatedColorCode = extras.getIntArray("generatedcode");

    }

    /*
    1. check for fully correct pins and color boxes
    2. check for color_only_correct and color boxes left.
    3. else dont color anything




/*check for fully correct placements, set array and color boxes black, return amount of colored
  black boxes for later use in colorWhiteBoxes();
    */
    public int setBlackBoxes() {


        int blackboxes = 0;

        for(int i = 0; i < 4; i++) {
            if (userColorCode[i] == generatedColorCode[i]) {

                        boxArray[i] = true;
                        blackboxes++;
                        boxCounter++;

            }


        }

        fullyCorrectPinsView.setText(String.valueOf(blackboxes) + this.getString(R.string.zeropinscorrect));

        System.out.println("Counter:" + blackboxes);
        blackBoxes = blackboxes;
        return blackboxes;
    }



/*

    *
    check for right colors, but wrong places. Set Array, and color boxes.*/

    public int setWhiteBoxes( ) {


        int whiteboxes  = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!boxArray[i] && generatedColorCode[i] ==  userColorCode[j] ) {

                            boxArray[i] = true;
                            whiteboxes++;



                }
            }
            onlyColorCorrectPinView.setText(String.valueOf(whiteboxes)  + this.getString(R.string.correctcolorbutwrongpos));
        }
        whiteBoxes = whiteboxes;
        return whiteboxes;}

        //color boxes according to the desired color in boxcolorarray

        public void colorBlackBoxes () {
        int temp = setBlackBoxes();

        //int temp2 = setWhiteBoxes();

        for(int i = 0; i < temp; i++){
            boxlayout.getChildAt(i).setBackgroundColor(getColor(R.color.black));




        }
        }

        public void colorWhiteBoxes(){
            int temp2 = setWhiteBoxes(); // returns amount of white boxes

            for(int i = boxCounter; i < (temp2 + boxCounter);i++){

                   boxlayout.getChildAt(i).setBackgroundColor(getColor(R.color.white));

            }



        }


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.dismissButton:
                    Intent intent = new Intent(CheckScreen.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("blackboxesDATA", blackBoxes);
                    bundle.putInt("whiteboxesDATA", whiteBoxes);
                    setResult(RESULT_OK, intent);
                    intent.putExtras(bundle);
                   // intent.putExtra("whiteboxes", whiteBoxes);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);


            }


        }
    }

