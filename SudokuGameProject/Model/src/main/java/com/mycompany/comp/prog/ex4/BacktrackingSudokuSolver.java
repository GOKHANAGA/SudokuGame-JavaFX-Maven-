/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

/**
 *
 * @author gokhan
 */
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class BacktrackingSudokuSolver {

    private final int MAX_CELL_VALUE = 9;
    private final int MIN_CELL_VALUE = 1;
    private final String name = "Bactracking";

    @Override
    public boolean equals(Object myObject) {
        if (myObject == null) {
            return false;
        }
        if (getClass() != myObject.getClass()) {
            return false;
        }
        final BacktrackingSudokuSolver checker = (BacktrackingSudokuSolver) myObject;
        return Objects.equal(this.name, checker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", name)
                .toString();
    }

    public boolean fillBoard(SudokuBoard gameBoard) {

        int[] freeCell = getEmptyCell(gameBoard);
        int initialRow = freeCell[0];
        int initialColumn = freeCell[1];

        if (initialRow == -1) {
            return true; // last of sudoku
        }
        for (int i = 0; i < 9; i++) {
            int temp = generateRandomNumber();
            if (gameBoard.setGameCell(initialRow, initialColumn, temp)) {
                if (fillBoard(gameBoard)) {
                    return true;
                }
                gameBoard.getBoardCell(initialRow, initialColumn).setValue(0);
            }
        }
        return false;
    }

    private int[] getEmptyCell(SudokuBoard gameBoard) {
        int[] cell = new int[2]; // 0 for row and 1 for column
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (gameBoard.getBoardCell(row, column).getValue() == 0) {
                    cell[0] = row;
                    cell[1] = column;
                    return cell;
                }
            }
        }
        cell[0] = -1;
        cell[1] = -1;
        return cell;
    }

    private boolean isTrue(int row, int col, int value, SudokuBoard gameBoard) {
        if (gameBoard.getColumn(col).verify(value) && gameBoard.getRow(row).verify(value) && gameBoard.getBox(row, col).verify(value)) {
            return true;
        }
        return false;
    }

    private int generateRandomNumber() {
        int range = (MAX_CELL_VALUE - MIN_CELL_VALUE) + 1;
        return (int) (Math.random() * range) + MIN_CELL_VALUE;
    }

}
