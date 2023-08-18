package com.ferroeduardo.steamlogin.security;

import com.ferroeduardo.steamlogin.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SteamUserPrincipal implements UserDetails {
    private Long                                   id;
    private String                                 steamId;
    private String                                 username;
    private Map<String, Object>                    attributes;
    private Collection<? extends GrantedAuthority> authorities;

    public SteamUserPrincipal(Long id, String steamId, String username, Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.steamId = steamId;
        this.username = username;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public static SteamUserPrincipal create(User user, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new SteamUserPrincipal(user.getId(), user.getSteamId(), user.getUsername(), Collections.unmodifiableMap(attributes), authorities);
    }

    public long getId() {
        return id;
    }

    public String getSteamId() {
        return steamId;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
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
