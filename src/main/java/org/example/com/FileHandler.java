package org.example.com;

import org.example.com.execeptions.CreateReverseFiboFileException;
import org.example.com.execeptions.LineWithFileException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String inputFilePath;
    private String outputFilePath;

    public FileHandler(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    private List<String> getListLineWithFile() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(inputFilePath));
        } catch (IOException e) {
            throw new LineWithFileException("File does not exist");
        }

        return lines;
    }

    private List<Integer> getListNumberFibo(List<String> lineList) {
        List<Integer> listFibo = new ArrayList<>();
        listFibo.add(0);
        listFibo.add(1);
        listFibo.add(2);
        int upperFibo = lineList.size();
        while (listFibo.get(listFibo.size() - 1) + listFibo.get(listFibo.size() - 2) <= upperFibo) {
            listFibo.add(listFibo.get(listFibo.size() - 1) + listFibo.get(listFibo.size() - 2));
        }

        return listFibo;
    }

    private List<String> getListStringFibo() {
        List<String> listLine = getListLineWithFile();
        List<Integer> numberFibo = getListNumberFibo(listLine);
        List<String> fiboListString = new ArrayList<>();
        for (Integer number : numberFibo) {
            fiboListString.indexOf(number);
            fiboListString.add(listLine.get(number));
        }

        return fiboListString;
    }

    private List<String> getReverseStringList(List<String> fiboListString) {
        List<String> reverseList = new ArrayList<>();
        for (String line : fiboListString) {
            char[] charArray = line.toCharArray();
            char[] reverseArray = new char[charArray.length];
            int count = 0;
            for (int i = charArray.length - 1; i >= 0; i--) {
                reverseArray[count] = charArray[i];
                count++;
            }
            reverseList.add(String.valueOf(reverseArray));
        }

        return reverseList;
    }

    private void createReverseFiboFile(List<String> reverseStringList) {
        try {
            File f = new File(outputFilePath);
            f.createNewFile();
            for (String line : reverseStringList) {
                String separateLine = line + "\n";
                Files.write(Paths.get("src/main/resources/output.txt"), separateLine.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException ioException) {
            throw new CreateReverseFiboFileException("File not created");
        }
    }

    public void createReverseFile() {

        createReverseFiboFile(getReverseStringList(getListStringFibo()));
    }

}
