package org.example.patternproject2.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observer;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private int age;
    private int coin;
    private double lacky;

    @ElementCollection
    @CollectionTable(name = "user_notifications", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "notification")
    private List<String> notifiсations;

    @Transient
    private final List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    // Уведомление всех наблюдателей
    private void notifyObservers(String message) {
        for (GameObserver observer : observers) {
            observer.update(message);
        }
    }

    public void updateCoins(int amount) {
        this.coin += amount;
        notifyObservers(this.email+ " ->  Coins updated: " + amount + " coins added. New total: " + this.coin);
    }

    public void increaseLuck(double amount) {
        this.lacky += amount;
        notifyObservers(this.email+ " ->  Luck increased by: " + amount + ". Current luck: " + this.lacky);
    }
    private User(UserBuilder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.age = builder.age;
        this.coin = builder.coin;
        this.password=builder.password;
    }




    public static class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private int age;
        private int coin;


        public UserBuilder id(Long id) {
            this.id = id;

            return this;
        }

        public UserBuilder name(String name) {
            this.email = name;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder coin(int coin) {
            this.coin = coin;
            return this;
        }


        public User build() {
            return new User(this);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
