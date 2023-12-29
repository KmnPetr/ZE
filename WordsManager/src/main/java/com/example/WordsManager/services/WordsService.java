package com.example.WordsManager.services;

import com.example.WordsManager.models.PropModel;
import com.example.WordsManager.models.Word;
import com.example.WordsManager.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
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
    public Flux<Word> getAllWords(){
        log.info("Метод: getAllWords() начался");
        Integer cacheVersion = wordsCache.getLatest_words_version_update();
        Mono<PropModel> DBVersion = propService.get_Latest_words_version_update();

        DBVersion.subscribe(System.out::println);


        log.info("Метод: getAllWords() закончился");
        return Flux.empty();
    }
    public Mono<Word> getWordById(Integer idWord) {
        return wordsRepository.findById(idWord);
    }
}
