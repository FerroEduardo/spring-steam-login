package com.ferroeduardo.steamlogin.security;

import com.ferroeduardo.steamlogin.model.User;
import com.ferroeduardo.steamlogin.service.SteamService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class SteamAuthenticationProvider implements AuthenticationProvider {

    private final SteamUserService userService;
    private final SteamService     steamService;

    public SteamAuthenticationProvider(SteamUserService userService, SteamService steamService) {
        this.userService = userService;
        this.steamService = steamService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String steamId = ((SteamAutenticationToken) authentication).getSteamId();

        Map<String, Object> userAttributes;
        try {
            userAttributes = steamService.getUserData(steamId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Optional<User> userOptional = userService.findBySteamId(steamId);
        User user = userOptional.orElseGet(() -> {
            String username = (String) userAttributes.get("personaname");
            return userService.save(new User(null, steamId, username));
        });
        SteamUserPrincipal steamUserPrincipal = SteamUserPrincipal.create(user, userAttributes);

        return new SteamAutenticationToken(steamId, steamUserPrincipal, steamUserPrincipal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SteamAutenticationToken.class);
    }
}
