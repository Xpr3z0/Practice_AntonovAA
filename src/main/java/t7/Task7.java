package t7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Task7 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task 7");

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(300, 400);
        root.getChildren().add(canvas);

        drawLadybug(canvas.getGraphicsContext2D());

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void drawLadybug(GraphicsContext gc) {

        // Рисуем голову
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillOval(110, 55, 80, 120);

        // Рисуем обводку божьей коровки
        gc.setFill(Color.BLACK);
        gc.fillOval(47, 97, 206, 226);

        // Рисуем корпус божьей коровки
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillOval(50, 100, 200, 220);

        // Линия посередине
        gc.setFill(Color.BLACK);
        gc.fillRect(148.5, 100, 3, 220);

        // Рисуем точки на корпусе
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillOval(152, 179, 32, 42);
        gc.fillOval(165, 110, 32, 42);
        gc.fillOval(210, 160, 32, 42);
        gc.fillOval(200, 230, 32, 42);
        gc.fillOval(160, 272, 32, 42);
        gc.fillOval(110, 129, 32, 42);
        gc.fillOval(60, 150, 32, 42);
        gc.fillOval(85, 195, 32, 42);
        gc.fillOval(65, 240, 32, 42);
        gc.fillOval(116, 250, 32, 42);

        // Рисуем усики
        gc.fillRect(145, 43, 3, 15);
        gc.fillRect(154, 43, 3, 15);
        gc.fillPolygon(new double[]{148, 137, 147}, new double[]{44, 46, 39}, 3);
        gc.fillPolygon(new double[]{154, 164, 155}, new double[]{44, 46, 39}, 3);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

