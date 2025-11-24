package com.example.template;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

public class GridSpot {
    int row;
    int column;
    ArrayList<String> possibleDirections = new ArrayList<>();

    public GridSpot(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public ArrayList<String> findPossibleSpots(String word, Button[][] boardSpotsBtn, Boolean medium, Boolean hard){
        possibleDirections.clear();

        if (column + word.length() <= boardSpotsBtn[0].length) {
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (hard) {
                    if (!Objects.equals(boardSpotsBtn[row][column + i].getText(), "-")){
                        if (!Objects.equals(boardSpotsBtn[row][column + i].getText(), word.substring(i, i + 1))){
                            notTaken = false;
                        }
                    }
                } else {
                    if (!Objects.equals(boardSpotsBtn[row][column + i].getText(), "-")) {
                        notTaken = false;
                    }
                }
            }

            if (notTaken){
                possibleDirections.add("Right");
            }
        }

        if (column - word.length() >= 0) {
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (hard) {
                    if (!Objects.equals(boardSpotsBtn[row][column - i].getText(), "-")){
                        if (!Objects.equals(boardSpotsBtn[row][column - i].getText(), word.substring(i, i + 1))){
                            notTaken = false;
                        }
                    }
                } else {
                    if (!Objects.equals(boardSpotsBtn[row][column - i].getText(), "-")) {
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Left");

            }
        }

        if (row + word.length() <= boardSpotsBtn.length && (medium || hard)) {
            boolean notTaken = true;

            for (int i=0;i<word.length();i++){
                if (hard) {
                    if (!Objects.equals(boardSpotsBtn[row + i][column].getText(), "-")){
                        if (!Objects.equals(boardSpotsBtn[row + i][column].getText(), word.substring(i, i + 1))){
                            notTaken = false;
                        }
                    }
                }else {
                    if (!Objects.equals(boardSpotsBtn[row + i][column].getText(), "-")) {
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Down");
            }
        }

        if (row - word.length() >= 0 && (medium || hard)) {
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (hard) {
                    if (!Objects.equals(boardSpotsBtn[row - i][column].getText(), "-")){
                        if (!Objects.equals(boardSpotsBtn[row - i][column].getText(), word.substring(i, i + 1))){
                            notTaken = false;
                        }
                    }
                } else {
                    if (!Objects.equals(boardSpotsBtn[row - i][column].getText(), "-")) {
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Up");
            }
        }

        if (row + word.length() <= boardSpotsBtn.length && column + word.length() <= boardSpotsBtn[0].length && hard){
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (!Objects.equals(boardSpotsBtn[row+i][column+i].getText(), "-")){
                    if (!Objects.equals(boardSpotsBtn[row+i][column+i].getText(), word.substring(i,i+1))){
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Right-Down");
            }

        }

        if (row-word.length()>= 0 &&column-word.length()>= 0 && hard){
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (!Objects.equals(boardSpotsBtn[row-i][column-i].getText(), "-")){
                    if (!Objects.equals(boardSpotsBtn[row-i][column-i].getText(), word.substring(i,i+1))){
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Left-Up");
            }
        }

        if (row-word.length()>= 0 && column + word.length() <= boardSpotsBtn[0].length && hard){
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (!Objects.equals(boardSpotsBtn[row-i][column+i].getText(), "-")){
                    if (!Objects.equals(boardSpotsBtn[row-i][column+i].getText(), word.substring(i,i+1))){
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Right-Up");
            }
        }

        if (row + word.length() <= boardSpotsBtn.length && column-word.length()>= 0 && hard){
            boolean notTaken = true;
            for (int i=0;i<word.length();i++){
                if (!Objects.equals(boardSpotsBtn[row+i][column-i].getText(), "-")){
                    if (!Objects.equals(boardSpotsBtn[row+i][column-i].getText(), word.substring(i,i+1))){
                        notTaken = false;
                    }
                }
            }
            if (notTaken){
                possibleDirections.add("Left-Down");
            }
        }

        return possibleDirections;
    }




}
