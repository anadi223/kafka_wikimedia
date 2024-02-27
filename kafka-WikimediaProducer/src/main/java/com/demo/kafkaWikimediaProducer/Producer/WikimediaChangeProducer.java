package com.demo.kafkaWikimediaProducer.Producer;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangeProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeProducer.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() {
        String topic = "wikimedia_recentchange";

        //to read real time stream data from wikimedia we use event source
        // (library okhttp Event source and since we are dealing with json data we also need jackson core and jackson databind

        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        BackgroundEventSource eventSource = new BackgroundEventSource.Builder(eventHandler,new EventSource.Builder(ConnectStrategy.http(URI.create(url)))).build();
        eventSource.start();
        try {
            TimeUnit.MINUTES.sleep(10);
        }catch (InterruptedException ex){
            LOGGER.error(String.format("Error :%e",ex.getMessage()));
            throw new RuntimeException("An error has occurred");
        }
    }
}
