package com.example.WordsManager.repositories;

import com.example.WordsManager.models.Word;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WordsRepository extends ReactiveCrudRepository<Word,Integer> {
}
