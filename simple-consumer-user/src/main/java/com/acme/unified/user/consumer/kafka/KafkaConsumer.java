package com.acme.unified.user.consumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "create-user")
	public void receiveMessage(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		
		LOGGER.info("Received message='{}' on topic='{}'", message, topic);
		
	}	
	
}

