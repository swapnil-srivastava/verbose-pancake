package eu.swapnilsrivastava.swapnilsrivastava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class SwapnilsrivastavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapnilsrivastavaApplication.class, args);
	}

}

@RequiredArgsConstructor
@Component
class Producer {

	private final KafkaTemplate<Integer, String> template;

	@EventListener(ApplicationStartedEvent.class)
	public void generate() {
		template.send("hello", 7, "swapnilsrivastava");
	}
}

@Component
class Consumer {

	@KafkaListener(topics = {"hello"}, groupId = "springboot-swapnil")
	public void consume(String message) {
		System.out.println("LOADING ..........." + message);
	}

}