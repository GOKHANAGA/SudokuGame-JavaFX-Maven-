/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Client {

    FileSudokuBoardDAO fsDao = SudokuBoardDAOFactory.getFileDao("sudokuGame.txt");

    public void saveBoard(SudokuBoard sudokuBoard) {
        fsDao.write(sudokuBoard);
    }
    
    public SudokuBoard loadBoard(){
        return fsDao.read();
    }
    
    public void saveList(ArrayList<Integer> solvedBoard){
        fsDao.writeList(solvedBoard);
    }
    
    public SudokuBoard loadList(){
        return fsDao.readList();
    }
}
