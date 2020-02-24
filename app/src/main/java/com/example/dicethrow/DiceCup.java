package com.example.dicethrow;

import java.io.Serializable;
import java.util.Date;

public class DiceCup implements Serializable {
    private int[] result;
    private Date timestamp;

    public int[] getResult() {
        return result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setResult(int[] result) {
        this.result = result;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public DiceCup(int[] result, Date timestamp) {
        this.result = result;
        this.timestamp = timestamp;
    }
}
