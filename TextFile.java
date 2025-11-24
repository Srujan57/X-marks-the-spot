package com.example.template;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFile {
    private ArrayList<String> stringArray = new ArrayList<>();



    public TextFile() {

    }


    public void makeTextFile() {
        String outputFile = "src/main/resources/puzzles.txt";
        try{
            PrintWriter out = new PrintWriter(outputFile);
            out.println("cat");
            out.println("toe");
//            out.println("word");



            out.close();
        }catch (FileNotFoundException var){
            System.out.println("no file with that name");
        }
    }

    public ArrayList<String> loadEasyAnimals() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/easyAnimals.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadMediumAnimals() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/mediumAnimals.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadHardAnimals() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/hardAnimals.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadEasyFood() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/easy_food_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadMediumFood() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/medium_food_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadHardFood() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/hard_food_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadEasySports() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/easy_sports_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadMediumSports() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/medium_sports_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }

    public ArrayList<String> loadHardSports() {
        try {
            stringArray.clear();
            FileReader reader = new FileReader("src/main/resources/hard_sports_words.txt");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                stringArray.add(in.nextLine());
            }
        }catch (FileNotFoundException var){
            System.out.println("File not found");
        }
        return stringArray;
    }


}
