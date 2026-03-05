package com.openclassrooms.mddapi.service.Impl;

import com.openclassrooms.mddapi.dto.UpdateUserDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import lombok.AllArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Create class UserServiceImpl
 * Execute business processing
 * Use the property UserRepository and interface UserService
 */

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO getUserAuth(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String login){

        Optional<User> user;

        if(login.contains("@")){
            user = userRepository.findByEmail(login);
        }else {
            user = userRepository.findByUsername(login);
        }

        return (UserDetails) user.orElseThrow(() -> new UnauthorizedException("error"));
    }

    @SneakyThrows
    public User updateUser(UpdateUserDTO updateUserDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userRepository.existsByUsername(updateUserDTO.username())){
            throw new BadRequestException("username exist");
        }
        user.setUsername(updateUserDTO.username());

        if(userRepository.existsByEmail(updateUserDTO.email())){
            throw new BadRequestException("email exist");
        }
        user.setEmail(updateUserDTO.email());

        if(updateUserDTO.password() != null){
            user.setPassword(passwordEncoder.encode(updateUserDTO.password()));
        }
       return userRepository.save(user);
    }
}
