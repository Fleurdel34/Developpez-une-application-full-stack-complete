package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.dto.UpdateUserDTO;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collections;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loadUserByUsernameByUsernameTest(){
        User josette = new User();
        josette.setUsername("josette");
        josette.setEmail("josette@gmail.com");
        josette.setPassword("test1234");

        Mockito.when(userRepository.findByUsername("josette"))
                .thenReturn(Optional.of(josette));

        UserDetails result1 = this.userService.loadUserByUsername("josette");

        Mockito.verify(userRepository).findByUsername("josette");

        User user =(User) result1;
        assertEquals("josette", user.getUserName());

    }

    @Test
    void loadUserByUsernameByEmailTest(){
        User josephine = new User();
        josephine.setEmail("josephine@gmail.com");
        josephine.setUsername("josephine");
        josephine.setPassword("test1234");

        Mockito.when(userRepository.findByEmail("josephine@gmail.com"))
                .thenReturn(Optional.of(josephine));

        UserDetails result2 = this.userService.loadUserByUsername("josephine@gmail.com");

        Mockito.verify(userRepository).findByEmail("josephine@gmail.com");

        User user =(User) result2;
        assertEquals("josephine@gmail.com", user.getEmail());
    }

    @Test
    void loadUserByUsernameFailedTest(){
        User julien = new User();
        julien.setUsername("julien");
        julien.setEmail("julien@gmail.com");
        julien.setPassword("test1234");

        Mockito.when(userRepository.findByUsername("julien"))
                .thenReturn(Optional.empty());

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            this.userService.loadUserByUsername("julien");
        });

        assertThat(exception.getMessage()).isEqualTo("error");

        Mockito.verify(userRepository, Mockito.never())
                .save(Mockito.any());
    }

    @Test
    public void updateUserTest(){
        User userMock = new User();
        userMock.setId(1L);
        userMock.setUsername("josette");
        userMock.setEmail("josette@gmail.com");
        userMock.setPassword("test1234");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userMock,
                null,
                Collections.emptyList()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                "jojo",
                "josette@gmail.com",
                "test1234"
        );

        Mockito.when(userRepository.existsByUsername("jojo"))
                .thenReturn(false);

        Mockito.when(userRepository.save(userMock))
                .thenReturn(userMock);
        User updateUser = userService.updateUser(updateUserDTO);
        assertEquals("jojo", updateUser.getUserName());
    }

    @Test
    public void updateUserExistTest(){
        User userMock1 = new User();
        userMock1.setId(2L);
        userMock1.setUsername("alain");
        userMock1.setEmail("alain@gmail.com");
        userMock1.setPassword("test1234");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userMock1,
                null,
                Collections.emptyList()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(
                "alain",
                "alain@gmail.com",
                "test1234"
        );

        Mockito.when(userRepository.existsByUsername("alain"))
                .thenReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            this.userService.updateUser(updateUserDTO);
        });

        assertThat(exception.getMessage()).isEqualTo("username exist");

        Mockito.verify(userRepository, Mockito.never())
                .save(Mockito.any());
    }

}
