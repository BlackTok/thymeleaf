package ru.gb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.dto.AuthRequestDto;
import ru.gb.dto.AuthResponseDto;
import ru.gb.service.JwtService;

@RestController
@Slf4j
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth")
    public AuthResponseDto authorize(@RequestBody AuthRequestDto request) {
        log.info("Auth request: [{}, {}]", request.getMail(), request.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateJwtToken(user);

            return new AuthResponseDto(jwtToken);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
