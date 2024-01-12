package com.example.WordsManager.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class BackupWordService {

    @Async
    public void saveWords() {

        String filePath = "C:/Users/Petr/git/ZE/WordsManager/src/main/resources/backup/";
        String fileName = "file.sql";

        File file = new File(filePath+fileName);

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
