/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author gokhan
 */
public class SudokuBoard implements Comparable<SudokuBoard>, Cloneable, Serializable {

    private final ArrayList<ArrayList<SudokuField>> gameBoardList = new ArrayList<>(9);

    //Initialize SudokuBoard Objects so they will not be null....
    public void initialList() {
        for (int j = 0; j < 9; j++) {

            gameBoardList.add(columnCreater());
        }
    }

    private boolean checkBoard(int row, int column, int value) {

        return getColumn(column).verify(value) && getRow(row).verify(value) && getBox(row, column).verify(value);
    }

    public SudokuField getBoardCell(int row, int col) {
        return gameBoardList.get(row).get(col);
    }

    //Component Getters Starting Here..............................................................
    public SudokuRow getRow(int rowNum) {
        ArrayList<SudokuField> sudokuFields = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            sudokuFields.add(i, gameBoardList.get(rowNum).get(i));
        }

        SudokuRow sudokuRow = new SudokuRow(sudokuFields); // put array of fields here

        return sudokuRow;
    }

    public SudokuColumn getColumn(int colNum) {
        ArrayList<SudokuField> sudokuFields = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            sudokuFields.add(i, gameBoardList.get(i).get(colNum));
        }
        SudokuColumn sudokuColumn = new SudokuColumn(sudokuFields); // put array of fields here

        return sudokuColumn;
    }

    public boolean setGameCell(int row, int col, int value) {
        if (checkBoard(row, col, value)) {
            gameBoardList.get(row).get(col).setValue(value);
            return true;
        }
        return false;
    }

    public SudokuBox getBox(int rowNum, int colNum) {
        ArrayList<SudokuField> sudokuFields = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            sudokuFields.add(new SudokuField());
        }
        int x1 = 3 * (rowNum / 3); //check the little 3X3 box
        int y1 = 3 * (colNum / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int i = 0; i < sudokuFields.size(); i++) {
                    if (sudokuFields.get(i).getValue() == 0) {
                        sudokuFields.add(i, gameBoardList.get(x).get(y));
                        break;
                    }
                }
            }
        }

        SudokuBox sudokuBox = new SudokuBox(sudokuFields); // put array of fields here

        return sudokuBox;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("BoardValues", gameBoardList).toString();
    }

    @Override
    public int compareTo(SudokuBoard sudokuBoard) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result = ComparisonChain.start().compare(getColumn(i).getColumnField().get(j).getValue(), sudokuBoard.getColumn(i).getColumnField().get(j).getValue()).result();
            }
            if (result != 0) {
                break;
            }
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.gameBoardList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        if (!Objects.equals(this.gameBoardList, other.gameBoardList)) {
            return false;
        }
        return true;
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        try {
            return (SudokuBoard) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported!!");
            return this;
        }
    }

    public SudokuBoard deepClone(SudokuBoard sudokuBoard) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sudokuBoard);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (SudokuBoard) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<SudokuField> columnCreater() {
        ArrayList<SudokuField> columnList = new ArrayList<>(9);

        for (int i = 0; i < 9; i++) {
            columnList.add(new SudokuField());
        }

        return columnList;
    }

}
