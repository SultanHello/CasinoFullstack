package org.example.patternproject2.connection;

import lombok.AllArgsConstructor;

import org.example.patternproject2.model.DTO.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ConnectionEmail {

    //@Autowired
    //private final RestTemplate restTemplate;




    public void sendMessage(String message, String email){


        try {
            RestTemplate restTemplate = new RestTemplate();
            Sender sender=new Sender();
            sender.setEmail(email);
            sender.setMessage(message);
            System.out.println(1);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            org.springframework.http.HttpEntity<Sender> entity = new HttpEntity<>(sender, headers);

            restTemplate.exchange("http://localhost:1234/noti/send", HttpMethod.POST, entity, String.class);

            System.out.println(2);

//            Map<String, String> map = new HashMap<>();
//            map.put("email",email);
//            map.put("message",message);
//            kafkaTemplate1.send("my-topic-email",map);
        }catch (Exception e){
            throw new RuntimeException("error when connect");
        }


    }


}
