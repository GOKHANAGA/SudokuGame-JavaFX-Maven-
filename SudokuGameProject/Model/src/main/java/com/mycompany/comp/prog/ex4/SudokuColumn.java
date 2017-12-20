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
public class SudokuColumn extends SudokuComponent implements Comparable<SudokuColumn>, Cloneable, Serializable {

    private ArrayList<SudokuField> columnField = new ArrayList<>(9);

    public SudokuColumn(ArrayList<SudokuField> sudokuFields) {
        super(sudokuFields);
        for (int i = 0; i < 9; i++) {
            columnField.add(i, sudokuFields.get(i));
        }
    }

    @Override
    public boolean verify(int value) {
        {
            ArrayList<SudokuField> copyList = new ArrayList<SudokuField>(9);
            copyList.addAll(columnField);

            for (int i = 0; i < 8; i++) {
                if (copyList.get(i).getValue() != 0 && copyList.get(i).getValue() == value) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * @return the columnField
     */
    public ArrayList<SudokuField> getColumnField() {
        return columnField;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("ArrayValues", columnField).toString();
    }

    @Override
    public int compareTo(SudokuColumn sudokuColumn) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result = ComparisonChain.start().compare(columnField.get(i).getValue(), sudokuColumn.getColumnField().get(i).getValue()).result();
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnField);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuColumn other = (SudokuColumn) obj;
        if (!Objects.equals(this.columnField, other.columnField)) {
            return false;
        }
        return true;
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {
        try {
            return (SudokuColumn) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported!!");
            return this;
        }
    }

    public SudokuColumn deepClone(SudokuColumn sudokuColumn) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sudokuColumn);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (SudokuColumn) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/*
 private void initialArray() {
 for (SudokuField colComponents : columnField) {
 colComponents = new SudokuField();
 }
 }
 */
