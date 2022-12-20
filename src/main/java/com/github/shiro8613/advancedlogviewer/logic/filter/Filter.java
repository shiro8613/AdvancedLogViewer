package com.github.shiro8613.advancedlogviewer.logic.filter;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    public List<Label> includeText(String text, ListView list) {
        List<Label> tmp = new ArrayList<Label>();

        List<Label> old = (List<Label>) list.getItems();
        old.forEach(x -> {
            if (x.getText().contains(text)) tmp.add(x);
        });

        return tmp;
    }

    public List<Label> matchText(String text, ListView list) {
        List<Label> tmp = new ArrayList<Label>();

        List<Label> old = (List<Label>) list.getItems();
        old.forEach(x -> {
            if (x.getText().equals(text)) tmp.add(x);
        });

        return tmp;
    }

    public List<Label> notIncludeText(String text, ListView list) {
        List<Label> tmp = new ArrayList<Label>();

        List<Label> old = (List<Label>) list.getItems();
        old.forEach(x -> {
            if (!x.getText().contains(text)) tmp.add(x);
        });

        return tmp;
    }

    public List<Label> notMatchText(String text, ListView list) {
        List<Label> tmp = new ArrayList<Label>();

        List<Label> old = (List<Label>) list.getItems();
        old.forEach(x -> {
            if (!x.getText().equals(text)) tmp.add(x);
        });

        return tmp;
    }

}
