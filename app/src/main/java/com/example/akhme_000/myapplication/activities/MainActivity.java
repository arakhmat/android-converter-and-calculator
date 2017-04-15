package com.example.akhme_000.myapplication.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.akhme_000.myapplication.R;


public class MainActivity extends Activity {

    private Button converter_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        converter_button = (Button) findViewById(R.id.converter_button);

        converter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConverterActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean exit_dialog() {
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(this);

        // set the AlertDialog's message
        confirmBuilder.setMessage("Are sure you want to exit?");
        // set the AlertDialog's negative Button
        confirmBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    // called when "Cancel" Button is clicked
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel(); // dismiss dialog
                    }
                }
        ); // end call to setNegativeButton
        // set the AlertDialog's positive Button
        confirmBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    // called when "Cancel" Button is clicked
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                    }
                } // end OnClickListener
        ); // end call to setPositiveButton
        confirmBuilder.create().show(); // display AlertDialog
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) return exit_dialog();

        return super.onKeyDown(keyCode, event);
    }
}
