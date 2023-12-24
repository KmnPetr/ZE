package com.example.WordsManager.controllers;

import com.example.WordsManager.models.Word;
import com.example.WordsManager.services.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/words")
public class WordsController {
    private final WordsService wordsService;
    @Autowired
    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping
    public Flux<Word> getAllWords(){
        return wordsService.getAllWords();
    }

    @GetMapping("/{idWord}")
    public Mono<Word> getWordById(@PathVariable Integer idWord){
        return wordsService.getWordById(idWord);
    }
}
