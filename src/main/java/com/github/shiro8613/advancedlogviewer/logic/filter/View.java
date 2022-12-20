package com.github.shiro8613.advancedlogviewer.logic.filter;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class View {

    public List<Label> Marking(List<Label> Old, List<Label> New) {
        List<Label> list = new ArrayList<Label>();
        Old.forEach(x -> {
            New.forEach(y -> {
                if (x == y) {
                    y.setStyle("-fx-background-color: lime;");
                    list.add(y);
                } else {
                    list.add(x);
                }
            });
        });

        return list;
    }

}
