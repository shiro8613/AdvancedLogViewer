package com.github.shiro8613.advancedlogviewer;

import com.github.shiro8613.advancedlogviewer.logic.file.FileController;
import com.github.shiro8613.advancedlogviewer.logic.filter.Filter;
import com.github.shiro8613.advancedlogviewer.logic.filter.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.util.*;
import java.util.zip.GZIPInputStream;

import org.apache.commons.io.*;

public class WindowController {
    @FXML
    private VBox vbox;

    @FXML
    private ListView MainList;

    @FXML
    private ListView SubList;

    private Map<String, ArrayList<Label>> LogMap = new HashMap<String, ArrayList<Label>>();

    public ListView getMainList() {
        return this.MainList;
    }

    public void setMainList(List<Object> list) {
        this.MainList.getItems().setAll(list);
    }

    @FXML
    protected void OpenButton() throws IOException {

        //ステージ取得
        Stage stage = (Stage) vbox.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        ;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Log Files", "*.log"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Archive Files", "*.gz"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        if(selectedFiles == null) return;

        for(File selectedFile: selectedFiles) {
            String name = FilenameUtils.getBaseName(selectedFile.getName());
            ArrayList<Label> log = new FileController().OpenLogic(selectedFile, name, SubList);
            LogMap.put(name, log);
        }
    }
    @FXML
    protected void QuitButton() {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void EjectFiles() {
        int selectIndex = SubList.getSelectionModel().getSelectedIndex();
        if (selectIndex < 0) return;
        String name = SubList.getItems().get(selectIndex).toString();
        LogMap.remove(name);
        SubList.getItems().remove(selectIndex);
        MainList.getItems().setAll(new ArrayList<>());

    }

    @FXML
    protected void SubSelectView() {
        int selectIndex = SubList.getSelectionModel().getSelectedIndex();
        if (selectIndex < 0) return;
        String name = SubList.getItems().get(selectIndex).toString();
        List<Label> loglist = LogMap.get(name);
        MainList.getItems().setAll(loglist);
    }

    @FXML
    protected void Marking() {
        int selectIndex = MainList.getSelectionModel().getSelectedIndex();
        if(selectIndex < 0) return;
        List<Label> New = MainList.getItems().stream().toList();
        New.get(selectIndex).setStyle("-fx-background-color: yellow;");
        MainList.getItems().setAll(New);
    }

    @FXML
    protected void Copy() {
        int selectIndex = MainList.getSelectionModel().getSelectedIndex();
        if(selectIndex < 0) return;
        Label label = (Label) MainList.getItems().get(selectIndex);
        String text = label.getText();


        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);

    }

    @FXML
    protected void Filter() throws IOException {
        while (true) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("filter.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 200);
            stage.setResizable(false);
            stage.setTitle("FilterWindow");
            stage.setScene(scene);
            stage.showAndWait();

            FilterWindowController controller = fxmlLoader.getController();

            if (controller.isCanceled()) {
                break;
            }

            int filterModeIndex = controller.getFilterModeIndex();
            int viewModeIndex = controller.getViewModeIndex();
            String text = controller.getText();
            Filter filter = new Filter();
            View view = new View();
            List<Label> tmp;

            if (text.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NotData");
                alert.setHeaderText(null);
                alert.setContentText("フィルターする文字列を入力してください。");
                alert.showAndWait();
                continue;
            }

            //ここから処理分岐
            switch (filterModeIndex) {
                case 0: //完全一致
                    tmp = filter.matchText(text, MainList);
                    break;

                default:
                case 1: //～を含む
                    tmp = filter.includeText(text, MainList);
                    break;

                case 2: //～を含まない
                    tmp = filter.notIncludeText(text, MainList);
                    break;

                case 3: //一致しない
                    tmp = filter.notMatchText(text, MainList);
                    break;
            }

            if (tmp.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NotFound");
                alert.setHeaderText(null);
                alert.setContentText("見つかりませんでした。");
                alert.showAndWait();
                return;
            }

            switch (viewModeIndex) {
                default:
                case 0:
                    List<Label> list = view.Marking(MainList.getItems().stream().toList(), tmp);
                    MainList.getItems().setAll(list);
                    break;

                case 1:
                    MainList.getItems().setAll(tmp);
                    break;
            }
            break;
        }
    }
}