package com.mycompany.view;

import com.mycompany.comp.prog.ex4.BacktrackingSudokuSolver;
import com.mycompany.comp.prog.ex4.Client;
import com.mycompany.comp.prog.ex4.SudokuBoard;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class FXMLController implements Initializable {

    private ResourceBundle bundle;
    private Locale locale;
    private final Locale locale2 = new Locale("en");
    @FXML
    private Label label;
    @FXML
    private Menu file;
    @FXML
    private Menu newGame;
    @FXML
    private MenuItem easyGame;
    @FXML
    private MenuItem mediumGame;
    @FXML
    private MenuItem hardGame;
    @FXML
    private MenuItem saveGame;
    @FXML
    private MenuItem loadGame;
    @FXML
    private MenuItem close;
    @FXML
    private Menu edit;
    @FXML
    private Menu help;
    @FXML
    private Menu language;
    @FXML
    private MenuItem english;
    @FXML
    private MenuItem turkish;
    @FXML
    private Button check;

    public ArrayList<Integer> helpToCheck = new ArrayList<>();

    @FXML
    private GridPane gridPane;

    Region content;

    @FXML
    private void localeTryingTR(ActionEvent event) {
        loadLang("tr");
    }

    @FXML
    private void localeTryingEN(ActionEvent event) {
        loadLang("en");
    }

    @FXML
    private void checkGameBoardButtonAction(ActionEvent event) {

        int counter = 0;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextArea) {
                if (((TextArea) node).getText().equals("")) {
                    counter++;
                    continue;
                } else {
                    if (((TextArea) node).getText().equals(String.valueOf(helpToCheck.get(counter)))) { // if entered value is true then paint with green
                        content = (Region) ((TextArea) node).lookup(".content");
                        content.setStyle("-fx-background-color:greenyellow;");
                    } else {
                        content = (Region) ((TextArea) node).lookup(".content"); // if value not true then paint it wih red(salmon)
                        content.setStyle("-fx-background-color:salmon;");
                    }
                }
            }
            counter++;
        }
    }

    @FXML
    public void levelButtonAction(ActionEvent event) {
        String idCheck = "";
        LevelControlEnum controlEnum;
        SudokuBoard sudokuBoard = new SudokuBoard();
        ArrayList<Integer> trying = new ArrayList<>();
        clearTheBoard();

        sudokuBoard.initialList();

        BacktrackingSudokuSolver btSolver = new BacktrackingSudokuSolver();
        btSolver.fillBoard(sudokuBoard);

        clearTheHelper(sudokuBoard);
        if (event.getTarget() instanceof MenuItem) {
            idCheck = event.getTarget().toString().substring(12, 20);
        }
        if (idCheck.equals("hardGame")) {
            controlEnum = LevelControlEnum.HARD;
        } else if (idCheck.equals("mediumGa")) {
            controlEnum = LevelControlEnum.MEDIUM;
        } else {
            controlEnum = LevelControlEnum.EASY;
        }

        controlEnum.createEmptyFields(sudokuBoard);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                trying.add(sudokuBoard.getBoardCell(i, j).getValue());
            }
        }

        fillTheGrid(trying);
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
        ArrayList<Integer> boardElements = new ArrayList<>();
        Client client = new Client();
        int counter = 0;
        SudokuBoard savingBoard = new SudokuBoard();
        savingBoard.initialList();

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextArea) {
                if (((TextArea) node).getText().equals("")) {
                    boardElements.add(0);
                } else {
                    boardElements.add(Integer.parseInt(((TextArea) node).getText()));
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                savingBoard.getRow(i).getRowField().get(j).setValue(boardElements.get(counter));
                counter++;
            }
        }
        System.out.println(boardElements.toString());
        System.out.println(savingBoard);
        client.saveBoard(savingBoard);
        client.saveList(this.helpToCheck);
    }

    @FXML
    private void loadButtonAction(ActionEvent event) {
        Client client = new Client();
        SudokuBoard loadedBoard = client.loadBoard();
        ArrayList<Integer> trying = new ArrayList<>();
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        clearTheHelper(client.loadList());
        clearTheBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                trying.add(loadedBoard.getBoardCell(i, j).getValue());
            }
        }

        fillTheGrid(trying);
    }

    @FXML
    private void closeStageAction(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    public void newGameDialog(ActionEvent event) {
        ButtonType yes;
        ButtonType no;
        Alert alert;
        if (locale == null) {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale2);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.newDialog"), yes, no);
            alert.showAndWait();
        } else {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.newDialog"), yes, no);
            alert.showAndWait();
        }
        String idCheck = "";

        if (alert.getResult() == yes) {
            if (event.getTarget() instanceof MenuItem) {
                idCheck = event.getTarget().toString().substring(12, 20);
            }
            if (idCheck.equals("hardGame")) {
                levelButtonAction(event);
            } else if (idCheck.equals("mediumGa")) {
                levelButtonAction(event);
            } else {
                levelButtonAction(event);
            }
        } else {
            alert.close();
        }
    }

    @FXML
    public void saveDialog(ActionEvent event) {
        ButtonType yes;
        ButtonType no;
        Alert alert;
        if (locale == null) {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale2);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.saveDialog"), yes, no);
            alert.showAndWait();
        } else {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.saveDialog"), yes, no);
            alert.showAndWait();
        }

        if (alert.getResult() == yes) {
            saveButtonAction(event);
        } else {
            alert.close();
        }
    }

    @FXML
    public void loadDialog(ActionEvent event) {
        ButtonType yes;
        ButtonType no;
        Alert alert;
        if (locale == null) {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale2);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.loadDialog"), yes, no);
            alert.showAndWait();
        } else {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.loadDialog"), yes, no);
            alert.showAndWait();
        }

        if (alert.getResult() == yes) {
            loadButtonAction(event);
        } else {
            alert.close();
        }
    }

    @FXML
    public void closeBoardDialog(ActionEvent event) {
        Alert alert;
        ButtonType yes;
        ButtonType no;
        if (locale == null) {
            bundle = ResourceBundle.getBundle("propreties.Lang", locale2);
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.closeDialog"), yes, no);

            alert.showAndWait();
        } else {
            yes = new ButtonType(bundle.getString("button.yes"), ButtonBar.ButtonData.YES);
            no = new ButtonType(bundle.getString("button.no"), ButtonBar.ButtonData.NO);
            bundle = ResourceBundle.getBundle("propreties.Lang", locale);
            alert = new Alert(Alert.AlertType.NONE, bundle.getString("button.closeDialog"), yes, no);
            alert.showAndWait();
        }

        if (alert.getResult() == yes) {
            closeStageAction(event);
        } else {
            alert.close();
        }
    }

    public void clearTheBoard() {
        //clear the board
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextArea) {
                content = (Region) ((TextArea) node).lookup(".content");
                content.setStyle("-fx-background-color:black;");

            }
        }
    }

    public void clearTheHelper(SudokuBoard loadedBoard) {
        int counter2 = 0;
        if (helpToCheck.isEmpty()) {
            for (int i = 0; i < 81; i++) {
                helpToCheck.add(i, 0);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                helpToCheck.set(counter2, loadedBoard.getBoardCell(i, j).getValue());
                counter2++;
            }
        }
    }

    public void fillTheGrid(ArrayList<Integer> trying) {
        int counter = 0;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextArea) {
                if (trying.get(counter) == 0) {
                    ((TextArea) node).setText("");
                } else {
                    ((TextArea) node).setText(String.valueOf(trying.get(counter)));

                }
                counter++;
            }
        }
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("propreties.Lang", locale);
        label.setText(bundle.getString("button.english"));
        file.setText(bundle.getString("button.file"));
        newGame.setText(bundle.getString("button.newGame"));
        easyGame.setText(bundle.getString("button.easy"));
        mediumGame.setText(bundle.getString("button.medium"));
        hardGame.setText(bundle.getString("button.hard"));
        saveGame.setText(bundle.getString("button.save"));
        loadGame.setText(bundle.getString("button.load"));
        close.setText(bundle.getString("button.close"));
        edit.setText(bundle.getString("button.edit"));
        help.setText(bundle.getString("button.help"));
        language.setText(bundle.getString("button.language"));
        english.setText(bundle.getString("button.english"));
        turkish.setText(bundle.getString("button.turkish"));
        check.setText(bundle.getString("button.check"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
