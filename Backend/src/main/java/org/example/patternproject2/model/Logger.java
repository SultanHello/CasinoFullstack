package org.example.patternproject2.model;

import java.time.LocalDateTime;

public class Logger {
    static void info(NotificationBase notificationBase){
        System.out.println(LocalDateTime.now()+" : "+notificationBase.getNotifications());

    }
}
