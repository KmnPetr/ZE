package com.example.WordsManager.services;

import com.example.WordsManager.models.PropModel;
import com.example.WordsManager.repositories.PropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class PropService {
    private final PropRepository propRepository;
    @Autowired
    public PropService(PropRepository propRepository) {
        this.propRepository = propRepository;
    }


    /**
     * метод установит новое значение update_at в таблице properties
     */
    @Transactional
    public void setUpdateAt() {
//        propRepository.setNewValue("latest_words_version_update", ZonedDateTime.now().toString());
    }

    /**
     * метод выдаст версию последнего обновления списка Word БД
     */
    public Mono<PropModel> get_Latest_words_version_update() {
        return propRepository.findByKey("latest_words_version_update");
    }
}
