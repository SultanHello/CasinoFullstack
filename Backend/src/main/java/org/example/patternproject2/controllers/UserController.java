package org.example.patternproject2.controllers;


import lombok.AllArgsConstructor;
import org.example.patternproject2.model.DTO.LogUser;
import org.example.patternproject2.model.User;
import org.example.patternproject2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://127.0.0.1:8050")
public class UserController {
    @Autowired
    private final UserService serviceUser;


    @GetMapping("")
    public List<User> getUsers(){
        return serviceUser.getUsers();
    }
    @PostMapping("/register")
    public String saveUser(@RequestBody User user){
        return serviceUser.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LogUser logUser){
        return serviceUser.login(logUser);
    }

    @PostMapping("/playCasino")
    public String playCasino(@RequestHeader("Authorization") String authorizationHeader,@RequestBody int coin){
        String token = authorizationHeader.replace("Bearer ", "");
        System.out.println(12);
        return serviceUser.playOnlineCasino(token,coin);
    }
    @PostMapping("/playBukmaker")
    public String playBukmaker(@RequestHeader("Authorization") String authorizationHeader,@RequestBody int coin){
        String token = authorizationHeader.replace("Bearer ", "");
        return serviceUser.playBukmaker(token,coin);
    }
    @PostMapping("/playAllgames")
    public String playAll(@RequestHeader("Authorization") String authorizationHeader,@RequestBody int coin){
        String token = authorizationHeader.replace("Bearer ", "");
        return serviceUser.playAll(token,coin);
    }
    @GetMapping("/notifications")
    public  List<String> getNotifications(){
        return serviceUser.getNotifications();
    }


    @GetMapping("/getEmail")
    public String getEmail(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        return serviceUser.getEmail(token);
    }

    @GetMapping("/getCoins")
    public int getCoins(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        return serviceUser.getCoins(token);
    }



}
