package com.example.template;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Objects;

public class HelloController {

@FXML
public TextField txtInput;
@FXML
public Label lblDisplay;
public GridPane gdpGrid;
public ListView lstWords;
public Button btnEasy, btnMedium, btnHard, btnAnimals, btnRestart, btnMakePuzzle;
public Label lblEnd;
public Button btnMakeGrid;
public Button btnFood;
public Button btnSports;
    public Button btnCheck;
    private ArrayList<GridSpot> board = new ArrayList<>();
private int gridSize = 0;
private Button[][] boardSpotsBtn;
private int rowIndex, colIndex;

private TextFile file = new TextFile();
private ArrayList<String> words = new ArrayList<>();
private String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
private ArrayList<Integer> coordinates = new ArrayList<>();
private String chosenDirection;
private int lengthOfWord = 0;
private String category = "";
private ArrayList<String> wordsAdded = new ArrayList<>();
private boolean medium = false;
private boolean hard = false;








public void handleMakePuzzle(ActionEvent actionEvent) {
for (String word: words){
    String whichDirection;
    GridSpot pickSpot;
    ArrayList<GridSpot> openSpots = new ArrayList<>();
    for (GridSpot spots : board) {
        if (boardSpotsBtn[spots.getRow()][spots.getColumn()].getText().equals("-")){
            openSpots.add(spots);
        }
    }

    if (!openSpots.isEmpty()){
        int randomSpot = (int) (Math.random()*openSpots.size());
        pickSpot = openSpots.get(randomSpot);
        ArrayList<String> possibleDirections = new ArrayList<>(pickSpot.findPossibleSpots(word,boardSpotsBtn, medium, hard));
        int randomDirection = (int) (Math.random()* possibleDirections.size());
        if (!possibleDirections.isEmpty()){
            whichDirection = possibleDirections.get(randomDirection);
            placeLetters(word,pickSpot,whichDirection);
            wordsAdded.add(word);
        }
    }



}
updateList();

//for (int i = 0; i< boardSpotsBtn.length; i++){
//    for (int j=0; j< boardSpotsBtn[0].length; j++){
//        if (Objects.equals(boardSpotsBtn[i][j].getText(), "-")){
//            int randomLetter = (int) (Math.random()*boardSpotsBtn.length);
//            boardSpotsBtn[i][j].setText(alphabets[randomLetter]);
//        }
//    }
//}

btnMakePuzzle.setDisable(true);
btnCheck.setDisable(false);



}

public void placeLetters(String word, GridSpot pickSpot, String whichDirection){
for (int i = 0; i < word.length(); i++) {
    if (pickSpot != null){
        if (whichDirection.equals("Right")) {
            if (pickSpot.getColumn() + i <= boardSpotsBtn[0].length) {
                boardSpotsBtn[pickSpot.getRow()][pickSpot.getColumn() + i].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Left")) {
            if (pickSpot.getColumn() - i >= 0) {
                boardSpotsBtn[pickSpot.getRow()][pickSpot.getColumn() - i].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Down")) {
            if (pickSpot.getRow() + i <= boardSpotsBtn.length) {
                boardSpotsBtn[pickSpot.getRow() + i][pickSpot.getColumn()].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Up")) {
            if (pickSpot.getRow() - i >= 0) {
                boardSpotsBtn[pickSpot.getRow() - i][pickSpot.getColumn()].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Right-Down")) {
            if (pickSpot.getRow() + i <= boardSpotsBtn.length && pickSpot.getColumn() + i <= boardSpotsBtn[0].length) {
                boardSpotsBtn[pickSpot.getRow() + i][pickSpot.getColumn() + i].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Left-Up")) {
            if (pickSpot.getRow() - i >= 0 && pickSpot.getColumn() - i >= 0) {
                boardSpotsBtn[pickSpot.getRow() - i][pickSpot.getColumn() - i].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Right-Up")) {
            if (pickSpot.getRow() - i >= 0 && pickSpot.getColumn() + i <= boardSpotsBtn[0].length) {
                boardSpotsBtn[pickSpot.getRow() - i][pickSpot.getColumn() + i].setText(word.substring(i, i + 1));
            }
        } else if (whichDirection.equals("Left-Down")) {
            if (pickSpot.getRow() + i <= boardSpotsBtn.length && pickSpot.getColumn() - i >= 0) {
                boardSpotsBtn[pickSpot.getRow() + i][pickSpot.getColumn() - i].setText(word.substring(i, i + 1));
            }
        }
    }
}
}


public void checkDirectionToSolve(){
int startRow = coordinates.get(0);
int startColumn = coordinates.get(1);
int endRow = coordinates.get(2);
int endColumn = coordinates.get(3);
if (startRow == endRow){
    if (startColumn < endColumn){
        chosenDirection = "Right";
    }else {
        chosenDirection = "Left";
    }
    lengthOfWord = Math.abs(endColumn - startColumn) + 1;

}else if (startColumn == endColumn){
    if (startRow < endRow){
        chosenDirection = "Down";

    }else {
        chosenDirection = "Up";

    }
    lengthOfWord = Math.abs(endRow - startRow) + 1;
}else if (Math.abs(endRow - startRow) == Math.abs(endColumn - startColumn)){
    if (startRow < endRow && startColumn < endColumn) {
        chosenDirection = "Right-Down";
    } else if (startRow > endRow && startColumn > endColumn) {
        chosenDirection = "Left-Up";
    } else if (startRow > endRow && startColumn < endColumn) {
        chosenDirection = "Right-Up";
    } else {
        chosenDirection = "Left-Down";
    }
    lengthOfWord = Math.abs(endRow - startRow) + 1;
}
}


public void handleCheck(ActionEvent actionEvent) {
checkDirectionToSolve();

String userAnswer = "";
int startRow = coordinates.get(0);
int startColumn = coordinates.get(1);
for (int i = 0; i < lengthOfWord; i++) {
    if (chosenDirection.equals("Right")) {
        userAnswer += boardSpotsBtn[startRow][startColumn + i].getText();
    } else if (chosenDirection.equals("Left")) {
        userAnswer += boardSpotsBtn[startRow][startColumn - i].getText();
    } else if (chosenDirection.equals("Down")) {
        userAnswer += boardSpotsBtn[startRow + i][startColumn].getText();
    } else if (chosenDirection.equals("Up")) {
        userAnswer += boardSpotsBtn[startRow - i][startColumn].getText();
    } else if (chosenDirection.equals("Right-Down")) {
        userAnswer += boardSpotsBtn[startRow + i][startColumn + i].getText();
    } else if (chosenDirection.equals("Left-Up")) {
        userAnswer += boardSpotsBtn[startRow - i][startColumn - i].getText();
    } else if (chosenDirection.equals("Right-Up")) {
        userAnswer += boardSpotsBtn[startRow - i][startColumn + i].getText();
    } else if (chosenDirection.equals("Left-Down")) {
        userAnswer += boardSpotsBtn[startRow + i][startColumn - i].getText();
    }
}

for (int i = wordsAdded.size() -1; i >= 0; i--){
    if (userAnswer.equals(wordsAdded.get(i))) {
        for (int j = 0; j < lengthOfWord; j++) {
            if (chosenDirection.equals("Right")) {
                boardSpotsBtn[startRow][startColumn + j].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Left")) {
                boardSpotsBtn[startRow][startColumn - j].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Down")) {
                boardSpotsBtn[startRow + j][startColumn].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Up")) {
               boardSpotsBtn[startRow - j][startColumn].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Right-Down")) {
                boardSpotsBtn[startRow + j][startColumn + j].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Left-Up")) {
                boardSpotsBtn[startRow - j][startColumn - j].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Right-Up")) {
                boardSpotsBtn[startRow - j][startColumn + j].setFont(Font.font("System", FontWeight.BOLD, 14));
            } else if (chosenDirection.equals("Left-Down")) {
                boardSpotsBtn[startRow + j][startColumn - j].setFont(Font.font("System", FontWeight.BOLD, 14));
            }
        }

        wordsAdded.remove(i);
        updateList();
    }

}

if (wordsAdded.isEmpty()){
    lblEnd.setText("All Words Found!! GAME OVER!");
    btnRestart.setVisible(true);
    btnAnimals.setDisable(true);
    btnCheck.setDisable(true);
}

for (int i = 0; i < boardSpotsBtn.length; i++) {
    for (int j = 0; j < boardSpotsBtn[0].length; j++) {
        boardSpotsBtn[i][j].setStyle(null);
        coordinates.clear();
    }
}


System.out.println(userAnswer);


}

public void updateList(){
lstWords.getItems().clear();
for (int i = 0; i < wordsAdded.size(); i++) {
    lstWords.getItems().add(wordsAdded.get(i));
}
}


public void handleEasy(ActionEvent actionEvent) {
    if (category.equals("Animals")){
        words = file.loadEasyAnimals();
    }else if (category.equals("Food")){
        words = file.loadEasyFood();
    }else if (category.equals("Sports")){
        words = file.loadEasySports();
    }

    btnEasy.setDisable(true);
    btnMedium.setDisable(true);
    btnHard.setDisable(true);
    btnMakePuzzle.setDisable(false);

    medium = false;
    hard = false;

}

public void handleHard(ActionEvent actionEvent) {
    if (category.equals("Animals")){
        words = file.loadHardAnimals();
    }else if (category.equals("Food")){
        words = file.loadHardFood();
    }else if (category.equals("Sports")){
        words = file.loadHardSports();
    }

    btnEasy.setDisable(true);
    btnMedium.setDisable(true);
    btnHard.setDisable(true);
    btnMakePuzzle.setDisable(false);

    medium = false;
    hard = true;
}

public void handleMedium(ActionEvent actionEvent) {
    if (category.equals("Animals")){
        words = file.loadMediumAnimals();
    }else if (category.equals("Food")){
        words = file.loadMediumFood();
    }else if (category.equals("Sports")){
        words = file.loadMediumSports();
    }

    btnEasy.setDisable(true);
    btnMedium.setDisable(true);
    btnHard.setDisable(true);
    btnMakePuzzle.setDisable(false);

    medium = true;
    hard = false;
}

public void handleAnimals(ActionEvent actionEvent) {
    category = "Animals";
    btnAnimals.setDisable(true);
    btnSports.setDisable(true);
    btnFood.setDisable(true);
    btnEasy.setDisable(false);
    btnMedium.setDisable(false);
    btnHard.setDisable(false);

}

public void handleRestart(ActionEvent actionEvent) {
    coordinates.clear();
    for (int i = 0; i < boardSpotsBtn.length; i++){
        for (int j = 0; j < boardSpotsBtn[0].length; j++){
            boardSpotsBtn[i][j].setText("-");
            boardSpotsBtn[i][j].setFont(Font.font("System", FontWeight.NORMAL, 14));
        }
    }
    words.clear();
    wordsAdded.clear();
    updateList();
    btnAnimals.setDisable(false);
    btnSports.setDisable(false);
    btnFood.setDisable(false);
    btnMakeGrid.setDisable(false);
    txtInput.setDisable(false);
    lblEnd.setText("");
    btnRestart.setVisible(false);

}

public void handleMakeGrid(ActionEvent actionEvent) {
    gridSize = Integer.parseInt(txtInput.getText());
    gdpGrid.setGridLinesVisible(false);
    gdpGrid.getChildren().clear();
    gdpGrid.setGridLinesVisible(true);
    boardSpotsBtn = new Button[gridSize][gridSize];
    gdpGrid.setHgap(10);
    gdpGrid.setVgap(10);
    gdpGrid.setPadding(new Insets(10));
    gdpGrid.setGridLinesVisible(true);
    gdpGrid.setAlignment(Pos.CENTER);

    for (int i = 0; i < boardSpotsBtn.length; i++) {
        for (int j = 0; j < boardSpotsBtn[0].length; j++) {
            boardSpotsBtn[i][j] = new Button("-");
            boardSpotsBtn[i][j].setPrefHeight(200);
            boardSpotsBtn[i][j].setPrefWidth(200);
            gdpGrid.add(boardSpotsBtn[i][j], j, i);
            board.add(new GridSpot(i, j));
        }
    }

    updateList();


    EventHandler<ActionEvent> z = new EventHandler<>() {
        @Override
        public void handle(ActionEvent t) {
            rowIndex = GridPane.getRowIndex(((Button) t.getSource()));
            colIndex = GridPane.getColumnIndex(((Button) t.getSource()));

            if (Objects.equals(boardSpotsBtn[rowIndex][colIndex].getStyle(), "-fx-background-color: yellow;")){
                boardSpotsBtn[rowIndex][colIndex].setStyle(null);

                ArrayList<Integer> newCoordinates = new ArrayList<>();
                for (int i = 0; i < coordinates.size(); i += 2) {
                    if (!(coordinates.get(i) == rowIndex && coordinates.get(i + 1) == colIndex)) {
                        newCoordinates.add(coordinates.get(i));
                        newCoordinates.add(coordinates.get(i + 1));
                    }
                }
                coordinates = newCoordinates;
            }else {
                boardSpotsBtn[rowIndex][colIndex].setStyle("-fx-background-color: yellow;");
                if (coordinates.size() >= 4) {
                    coordinates.clear();
                }
                coordinates.add(rowIndex);
                coordinates.add(colIndex);
            }

        }
    };

    for (int i = 0; i < boardSpotsBtn.length; i++) {
        for (int j = 0; j < boardSpotsBtn[0].length; j++) {
            boardSpotsBtn[i][j].setOnAction(z);
        }
    }
    btnMakeGrid.setDisable(true);
    btnAnimals.setDisable(false);
    btnSports.setDisable(false);
    btnFood.setDisable(false);
    txtInput.setDisable(true);
}

public void handleFood(ActionEvent actionEvent) {
    category = "Food";
    btnAnimals.setDisable(true);
    btnSports.setDisable(true);
    btnFood.setDisable(true);
    btnEasy.setDisable(false);
    btnMedium.setDisable(false);
    btnHard.setDisable(false);
}

public void handleSports(ActionEvent actionEvent) {
    category = "Sports";
    btnAnimals.setDisable(true);
    btnSports.setDisable(true);
    btnFood.setDisable(true);
    btnEasy.setDisable(false);
    btnMedium.setDisable(false);
    btnHard.setDisable(false);
}



}