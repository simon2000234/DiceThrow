package com.example.dicethrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    private DiceAdapter da;
    private Button btn;
    private DiceHistory diceHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        diceHistory = (DiceHistory) getIntent().getSerializableExtra("history");
        listView = findViewById(R.id.cellList);
        da = new DiceAdapter(this, R.layout.cell, diceHistory.getArray());
        listView.setAdapter(da);
        btn = findViewById(R.id.clearDetailHistory);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });


    }


    private void clear()
    {
        //getIntent().removeExtra("history");
        //diceHistory.getArray().clear();
        //listView.setAdapter(null);
        Intent data = new Intent();
        setResult(RESULT_OK, data);
        finish();
    }
}
