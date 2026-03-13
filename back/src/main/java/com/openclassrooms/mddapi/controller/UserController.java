package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UpdateUserDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "user management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/user"})
public class UserController {

    private final UserServiceImpl userService;

    @Operation(summary = "get one user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "get one user, authorized with JWTToken"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserAuth() {
        UserDTO userDTO = this.userService.getUserAuth();
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Operation(summary = "update one user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "update one user, authorized with JWTToken"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok("update successful!");
    }
}
