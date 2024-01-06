package wildau.web.live.chat.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.repository.UserRepository;

@ExtendWith(MockitoExtension.class)//close the resource after each test => like AutoCloseable
public class UserServiceTest {


    @Mock private UserRepository userRepository;
    private UserService underTest;

    @BeforeEach
    void setUp(){
        underTest = new UserService(userRepository); 
    }


    @Test
    void testCreateUser() {

    }

    @Test
    void testDeleteUser() {

    }

    @Test
    void testGetAllUsers() {
        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();

    }

    @Test
    void testGetUserById() {

    }

    @Test
    void testAddUser() throws BadRequestException {

        //given
        UserEntity user = new UserEntity();
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
       //when
       underTest.addUser(user);

       ArgumentCaptor<UserEntity> userArg =ArgumentCaptor.forClass(UserEntity.class);

       verify(userRepository).save(userArg.capture());

       UserEntity capturedUserEntity = userArg.getValue();
       
       assertThat(capturedUserEntity).isEqualTo(user);
    }

    @Test
    void testUpdateUser() {

    }
}
