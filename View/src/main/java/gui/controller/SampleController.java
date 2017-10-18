package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.*;

public class SampleController implements Initializable {
    private static SudokuBoard board;

    private static String whatLevel;
    private static Locale localePL = new Locale("PL");
    private static Locale localeENG = new Locale("ENG");
    private static Locale locale = localeENG;
    private ResourceBundle bundle;

    public static String getWhatLevel() {
        return whatLevel;
    }

    public static SudokuBoard getBoard() {
        return board;
    }

    public static Locale getLocale() {
        return locale;
    }

    public ResourceBundle getBundle() {
        return this.bundle;
    }

    public void setLanguage(Locale locale) {
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("lang", locale);
    }

    @FXML
    private Label welcomeLabel, levelLabel, languageLabel;

    @FXML
    private Button  mediumButton, easyButton, difficultButton, infoButton;

    @FXML
    private void handleEasyButtonAction(ActionEvent event) {
        whatLevel = "easy";
        EasyLevel level = new EasyLevel();
        createNewGrid(event);
    }

    @FXML
    private void handleMediumButtonAction(ActionEvent event) {
        whatLevel = "medium";
        MediumLevel level = new MediumLevel();
        createNewGrid(event);
    }

    @FXML
    private void handleDifficultButtonAction(ActionEvent event) {
        whatLevel = "difficult";
        DifficultLevel level = new DifficultLevel();
        createNewGrid(event);
    }

    @FXML
    private void handlePLButtonAction(ActionEvent event) throws Exception {
        setLanguage(localePL);
        reload(event);
    }

    @FXML
    private void handleENGButtonAction(ActionEvent event) throws Exception {
        setLanguage(localeENG);
        reload(event);
    }

    @FXML
    private void handleInfoButtonAction(ActionEvent event) throws Exception {
        ResourceBundle res;
       if (locale == localeENG) {
            res = ResourceBundle.getBundle("i18n.List_eng_US", new Locale("ENG"));
            Parent root = FXMLLoader.load(getClass().getResource("/Info.fxml"), res);
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);

            stage.show();
        }
        if (locale == localePL) {
            res = ResourceBundle.getBundle("i18n.List_pl_PL", new Locale("PL"));
            Parent root = FXMLLoader.load(getClass().getResource("/Info.fxml"), res);
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);

            stage.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        mediumButton.setText(bundle.getString("mediumButton"));
        easyButton.setText(bundle.getString("easyButton"));
        difficultButton.setText((bundle.getString("difficultButton")));
        languageLabel.setText(bundle.getString("languageLabel"));
        welcomeLabel.setText(bundle.getString("welcomeLabel"));
        levelLabel.setText(bundle.getString("levelLabel"));
        infoButton.setStyle(
                "-fx-background-radius: 40em; " +
                        "-fx-min-width: 40px; " +
                        "-fx-min-height: 40px; " +
                        "-fx-max-width: 40px; " +
                        "-fx-max-height: 40px;"
        );
        board = new SudokuBoard();
    }

    public void reload(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Sample.fxml"), ResourceBundle.getBundle("lang", locale));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(bundle.getString("gameName"));
    }

    private void createNewGrid(ActionEvent event) {
        try {
            GridController grid = new GridController();
            Parent root = FXMLLoader.load(getClass().getResource("/Grid.fxml"), ResourceBundle.getBundle("lang", locale));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(bundle.getString("gameName"));
            stage.show();
        } catch (IOException e) {
            System.out.println("aaa!");
        }
    }

}
