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
import eu.hansolo.enzo.lcd.Lcd;
import eu.hansolo.enzo.lcd.LcdBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;
public class EnzoLCD extends Application {
  private Lcd            lcd;
  private Random         random;
  private long           lastTimerCall;
  private double         charge;
  private AnimationTimer timer;
  @Override public void init() {
    // Initialize the AnimationTimer
    random        = new Random();
    lastTimerCall = System.nanoTime();
    charge        = 0;
    timer         = new AnimationTimer() {
      @Override public void handle(long now) {
        if (now > lastTimerCall + 3_000_000_000l) {
          lcd.setValue(random.nextDouble() * 100);
          lcd.setTrend(Lcd.Trend.values()[random.nextInt(5)]);
          charge += 0.02;
          if (charge > 1.0) charge = 0.0;
          lcd.setBatteryCharge(charge);
          lastTimerCall = now;
          System.out.println(lcd.getValue());
        }
      }
    };
    // Initialize the Enzo Lcd control
    lcd = LcdBuilder.create()
                    .styleClass(Lcd.STYLE_CLASS_BLUE_GRAY)
                    .title("Temperatura")
                    .unit("°C")
                    .decimals(2)
                    .minMeasuredValueDecimals(2)
                    .maxMeasuredValueDecimals(2)
                    .unitVisible(true)
                    .batteryVisible(true)
                    .alarmVisible(true)
                    .minMeasuredValueVisible(true)
                    .maxMeasuredValueVisible(true)
                    .lowerRightTextVisible(true)
                    .formerValueVisible(true)
                    .trendVisible(true)
                    .lowerRightText("Info")
                    //.valueAnimationEnabled(true)
                    .foregroundShadowVisible(true)
                    .crystalOverlayVisible(false)
                    .backgroundVisible(true)
                    .build();
  }
  @Override public void start(Stage stage) {
    // Prepare stage and add controls
    StackPane root = new StackPane();
    root.setPadding(new Insets(10, 10, 10, 10));
    root.getChildren().add(lcd);
    stage.setTitle("Enzo in JavaFX");
    stage.setScene(new Scene(root, 528/2, 192/2));
    stage.show();
    // Start the timer
    timer.start();
  }
  public static void main(String[] args) {
    launch(args);
  }
}

