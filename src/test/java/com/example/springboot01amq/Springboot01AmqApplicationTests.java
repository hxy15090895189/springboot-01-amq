package com.example.springboot01amq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
class Springboot01AmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Test
    void  cearte(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建完成");
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

    }

    @Test
    void contextLoads() {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("msg","这是一个信息");
        hashMap.put("data", Arrays.asList("helloWord",123,true));
        rabbitTemplate.convertAndSend("huangxinyu","muast",hashMap);
    }

    @Test
    void getMessage(){
        Object o = rabbitTemplate.receiveAndConvert("xiaoxiduilie1");
        System.out.println(o.getClass());
        System.out.println(o);
    }

}
