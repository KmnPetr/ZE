package com.example.WordsManager.repositories;

import com.example.WordsManager.models.VoiceFile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceFilesRepository extends ReactiveCrudRepository<VoiceFile, Integer> {
}
