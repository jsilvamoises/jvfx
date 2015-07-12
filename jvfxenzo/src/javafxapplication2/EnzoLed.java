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
import eu.hansolo.enzo.led.LedBuilder;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Random;



/**
 * Created by
 * User: hansolo
 * Date: 16.11.12
 * Time: 10:09
 */
public class EnzoLed extends Application {
    private static final Random RND = new Random();
    private static int   noOfNodes  = 0;
    private LocalTime    start;
    private LocalTime    stop;

    @Override public void init() {
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        for (int y = 0 ; y < 20 ; y++) {
            for (int x = 0 ; x < 20 ; x++) {
                pane.add(LedBuilder.create()
                                   .ledColor(Color.rgb(RND.nextInt(255), RND.nextInt(255), RND.nextInt(255)))
                                   .frameVisible(false)
                                   .interval(50000)
                                   .blinking(true)
                                   .build(), x, y);
            }
        }
        pane.sceneProperty().addListener(observable  -> start = LocalTime.now());

        Scene scene = new Scene(pane);

        stage.setTitle("Led demo");
        stage.setScene(scene);
        stage.show();

        stop = LocalTime.now();
        System.out.println("CSS Led: ");
        System.out.println("Start: " + start);
        System.out.println("Stop : " + stop);

        calcNoOfNodes(scene.getRoot());
        System.out.println(noOfNodes + " Nodes in SceneGraph");
        System.out.println(((stop.toNanoOfDay() - start.toNanoOfDay())) / noOfNodes / 1000000d + " [ms/node]");
    }

    @Override public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }


    // ******************** Misc **********************************************
    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                    //System.out.println(n.getStyleClass().toString());
                }
            }
        }
    }
}
