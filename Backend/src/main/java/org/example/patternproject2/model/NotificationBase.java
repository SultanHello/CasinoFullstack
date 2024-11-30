package org.example.patternproject2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Table(name = "Notifications")
@Entity@Data
public class NotificationBase implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notifications;


    @Override
    public NotificationBase clone() {
        try {
            NotificationBase clone = (NotificationBase) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
