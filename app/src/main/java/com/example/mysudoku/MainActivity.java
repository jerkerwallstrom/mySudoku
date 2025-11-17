package com.example.mysudoku;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import android.os.Handler;
import android.widget.TextView;

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

    private ProgressBar loadingIndicator;

    // To this:
    private final ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Integer selectedX = -1;
    private Integer selectedY = -1;

    ListView listView;
    String tutorials[] = { "Lätt",
            "Medel",
            "Svår" };
    TextView tmpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        TextView infoText = (TextView) findViewById(R.id.textViewSettingUpGame);
        infoText.setVisibility(View.INVISIBLE);


        tmpText = (TextView) findViewById(R.id.editTextTestInput);
        EditText valueText = (EditText) findViewById(R.id.editTextNumber11);
        valueText.setText("0");
        valueText.clearFocus();
        mySudoku = new Sudoku();
        InitPlayingField();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.my_spinner_view);
        ArrayAdapter<String> arr;

        arr = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,tutorials);
        spinner.setAdapter(arr);
        SpinnerActivity spinnerActivity = new SpinnerActivity();

        spinner.setOnItemSelectedListener(spinnerActivity);

        Button button = (Button) findViewById(R.id.btnDebug);
        //button.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

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
        valueText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    valueText.setTextColor(Color.GRAY);
                }

            }
        });
        Integer square = mySudoku.getSquare(x, y);
        switch (square){
            case 1: case 3: case 5: case 7: case 9:
                valueText.setBackgroundColor(0x66EEEEEE);
                break;
            case 2: case 4: case 6: case 8:
                // Replaces your current selection
                //valueText.setBackgroundColor(0x66FAFAD2); //Ljusgul
                valueText.setBackgroundColor(0xFFD0F0C0);  //Ljusgrön
                break;
        }
        playingField[y - 1][x - 1] = valueText;
    }

    private EditText GetSelected() {
        selectedX = -1;
        selectedY = -1;
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                EditText selected = playingField[y-1][x-1];
                if (selected.hasFocus())
                {
                    selectedX = x;
                    selectedY = y;
                    return selected;
                }
            }
        }
        return null;
    }

    public void start(View view) {
        starting();
    }
    public void correct(View view) { correcting();  }
    public void reset(View view) { reseting();  }

    public void correctSquare(View view) { correctSquareFocused();  }

    public void debug(View view) { debugging(); }

    public void lock(View view) { lockSolution(); }

    private void starting() {
        Spinner spinner = (Spinner) findViewById(R.id.my_spinner_view);
        String text = spinner.getSelectedItem().toString();

        TextView infoText = (TextView) findViewById(R.id.textViewSettingUpGame);
        infoText.setVisibility(View.VISIBLE);


        loadingIndicator.setVisibility(View.VISIBLE);
        gridSetVisibility(false); // Hide the grid while loading
        executor.execute(() -> {
            mySudoku.Reset();
            mySudoku.SetLevel(text);
            String szSudoku = mySudoku.GetPresentation();
            //findViewById(R.id.gridLayout).setVisibility(View.VISIBLE);
            handler.post(() -> {
                // --- This code runs safely on the UI thread ---

                // Now that the Sudoku is generated, update the EditText fields
                SetupField(true);

                // Hide the loading indicator and show the completed grid
                loadingIndicator.setVisibility(View.GONE);
                infoText.setVisibility(View.INVISIBLE);

                gridSetVisibility(true); // Show the grid after loading

            });
        });
    }

    private void gridSetVisibility(boolean visible) {
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                ShowFieldCell(visible, x, y);
            }
        }
    }

    private void ShowFieldCell(boolean visible, Integer x, Integer y) {
        EditText valueText = playingField[y-1][x-1];
        if (visible) {
            valueText.setVisibility(View.VISIBLE);
        }
        else {
            valueText.setVisibility(View.INVISIBLE);
        }
    }

    private void  SetupField(Boolean reset)
    {
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                SetValue(x, y, reset);
            }
        }
    }

    private void SetValue(Integer x, Integer y, Boolean reset) {
        EditText valueText = playingField[y-1][x-1];
        Integer value = mySudoku.matrisPresentation[y - 1][x - 1];
        String szValue = Integer.toString(value);
        if (value <= 0) {
            if (reset) {
                szValue = "";
                valueText.setText(szValue);
            }
            valueText.setTextColor(Color.BLACK);
            valueText.setFocusable(true);
            valueText.setFocusableInTouchMode(true);
        }
        else {
            valueText.setTextColor(Color.BLUE);
            valueText.setFocusable(false);
            valueText.setFocusableInTouchMode(false);
            valueText.setText(szValue);
        }
        valueText.clearFocus();
    }

    private void correcting() {
        boolean allCorrect = true;
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                if (!CorrectField(x, y)) {
                    allCorrect = false;
                }
            }
        }
        if (allCorrect) {
            ShoutItLoud("Congratulations!", "Alla rätt"); //"You have successfully solved the Sudoku!
        }
    }

    private void ShoutItLoud(String szTitle, String szMsg) {
        //Create and show a pop-up window to congratulate the user.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(szTitle);
        builder.setMessage(szMsg);
        builder.setPositiveButton("Play Again", (dialog, which) -> {
            //This will start a new game when the user clicks the button.
            starting();
        });
        builder.setNegativeButton("Close", (dialog, which) -> {
            //This will simply close the pop-up.
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean CorrectField(Integer x, Integer y) {
        boolean res = true;
        EditText valueText = playingField[y-1][x-1];
        String szInput = valueText.getText().toString().trim();
        Integer iInput = -1;
        try {
            iInput = Integer.parseInt(szInput);
        }
        catch(Exception e) {

        }
        Integer test = mySudoku.matris[y - 1][x - 1];
        //if (szInput.length() <= 0 || test != Integer.parseInt(szInput)) {
        if (test != iInput) {
            if (iInput == -1) {
                szInput = "";
            }
            String szValue = szInput + "(" + Integer.toString(test) + ")";
            valueText.setTextColor(Color.RED);
            valueText.setText(szValue);
            res = false;
        }
        valueText.clearFocus();
        return res;

    }
    private void correctSquareFocused() {
        EditText selected = GetSelected();
        if (selected != null)
        {
            if (CorrectField(selectedX, selectedY) ) {
                selected.setTextColor(Color.GREEN);
            }
        }

    }
    private void reseting() {
        //mySudoku.Reset();
        //ResetField();
        SetupField(true);
    }

    private void ResetField(){
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                SetValue(x, y, true);
            }
        }
    }

    private void debugging() {
        mySudoku.ChangeLevel();
        SetupField(false);
    }

    private void lockSolution() {
        for (Integer y = 1; y <= 9; y++) {
            for (Integer x = 1; x <= 9; x++) {
                if (!mySudoku.IsCoordInMatrisPres(x, y)) {
                    EditText valueText = playingField[y-1][x-1];
                    String szValue = valueText.getText().toString().trim();
                    if (1 == szValue.length()) {
                        valueText.setTextColor(Color.MAGENTA);
                    }
                    valueText.clearFocus();
                }
            }
        }

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item is selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos).
            int ibreak = 4;
            String text = parent.getItemAtPosition(pos).toString();
            mySudoku.SetLevel(text);
            String tmp = String.format("Sätt nivå: %s", text);
            //TextView tmpText = (TextView) findViewById(R.id.editTextTestInput);
            tmpText.setText(tmp);

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback.
        }
    }

}