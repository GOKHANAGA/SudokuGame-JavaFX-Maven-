/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gokhan
 */
public class SudokuBoardTest {

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private SudokuBoard sudokuBoard1;
    private BacktrackingSudokuSolver btSolver = new BacktrackingSudokuSolver();
    private Client client = new Client();

    public SudokuBoardTest() {

    }

    /**
     * Test of fillBoard method, of class SudokuBoard.
     *
     */
    @Test
    public void testFillBoard() {
        sudokuBoard.initialList();
        System.out.println("Test Is Here");
        btSolver.fillBoard(sudokuBoard);
        System.out.println("--------------Elements--------------------");

        System.out.println("--------------Verifying--------------------");
        System.out.println(sudokuBoard.getRow(0));

        client.saveBoard(sudokuBoard);
        sudokuBoard1 = client.loadBoard();
        System.out.println(sudokuBoard1.getRow(0));
    }

    /**
     * Test of getRow method, of class SudokuBoard.
     */
}
