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
import eu.hansolo.enzo.simpleindicator.SimpleIndicator;
import eu.hansolo.enzo.simpleindicator.SimpleIndicatorBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by
 * User: hansolo
 * Date: 19.11.12
 * Time: 03:33
 */
public class EnzoSimpleIndicator extends Application {
    private SimpleIndicator control;
    private boolean         toggle;
    private long            lastTimerCall;
    private AnimationTimer  timer;


    @Override public void init() {
        control       = SimpleIndicatorBuilder.create().build();
        toggle        = false;
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long NOW) {
                if (NOW > lastTimerCall + 1_000_000_000l) {
                    toggle ^= true;
                    if (toggle) {
                        //control.getStyleClass().setAll("indicator", SimpleIndicator.STYLE_CLASS_PURPLE);
                        control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.PURPLE);
                    } else {
                        //control.getStyleClass().setAll("indicator", SimpleIndicator.STYLE_CLASS_MAGENTA);
                        control.setIndicatorStyle(SimpleIndicator.IndicatorStyle.MAGENTA);
                    }
                    lastTimerCall = NOW;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane, 50, 50, Color.DARKGRAY);

        stage.setScene(scene);
        stage.show();

        timer.start();
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
