package com.acme.unified.user.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;	

	public KafkaProducer() {
		super();
	}

	public ListenableFuture<SendResult<String, String>> send(String topic, String message) {
		return kafkaTemplate.send(topic, message);
	}	
	
}
