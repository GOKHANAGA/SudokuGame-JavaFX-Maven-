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
public class SudokuBox extends SudokuComponent implements Comparable<SudokuBox>, Cloneable, Serializable {

    private final ArrayList<SudokuField> boxField = new ArrayList<>(9);

    public SudokuBox(ArrayList<SudokuField> sudokuFields) {
        super(sudokuFields);
        for (int i = 0; i < 9; i++) {
            boxField.add(i, sudokuFields.get(i));
        }
    }

    @Override
    public boolean verify(int value) {
        {
        ArrayList<SudokuField> copyList = new ArrayList<>(9);
        copyList.addAll(boxField);

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ( copyList.get(i).getValue() != 0 && copyList.get(i).getValue() == value ) {
                    return false;
                }
            }
        }
        return true;
    }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("ArrayValues", getBoxField()).toString();
    }

    @Override
    public int compareTo(SudokuBox sudokuBox) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result = ComparisonChain.start().compare(getBoxField().get(i).getValue(), sudokuBox.getBoxField().get(i).getValue()).result();
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    /**
     * @return the boxField
     */
    public ArrayList<SudokuField> getBoxField() {
        return boxField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxField);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBox other = (SudokuBox) obj;
        if (!Objects.equals(this.boxField, other.boxField)) {
            return false;
        }
        return true;
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {
        try {
            return (SudokuBox) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported!!");
            return this;
        }
    }

    public SudokuBox deepClone(SudokuBox sudokuBox) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sudokuBox);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (SudokuBox) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
