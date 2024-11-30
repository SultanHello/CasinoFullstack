package org.example.patternproject2.model;

import lombok.AllArgsConstructor;
import org.example.patternproject2.model.GameObserver;
import org.example.patternproject2.model.User;
import org.example.patternproject2.perositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class NotificationObserver implements GameObserver {
    private final User user;

    private final NotificationRepository repository;


    public NotificationObserver(User user, NotificationRepository repository) {
        this.user = user;

        this.repository = repository;
    }


    @Override
    public void update(String message) {
        user.getNotifiсations().add(message);
        NotificationBase notificationBaseForAll = new NotificationBase();
        notificationBaseForAll.setNotifications(message);



        notificationBaseForAll.setNotifications(message);
        repository.save(notificationBaseForAll);

        NotificationBase notificationBaseForLogger = notificationBaseForAll.clone();
        Logger.info(notificationBaseForLogger);
    }


}

