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
public class SudokuRow extends SudokuComponent implements Comparable<SudokuRow>,Cloneable,Serializable{

    private ArrayList<SudokuField> rowField = new ArrayList<>(9);

    public SudokuRow(ArrayList<SudokuField> sudokuFields) {
        super(sudokuFields);
        for (int i = 0; i < 9; i++) {
            rowField.add(new SudokuField());
        }

        for (int i = 0; i < 9; i++) {
            rowField.set(i, sudokuFields.get(i));
        }
    }

    @Override
    public boolean verify(int value) {
        {
        ArrayList<SudokuField> copyList = new ArrayList<SudokuField>(9);
        copyList.addAll(rowField);

        for (int i = 0; i < 9; i++) {
                if ( copyList.get(i).getValue() != 0 && copyList.get(i).getValue() == value ) {
                    return false;
            }
        }
        return true;
    }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("ArrayValues", getRowField()).toString();
    }

    @Override
    public int compareTo(SudokuRow sudokuRow) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result = ComparisonChain.start().compare(getRowField().get(i).getValue(), sudokuRow.getRowField().get(i).getValue()).result();
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    /**
     * @return the rowField
     */
    public ArrayList<SudokuField> getRowField() {
        return rowField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowField);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuRow other = (SudokuRow) obj;
        if (!Objects.equals(this.rowField, other.rowField)) {
            return false;
        }
        return true;
    }
    
       @Override
    public SudokuRow clone() throws CloneNotSupportedException {
        try {
            return (SudokuRow) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported!!");
            return this;
        }
    }

    public SudokuRow deepClone(SudokuRow sudokuRow) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sudokuRow);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (SudokuRow) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
