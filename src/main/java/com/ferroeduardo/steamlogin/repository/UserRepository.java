package com.ferroeduardo.steamlogin.repository;

import com.ferroeduardo.steamlogin.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    private static long       nextId   = 0;
    private final  List<User> userList = new ArrayList<>();

    public Optional<User> findUserBySteamId(String steamId) {
        return userList.stream().filter(user -> user.getSteamId().equals(steamId)).findFirst();
    }

    public User save(User user) {
        user.setId(nextId++);
        this.userList.add(user);

        return user;
    }
}
