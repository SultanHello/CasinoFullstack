package org.example.emailsenderservice.controller;


import lombok.AllArgsConstructor;
import org.example.emailsenderservice.models.Sender;
import org.example.emailsenderservice.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noti")
@AllArgsConstructor
public class EmailController {
    private final EmailSenderService emailSenderService;
    @PostMapping("/send")
    public void send(@RequestBody Sender sender){
        emailSenderService.sendEmail(sender);
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";


    }
}
