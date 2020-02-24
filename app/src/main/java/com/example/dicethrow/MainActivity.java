package com.example.dicethrow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rnd;
    private Button throwDiceBtn;
    private Spinner input;
    private LinearLayout diceRow;
    private Button historyBtn;
    private final String shKey = "dab";
    private final String sdKey = "chad";
    private DiceHistory savedHistory;
    private int[] savedDiceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rnd = new Random(42069);
        input = findViewById(R.id.inputNumberOfDice);
        historyBtn = findViewById(R.id.openHistoryBtn);
        diceRow = findViewById(R.id.diceRow);
        throwDiceBtn = findViewById(R.id.calDice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dice_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input.setAdapter(adapter);
        throwDiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDiceHistory(throwAllDice(Integer.parseInt(input.getSelectedItem().toString())));
            }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHistory();
            }
        });
        if(savedInstanceState != null)
        {
            if(savedInstanceState.getIntArray(sdKey) != null)
            {
                createDiceView(savedInstanceState.getIntArray(sdKey));
            }
            if(savedInstanceState.getSerializable(shKey) != null)
            {
                savedHistory = (DiceHistory) savedInstanceState.getSerializable(shKey);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle state)
    {
        super.onSaveInstanceState(state);
        state.putIntArray(sdKey, savedDiceview);
        state.putSerializable(shKey, savedHistory);
    }

    private void createDiceView(int diceNumber[])
    {
        for (int i = 0; i < diceNumber.length; i++) {
            ImageView view = new ImageView(this);
            switch (diceNumber[i])
            {
                case 1:
                    view.setImageResource(R.drawable.en);
                    break;
                case 2:
                    view.setImageResource(R.drawable.to);
                    break;
                case 3:
                    view.setImageResource(R.drawable.tre);
                    break;
                case 4:
                    view.setImageResource(R.drawable.fire);
                    break;
                case 5:
                    view.setImageResource(R.drawable.fem);
                    break;
                case 6:
                    view.setImageResource(R.drawable.seks);
                    break;
            }
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            diceRow.addView(view);
            view.getLayoutParams().height = 150;
            view.getLayoutParams().width = 150;
        }

        //view.setText(diceNumber);
        savedDiceview = diceNumber;
    }

    private int[] throwAllDice(int numberOfDice)
    {
        diceRow.removeAllViews();
        //String diceView = "";
        int[] currentResult = new int[numberOfDice];
        for (int i = 0; i < numberOfDice; i++)
        {
            currentResult[i] = diceThrow(rnd);
            //diceView = diceView + " | " + currentResult[i];
        }
        //diceView = diceView + " |" ;
        //createDiceView(diceView);
        createDiceView(currentResult);
        return currentResult;
    }

    private void clearHistory()
    {
        diceRow.removeAllViews();
        savedHistory = null;
        savedDiceview = null;
    }

    private void setDiceHistory(int[] currentResult)
    {
        if(savedHistory == null)
        {
            savedHistory = new DiceHistory();
        }
        savedHistory.getArray().add(new DiceCup(currentResult, new Date()));
    }

    private int diceThrow(Random rand)
    {
        return rand.nextInt(6) + 1;
    }

    private void goToHistory()
    {
        Log.d("dab", "go to history is called");
        if(savedDiceview != null && savedHistory != null)
        {
            Intent historyIntent = new Intent();
            historyIntent.putExtra("history", savedHistory);
            historyIntent.setClass(this, HistoryActivity.class);
            startActivityForResult(historyIntent, 2);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 2)
        {
            switch (resultCode)
            {
                case RESULT_OK:
                    clearHistory();
                    break;
            }
        }
    }
}
