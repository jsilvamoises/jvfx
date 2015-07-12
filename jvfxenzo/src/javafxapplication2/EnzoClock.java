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
import eu.hansolo.enzo.clock.Clock;
import eu.hansolo.enzo.clock.ClockBuilder;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;



/**
 * Created with IntelliJ IDEA.
 * User: hansolo
 * Date: 31.10.12
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class EnzoClock extends Application {

    private Clock      clock1;
    private Clock      clock2;
    private Clock      clock3;
    private Clock      clock4;
    private Clock      clock5;
    private Clock      clock6;
    private static int noOfNodes;

    @Override public void init() {
        clock1 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.BOSCH)                             
                             .running(true)
                             .text("San Francisco")
                            // .offset(Duration.of(-9, ChronoUnit.HOURS))
                             .autoNightMode(false)
                             .build();
        clock2 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.IOS6)
                             .nightMode(true)
                             .running(true)
                             .text("New York")
                           //  .offset(Duration.of(-6, ChronoUnit.HOURS))
                             .autoNightMode(true)
                             .build();
        clock3 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.DB)
                             .running(true)
                             .text("Berlin")
                             .autoNightMode(true)
                             .build();
        clock4 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.DB)
                             .nightMode(true)
                             .running(true)
                             .text("Moskau")
                            // .offset(Duration.of(3, ChronoUnit.HOURS))
                             .autoNightMode(true)  
                             .build();
        clock5 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.BRAUN)
                             .discreteSecond(true)
                             .running(true)
                             .text("Hongkong")
                           //  .offset(Duration.of(7, ChronoUnit.HOURS))
                             .autoNightMode(true)
                             .build();
        clock6 = ClockBuilder.create()
                             .prefSize(400, 400)
                             .design(Clock.Design.BRAUN)
                             .nightMode(true)
                             .discreteSecond(true)
                            // .offset(Duration.of(90, ChronoUnit.MINUTES))
                             .running(true)
                             .text("Sydney")
                            // .offset(Duration.of(10, ChronoUnit.HOURS))
                             .autoNightMode(true)
                             .build();
    }

    @Override public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(clock1, 0, 0);
        pane.add(clock2, 0, 1);
        pane.add(clock3, 1, 0);
        pane.add(clock4, 1, 1);
        pane.add(clock5, 2, 0);
        pane.add(clock6, 2, 1);

        //Scene scene = new Scene(pane, 400, 400, Color.rgb(195, 195, 195));
        Scene scene = new Scene(pane, Color.BLACK);

        stage.setTitle("Clock Demo");
        stage.setScene(scene);
        stage.show();

        //clock.setMajorTickHeight(30);

        calcNoOfNodes(scene.getRoot());
        System.out.println("No. of nodes in scene: " + noOfNodes);
    }

    @Override public void stop() {

    }

    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}