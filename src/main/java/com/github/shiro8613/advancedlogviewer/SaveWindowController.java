package com.github.shiro8613.advancedlogviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class SaveWindowController {

    @FXML
    private VBox vbox;
    @FXML
    private ListView listView;

    @FXML
    private TextField text;

    public void setList(List<String> list) {
        this.listView.getItems().setAll(list);
    }

    public String getPath() {
        return text.getText();
    }

    public List<Integer> getSelected() {
        return listView.getSelectionModel().getSelectedIndices();
    }

    public List<String> getList() {
        return listView.getItems().stream().toList();
    }

    @FXML
    protected void CancelButton() {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void ExecuteButton() {
        Stage stage = (Stage) vbox.getScene().getWindow();

        if (listView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("エラー");
            alert.setHeaderText(null);
            alert.setContentText("保存対象を選択してください。");
            alert.showAndWait();
            return;
        }

        if (text.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("エラー");
            alert.setHeaderText(null);
            alert.setContentText("保存先を選択してください。");
            alert.showAndWait();
            return;
        }

        stage.close();
    }

    @FXML
    protected void ChoiceButton() {
        Stage stage = (Stage) vbox.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle( "保存先ディレクトリ選択" );
        File file = directoryChooser.showDialog( stage );

        if(file == null) return;

        text.setText(file.getPath());
    }

}
