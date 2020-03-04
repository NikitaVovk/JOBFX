package controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.io.IOException;

public class MainScene {
    public Tab excel;
    public Tab word;
    public Tab settings;

    public void excelTab(Event event) {
        try {
            Parent node = FXMLLoader.load(getClass().getResource("/fxml/excelTab.fxml"));
            excel.setContent(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wordTab(Event event) {
        try {
            Parent node = FXMLLoader.load(getClass().getResource("/fxml/wordTab.fxml"));
            word.setContent(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void settingsTab(Event event) {
        try {
            Parent node = FXMLLoader.load(getClass().getResource("/fxml/settingsTab.fxml"));
            settings.setContent(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
