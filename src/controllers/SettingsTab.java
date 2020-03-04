package controllers;

import backend.ConfController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class SettingsTab {

    public TextField date;
    public TextField numTrack;
    public TextField route;
    public TextField numberAkt;
    public TextField val;
    public Button saveButt;
    public RadioButton optionAN;
    public RadioButton optionAN1;
    public RadioButton optionMetro;
    public ToggleGroup radioGroup;
    public void initialize() {
        date.setText(getLiteral(ConfController.date));
        numTrack.setText(getLiteral(ConfController.numTrack));
        route.setText(getLiteral(ConfController.marszrut));
        numberAkt.setText(getLiteral(ConfController.numberAkt));
        val.setText(getLiteral(ConfController.val));
        setDefaultRadio();
    }

    void setDefaultRadio(){
        if(ConfController.firma.equals("AN"))
            optionAN.setSelected(true);
        if(ConfController.firma.equals("AN1"))
            optionAN1.setSelected(true);
        if(ConfController.firma.equals("Metro"))
            optionMetro.setSelected(true);
    }

    private String getLiteral(int i){
        String literal = String.valueOf((char) (i+65));
        return literal;
    }
    private int setNumLiteral(char znak){
        int numLit = (int)znak-65;
        return numLit;
    }

    public void saveButt(ActionEvent actionEvent) {
        Toggle selectedRadio;
        try{ selectedRadio=radioGroup.getSelectedToggle();}
        catch (NullPointerException e){e.printStackTrace();
            selectedRadio=null;}
        if (!date.getText().isEmpty()&&!numTrack.getText().isEmpty()&&!route.getText().isEmpty()&&!numberAkt.getText().isEmpty()&&!val.getText().isEmpty()){
        if ((int)date.getText().charAt(0)>64&&(int)date.getText().charAt(0)<91&&
                (int)numTrack.getText().charAt(0)>64&&(int)date.getText().charAt(0)<91&&
                (int)route.getText().charAt(0)>64&&(int)date.getText().charAt(0)<91&&
                (int)numberAkt.getText().charAt(0)>64&&(int)date.getText().charAt(0)<91&&
                (int)val.getText().charAt(0)>64&&(int)date.getText().charAt(0)<91&&
                selectedRadio!=null){
            ConfController.date=setNumLiteral(date.getText().charAt(0));
            ConfController.numTrack=setNumLiteral(numTrack.getText().charAt(0));
            ConfController.marszrut=setNumLiteral(route.getText().charAt(0));
            ConfController.numberAkt=setNumLiteral(numberAkt.getText().charAt(0));
            ConfController.val=setNumLiteral(val.getText().charAt(0));
            setFirmVal();
            Notifications noti =   Notifications.create();
            noti.text("ОК");
            noti.title("ЗМІНИ ЗБЕРЕЖЕНО");
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.millis(2000));
            noti.show();
        }
        else {
            Notifications noti =   Notifications.create();
            noti.text("УПС");
            noti.title("НЕ ПРАВИЛЬНІ ДАНІ");
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.millis(2000));
            noti.showError();
        }
        }
        else{
            Notifications noti =   Notifications.create();
            noti.text("УПС");
            noti.title("ЗАПОВНИ ВСІ ПОЛЯ");
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.millis(2000));
            noti.showError();
        }


    }
    void setFirmVal(){
        if (optionAN.isSelected())
            ConfController.firma="AN";
        if (optionAN1.isSelected())
            ConfController.firma="AN1";
        if (optionMetro.isSelected())
            ConfController.firma="Metro";

    }


    public void radioSetup(ActionEvent actionEvent) {
        radioGroup= new ToggleGroup();
        optionAN.setToggleGroup(radioGroup);
        optionAN1.setToggleGroup(radioGroup);
        optionMetro.setToggleGroup(radioGroup);
    }
}
