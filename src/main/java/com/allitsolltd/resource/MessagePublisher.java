package com.allitsolltd.resource;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allitsolltd.config.MQConfig;
import com.allitsolltd.model.CustomMessage;

@RestController
@RequestMapping("rabbitmq")
public class MessagePublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private DirectExchange exchange;
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody CustomMessage message) {
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		template.convertAndSend(exchange.getName(),MQConfig.ROUTING_KEY_A,message);
		
		
		return "Message Publish";
		
	}

}
