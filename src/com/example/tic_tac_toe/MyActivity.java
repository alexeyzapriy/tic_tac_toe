package com.example.tic_tac_toe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import java.util.Random;

public class MyActivity extends Activity {
    private ImageView[] arr = new ImageView[9];
    private Random random = new Random();
    private int viewTarget =-1;
    private ImageView currentStep;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        arr[0] = (ImageView) findViewById(R.id.a1);
        arr[1] = (ImageView) findViewById(R.id.a2);
        arr[2] = (ImageView) findViewById(R.id.a3);
        arr[3] = (ImageView) findViewById(R.id.a4);
        arr[4] = (ImageView) findViewById(R.id.a5);
        arr[5] = (ImageView) findViewById(R.id.a6);
        arr[6] = (ImageView) findViewById(R.id.a7);
        arr[7] = (ImageView) findViewById(R.id.a8);
        arr[8] = (ImageView) findViewById(R.id.a9);
    }

    public void newGame_Click(View view){
        for (int i = 0; i < arr.length; i++ ){
            arr[i].setTag(null);
            arr[i].setImageDrawable(null);
        }
        RadioButton rb = (RadioButton)findViewById(R.id.radioButton2);
        if(rb.isChecked()){
            goAndroid();
        }
    }
    public void viewClicked(View view) {
        if(((ImageView)view).getTag()==null){
        ((ImageView) view).setImageDrawable(getResources().getDrawable(R.drawable.x));
        ((ImageView) view).setTag("X");

       // Log.e("tic", "clicked on view tag=" + view.getTag() + "  id=" + view.getId());
        if(checkWin()){
            showAlert("X");
        }else{
            goAndroid();
         }
        }
    }

    private void goAndroid() {

        //проаерка можно ли сделать победный ход
        if(if_I_win()){
            currentStep.setTag("O");
            currentStep.setImageDrawable(getResources().getDrawable(R.drawable.o));
            if(checkWin()) showAlert("O");
        }
        else{
        while (!getNumber()){}
        arr[viewTarget].setTag("O");
        arr[viewTarget].setImageDrawable(getResources().getDrawable(R.drawable.o));
        if(checkWin()) showAlert("O");
        }
    }

    private boolean getNumber() {

        viewTarget = random.nextInt(9);
        if(arr[viewTarget].getTag()==null){
            return true;
        }else return false;
    }

    private void showAlert(String pleer) {
        new AlertDialog.Builder(this)
                .setTitle("Победа!")
                .setMessage("Победил "+ pleer)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();                   }
                })
                .show();
    }

    private boolean checkWin() {
        if (winsInLine(arr[0], arr[1], arr[2]) ||
                winsInLine(arr[3], arr[4], arr[5]) ||
                winsInLine(arr[6], arr[7], arr[8]) ||
                winsInLine(arr[0], arr[3], arr[6]) ||
                winsInLine(arr[1], arr[4], arr[7]) ||
                winsInLine(arr[2], arr[5], arr[8]) ||
                winsInLine(arr[0], arr[4], arr[8]) ||
                winsInLine(arr[2], arr[4], arr[6]) )
        {
            return true;
        }
        else return  false;
    }

    private boolean winsInLine(ImageView field1, ImageView field2, ImageView field3) {
        return field1.getTag()!=null &&
                field1.getTag()==(field2.getTag()) &&
                field2.getTag()==(field3.getTag());
    }
    private boolean if_I_win() {
               if(winning_move(arr[0], arr[1], arr[2])){ return true;}
        if(winning_move(arr[3], arr[4], arr[5])){ return true;}
        if(winning_move(arr[6], arr[7], arr[8])){ return true;}
        if(winning_move(arr[0], arr[3], arr[6])){ return true;}
        if(winning_move(arr[1], arr[4], arr[7])){ return true;}
        if(winning_move(arr[2], arr[5], arr[8])){ return true;}
        if(winning_move(arr[0], arr[4], arr[8])){ return true;}
        if(winning_move(arr[2], arr[4], arr[6])){ return true;}
        return  false;
    }
    private boolean winning_move(ImageView field1, ImageView field2, ImageView field3) {
       ImageView[] arrTemp = {field1, field2, field3};
        int count_O = 0;
        int index_empty = -1;
        for (int i=0; i<arrTemp.length; i++){
                if(arrTemp[i].getTag()=="O"){
                    count_O++;
                }
                else if(arrTemp[i].getTag()==null) {
                    index_empty = i;
                }
        }
        if(count_O==2&&index_empty!=-1){
        currentStep = arrTemp[index_empty];
            return true;
        }
        return false;
    }
}
