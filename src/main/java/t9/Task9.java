package t9;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Task9 extends Application {
    private static final int GRID_SIZE = 5;
    private static final int CARD_COUNT = 52;
    private List<Integer> cards;
    private int[][] grid;
    private GridPane gridPane;
    private boolean playerTurn = true;
    private int playerScore = 0;
    private int computerScore = 0;
    private VBox root;
    private BorderPane rootBorderPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeGame();
        root = createStartScreen();
        rootBorderPane = new BorderPane();
        rootBorderPane.setCenter(root);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(rootBorderPane, 400, 400);
        primaryStage.setTitle("Математико");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the game immediately after launching the application.
        startGame();
    }

    private void initializeGame() {
        cards = generateCards();
        grid = new int[GRID_SIZE][GRID_SIZE];
        Collections.shuffle(cards);
    }

    private List<Integer> generateCards() {
        List<Integer> cardList = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            cardList.add(i);
            cardList.add(i);
            cardList.add(i);
            cardList.add(i);
        }
        Collections.shuffle(cardList);
        return cardList.subList(0, CARD_COUNT);
    }

    private VBox createStartScreen() {
        return new VBox(20);
    }

    private void startGame() {
        gridPane = createGridPane();
        root.getChildren().add(gridPane);
        showScores();

        playComputerTurn();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER); // Add this line to set the alignment to the center
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                int finalI = i;
                int finalJ = j;
                button.setOnAction(e -> handleButtonClick(finalI, finalJ));
                gridPane.add(button, j, i);
            }
        }
        return gridPane;
    }


    private void handleButtonClick(int row, int col) {
        if (grid[row][col] == 0 && playerTurn) {
            int card = cards.remove(0);
            grid[row][col] = card;
            updateButton(row, col, card);
            calculateScore(card);
            if (cards.size() <= 27) {
                endGame();
            } else {
                playerTurn = false;
                playComputerTurn();
            }
        }
    }

    private void playComputerTurn() {
        if (!cards.isEmpty()) {
            int randomIndex = new Random().nextInt(cards.size());
            int card = cards.remove(randomIndex);

            // Introduce a delay before the computer's turn.
            new Thread(() -> {
                try {
                    Thread.sleep(500); // 500 milliseconds (0.5 seconds) delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Ensure JavaFX updates are done on the JavaFX application thread.
                Platform.runLater(() -> {
                    for (int i = 0; i < GRID_SIZE; i++) {
                        for (int j = 0; j < GRID_SIZE; j++) {
                            if (grid[i][j] == 0) {
                                grid[i][j] = card;
                                updateButton(i, j, card);
                                calculateScore(card);
                                playerTurn = true;
                                if (cards.size() <= 27) {
                                    endGame();
                                }
                                return;
                            }
                        }
                    }
                    playerTurn = true;
                });
            }).start();
        }
    }

    private void updateButton(int row, int col, int card) {
        Button button = (Button) gridPane.getChildren().get(row * GRID_SIZE + col);
        button.setText(String.valueOf(card));
    }

    private void calculateScore(int card) {
        int rowPoints = calculateRowPoints();
        int columnPoints = calculateColumnPoints();
        int diagonalPoints = calculateDiagonalPoints();

        if (playerTurn) {
            playerScore += rowPoints + columnPoints + diagonalPoints;
        } else {
            computerScore += rowPoints + columnPoints + diagonalPoints;
        }

        showScores();
    }

    private int calculateRowPoints() {
        int rowPoints = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            int count = 1;
            for (int j = 1; j < GRID_SIZE; j++) {
                if (grid[i][j] == grid[i][j - 1] && grid[i][j] != 0) {
                    count++;
                } else {
                    rowPoints += getRowPoints(count);
                    count = 1;
                }
            }
            rowPoints += getRowPoints(count);
        }
        return rowPoints;
    }

    private int calculateColumnPoints() {
        int columnPoints = 0;
        for (int j = 0; j < GRID_SIZE; j++) {
            ArrayList<Integer> numbersInCol = new ArrayList<>();
            int count = 1;
            for (int i = 1; i < GRID_SIZE; i++) {
                numbersInCol.add(grid[i][j]);
                if (grid[i][j] == grid[i - 1][j] && grid[i][j] != 0) {
                    count++;
                } else {
                    columnPoints += getRowPoints(count);
                    count = 1;
                }
            }
            columnPoints += getRowPoints(count);
        }
        return columnPoints;
    }

    private int calculateDiagonalPoints() {
        int diagonalPoints = 0;

        int count = 1;
        for (int i = 1; i < GRID_SIZE; i++) {
            if (grid[i][i] == grid[i - 1][i - 1] && grid[i][i] != 0) {
                count++;
            } else {
                diagonalPoints += getDiagonalPoints(count);
                count = 1;
            }
        }
        diagonalPoints += getDiagonalPoints(count);

        count = 1;
        for (int i = 1; i < GRID_SIZE; i++) {
            if (grid[i][GRID_SIZE - i - 1] == grid[i - 1][GRID_SIZE - i] && grid[i][GRID_SIZE - i - 1] != 0) {
                count++;
            } else {
                diagonalPoints += getDiagonalPoints(count);
                count = 1;
            }
        }
        diagonalPoints += getDiagonalPoints(count);

        return diagonalPoints;
    }

    private int getRowPoints(int count) {
        switch (count) {
            case 2:
                return 10;
            case 3:
                return 40;
            case 4:
                return 80;
            case 5:
                return 50;
            default:
                return 0;
        }
    }

    private int getDiagonalPoints(int count) {
        switch (count) {
            case 2:
                return 20;
            case 3:
                return 50;
            case 4:
                return 90;
            case 5:
                return 60;
            default:
                return 0;
        }
    }

    private void showScores() {
        HBox scoresBox = new HBox(20);
        scoresBox.setAlignment(Pos.CENTER);
        Label playerScoreLabel = new Label("Очки игрока: " + playerScore);
        Label computerScoreLabel = new Label("Очки компьютера: " + computerScore);
        scoresBox.getChildren().addAll(playerScoreLabel, computerScoreLabel);

        rootBorderPane.setBottom(scoresBox);
    }

    private void endGame() {
        String result = "Игра завершена!";
        if (computerScore > playerScore) {
            result = "Игра завершена!\nПобедил компьютер";
        } else if (computerScore < playerScore) {
            result = "Игра завершена!\nПобедил игрок";
        } else if (computerScore == playerScore) {
            result = "Игра завершена!\nНичья!";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
        alert.setTitle("Игра окончена");
        alert.show();
    }
}
