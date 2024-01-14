package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BackupWordService {
    private final WordsService wordsService;
    @Autowired
    public BackupWordService(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    /**
     * метод создаст новый файл.sql в папке res/backup
     * с INSERT запросом всех words из БД
     * запрос формируется в отсортированном по id порядке под какими id они лежали в бд
     */
    @Async
    public void saveWords() {

        String filePath = "C:/Users/Petr/git/ZE/WordsManager/src/main/resources/backup/";

        // Форматирование текущей даты и времени
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_(HH-mm-ss)");

        String fileName = "wordsSave__"+ LocalDateTime.now().format(formatter) +".sql";



        File file = new File(filePath+fileName);

        createFile(file);

        printTextIntoFile(file,buildList());
    }

    /**
     * составит список строк для записи в файл
     */
    private List<String> buildList() {
        List<String> rows = new ArrayList<>();

        rows.add("INSERT INTO word(foreign_word,transcription,translation,description,link_voice,link_image,topic,sorting_value)");
        rows.add("VALUES");
        rows.add("");

        List<Word> words = wordsService.getAllWords().toStream().toList();
        List<Word> sortedWords = words.stream().sorted(Comparator.comparingInt(Word::getId)).toList();

        for (int i = 0; i < sortedWords.size(); i++) {
            String row =
                    "("
                            + "'" + sortedWords.get(i).getForeign_word() + "',"
                            + "'" + sortedWords.get(i).getTranscription() + "',"
                            + "'" + sortedWords.get(i).getTranslation() + "',"
                            + "'" + sortedWords.get(i).getDescription() + "',"
                            + "'" + sortedWords.get(i).getLink_voice() + "',"
                            + "'" + sortedWords.get(i).getLink_image() + "',"
                            + "'" + sortedWords.get(i).getTopic() + "',"
                            + "'" + sortedWords.get(i).getSorting_value() + "'"
                    + ")";
            if (i != (sortedWords.size()-1)){
                rows.add(row+",");
            } else if (i == (sortedWords.size()-1)) {
                rows.add(row);
            }
        }

        return rows;
    }

    /**
     * заполнит файл данными
     */
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
