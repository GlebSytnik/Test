package org.example;

import org.example.com.FileHandler;

public class App {
    public static void main( String[] args ) {
        String inputFile = "src/main/resources/source.txt";
        String outputFile = "src/main/resources/output.txt";
        FileHandler fileHandler = new FileHandler(inputFile, outputFile);
        fileHandler.createReverseFile();
    }
}
