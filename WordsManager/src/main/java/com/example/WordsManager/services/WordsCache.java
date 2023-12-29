package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordsCache {
    private final List<Word> listAllWords = new ArrayList<>();


}
