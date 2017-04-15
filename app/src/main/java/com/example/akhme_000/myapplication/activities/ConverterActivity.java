package com.example.akhme_000.myapplication.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.akhme_000.myapplication.converter.Calculator;
import com.example.akhme_000.myapplication.fragments.KeyboardFragment;
import com.example.akhme_000.myapplication.utils.MyClipboardManager;
import com.example.akhme_000.myapplication.R;
import com.example.akhme_000.myapplication.utils.TextSeekBar;


public class ConverterActivity extends Activity implements KeyboardFragment.OnKeyboardInteractionListener {

    String input;
    MyClipboardManager clip;

    private EditText input_edittext;
    private TextView output_textview;
    private TextSeekBar inputBase_seekbar;
    private TextSeekBar outputBase_seekbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        input = "";
        clip = new MyClipboardManager();

        input_edittext  = (EditText) findViewById(R.id.input_edittext);
        output_textview = (TextView) findViewById(R.id.output_textview);
        inputBase_seekbar   = (TextSeekBar)  findViewById(R.id.inputBase_seekbar);
        outputBase_seekbar  = (TextSeekBar)  findViewById(R.id.outputBase_seekbar);

        input_edittext.setEnabled(false);
        input_edittext.setFocusable(true);
        input_edittext.setClickable(true);
        input_edittext.setShowSoftInputOnFocus(false);
        input_edittext.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                copy_paste_dialog();
                return true;
            }

        });

        output_textview.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                clip.copyToClipboard(getApplicationContext(), "\0" + output_textview.getText());
                return true;
            }

        });


        inputBase_seekbar.setProgress(0);
        outputBase_seekbar.setProgress(8);

        inputBase_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                String concat = getResources().getString(R.string.input_base) + String.valueOf(progress + 2);
//                inputBase_textview.setText(concat);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        outputBase_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                String concat = getResources().getString(R.string.output_base) + String.valueOf(progress + 2);
//                outputBase_textview.setText(concat);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        KeyboardFragment keyboardFragment = KeyboardFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, keyboardFragment);
        transaction.commit();

    }

    private boolean copy_paste_dialog() {
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(this);

        confirmBuilder.setMessage("What do you want to do?");
        confirmBuilder.setNegativeButton("Copy",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        clip.copyToClipboard(getApplicationContext(), input);
                    }
                }
        );
        confirmBuilder.setPositiveButton("Paste",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        input = clip.readFromClipboard(getApplicationContext());
                        update_input();
                    }
                }
        );
        confirmBuilder.create().show();
        return true;
    }

    public void onKeyboardInteraction(KeyboardFragment.ButtonEnum keyboard_input) {

        System.out.println(input_edittext.getSelectionStart() + " " + input_edittext.getSelectionEnd());
        if (keyboard_input == KeyboardFragment.ButtonEnum.BACKSPACE) {
            int length = input.length();
            if (length > 1) input = input.substring(0, length - 1);
            else input = "";
        }
        else if (keyboard_input == KeyboardFragment.ButtonEnum.CLEAR) {
            input = "";
        }
        else if (keyboard_input == KeyboardFragment.ButtonEnum.EQUALS) {
            evaluate();
        }
        else {
            input = input + keyboard_input.getCharacter();
        }
        update_input();
    }

    private void update_input() {
        input_edittext.setText(input);
    }

    private void evaluate() {
        output_textview.setText(Calculator.evaluate(input, inputBase_seekbar.getProgress() + 2, outputBase_seekbar.getProgress() + 2));
    }

}
