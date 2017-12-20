/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.view;

import com.mycompany.comp.prog.ex4.SudokuBoard;

/**
 *
 * @author gokhan
 */
public enum LevelControlEnum {

    EASY(25), MEDIUM(45), HARD(60);

    private int CellNoToClear;
    private final int MAX_SUDOKU_VALUE = 9;
    private final int MIN_SUDOKU_VALUE = 1;
    private final int EMPTY_VALUE = 0;

    LevelControlEnum(int CellNoToClear) {
        this.CellNoToClear = CellNoToClear;
    }

    public int getCellNoToClear() {
        return CellNoToClear;
    }

    public SudokuBoard createEmptyFields(SudokuBoard board) {
        int counter = 0;
        int row, col;

        while (counter < CellNoToClear) {
            row = generateRandomNumber() - 1;
            col = generateRandomNumber() - 1;
            if (board.getBoardCell(row, col).getValue() != EMPTY_VALUE) {
                board.setGameCell(row, col, 0);
                counter++;
            }
        }
        return board;
    }

    private int generateRandomNumber() {
        int range = (MAX_SUDOKU_VALUE - MIN_SUDOKU_VALUE) + 1;
        return (int) (Math.random() * range) + MIN_SUDOKU_VALUE;
    }

}
