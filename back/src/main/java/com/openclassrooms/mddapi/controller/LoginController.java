package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AuthenticationDTO;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Login", description = "login management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api"})
public class LoginController {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Operation(summary = "login")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "login, return JWTToken"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
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
            throw new UnauthorizedException("Authentication failed");
        }

        String email = authenticate.getName();

        Map<String, String> token = this.jwtService.generate(email);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
