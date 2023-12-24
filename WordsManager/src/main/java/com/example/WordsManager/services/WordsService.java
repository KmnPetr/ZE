package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import com.example.WordsManager.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WordsService {
    private final WordsRepository wordsRepository;
    @Autowired
    public WordsService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }
    public Flux<Word> getAllWords(){
        return wordsRepository.findAll();
    }
    public Mono<Word> getWordById(Integer idWord) {
        return wordsRepository.findById(idWord);
    }
}
