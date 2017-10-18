package gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import logic.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GridController implements Initializable {
    private ResourceBundle bundle;
    private Locale locale = SampleController.getLocale();
    private Locale localePL = new Locale("PL");
    private Locale localeENG = new Locale("ENG");
    private SudokuBoard board;
    private String difficultyLevel = "";

    private SudokuBoard originalBoard = new SudokuBoard();

    public void setLanguage(Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle("lang", locale);
    }

    @FXML
    private ArrayList<TextField> gridList = new ArrayList();

    @FXML
    private GridPane grid;

    @FXML
    private Button checkButton, resetButton, loadButton, saveButton, PLButton, ENGButton;

    @FXML
    private Label checkResultLabel;

    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        String level = difficultyLevel;
        prepareGrid(level, new SudokuBoard());
        writeGrid(board);
        checkResultLabel.setText(bundle.getString("gameReset"));
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            factory.getFileDao("SudokuFile.txt").write(board);
            checkResultLabel.setText(bundle.getString("gameSaved"));
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            this.board = (SudokuBoard) factory.getFileDao("SudokuFile.txt").read();
            writeGrid(board);
            checkResultLabel.setText(bundle.getString("gameLoaded"));

        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
    }

    @FXML
    private void handleCheckButtonAction(ActionEvent event) {
        if (checkBoard()) {
            checkResultLabel.setText(bundle.getString("correct"));
        } else {
            checkResultLabel.setText(bundle.getString("wrong"));
        }
    }

    @FXML
    private void handleENGButtonAction(ActionEvent event) {
        setLanguage(localeENG);
        checkButton.setText(bundle.getString("checkButton"));
        resetButton.setText(bundle.getString("resetButton"));
        saveButton.setText(bundle.getString("save"));
        loadButton.setText(bundle.getString("load"));
        checkResultLabel.setText(" ");

    }

    @FXML
    private void handlePLButtonAction(ActionEvent event) {
        setLanguage(localePL);
        checkButton.setText(bundle.getString("checkButton"));
        resetButton.setText(bundle.getString("resetButton"));
        saveButton.setText(bundle.getString("save"));
        loadButton.setText(bundle.getString("load"));
        checkResultLabel.setText(" ");

    }

    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getBoardElement(i, j).getFieldValue() != originalBoard.getBoardElement(i, j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        checkButton.setText(bundle.getString("checkButton"));
        resetButton.setText(bundle.getString("resetButton"));
        saveButton.setText(bundle.getString("save"));
        loadButton.setText(bundle.getString("load"));
        difficultyLevel = SampleController.getWhatLevel();
        prepareGrid(difficultyLevel, SampleController.getBoard());
        writeGrid(board);
    }

   /* private void reload(ActionEvent event, SudokuBoard board) {
        String level = difficultyLevel;
        prepareGrid(level, board);
        writeGrid(board);

    } */

    public void prepareGrid(String difficulty, SudokuBoard board) {
        Level level = null;
        this.board = board;


        if (difficulty == "easy") {
            level = new EasyLevel();
        }
        if (difficulty == "medium") {
            level = new MediumLevel();
        }
        if (difficulty == "difficult") {
            level = new DifficultLevel();
        }

        try {
            BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
            solver.solve(board);
            int i = 0;
            while (i < 9) {
                for (int j = 0; j < 9; j++) {
                    originalBoard.getBoardElement(i, j).setFieldValue(board.getBoardElement(i, j).getFieldValue());
                }
                i++;
            }
            level.prepareEmptyFields(board);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException thrown, because there is no such level!");
        }

    }

    public void writeGrid(SudokuBoard board) {
        this.board = board;
        StringConverter<Number> converter = new NumberStringConverter();

        int currentBoardElementValue = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                currentBoardElementValue = board.getBoardElement(i, j).getFieldValue();
                if (currentBoardElementValue != 0) {
                    TextField text = new TextField("" + currentBoardElementValue);
                    text.setEditable(false);
                    text.setFont(Font.font("System Bold", FontWeight.BOLD, 13));
                    grid.add(text, j, i);
                } else {
                    TextField text = new TextField();
                    text.setTextFormatter(new TextFormatter<>(c -> {
                        if (c.getControlNewText().matches("[1-9]?")) {
                            return c;
                        } else {
                            return null;
                        }
                    }));
                    try {
                        JavaBeanIntegerProperty wrapper = new JavaBeanIntegerPropertyBuilder().bean(board.getBoardElement(i, j)).name("FieldValue").build();
                        Bindings.bindBidirectional(text.textProperty(), wrapper, converter);
                        text.setEditable(true);
                        text.setPromptText(" ?");
                        text.setFont(Font.font("System Bold", 14));
                        grid.add(text, j, i);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(GridController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}




