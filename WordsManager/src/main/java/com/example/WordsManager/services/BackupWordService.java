package com.example.WordsManager.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackupWordService {

    @Async
    public void saveWords() {

        String filePath = "C:/Users/Petr/git/ZE/WordsManager/src/main/resources/backup/";
        String fileName = "file.sql";

        File file = new File(filePath+fileName);

        createFile(file);

        List<String> rows = new ArrayList<>();
        rows.add("Test row 1");
        rows.add("Test row 2");
        rows.add("Test row 3");
        rows.add("Test row 4");
        rows.add("Test row 5");
        rows.add("Test row 6");
        rows.add("Test row 7");
        rows.add("Test row 8");
        rows.add("Test row 9");
        rows.add("Test row 10");
        rows.add("Test row 11");
        rows.add("Test row 12");
        rows.add("Test row 13");
        rows.add("Test row 14");

        printTextIntoFile(file,rows);


    }

    private void printTextIntoFile(File file, List<String> rows) {
        PrintWriter printWriter;
        try{
            printWriter = new PrintWriter(file);

            for (String row:rows) {
                printWriter.println(row);
            }

            printWriter.close();


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * сюда вынесен код по созданию файла
     */
    private void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Файл был успешно создан.");
            } else {
                System.out.println("Файл уже существует.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
