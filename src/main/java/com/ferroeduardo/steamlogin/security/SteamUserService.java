package com.ferroeduardo.steamlogin.security;

import com.ferroeduardo.steamlogin.model.User;
import com.ferroeduardo.steamlogin.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SteamUserService {
    private final UserRepository repository;

    public SteamUserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findBySteamId(String id) {
        return repository.findUserBySteamId(id);
    }

    public User save(User user) {
        return repository.save(user);
    }
}
