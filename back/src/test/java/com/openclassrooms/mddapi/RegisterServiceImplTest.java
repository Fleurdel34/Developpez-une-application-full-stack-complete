package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.RegisterRepository;
import com.openclassrooms.mddapi.service.Impl.RegisterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
public class RegisterServiceImplTest {

    @Mock
    private RegisterRepository registerRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterServiceImpl registerServiceImpl;

    @Test
    void createUserTest(){
        User josette = new User( null, "josette", "josette@gmail.com","Josette1234*");

        User josetteOk = new User( 1L, "josette", "josette@gmail.com","Josette1234*");

        Mockito.when(registerRepository.findByEmail("josette@gmail.com"))
                .thenReturn(Optional.empty());

        String bcryptPassword = this.passwordEncoder.encode(josette.getPassword());
        josette.setPassword(bcryptPassword);

        Mockito.when(registerRepository.save(josette))
                .thenReturn(josetteOk);

        this.registerServiceImpl.createUser(josette);

        Mockito.verify(registerRepository).save(josette);
    }

    @Test
    void testCreateUserWithExceptionEmailExists(){

        User john = new User();
        john.setEmail("john@gmail.com");

        Mockito.when(registerRepository.findByEmail("john@gmail.com"))
                .thenReturn(Optional.of(john));

        try {
            this.registerServiceImpl.createUser(john);
        }catch(Exception e){
            assertThat(e).isInstanceOf(BadRequestException.class)
                    .hasMessage("E-mail is already in use");
        }
    }

}
