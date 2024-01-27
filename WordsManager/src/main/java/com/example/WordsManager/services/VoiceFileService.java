package com.example.WordsManager.services;

import com.example.WordsManager.repositories.VoiceFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoiceFileService {
    private final VoiceFilesRepository voiceFilesRepository;

    @Autowired
    public VoiceFileService(VoiceFilesRepository voiceFilesRepository) {
        this.voiceFilesRepository = voiceFilesRepository;
    }


    /**
     * метод достанет из папки static/voice файлы и сохранит их в БД
     */
    @Async
    @Transactional
    public void saveFiles(){

    }
}
