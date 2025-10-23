package com.example.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Sudoku mySudoku;

    private EditText[][] playingField= {
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null, null}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText valueText = (EditText) findViewById(R.id.editTextNumber11);
        valueText.setText("0");
        valueText.clearFocus();
        mySudoku = new Sudoku();
        InitPlayingField();
    }

    private void InitPlayingField() {
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber11), 1, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber12), 2, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber13), 3, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber14), 4, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber15), 5, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber16), 6, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber17), 7, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber18), 8, 1);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber19), 9, 1);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber21), 1, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber22), 2, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber23), 3, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber24), 4, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber25), 5, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber26), 6, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber27), 7, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber28), 8, 2);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber29), 9, 2);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber31), 1, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber32), 2, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber33), 3, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber34), 4, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber35), 5, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber36), 6, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber37), 7, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber38), 8, 3);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber39), 9, 3);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber41), 1, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber42), 2, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber43), 3, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber44), 4, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber45), 5, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber46), 6, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber47), 7, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber48), 8, 4);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber49), 9, 4);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber51), 1, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber52), 2, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber53), 3, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber54), 4, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber55), 5, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber56), 6, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber57), 7, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber58), 8, 5);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber59), 9, 5);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber61), 1, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber62), 2, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber63), 3, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber64), 4, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber65), 5, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber66), 6, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber67), 7, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber68), 8, 6);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber69), 9, 6);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber71), 1, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber72), 2, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber73), 3, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber74), 4, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber75), 5, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber76), 6, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber77), 7, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber78), 8, 7);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber79), 9, 7);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber81), 1, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber82), 2, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber83), 3, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber84), 4, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber85), 5, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber86), 6, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber87), 7, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber88), 8, 8);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber89), 9, 8);

        SetupPlayingField((EditText) findViewById(R.id.editTextNumber91), 1, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber92), 2, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber93), 3, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber94), 4, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber95), 5, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber96), 6, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber97), 7, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber98), 8, 9);
        SetupPlayingField((EditText) findViewById(R.id.editTextNumber99), 9, 9);

    }

    private void SetupPlayingField(EditText valueText, Integer x, Integer y) {
        playingField[y - 1][x - 1] = valueText;
    }

    public void start(View view) {
        starting();
    }
    public void correct(View view) { correcting();  }
    public void reset(View view) { reseting();  }

    private void starting() {
        mySudoku.Reset();
        String szSudoku = mySudoku.GetPresentation();
        SetupField();
    }
    private void  SetupField()
    {
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                SetValue(x, y);
            }
        }
    }

    private void SetValue(Integer x, Integer y) {
        EditText valueText = playingField[y-1][x-1];
        Integer test = mySudoku.matrisPresentation[y - 1][x - 1];
        String szValue = Integer.toString(test);
        if (test <= 0) {
            szValue = "";
            valueText.setTextColor(Color.BLACK);
        }
        else {
            valueText.setTextColor(Color.BLUE);
        }
        valueText.setText(szValue);
        valueText.clearFocus();
    }

    private void correcting() {
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                CorrectField(x, y);
            }
        }
    }

    private void CorrectField(Integer x, Integer y) {
        EditText valueText = playingField[y-1][x-1];
        String input = valueText.getText().toString().trim();
        Integer test = mySudoku.matris[y - 1][x - 1];
        if (input.length() <= 0 || test != Integer.parseInt(input)) {
            String szValue = input + "(" + Integer.toString(test) + ")";
            valueText.setTextColor(Color.RED);
            valueText.setText(szValue);
        }
        valueText.clearFocus();

    }
    private void reseting() {
        //mySudoku.Reset();
        //ResetField();
        SetupField();
    }

    private void ResetField(){
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                SetValue(x, y);
            }
        }
    }

}