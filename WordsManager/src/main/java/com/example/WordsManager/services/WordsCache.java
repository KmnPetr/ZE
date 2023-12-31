package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class WordsCache {
    private List<Word> listAllWords = new ArrayList<>();
    private Integer latest_words_version_update = 0;

    public WordsCache() {
        listAllWords.add(
                new Word(
                        0,
                        "testWord",
                        "[testWord]",
                        "тестовое Слово",
                        "тестовое Слово",
                        null,
                        null,
                        "тестовая группа",
                        0)); //TODO надо будет убрать это слово из кэша
    }
}
