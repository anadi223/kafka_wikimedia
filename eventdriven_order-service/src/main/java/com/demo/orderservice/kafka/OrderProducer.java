package com.demo.orderservice.kafka;

import com.demo.basedomains.DTO.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> template;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> template) {
        this.topic = topic;
        this.template = template;
    }

    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("Message send -> %s",event.toString()));

        //create message
        Message<OrderEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC,topic.name()).build();

        template.send(message);
    }
}
