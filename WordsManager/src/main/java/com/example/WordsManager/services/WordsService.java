package com.example.WordsManager.services;

import com.example.WordsManager.models.Word;
import com.example.WordsManager.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@EnableScheduling
public class WordsService {
    private final WordsRepository wordsRepository;
    private final WordsCache wordsCache;
    private final PropService propService;
    @Autowired
    public WordsService(WordsRepository wordsRepository, WordsCache wordsCache, PropService propService) {
        this.wordsRepository = wordsRepository;
        this.wordsCache = wordsCache;
        this.propService = propService;
    }

    /**
     * метод возвращает флакс на запрос списка всех Words из DB
     */
    public Flux<Word> getAllWords(){
        return Flux
                .fromIterable(wordsCache.getListAllWords());
    }

    /**
     * метод возвращает список моно на запрос одного экземпляра Word из DB по id
     */
    public Mono<Word> getWordById(Integer idWord) {
        return wordsRepository.findById(idWord);
    }
    
    @Scheduled(fixedDelay = 2000)
    private void upgradeCache(){
        Integer DBdicVers = Integer.valueOf(Objects.requireNonNull(propService.getDictionaryVersion().block()).getValue());

        if (DBdicVers>wordsCache.getDictionaryVersion()){
            List<Word> newList = wordsRepository.findAll().toStream().toList();
            wordsCache.setListAllWords(newList);

            wordsCache.setDictionaryVersion(DBdicVers);

            log.info("Произведена замена кэша.");
        }
    }
}