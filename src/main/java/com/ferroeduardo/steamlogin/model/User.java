package com.ferroeduardo.steamlogin.model;

public class User {
    private Long   id;
    private String steamId;
    private String username;

    public User() {
    }

    public User(Long id, String steamId, String username) {
        this.id = id;
        this.steamId = steamId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
