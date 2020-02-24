package com.example.dicethrow;

import java.io.Serializable;
import java.util.ArrayList;

public class DiceHistory implements Serializable {
    private ArrayList<DiceCup> Array;

    public DiceHistory() {
        Array = new ArrayList<>();
    }

    public ArrayList<DiceCup> getArray() {
        return Array;
    }
}
