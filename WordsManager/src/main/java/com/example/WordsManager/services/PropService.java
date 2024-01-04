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
     * метод установит новое значение dictionary_version в таблице properties
     */
    @Transactional
    public void setDictionaryVersion() {
//        propRepository.setNewValue("dictionary_version", ZonedDateTime.now().toString()); //TODO теперь используем int вместо формата времени
    }

    /**
     * метод выдаст версию последнего обновления словаря Word БД
     */
    public Mono<PropModel> getDictionaryVersion() {
        return propRepository.findByKey("dictionary_version");
    }
}
