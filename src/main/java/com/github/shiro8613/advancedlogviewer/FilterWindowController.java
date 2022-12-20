package com.github.shiro8613.advancedlogviewer;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import com.github.shiro8613.advancedlogviewer.WindowController.*;

import java.util.ArrayList;
import java.util.List;

public class FilterWindowController {
    @FXML
    private Button Cancelbutton;

    private boolean Canceled = false;
    @FXML
    private TextField textfield;

    @FXML
    private ChoiceBox filterMode;

    @FXML
    private ChoiceBox viewMode;

    private String text;
    private int filterModeIndex;
    private int viewModeIndex;

    public String getText() { return this.text; }
    public int getFilterModeIndex() { return this.filterModeIndex; }
    public int getViewModeIndex() { return this.viewModeIndex; }

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
        this.filterModeIndex = filterMode.getSelectionModel().getSelectedIndex();
        this.viewModeIndex = viewMode.getSelectionModel().getSelectedIndex();

        stage.close();
    }
}
