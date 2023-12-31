package com.example.WordsManager.services;

import com.example.WordsManager.models.PropModel;
import com.example.WordsManager.models.Word;
import com.example.WordsManager.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
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
        return Flux.fromIterable(wordsCache.getListAllWords());
    }

    /**
     * метод возвращает список моно на запрос одного экземпляра Word из DB по id
     */
    public Mono<Word> getWordById(Integer idWord) {
        return wordsRepository.findById(idWord);
    }


    /**
     * метод в цикле следит за актуальностью кэша в обьекте WordsCache
     */
    @Scheduled(fixedDelay = 500) // Запуск каждые 500 миллисекунд строго после выполнения предыдущего цикла
    protected void updateCache() {
        log.info("Начало работы метода updateCache()");

        Integer cacheVersion = wordsCache.getLatest_words_version_update();
        Integer DBversion = Integer.valueOf(propService.get_Latest_words_version_update().block().getValue());
        log.info("DBversion: "+ DBversion);

        if (Objects.equals(cacheVersion, DBversion)){
            log.info("Размер до обращения в бд"+ wordsCache.getListAllWords().size());
            wordsCache.setListAllWords(wordsRepository.findAll().collectList().block());
            log.info("Размер после обращения в бд"+ wordsCache.getListAllWords().size());
        }

        log.info("Завершение работы метода updateCache()");
    }


}
