package controllers;

import backend.XSLXController;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTab {
    public Button butChoose;
    public TextField startRow;
    public TextField endRow;
    public Button makeJob;
    public TextField pathField;
    public TextField pathField2;
    public Button butChoose2;

    public void butChooseAction(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile!=null){
            pathField.setText(selectedFile.getAbsolutePath());
        }
    }

    public void makeJobAction(ActionEvent actionEvent) throws IOException {
        if (!pathField.getText().isEmpty()&&!startRow.getText().isEmpty()&&!endRow.getText().isEmpty()&&!pathField2.getText().isEmpty()){
            FileInputStream fis = new FileInputStream(pathField.getText());
            XSLXController xslxController = new XSLXController(Integer.parseInt(startRow.getText())-1,Integer.parseInt(endRow.getText())-1,fis,pathField2.getText());
            Notifications noti =   Notifications.create();
            noti.text("ПЕРЕГЛЯНЬТЕ ПАПКУ З ГОТОВИМИ ДОКУМЕНТАМИ");
            noti.title("РОБОТУ ВИКОНАНО");
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.millis(2000));
            noti.showInformation();
        }
        else {
            Notifications noti =   Notifications.create();
            noti.text("Заповніть всі поля");
            noti.title("Не всі поля заповнені");
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.millis(2000));
            noti.showError();}
    }

    public void but2ChooseAction(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        File selectedFile = dc.showDialog(null);
        if (selectedFile!=null){
            pathField2.setText(selectedFile.getAbsolutePath());
        }
    }
}
