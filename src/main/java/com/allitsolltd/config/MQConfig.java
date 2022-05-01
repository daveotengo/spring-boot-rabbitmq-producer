/**
 * 
 */
package com.allitsolltd.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author daveotengo
 *
 */

@Configuration
public class MQConfig {
	
	public static final String QUEUE_A = "queue.A";
	public static final String QUEUE_B = "queue.B";

	public static final String EXCHANGE = "exchange.direct";
	public static final String ROUTING_KEY_A = "route.A";
	public static final String ROUTING_KEY_B = "route.B";


	
	@Primary
	@Bean
	public Queue queueA() {
		
		return new Queue(QUEUE_A,false);
		
	}
	
	@Bean
	public Queue queueB() {
		
		return new Queue(QUEUE_B,false);
		
	}
	
//	@Bean
//	public TopicExchange exchange() {
//		
//		return new TopicExchange(EXCHANGE);
//	}
	
	@Bean
	public DirectExchange exchange() {
		
		return new DirectExchange(EXCHANGE);
	}
	
	@Bean
	public Binding bindingA(Queue queue,DirectExchange exchange) {
		
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_A);
		
	}
	
	@Bean
	public Binding bindingB(Queue queue,DirectExchange exchange) {
		
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_B);
		
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
		
	}

//	@Bean
//	public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper mapper) {
//	    RabbitTemplate jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
//	    jsonRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
//	    return jsonRabbitTemplate;
//	}
}
