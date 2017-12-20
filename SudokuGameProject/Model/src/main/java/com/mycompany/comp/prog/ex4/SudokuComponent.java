/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author student
 */
public abstract class SudokuComponent {

    private ArrayList<SudokuField> componentList;

    public SudokuComponent(ArrayList<SudokuField> sudokuFields) {
        componentList = new ArrayList<SudokuField>(sudokuFields);
        for (int i = 0; i < 9; i++) {
         componentList.add(i,sudokuFields.get(i));
        }
    }


    public abstract boolean verify(int value);

}
