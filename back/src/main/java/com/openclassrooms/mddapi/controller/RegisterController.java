package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.Impl.RegisterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Register", description = "register management")
@RestController
@RequestMapping("/api")
public class RegisterController {

    private final RegisterServiceImpl registerService;

    public RegisterController(RegisterServiceImpl registerService) {
        this.registerService = registerService;
    }

    @Operation(summary = "register  user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "register user and create account"),
    @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        registerService.createUser(user);
        return ResponseEntity.ok("registration successful!");
    }
}
