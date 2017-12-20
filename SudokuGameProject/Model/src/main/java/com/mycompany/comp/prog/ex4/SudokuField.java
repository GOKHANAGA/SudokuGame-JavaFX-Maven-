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
import java.util.Objects;
/**
 *
 * @author gokhan
 */
public class SudokuField implements Comparable<SudokuField>,Cloneable,Serializable {

    private int value;

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
       return MoreObjects.toStringHelper(getClass()).add("Value", this.getValue()).toString();    
}
    

    @Override
    public int compareTo(SudokuField sudokuField) {
        return ComparisonChain.start().compare(this.getValue(), sudokuField.getValue()).result();
    }
    
    
    
    @Override
    public int hashCode(){
        return Objects.hash(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuField other = (SudokuField) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }
    
    
       @Override
    public SudokuField clone() throws CloneNotSupportedException {
        try {
            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported!!");
            return this;
        }
    }

    public SudokuField deepClone(SudokuField sudokuField) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sudokuField);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (SudokuField) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
