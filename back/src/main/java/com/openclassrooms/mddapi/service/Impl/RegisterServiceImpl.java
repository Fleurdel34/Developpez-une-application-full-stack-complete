package com.openclassrooms.mddapi.service.Impl;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.repository.RegisterRepository;
import  com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Create class RegisterServiceImpl
 * Execute business processing
 * Use the property RegisterRepository and interface RegisterService
 */

@AllArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    private RegisterRepository registerRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @SneakyThrows
    public void createUser(User user){

        Optional<User> userRegister = this.registerRepository.findByEmail(user.getEmail());

        if(userRegister.isPresent()){
            throw new BadRequestException("E-mail is already in use");
        }

        String bcryptPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(bcryptPassword);

        this.registerRepository.save(user);

    }

}
