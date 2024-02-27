package com.example.kafkawikimediaconsumer;

import com.example.kafkawikimediaconsumer.entity.WikimediaData;
import com.example.kafkawikimediaconsumer.repository.WikimediaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    private WikimediaRepo wikimediaRepo;

    public KafkaWikimediaConsumer(WikimediaRepo wikimediaRepo) {
        this.wikimediaRepo = wikimediaRepo;
    }

    @KafkaListener(topics = "wikimedia_recentchange",groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received -> %s",eventMessage));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaRepo.save(wikimediaData);

    }
}
