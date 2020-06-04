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
    int boxCounter =0;




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


        colorBlackBoxes();
        colorWhiteBoxes();
        System.out.println("BoxArray:" + Arrays.toString(boxArray));
        //System.out.println("BoxColorArray:" + Arrays.toString(boxColorArray));


    }


    /*
    1. check for fully correct pins and color boxes
    2. check for color only correct and color boxes left.
    3. else dont color anything

    Pin correct und richtige pose
     */

    /*check for correct color and place, set array accordingly
    counter for filling boxes from right to left
    avaible colors elimates duplicate colors-> no white box when choosing duplicate colors
    when 3 boxes are already fully correct.*/

    public int setBlackBoxes() {


        int blackboxes = 0;

        for(int i = 0; i < 4; i++) {
            if (MainActivity.userColorCode[i] == MainActivity.generatedColorCode[i]) {

                        boxArray[i] = true;
                        blackboxes++;
                        boxCounter++;

            }


        }

        fullyCorrectPinsView.setText(String.valueOf(blackboxes) + " pin(s) are fully correct.");

        System.out.println("Counter:" + boxCounter);
        return blackboxes;
    }



//check for right colors, but wrong places and set array accordinly
    public int setWhiteBoxes( ) {


        int whiteboxes  = 0;
        for (int i = boxCounter; i < 4; i++) {
            for (int j = boxCounter; j < 4; j++) {
                if (MainActivity.userColorCode[i] == MainActivity.generatedColorCode[j] && boxArray[i] == false) {

                            boxArray[i] = true;
                            whiteboxes++;



                }
            }
            onlyColorCorrectPinView.setText(String.valueOf(whiteboxes) + " pins(s) have correct color, but wrong position");
        }
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
            int temp2 = setWhiteBoxes();
            for(int i = boxCounter; i < (temp2 + boxCounter);i++){
                boxlayout.getChildAt(i).setBackgroundColor(getColor(R.color.white));

            }



        }


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.dismissButton:
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);


            }


        }
    }

