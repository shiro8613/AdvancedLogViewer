package com.github.shiro8613.advancedlogviewer.logic.filter;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class View {

    public List<Label> Marking(Stream Data, List<Label> New, String color) {
        List<Label> list = new ArrayList<>();
        List<Label> Old = (List<Label>) Data;
        List<Integer> integers = new ArrayList<>();

        Old.forEach(x -> {
            Label tmp = new Label();
            tmp.setText(x.getText());
            list.add(tmp);
        });

        for(int i=0; i < Old.size(); i++) {
            Label x = Old.get(i);
            int I = i;
            New.forEach(y -> {if (x.equals(y)) integers.add(I);});
        }

        integers.forEach(integer -> list.get(integer)
                .setStyle(String.format("-fx-background-color: %s;", color)));

        return list;
    }

}
