package com.example.dicethrow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class DiceAdapter extends ArrayAdapter<DiceCup> {

    private ArrayList<DiceCup> diceCups;
    public DiceAdapter(Context context, int textViewResourceId, ArrayList<DiceCup> diceCups) {
        super(context, textViewResourceId, diceCups);
        this.diceCups = diceCups;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.cell, null);
        }

        DiceCup diceCup = diceCups.get(position);

        TextView x = convertView.findViewById(R.id.dateText);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        x.setText(dateFormat.format(diceCup.getTimestamp()));

        Log.d("dab", "Posstion = " + position);
        LinearLayout layout = convertView.findViewById(R.id.layout);
        layout.removeAllViews();
        for (int dice: diceCup.getResult()) {
            ImageView view = new ImageView(getContext());
            switch (dice)
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
            layout.addView(view);
            view.getLayoutParams().height = 100;
            view.getLayoutParams().width = 100;
        }
        return convertView;
    }
}
