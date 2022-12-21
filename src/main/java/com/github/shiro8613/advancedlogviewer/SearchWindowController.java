package com.github.shiro8613.advancedlogviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchWindowController {

    @FXML
    private Button Cancelbutton;

    private boolean Canceled = false;
    @FXML
    private TextField textfield;


    private String text;
    public String getText() { return this.text; }
    public boolean isCanceled() { return this.Canceled; }
    @FXML
    protected void CancelButton() {
        Stage stage = (Stage) Cancelbutton.getScene().getWindow();
        this.Canceled = true;
        stage.close();
    }

    @FXML
    protected void ExecuteButton() {
        Stage stage = (Stage) Cancelbutton.getScene().getWindow();
        this.text = textfield.getText();

        stage.close();
    }

}
