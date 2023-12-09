package t8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Task8 extends Application {
    private String[] menuItems;
    private int selectedIndex;
    private boolean enterPressed;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        String menuString = "Первое Второе Третье";
        init(menuString);

        HBox root = new HBox();
        drawMenu(root);

        Scene scene = new Scene(root, 400, 100);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode(), primaryStage));

        primaryStage.setTitle("Простое меню");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void init(String menuString) {
        menuItems = menuString.split(" ");
        selectedIndex = 0;
        enterPressed = false;
    }


    private void drawMenu(HBox root) {
        root.getChildren().clear();

        for (int i = 0; i < menuItems.length; i++) {
            Label label = new Label(menuItems[i]);
            if (i == selectedIndex && !enterPressed) {
                label.setStyle("-fx-background-color: lightgray;");
            } else if (i == selectedIndex && enterPressed) {
                label.setStyle("-fx-background-color: lightblue;");
            }
            label.setStyle(label.getStyle() + "-fx-padding: 5px;");
            root.getChildren().add(label);
        }
    }


    private void handleKeyPress(KeyCode keyCode, Stage primaryStage) {
        switch (keyCode) {
            case SPACE:
                if (!enterPressed) {
                    // Перебор пунктов нажатием клавиши пробела
                    selectedIndex = (selectedIndex + 1) % menuItems.length;
                    drawMenu((HBox) primaryStage.getScene().getRoot());
                }
                break;

            case ENTER:
                // Зафиксировать выбор нажатием клавиши Enter
                if (!enterPressed) {
                    enterPressed = true;
                    drawMenu((HBox) primaryStage.getScene().getRoot());
                    System.out.println("\nВыбранный пункт: " + whatSel());
                    leftBoard(selectedIndex);
                    System.out.println("Длина названия пункта: " + len(selectedIndex));
                }
                break;

            case ESCAPE:
                // Отменить выбор нажатием клавиши Esc
                enterPressed = false;
                drawMenu((HBox) primaryStage.getScene().getRoot());
                break;
        }
    }


    private String select() {
        return enterPressed ? menuItems[selectedIndex] : "#27";
    }


    private void leftBoard(int index) {
        System.out.println("Начало выбранного пункта: \"" + menuItems[index].charAt(0) + "\"");
    }


    private int len(int index) {
        return menuItems[index].length();
    }


    private String whatSel() {
        return select();
    }
}
