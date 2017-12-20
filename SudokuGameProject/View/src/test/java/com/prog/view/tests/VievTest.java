/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prog.view.tests;

import com.mycompany.comp.prog.ex4.BacktrackingSudokuSolver;
import com.mycompany.comp.prog.ex4.SudokuBoard;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gokhan
 */
public class VievTest {


    SudokuBoard sudokuBoard = new SudokuBoard();
    BacktrackingSudokuSolver btSolver = new BacktrackingSudokuSolver();
    ArrayList<Integer> trying = new ArrayList<>();

    public VievTest() {

    }

    @Test
    public void testViewProject() {
        sudokuBoard.initialList();
        btSolver.fillBoard(sudokuBoard);
        System.out.println("---------------Before Empty Cell--------------");
        System.out.println(sudokuBoard.getRow(0));
        System.out.println();
        System.out.println("---------------After Empty Cell--------------");

        System.out.println(sudokuBoard.getRow(0));
        System.out.println(sudokuBoard.getRow(1));
        System.out.println(sudokuBoard.getBoardCell(0, 0));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                trying.add(sudokuBoard.getBoardCell(i, j).getValue());
            }
        }

        for (int i = 0; i < 9; i++) {
            System.out.println(trying.get(i));
        }
        for (int i = 9; i < 18; i++) {
            System.out.println(trying.get(i));
        }

    }
}
