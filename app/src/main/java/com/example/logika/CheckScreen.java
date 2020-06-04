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
    private String boxColorArray[] ={"empty","empty","empty","empty"};




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

        setBlackBoxes();
        colorBoxes();
        System.out.println("BoxArray:" + Arrays.toString(boxArray));
        System.out.println("BoxColorArray:" + Arrays.toString(boxColorArray));


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

    public void setBlackBoxes() {

        int counter = 0;
        int availableColors = 0;

        for(int i = 0; i < 4; i++) {

            if (MainActivity.userColorCode[i] == MainActivity.generatedColorCode[i]) {
                if (boxArray[counter] == false) {
                    boxArray[counter] = true;
                    boxColorArray[counter] = "black";
                    counter++;
                    availableColors++;



                }


            }


        }

        fullyCorrectPinsView.setText(String.valueOf(counter) + " pin(s) are fully correct.");
        setWhiteBoxes(counter, availableColors);
        System.out.println("Counter:" + counter);

    }



//check for right colors, but wrong places and set array accordinly
    public void setWhiteBoxes(int counter, int availableColors){
        int colors = 0;

        for(int i = counter+1; i < 4; i++){
            for(int j = availableColors+1; j<4;j++){
                if(MainActivity.userColorCode[i] == MainActivity.generatedColorCode[j]){
                    if(boxArray[i] == false ){
                        boxArray[i] = true;
                        boxColorArray[i] = "white";
                        colors++;



                    }

                }

            }
        }
    onlyColorCorrectPinView.setText(String.valueOf(colors) + " pins(s) have correct color, but wrong position");
    }


    //color boxes according to the desired color in boxcolorarray

    public void colorBoxes(){

            for (int i = 0; i < boxlayout.getChildCount(); i++) {
                if(boxArray[i] && boxColorArray[i].equals("black")){
                    boxlayout.getChildAt(i).setBackgroundColor(getColor(R.color.black));
                }
                else if(boxColorArray[i].equals("white")){
                    boxlayout.getChildAt(i).setBackgroundColor(getColor(R.color.white));
                }
            }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dismissButton:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);



        }


    }
}