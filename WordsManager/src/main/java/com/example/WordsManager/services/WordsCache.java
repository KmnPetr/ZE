package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class WordsCache {
    private final List<Word> listAllWords = new ArrayList<>();
    private Integer latest_words_version_update = 0;
}
