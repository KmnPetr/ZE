package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import com.example.WordsManager.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@EnableScheduling
public class WordsService {
    private final WordsRepository wordsRepository;
    private final WordsCache wordsCache;
    private final PropService propService;
    @Autowired
    public WordsService(WordsRepository wordsRepository, WordsCache wordsCache, PropService propServise) {
        this.wordsRepository = wordsRepository;
        this.wordsCache = wordsCache;
        this.propService = propServise;
    }

    /**
     * метод возвращает флакс на запрос списка всех Words из DB
     */
    public Flux<Word> getAllWords(){
        return wordsRepository.findAll();
    }

    /**
     * метод возвращает список моно на запрос одного экземпляра Word из DB по id
     */
    public Mono<Word> getWordById(Integer idWord) {
        return wordsRepository.findById(idWord);
    }
}