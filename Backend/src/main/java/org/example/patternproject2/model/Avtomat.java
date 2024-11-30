package org.example.patternproject2.model;

import lombok.AllArgsConstructor;
import org.example.patternproject2.perositories.NotificationRepository;
import org.example.patternproject2.perositories.UserRepository;

import java.util.Random;

@AllArgsConstructor
public class Avtomat {
    private final UserRepository repository;
    private final NotificationRepository notificationRepository;

    public void play(Long id,int coin) {

        User user = repository.findById(id).orElseThrow(null);
        NotificationObserver observer = new NotificationObserver(user,notificationRepository);
        user.addObserver(observer);
        user.updateCoins(coin);
        repository.save(user);
    }
    void upLucky(Long id){
        User user = repository.findById(id).orElseThrow(null);

        user.increaseLuck(0.1);
        repository.save(user);
    }
    double getLucky(Long id){
        User user = repository.findById(id).orElseThrow(null);
        return user.getLacky();
    }
    double getAge(Long id){
        User user = repository.findById(id).orElseThrow(null);
        return user.getAge();
    }


}