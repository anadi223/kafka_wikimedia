package com.demo.kafkaWikimediaProducer;

import com.demo.kafkaWikimediaProducer.Producer.WikimediaChangeProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaWikimediaProducerApplication implements CommandLineRunner {

    public KafkaWikimediaProducerApplication(WikimediaChangeProducer wikimediaChangeProducer) {
        this.wikimediaChangeProducer = wikimediaChangeProducer;
    }

    public static void main(String[] args) {
		SpringApplication.run(KafkaWikimediaProducerApplication.class, args);
	}


	private WikimediaChangeProducer wikimediaChangeProducer;
	@Override
	public void run(String... args) throws Exception {
		wikimediaChangeProducer.sendMessage();
	}
}
