package com.example.mysudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sudoku {

    public Integer[][] matris = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}
    };
    public String facit = "";
    public Integer[][] matrisPresentation = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}
    };
    public String szPres = "";

    private Integer cRows = 9;
    private Integer cCols = 9;
    private Integer cSqX = 3;
    private Integer cSqY = 3;
    private Integer cSquares = 9;

    private Integer GetRandomValue(Integer[] left) {
        if (left.length <= 0) {
            return 0;
        } else {
            int rnd = (int)(Math.random() * (left.length));
            return left[rnd];
        }
    }

    private int GetSquare(Integer x, Integer y) {
        Integer square = 0;
        Integer sqX = 0;
        switch (x) {
            case 1: case 2: case 3:
                sqX = 1;
                break;
            case 4: case 5: case 6:
                sqX = 2;
                break;
            case 7: case 8: case 9:
                sqX = 3;
                break;
        }
        Integer sqY = 0;
        switch (y) {
            case 1: case 2: case 3:
                sqY = 1;
                break;
            case 4: case 5: case 6:
                sqY = 2;
                break;
            case 7: case 8: case 9:
                sqY = 3;
                break;
        }

        switch (sqY) {
            case 1:
                square = sqX;
                break;
            case 2:
                square = sqX + 3;
                break;
            case 3:
                square = sqX + 6;
                break;
        }
        return square;
    }

    private Integer[] GetLeft(Integer[] used) {
        List<Integer> left = new ArrayList<Integer>();
        for (Integer i = 1; i < 10; i++) {
            left.add(i);
        }
        for (Integer u : used) {
            left.remove(u);
        }
        Integer[] simpleArray = new Integer[left.size()];
        left.toArray(simpleArray);
        return simpleArray;
    }

    private Integer[] GetUsed(Integer xRow, Integer yCol) {
        List<Integer> used = new ArrayList<Integer>();
        for (Integer x = 0; x < cRows; x++) {
            Integer v = matris[yCol - 1][x];
            if (v > 0) {
                if (Collections.frequency(used, v) <= 0) {
                    used.add(v);
                }
            }
        }
        for (Integer y = 0; y < cCols; y++) {

            Integer v = matris[y][xRow - 1];
            if (v > 0) {
                if (Collections.frequency(used, v) <= 0) {
                    used.add(v);
                }
            }
        }
        // GetUsed in Square
        // Get square
        Integer square = GetSquare(xRow, yCol);

        Integer sqXmin = 0;
        Integer sqYmin = 0;
        switch (square) {
            case 1:
                sqXmin = 1;
                sqYmin = 1;
                break;
            case 2:
                sqXmin = 4;
                sqYmin = 1;
                break;
            case 3:
                sqXmin = 7;
                sqYmin = 1;
                break;
            case 4:
                sqXmin = 1;
                sqYmin = 4;
                break;
            case 5:
                sqXmin = 4;
                sqYmin = 4;
                break;
            case 6:
                sqXmin = 7;
                sqYmin = 4;
                break;
            case 7:
                sqXmin = 1;
                sqYmin = 7;
                break;
            case 8:
                sqXmin = 4;
                sqYmin = 7;
                break;
            case 9:
                sqXmin = 7;
                sqYmin = 7;
                break;
        }
        for (Integer y = sqYmin - 1; y < sqYmin + 2; y++) {
            for (Integer x = sqXmin - 1; x < sqXmin + 2; x++) {

                Integer v = matris[y][x];

                if ((v > 0) && (Collections.frequency(used, v) <= 0)) {
                    used.add(v);
                }
            }
        }
        Integer[] simpleArray = new Integer[used.size()];
        used.toArray(simpleArray);
        return simpleArray;
    }

    public String CreateSudoku() {

        Integer y = 1;
        while (y < 10) {
            Integer x = 1;
            Integer iErrorCnt = 0;
            while (x < 10) {
                Integer[] used = GetUsed(x, y);
                Integer[] left = GetLeft(used);
                Integer value = GetRandomValue(left);
                if (0 == value) {
                    Integer ibreak = 2;
                    x = x - 1;
                    iErrorCnt = iErrorCnt + 1;
                } else {
                    matris[y - 1][x - 1] = value;
                    x = x + 1;
                }
                if (iErrorCnt > 2) {
                    break;
                }
            }
            if (iErrorCnt <= 0) {
                y = y + 1;
            } else {
                Integer xTmp = 1;
                while (xTmp < 10) {
                    matris[y - 1][xTmp - 1] = -1;
                    xTmp = xTmp + 1;
                }
            }
        }

        String szString = "";
        for (y = 1; y < cCols + 1; y++) {
            String tmpString = "";
            for (Integer x = 1; x < cRows + 1; x++){
                Integer value = matris[y - 1][x - 1];
                String szValue = "";
                if (x == cRows)
                    szValue = Integer.toString(value);
                else
                    szValue = Integer.toString(value) + ",";
                tmpString = tmpString + szValue;
            }
            //print(tmpString);
            if (y < cCols)
                tmpString = tmpString + ";";
            szString = szString + tmpString;
        }

        return szString;
    }

    private String SetPresentation()
    {
        for (Integer y = 1; y <= cCols; y++) {
            for(Integer x = 1; x <= cRows; x++)  {
                Integer value = matris[y-1][x-1];
                int rnd = (int)(Math.random() * 2);
                if (rnd==0)
                    value = 0;
                matrisPresentation[y-1][x-1] = value;
            }
        }
        String szString = "";
        for (Integer y = 1; y <=  cCols; y++) {
            String tmpString = "";
            for (Integer x = 1; x <= cRows; x++){
                Integer value = matrisPresentation[y - 1][x - 1];
                String szValue = "";
                if (x == cRows)
                    szValue = Integer.toString(value);
                else
                    szValue = Integer.toString(value) + ",";
                tmpString = tmpString + szValue;
            }
            //print(tmpString);
            if (y < cCols)
                tmpString = tmpString + ";";
            szString = szString + tmpString;
        }

        return szString;
    }

    public String GetPresentation() {
        facit = CreateSudoku();
        szPres = SetPresentation();
        return szPres;
    }

    public void Reset() {
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                matris[y-1][x-1] = -1;
            }
        }
    }

}
