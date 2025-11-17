package com.example.mysudoku;

import static java.lang.System.in;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sudoku {

    public Integer[][] matris = GetEmptyMatris();
    public String facit = "";
    public Integer[][] matrisPresentation = GetEmptyMatris();
    public CellInformation[][] playingField = {
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
    public Integer[][] solution = GetEmptyMatris();
    public Integer[][] testMatris = GetEmptyMatris();

    public Integer[][] debugMatris = GetEmptyMatris();

    private Integer myLevel = 2;
    public String szPres = "";

    private String szPresEasy = "";
    private String szPresMedium = "";
    private String szPresHard = "";


    private Integer cRows = 9;
    private Integer cCols = 9;
    private Integer cSqX = 3;
    private Integer cSqY = 3;
    private Integer cSquares = 9;

    private Integer N = 9;
    private Integer BOX = 3;

    public Integer GetMyLevel() {
        if (myLevel < 1) myLevel = 1;
        if (myLevel > 3) myLevel = 3;
        return myLevel;
    }
    private Integer[][] GetEmptyMatris() {
        Integer[][] tmp = {
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
        return tmp;
    };
    private Integer GetRandomValue(Integer[] left) {
        if (left.length <= 0) {
            return 0;
        } else {
            int rnd = (int)(Math.random() * (left.length));
            return left[rnd];
        }
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

    private Integer[] GetUsed(Integer xRow, Integer yCol, Integer[][] tmpMatris) {
        List<Integer> used = new ArrayList<Integer>();
        for (Integer x = 0; x < cRows; x++) {
            Integer v = tmpMatris[yCol - 1][x];
            if (v > 0) {
                if (Collections.frequency(used, v) <= 0) {
                    used.add(v);
                }
            }
        }
        for (Integer y = 0; y < cCols; y++) {

            Integer v = tmpMatris[y][xRow - 1];
            if (v > 0) {
                if (Collections.frequency(used, v) <= 0) {
                    used.add(v);
                }
            }
        }
        // GetUsed in Square
        Integer sqYmin = (BOX * ((yCol - 1) / BOX)) + 1;
        Integer sqXmin = (BOX * ((xRow - 1) / BOX)) + 1;
        for (Integer y = sqYmin - 1; y < sqYmin + 2; y++) {
            for (Integer x = sqXmin - 1; x < sqXmin + 2; x++) {

                Integer v = tmpMatris[y][x];

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
        Integer yErrorCnt = 0;
        while (y < 10) {
            Integer x = 1;
            Integer xErrorCnt = 0;
            while (x < 10) {
                Integer[] used = GetUsed(x, y, matris);
                Integer[] left = GetLeft(used);
                Integer value = GetRandomValue(left);
                if (0 == value) {
                    Integer ibreak = 2;
                    x = x - 1;
                    xErrorCnt = xErrorCnt + 1;
                } else {
                    matris[y - 1][x - 1] = value;
                    x = x + 1;
                }
                if (xErrorCnt > 2) {
                    break;
                }
            }
            if (xErrorCnt <= 0) {
                yErrorCnt = 0;
                y = y + 1;
            } else {
                yErrorCnt = yErrorCnt + 1;
                Integer xTmp = 1;
                while (xTmp < 10) {
                    matris[y - 1][xTmp - 1] = -1;
                    xTmp = xTmp + 1;
                }
            }
            if (yErrorCnt > 9) {
                yErrorCnt = 0;
                if (y > 1) {
                    y = y - 1;
                }
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

    private String MatrisPresentation() {

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

    public Integer SetLevel(String szLevel){
        Integer level = 2;
        if (szLevel.equals("Lätt")) {
            level = 1;
        }
        else if (szLevel.equals("Medel")) {
            level = 2;
        }
        else if (szLevel.equals("Svår")) {
            level = 3;
        }
        myLevel = level;
        return GetMyLevel();
    }


    public String GetPresentation() {
        facit = CreateSudoku();
        if (SetupPresentationMatris()) {
            //TBD
            //SetupDebugMatris();
            return MatrisPresentation();
        }
        return "";
    }
    private Boolean SetupPresentationMatris() {
        Integer[] cells = GetRandom(81, 81);
        matrisPresentation = CopyMatris(matris);

        for (Integer cellV : cells) {
            Integer y = (cellV / 9) + 1;
            Integer x = (cellV % 9) + 1;
            Integer tempV = matrisPresentation[y - 1][x - 1];
            matrisPresentation[y - 1][x - 1] = -1;
            testMatris = CopyMatris(matrisPresentation);
            int solutions = solve(2);
            if (solutions != 1) {
                matrisPresentation[y - 1][x - 1] = tempV;
            }
        }
        String tmpPres = MatrisPresentation();
        szPresHard = tmpPres;

        Integer[][] tmpMatrisPres =  CopyMatris(matrisPresentation);
        if (!SetPresForLevel(1, tmpPres)) return false;
        szPresEasy = MatrisPresentation();

        matrisPresentation = CopyMatris(tmpMatrisPres);
        if (!SetPresForLevel(2, tmpPres)) return false;
        szPresMedium = MatrisPresentation();

        switch (GetMyLevel()) {
            case 3: // Svår aka Hard
                matrisPresentation = CopyMatris(tmpMatrisPres);
                break;
            case 2: // Medel aka Medium
                matrisPresentation = SetupFrom(szPresMedium);
                break;
            case 1: // Lätt aka Easy
                matrisPresentation = SetupFrom(szPresEasy);
                break;
        }
        return true;

    }

    public void ChangeLevel() {
        switch (GetMyLevel()) {
            case 3: // Svår aka Hard
                if (szPresHard.length()>=81) {
                    matrisPresentation = SetupFrom(szPresHard);
                }
                break;
            case 2: // Medel aka Medium
                if (szPresMedium.length()>=81) {
                    matrisPresentation = SetupFrom(szPresMedium);
                }
                break;
            case 1: // Lätt aka Easy
                if (szPresEasy.length()>=81) {
                    matrisPresentation = SetupFrom(szPresEasy);
                }
                break;
        }
    }

    public boolean IsCoordInMatrisPres(Integer x, Integer y) {
        if (matrisPresentation[y-1][x-1] > 0) {
            return true;
        }
        return false;
    }

    private Integer[][] SetupFrom(String szPresMatris) {
        Integer y = 1;
        Integer x = 1;

        ArrayList<String> rows = new ArrayList<String>();
        for (String row : szPresMatris.split(";")) {
            rows.add(row);
        }
        for (String row : rows) {
            for (String value : row.split(",")) {
                matrisPresentation[y - 1][x - 1] = Integer.parseInt(value);
                x = x + 1;
                if (x > 9) {
                    x = 1;
                    y = y + 1;
                }
            }
        }
        return CopyMatris(matrisPresentation);
    }

    private Boolean SetPresForLevel(Integer inLevel, String tmpPres) {
        Boolean result = true;
        if (inLevel < 3) {
            Integer emptyCells = GetNumberOfEmpties(matrisPresentation);
            //Show 20 random numbers extra into matrisPresentation if level "lätt"(1)
            //Show 10 random numbers extra into matrisPresentation if level "medel"(2)
            Boolean left = true;
            Integer cnt = 15;
            if (inLevel == 2) {
                cnt = 10;
            }
            //If too many left in "svår" level don't add too many in easier levels...
            if (emptyCells > 33) {
                cnt = cnt - 5;
            }
            while (left) {
                Integer[] tmpCells = GetRandom(81, cnt);
                for (Integer cellV : tmpCells) {
                    Integer y = (cellV / 9) + 1;
                    Integer x = (cellV % 9) + 1;
                    Integer tempV = matrisPresentation[y - 1][x - 1];
                    if (tempV <= 0) {
                        matrisPresentation[y - 1][x - 1] = matris[y - 1][x - 1];
                        cnt = cnt - 1;
                    }
                }
                if (cnt <= 0) {
                    left = false;
                    break;
                }
            }
            String tmpPresLevel = MatrisPresentation();
            //result = !tmpPres.equals(tmpPresLevel);
            if (tmpPres.equals(tmpPresLevel)) {
                result = false;
            }
        }
        return result;
    }

    private Integer GetNumberOfEmpties(Integer[][] inMatrisPres) {
        Integer cnt = 0;
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                if (inMatrisPres[y-1][x-1] <= 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private Integer[][] CopyMatris(Integer[][] inMatris) {
        Integer[][] tmp;
        tmp = new Integer[cCols][cRows];
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                tmp[y - 1][x - 1] = inMatris[y - 1][x - 1];
            }
        }
        return tmp;
    }

    private Integer[] GetRandom(Integer Base, Integer Cnt) {
        List<Integer> randomList = new ArrayList<Integer>();
        while (randomList.size() < Cnt) {
            int rnd = (int) (Math.random() * (Base)) ;
            if (Collections.frequency(randomList, rnd) <= 0) {
                randomList.add(rnd);
            }
        }
        Integer[] simpleArray = new Integer[randomList.size()];
        randomList.toArray(simpleArray);
        return simpleArray;
    }


    private void SetupDebugMatris() {
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                debugMatris[y-1][x-1] = matrisPresentation[y-1][x -1];
            }

        }
    }

    public void Reset() {
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                matris[y-1][x-1] = -1;
            }
        }
    }

    private void SetupReduntantSolutions() {
        ClearPlayingField();
        for (Integer y = 1; y <= cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                solution[y - 1][x - 1] = matrisPresentation[y - 1][x - 1];
            }
        }
        for (Integer y = 1; y <= cCols; y++){
            for (Integer x = 1; x<= cRows; x++) {
                CellInformation cell = new CellInformation(x, y, solution[y-1][x-1]);
                cell.IsMajor = cell.satisfied;
                playingField[y-1][x-1] = cell;
            }
        }
    }

    private void ClearPlayingField() {
        for (Integer y = 1; y <= cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                playingField[y - 1][x - 1] = null;
                solution[y - 1][x - 1] = -1;
            }
        }
    }

    public void debug() {
        //Check (not ready yet)

        //Reset matrisPresentation to first cut.
        for (Integer y = 1; y <=  cCols; y++) {
            for (Integer x = 1; x <= cRows; x++) {
                matrisPresentation[y-1][x-1] = debugMatris[y-1][x -1];
            }
        }

        SetupReduntantSolutions();
        if (ReduntantSolutions()) {
            for (Integer y = 1; y <= cCols; y++) {
                for (Integer x = 1; x <= cRows; x++) {
                    debugMatris[y - 1][x - 1] = matrisPresentation[y - 1][x - 1];
                }
            }
        }

    }

    public String GetDebugCellValue(Integer x, Integer y) {
        CellInformation cell = playingField[y-1][x-1];
        if (cell != null) {
            return cell.GetDebugValue();
        }
        else
        {
            return "Nope";
        }

    }

    private void SearchForAloneNumbersRows(){
        for(Integer y = 1; y <= cCols; y++) {
            List<CellInformation> tmpCells = new ArrayList<CellInformation>();
            for(Integer x = 1; x <= cRows; x++) {
                CellInformation cell = playingField[y-1][x-1];
                tmpCells.add(cell);
            }
            for (Integer i = 1; i <= 9; i++) {
                Integer cnt = 0;
                for (CellInformation c: tmpCells) {
                    if (!c.satisfied && c.Contains(i))
                    {
                        cnt++;
                    }
                }
                if (1 == cnt) {
                    for (CellInformation c: tmpCells) {
                        if (!c.satisfied && c.Contains(i))
                        {
                            c.myValue = i;
                            c.satisfied = true;
                            c.ClearAvailable();
                        }
                    }
                }
            }
        }
    }
    private void SearchForAloneNumbersCols() {
        for(Integer x = 1; x <= cRows; x++) {
            List<CellInformation> tmpCells = new ArrayList<CellInformation>();
            for(Integer y = 1; y <= cCols; y++) {
                CellInformation cell = playingField[y-1][x-1];
                tmpCells.add(cell);
            }
            for (Integer i = 1; i <= 9; i++) {
                Integer cnt = 0;
                for (CellInformation c: tmpCells) {
                    if (!c.satisfied && c.Contains(i))
                    {
                        cnt++;
                    }
                }
                if (1 == cnt) {
                    for (CellInformation c: tmpCells) {
                        if (!c.satisfied && c.Contains(i))
                        {
                            c.myValue = i;
                            c.satisfied = true;
                            c.ClearAvailable();
                        }
                    }
                }
            }
        }
    }

    private boolean ReduntantSolutions() {
        //Setup preconditions
        boolean noSolution = true;

        Integer solutionCnt = 0;
        while (noSolution && solutionCnt < 20) {
            for (Integer y = 1; y <= cCols; y++){
                for (Integer x = 1; x<= cRows; x++) {
                    CellInformation cell = playingField[y-1][x-1];
                    cell.ClearAvailable();
                    if (!cell.satisfied) {
                        Integer[] used = GetUsed(x, y, solution);
                        if (!cell.UpdateAvailableFromUsed(used)){
                            int ibrek = cell.iError;
                        }
                        if (cell.satisfied) {
                            solution[y-1][x-1] = cell.myValue;
                        }
                    }
                }
            }
            solutionCnt++;
        }

        SearchForAloneNumbersRows();
        //Check if all fields satisfied!
        boolean allSatisfied = true;
        for (Integer y = 1; y <= cCols; y++){
            for (Integer x = 1; x<= cRows; x++) {
                CellInformation cell = playingField[y - 1][x - 1];
                if (!cell.satisfied) {
                    allSatisfied = false;
                }
            }
        }
        if (allSatisfied) {
            return false;
        }

        SearchForAloneNumbersCols();
        //Check if all fields satisfied!
        allSatisfied = true;
        for (Integer y = 1; y <= cCols; y++){
            for (Integer x = 1; x<= cRows; x++) {
                CellInformation cell = playingField[y - 1][x - 1];
                if (!cell.satisfied) {
                    allSatisfied = false;
                }
            }
        }
        if (allSatisfied) {
            return false;
        }

        //Check for not satisfied but missing equals
        List<CellInformation> tmpCells = new ArrayList<CellInformation>();
        for (Integer y = 1; y <= cCols; y++){
            for (Integer x = 1; x<= cRows; x++) {
                CellInformation cell = playingField[y - 1][x - 1];
                if (cell.HasTwoLeft()) {
                    tmpCells.add(cell);
                }
            }
        }
        for(CellInformation cell : tmpCells){
            String szTmp = cell.GetDebugValue();
            Integer x = cell.xPos;
            Integer y = cell.yPos;
        }
        if (tmpCells.size() > 0)
        {
            boolean tmpV = true;
            for(CellInformation cell : tmpCells){
                if (tmpV) {
                    Integer x = cell.xPos;
                    Integer y = cell.yPos;
                    matrisPresentation[y - 1][x - 1] = matris[y - 1][x - 1];
                    cell.satisfied = true;
                    cell.myValue = matris[y - 1][x - 1];
                    cell.ClearAvailable();

                    CellInformation tmpTest = playingField[y - 1][x - 1];
                    if (tmpTest.satisfied == false){
                        int iBreak = 101;
                    }

                }
                tmpV = !tmpV;
            }
            return true;
        }
        return true;
    }

    public boolean DebugValueMajor(Integer x, Integer y) {
        CellInformation cell = playingField[y-1][x-1];
        if (cell != null) {
            return cell.IsMajor;
        }
        else
        {
            return false;
        }

    }

// ------------------------------------------------------
//        # Steg 1: Grundläggande kontroller
// ------------------------------------------------------
    private Boolean numInRow(Integer y, Integer value){
        for (Integer x = 1; x <= cRows; x++) {
            if (testMatris[y - 1][x - 1] == value) {
                return true;
            }
        }
        return false;
    }

    private Boolean numInCol(Integer x, Integer value) {
        for (Integer y = 1; y <= cCols; y++) {
            if (testMatris[y - 1][x - 1] == value) {
                return true;
            }
        }
        return false;
    }

    //start_row, start_col = BOX * (row // BOX), BOX * (col // BOX)
    //for i in range(start_row, start_row + BOX):
    //        for j in range(start_col, start_col + BOX):
    //        if board[i][j] == num:
    //        return False
    private Boolean numInBox(Integer inX, Integer inY, Integer value) {
        try {
            Integer sqYmin = (BOX * ((inY - 1) / BOX)) + 1;
            Integer sqXmin = (BOX * ((inX - 1) / BOX)) + 1;
            for (Integer y = sqYmin; y < sqYmin + BOX; y++) {
                for (Integer x = sqXmin; x < sqXmin + BOX; x++) {
                    if (testMatris[y - 1][x - 1] == value) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean is_valid(Integer x, Integer y, Integer value) {
        try {
            //Kontrollera rad
            if (numInRow(y, value)) {
                return false;
            }
            //Kontrollera kolumn
            if (numInCol(x, value)) {
                return false;
            }
            //Kontrollera 3x3-box
            if (numInBox(x, y, value)) {
                return false;
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


// ------------------------------------------------------
//        # Steg 2: Backtracking-lösare
// ------------------------------------------------------

    private Integer solve(Integer limit) { //false, 2
        //Löser Sudoku.
        //Om count_solutions=True returneras antal lösningar (upp till 'limit')."""
        pair empty = find_empty();
        if (empty==null) {
            return 1;
        }

        Integer x = empty.getX();
        Integer y = empty.getY();
        Integer solutions = 0;
        for (Integer value = 1; value <= 9; value++) {
            if (is_valid(x, y, value)) {

                testMatris[y - 1][x - 1] = value;
                Integer result = solve(limit);
                solutions = solutions + result;
                if (solutions >= limit) {
                    break;
                }
                testMatris[y-1][x-1] = -1;
            }
        }
        testMatris[y-1][x-1] = -1;
        return solutions;
    }


    private pair find_empty() {

       for (Integer y = 1; y <= cCols; y++) {
           for (Integer x = 1; x <= cRows; x++) {
               if (testMatris[y - 1][x - 1] <= 0) {
                   return new pair(x, y);
               }
           }
       }
       return null;
    }

    public Integer getSquare(Integer x, Integer y) {
        if (y >= 4) {
            int ibrak = 1;
        }
        Integer sqY = ((y - 1) / BOX);
        Integer sqX = ((x - 1) / BOX) + 1;
        return  (sqY * 3) + sqX;
    }
}
