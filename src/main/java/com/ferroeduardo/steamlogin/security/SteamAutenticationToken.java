package com.ferroeduardo.steamlogin.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SteamAutenticationToken extends AbstractAuthenticationToken {

    private final SteamUserPrincipal principal;
    private final String             steamId;

    public SteamAutenticationToken(SteamUserPrincipal principal) {
        super(null);
        this.principal = principal;
        this.steamId = null;
        this.setAuthenticated(false);
    }

    public SteamAutenticationToken(String steamId) {
        super(null);
        this.steamId = steamId;
        this.principal = null;
        this.setAuthenticated(false);
    }

    public SteamAutenticationToken(String steamId, SteamUserPrincipal principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.steamId = steamId;
        this.setAuthenticated(true);
    }

    public String getSteamId() {
        return steamId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public SteamUserPrincipal getPrincipal() {
        return this.principal;
    }
}
