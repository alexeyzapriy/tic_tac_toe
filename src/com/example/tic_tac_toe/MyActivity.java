package com.example.tic_tac_toe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void viewClicked(View view) {
        Log.e("tic", "clicked on view " + view.getClass().getName());
    }
}
