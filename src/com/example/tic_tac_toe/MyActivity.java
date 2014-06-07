package com.example.tic_tac_toe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MyActivity extends Activity {
    private TextView[] arr = new TextView[9];

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        arr[0] = (TextView) findViewById(R.id.a1);
        arr[1] = (TextView) findViewById(R.id.a2);
        arr[2] = (TextView) findViewById(R.id.a3);
        arr[3] = (TextView) findViewById(R.id.a4);
        arr[4] = (TextView) findViewById(R.id.a5);
        arr[5] = (TextView) findViewById(R.id.a6);
        arr[6] = (TextView) findViewById(R.id.a7);
        arr[7] = (TextView) findViewById(R.id.a8);
        arr[8] = (TextView) findViewById(R.id.a9);
    }

    public void viewClicked(View view) {
        ((TextView) view).setText("X");
        Log.e("tic", "clicked on view tag=" + view.getTag() + "  id=" + view.getId());
        checkWin("X");
    }

    private void checkWin(String player) {
        if (winsInLine(arr[0], arr[1], arr[2]) ||
                winsInLine(arr[3], arr[4], arr[5]) ||
                winsInLine(arr[6], arr[7], arr[8]) ||
                winsInLine(arr[0], arr[3], arr[6]) ||
                winsInLine(arr[1], arr[4], arr[7]) ||
                winsInLine(arr[2], arr[5], arr[8]) ||
                winsInLine(arr[0], arr[4], arr[8]) ||
                winsInLine(arr[2], arr[4], arr[6]) )
        {
            new AlertDialog.Builder(this)
                    .setTitle("Победа!")
                    .setMessage("Победил " + player)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    private boolean winsInLine(TextView field1, TextView field2, TextView field3) {
        return (!TextUtils.isEmpty(field1.getText())) &&
                field1.getText().equals(field2.getText()) &&
                field2.getText().equals(field3.getText());
    }
}
