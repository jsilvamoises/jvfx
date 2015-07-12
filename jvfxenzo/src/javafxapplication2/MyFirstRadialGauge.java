/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;






import eu.hansolo.enzo.common.Marker;
import eu.hansolo.enzo.gauge.Gauge;
import eu.hansolo.enzo.gauge.GaugeBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;



import javafx.stage.Stage;





/**
 *
 * @author MOISES
 */
public class MyFirstRadialGauge extends Application {
     Gauge radial;
    @Override
    public void start(Stage primaryStage) {
        Marker temperatura = new Marker(19,Color.LIGHTSLATEGRAY.toString());
        Marker temperatura2 = new Marker(50,Color.BROWN.toString());
       
        Marker markerList[] = new Marker[]{temperatura,temperatura2};
        
       radial = GaugeBuilder.create()
               
                .prefWidth(50)
                .prefHeight(50)
               
                 .title("Temperatura")
                 .markers(markerList)
                .unit("ºC")
                
                .build();
        
        StackPane root = new StackPane();
        root.getChildren().add(radial);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        iniciaTela();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    void iniciaTela() {
        Task t = new Task() {

            @Override
            protected Object call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                       
                        radial.setValue(Math.random()*100);
                    });
                    Thread.sleep(1);
                }
            }
        };

        new Thread(t).start();
    }
    
    
}
