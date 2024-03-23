package com.example.WordsManager.websocket;

import com.example.WordsManager.models.VoiceFile;
import com.example.WordsManager.services.VoiceFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * класс занимается обработкой входящих данных через вэбсокет
 */
@Component
@Slf4j
public class ReceivedDataManager {

    private final EmitterProcessor<String> emitterProcessor = EmitterProcessor.create();
    private final VoiceFileService voiceFileService;

    public ReceivedDataManager(VoiceFileService voiceFileService) {
        this.voiceFileService = voiceFileService;
    }

    public EmitterProcessor<String> getEmitterProcessor() {
        return emitterProcessor;
    }

    /**
     * метод приймет сообщение, пришедшее с сокета и пустит его в обработку
     */
    public void processMessage(byte[] bytes) {
        MessageProtocol mp = new MessageProtocol(bytes);

        route(mp);
    }

    private void route(MessageProtocol mp) {
        switch (mp.getType()) {
            case PING:
                sendMessage(mp);
                break;
            case VOICE:
                saveVoiceInFolder(mp);
                break;
            default:
                System.out.println("Опция не распознана");
                break;
        }
    }

    /**
     * сохранит пришедший Voice в локальной папке проекта
     */
    private void saveVoiceInFolder(MessageProtocol mp){
        String filename = mp.getHeaders().get("filename");
        try{
            boolean result = voiceFileService.saveVoiceInLocalFolder(
                    new VoiceFile(
                            0,
                            filename,
                            mp.getBody())
            );
            if (result){

                Map<String,String> headers = new HashMap<>();
                headers.put("filename",filename);

                MessageProtocol replyMessage = new MessageProtocol(
                        TypeEnum.SUCCESSFUL_VOICE_SAVING,
                        headers,
                        null
                );
                //отправляем данные в процессор
                sendMessage(replyMessage);
            }
        }catch (Exception e){
            e.printStackTrace();

            Map<String,String> headers = new HashMap<>();
            headers.put("message",e.getMessage());

            MessageProtocol error = new MessageProtocol(
                    TypeEnum.VOICE_ERROR,
                    headers,
                    null
            );
            //отправим ошибку, возникшую при сохранении файла
            sendMessage(error);
        }
    }

    /**
     * отправит сообщение в EmitterProcessor
     */
    private void sendMessage(MessageProtocol replyMessage){
        getEmitterProcessor().onNext(new String(replyMessage.getMessage(),StandardCharsets.UTF_8));
    }
}
