package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jusia on 27.05.2017.
 */
public class InfoController implements Initializable {
    private ResourceBundle bundle;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        textOne.setText(String.valueOf(rb.getObject("uni")));
        textTwo.setText(String.valueOf(rb.getObject("subject")));
        textThree.setText(String.valueOf(rb.getObject("year")));
        textFour.setText(String.valueOf(rb.getObject("copyright")));
        textFive.setText(String.valueOf(rb.getObject("authors")));
        backButton.setText(String.valueOf(rb.getObject("back")));
    }

    @FXML
    public Label textOne, textTwo, textThree, textFour, textFive;


    @FXML
    public Button backButton;

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
