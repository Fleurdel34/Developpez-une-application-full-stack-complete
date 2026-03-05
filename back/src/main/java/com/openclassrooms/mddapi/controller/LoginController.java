package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AuthenticationDTO;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping({"/api/login"})
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationDTO authenticationDTO){

        if(authenticationDTO.login() == null || authenticationDTO.password() == null){
            throw new BadRequestException("Login ou password not null");
        }

        final Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (authenticationDTO.login(), authenticationDTO.password()));

        if(!authenticate.isAuthenticated()){
            return null;
        }

        Map<String, String> token = this.jwtService.generate(authenticationDTO.login());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
