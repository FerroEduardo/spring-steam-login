package com.ferroeduardo.steamlogin.controller;

import com.ferroeduardo.steamlogin.dto.SteamOpenidLoginDTO;
import com.ferroeduardo.steamlogin.repository.UserRepository;
import com.ferroeduardo.steamlogin.security.SteamAutenticationToken;
import com.ferroeduardo.steamlogin.security.SteamUserPrincipal;
import com.ferroeduardo.steamlogin.service.SteamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("steam")
public class SteamController {

    private final AuthenticationManager authenticationManager;
    private final SteamService          service;

    public SteamController(AuthenticationManager authenticationManager, UserRepository userRepository, SteamService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("steam", service.getOpenIdAttributes());
    }

    @GetMapping("login/redirect")
    public ModelAndView loginRedirect(HttpServletRequest request, @RequestParam Map<String, String> allRequestParams) {
        SteamOpenidLoginDTO dto = new SteamOpenidLoginDTO(
                allRequestParams.get("openid.ns"),
                allRequestParams.get("openid.op_endpoint"),
                allRequestParams.get("openid.claimed_id"),
                allRequestParams.get("openid.identity"),
                allRequestParams.get("openid.return_to"),
                allRequestParams.get("openid.response_nonce"),
                allRequestParams.get("openid.assoc_handle"),
                allRequestParams.get("openid.signed"),
                allRequestParams.get("openid.sig")
        );

        try {
            String                  steamUserId = service.validateLoginParameters(dto);
            SteamAutenticationToken authReq     = new SteamAutenticationToken(steamUserId);
            Authentication          auth        = authenticationManager.authenticate(authReq);
            SecurityContext         sc          = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/steam/failed");
        }

        return new ModelAndView("redirect:/steam/profile");
    }

    @GetMapping("profile")
    @ResponseBody
    public ResponseEntity<Object> success() {
        SteamAutenticationToken authentication = (SteamAutenticationToken) SecurityContextHolder.getContext().getAuthentication();
        SteamUserPrincipal      principal      = authentication.getPrincipal();

        return ResponseEntity.ok(principal);
    }

    @GetMapping("failed")
    public String failed() {
        return "failed";
    }
}
