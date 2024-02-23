package eu.swapnilsrivastava.swapnilsrivastava;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class SwapnilsrivastavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapnilsrivastavaApplication.class, args);
	}

	// Creating the topic using TopicBuilder which internally utlises KafkaAdmin API
	// @Bean
	// NewTopic helloSwapnil() {
	// 	return TopicBuilder.name("hello-swapnil").partitions(12).replicas(13).build();
	// }

}

@RequiredArgsConstructor
@Component
class Producer {

	private final KafkaTemplate<Integer, String> template;

	// Createing a data in the topic using KafkaTemplate which internally using Kafka Producer API
	@EventListener(ApplicationStartedEvent.class)
	public void generate() {
		template.send("hello_world", 3, "swapnil-grey");
	}
}

@Component
class Consumer {

	// Reading the data using kafka listener annotation which internally using the Kafka Consumer API
	@KafkaListener(topics = {"hello_world"}, groupId = "springboot-swapnil")
	public void listen(ConsumerRecord<Integer, String> record) {
		System.out.println("LOADING =========== " + "value:" + record.value() + " key:" + record.key());
	}

}