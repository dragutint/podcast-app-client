package com.mreze.podcastappclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private final Reproducing reproducing;

    public Controller() {
        this.reproducing = new Reproducing();
    }


    @FXML
    void doSomething(ActionEvent event) {
        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                reproducing.startReproducing();
            }});
        t1.start();

    }

    @FXML
    void doStop(ActionEvent event) {
    }

}
