/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

/**
 *
 * @author MOISES
 */

import eu.hansolo.enzo.lcd.Alarm.AlarmEvent;
import eu.hansolo.enzo.roundlcdclock.RoundLcdClock;
import eu.hansolo.enzo.roundlcdclock.RoundLcdClockBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;


/**
 * Created by
 * User: hansolo
 * Date: 11.07.13
 * Time: 06:16
 */

public class EnzoRoundLcdClock extends Application {
    private RoundLcdClock clock;

    @Override public void init() {
        clock = RoundLcdClockBuilder.create()
                               //.color(Color.CYAN)
                               //.hourColor(Color.LIME)
                               //.minuteColor(Color.AQUA)
                               //.secondColor(Color.MAGENTA)
                               //.textColor(Color.DARKOLIVEGREEN)
                               .alarmVisible(true)
                               .alarmOn(true)
                               .alarm(LocalTime.now().plusSeconds(20))
                               .dateVisible(true)
                               .build();
        clock.addEventHandler(AlarmEvent.ALARM, event -> {
            System.out.println("A L A R M");
        });
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.getChildren().addAll(clock);

        Scene scene = new Scene(pane);
        //scene.setFullScreen(true);

        stage.setTitle("RoundLcdClock");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {

    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
