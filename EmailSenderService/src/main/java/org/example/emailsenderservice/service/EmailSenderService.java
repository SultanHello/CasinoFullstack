package org.example.emailsenderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.emailsenderservice.models.Sender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service

@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
//    private final RestTemplate restTemplate;
//    Map<String ,String > map;
//    @KafkaListener(topics = "my-topic-email", groupId = "my-group2")
//    public void listenToObjectMessage(Map<String,String> map) {
//        this.map=map;
//        sendEmail();
//    }

    public void sendEmail(Sender sender) {



        try {
            String subject = "GameZone";
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("asimbek06@mail.ru");
            message.setTo(sender.getEmail());
            message.setSubject(subject);
            message.setText(sender.getMessage());

            mailSender.send(message);
        }catch (Exception e){

            throw new RuntimeException("error with Java Mail Sender");
        }

    }

}
