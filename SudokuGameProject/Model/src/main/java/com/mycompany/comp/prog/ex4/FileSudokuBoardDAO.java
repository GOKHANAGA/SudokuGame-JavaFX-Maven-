/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class FileSudokuBoardDAO implements Dao<SudokuBoard>, AutoCloseable {

    private String boardFileName;
    private String listFileName = "helperList.txt";

    public FileSudokuBoardDAO(String fileName) {
        this.boardFileName = fileName;
    }

    @Override
    public void close() throws Exception {
        System.out.print("Closing!!");
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialList();
        ArrayList<Integer> boardList = new ArrayList<>();
        int counter = 0;
        try {
            FileReader reader = new FileReader(boardFileName);
            int character;

            while ((character = reader.read()) != -1) {
                boardList.add(character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.getRow(i).getRowField().get(j).setValue(Character.getNumericValue(boardList.get(counter)));
                counter++;
            }
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getBoardFileName()))) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(String.valueOf(sudokuBoard.getRow(i).getRowField().get(j).getValue()));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    /**
     * @return the boardFileName
     */
    public String getBoardFileName() {
        return boardFileName;
    }

    /**
     * @param boardFileName the boardFileName to set
     */
    public void setBoardFileName(String boardFileName) {
        this.boardFileName = boardFileName;
    }

    //////////////////////////////////////////Read And Write for solved board for load operation////////////////////
    public void writeList(ArrayList<Integer> writingList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(getListFileName()))) {
            for (int i = 0; i < writingList.size(); i++) {
                bw.write(String.valueOf(writingList.get(i)));
            }
        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SudokuBoard readList() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.initialList();
        ArrayList<Integer> boardList = new ArrayList<>();
        int counter = 0;

        
        try {
            FileReader reader = new FileReader(getListFileName());
            int character;

            while ((character = reader.read()) != -1) {
                boardList.add(character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.getRow(i).getRowField().get(j).setValue(Character.getNumericValue(boardList.get(counter)));
                counter++;
            }
        }

        return sudokuBoard;

    }

    /**
     * @return the listFileName
     */
    public String getListFileName() {
        return listFileName;
    }

    /**
     * @param listFileName the listFileName to set
     */
    public void setListFileName(String listFileName) {
        this.listFileName = listFileName;
    }

}
