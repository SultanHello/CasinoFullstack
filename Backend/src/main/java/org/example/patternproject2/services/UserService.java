package org.example.patternproject2.services;


import lombok.AllArgsConstructor;
import org.example.patternproject2.connection.ConnectionEmail;
import org.example.patternproject2.model.*;
import org.example.patternproject2.model.DTO.LogUser;
import org.example.patternproject2.perositories.NotificationRepository;
import org.example.patternproject2.perositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final ConnectionEmail connectionEmail;
    @Autowired

    private final AuthenticationManager manager;

    @Autowired
    private final JwtService jwtService;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public String saveUser(User user){
        User newUser = new User.UserBuilder()
                .id(user.getId())
                .name(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .coin(user.getCoin()+100)
                .age(user.getAge())

                .build();


        return jwtService.generateToken(userRepository.save(newUser));
    }
    public String login(LogUser logUser) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(logUser.getEmail(),logUser.getPassword()));
        User user = userRepository.findByEmail(logUser.getEmail());
        return jwtService.generateToken(user);
    }
    public String playAll(String token,int coin){
        Avtomat avtomat = new Avtomat(userRepository,notificationRepository);
        OnlineCasino casino = new OnlineCasinoAdapterProxy(avtomat);
        Bucmaker bucmaker = new BucmakerAdapterProxy(avtomat);
        Random random = new Random();
        CasinoFacade allCasinoPlay= new CasinoFacade(bucmaker,casino);


        User user = userRepository.findByEmail(jwtService.extractUsername(token));
        Long id= user.getId();
        String message = allCasinoPlay.playAllVariants(id,coin,random.nextInt(5));

        connectionEmail.sendMessage(message,user.getEmail());

        return message;
    }
    public String playOnlineCasino(String token,int coin){
        Avtomat avtomat = new Avtomat(userRepository,notificationRepository);
        OnlineCasino casino = new OnlineCasinoAdapterProxy(avtomat);
        Random random = new Random();

        User user = userRepository.findByEmail(jwtService.extractUsername(token));
        Long id= user.getId();
        String message =casino.play(id,coin,random.nextInt(5));

        connectionEmail.sendMessage(message,user.getEmail());
        return message;
    }
    public String playBukmaker(String token,int coin){
        Avtomat avtomat = new Avtomat(userRepository,notificationRepository);
        Bucmaker casino = new BucmakerAdapterProxy(avtomat);
        Random random = new Random();

        User user = userRepository.findByEmail(jwtService.extractUsername(token));
        Long id= user.getId();
        String message =casino.play(id,coin,random.nextInt(5));

        connectionEmail.sendMessage(message,user.getEmail());
        return message;



    }

    public List<String> getNotifications() {
        return notificationRepository.findAll().stream()
                .map(NotificationBase::getNotifications)
                .collect(Collectors.toList());
    }

    public String getEmail(String token) {
        return jwtService.extractUsername(token);
    }

    public int getCoins(String token) {
        User user =userRepository.findByEmail(jwtService.extractUsername(token));
        return user.getCoin();

    }


}
