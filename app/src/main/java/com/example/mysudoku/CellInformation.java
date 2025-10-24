package com.example.mysudoku;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CellInformation {
    public Integer xPos = -1;
    public Integer yPos = -1;
    public Integer myValue = -1;
    public boolean satisfied = false;
    public int iError = 0;
    public List<Integer> available = new ArrayList<Integer>();

    public CellInformation(Integer x, Integer y, Integer aValue) {
        xPos = x;
        yPos = y;
        if (aValue>=1 && aValue <=9) {
            myValue = aValue;
            satisfied = true;
        }
    }

    public void AddAvailable(Integer value) {
        available.add(value);
    }

    public boolean RemoveAvailable(Integer value) {
        return  available.remove(value);
    }

    public void ClearAvailable() {
        available.clear();
    }

    public boolean HasTwoLeft() {
        return (2 == available.size());
    }
    public boolean Contains(Integer value){
        for (Integer i : available) {
            if (value == i) {
                return true;
            }
        }
        return false;
    }
    public boolean UpdateAvailableFromUsed(Integer[] used){
        available.clear();
        for (Integer i = 1; i<=9; i++){
            available.add(i);
        }
        for (Integer u : used) {
            available.remove(u);
        }
        if (available.size() == 0) {
            iError = 1;
            return false;
        }
        if (available.size() == 1) {
            myValue = available.get(0);
            satisfied = true;
            available.clear();
        }
        return true;
    }
    public String GetDebugValue(){
        String szValue = "";
        if (satisfied){
            szValue = Integer.toString(myValue);
        }
        else {
            szValue = "(";
            for( Integer i : available) {
                szValue = szValue + Integer.toString(i) + ",";
            }
            szValue = szValue + ")";
        }
        return szValue;
    }

}
