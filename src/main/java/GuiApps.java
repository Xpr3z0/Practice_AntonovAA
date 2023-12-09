import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import t7.Task7;
import t8.Task8;
import t9.Task9;

public class GuiApps extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GUI Apps");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER); // Центрируем элементы по центру

        // Создаем кнопку для Task 7
        Button task7Button = createButton("Задание 7");
        task7Button.setOnAction(e -> {
            Platform.runLater(() -> {
                new Task7().start(new Stage());
            });
        });

        // Создаем кнопку для Task 8
        Button task8Button = createButton("Задание 8");
        task8Button.setOnAction(e -> {
            Platform.runLater(() -> {
                new Task8().start(new Stage());
            });
        });

        // Создаем кнопку для Task 9
        Button task9Button = createButton("Задание 9");
        task9Button.setOnAction(e -> {
            Platform.runLater(() -> {
                new Task9().start(new Stage());
            });
        });

        // Добавляем кнопки в контейнер
        root.getChildren().addAll(task7Button, task8Button, task9Button);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для создания кнопки с настройками
    private Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setMinSize(150, 40); // Устанавливаем минимальный размер кнопки
        button.setStyle("-fx-font-size: 16;"); // Устанавливаем размер шрифта
        return button;
    }
}
