package com.github.shiro8613.advancedlogviewer.logic.file;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileController {

    public ArrayList<Label> OpenLogic(File selectedFile, String name, ListView SubList) throws IOException {
        Reader reader;

        switch (FilenameUtils.getExtension(selectedFile.getName())) {
            case "gz":
                GZIPInputStream in = new GZIPInputStream(FileUtils.openInputStream(selectedFile));
                reader = new StringReader(new String(in.readAllBytes()));
                break;

            default:
                reader = new FileReader(selectedFile);
                break;
        }

        BufferedReader br = new BufferedReader(reader);

        ArrayList<Label> log = new ArrayList<Label>();

        SubList.getItems().add(name);

        String str;
        while ((str = br.readLine()) != null) {
            Label label = new Label();
            label.setText(str);
            //label.setStyle("-fx-background-color: white;");
            log.add(label);
        }

        return log;
    }

    public void SaveLogic(String name, List<Label> list) throws IOException {
        File file = new File(name);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        list.forEach(l -> {
            try {
                bw.write(l.getText());
                bw.newLine();
            } catch (IOException e){}
        });

        bw.close();
    }
}
