package com.svalero.javafx.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class MainController {
    @FXML
    private TabPane container;

    @FXML
    private Button clientsBtn;

    @FXML
    private Button gardenersBtn;

    @FXML
    protected void onSelectList(Event event) {
        System.out.println(event);
        Button sourceButton = (Button) event.getSource();
        //if(sourceButton == clientsBtn)

    }

}