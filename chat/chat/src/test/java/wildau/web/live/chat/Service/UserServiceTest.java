package wildau.web.live.chat.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
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
import wildau.web.live.chat.exceptions.UserEntityNotFoundException;
import wildau.web.live.chat.repository.UserRepository;

@ExtendWith(MockitoExtension.class)//close the resource after each test => like AutoCloseable
public class UserServiceTest {


    @Mock private UserRepository userRepository;   // Mock um das getestete userRepository zu verwenden -> schneller
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

        //given
        UserEntity user = new UserEntity();
        user.setId(1L);

        given(userRepository.existsById(user.getId())).willReturn(false);
        assertThatThrownBy(()-> underTest.deleteUser(user.getId()))
            .isInstanceOf(UserEntityNotFoundException.class)
            .hasMessageContaining("User with id "+ user.getId()+" does not exsists => Delete failed");

       verify(userRepository,never()).deleteById(any());
 
    }
    @Test
    void testGetUserByNames(){
        underTest.getUserByNames(null);
        verify(userRepository).findAll();
        underTest.getUserByNames("Keyword");
        verify(userRepository).search("keyword");
    }

    @Test
    void testGetAllUsers()  {
        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();

    }

    @Test
    void testGetUserById() {
    underTest.getUserById(null);
        //then
        verify(userRepository).findById(null);
    }

    @Test
    void testAddUser() throws BadRequestException {

        //given
        UserEntity user1 = new UserEntity();
        user1.setUsername("aliDa");
        user1.setEmail("ali@gmail.com");
        user1.setPassword("123tests");
        user1.setPhoneNumber("17420499212");
        //when
        underTest.addUser(user1);
       ArgumentCaptor<UserEntity> userArg1 =ArgumentCaptor.forClass(UserEntity.class);
       verify(userRepository).save(userArg1.capture());
       UserEntity capturedUserEntity = userArg1.getValue();
       assertThat(capturedUserEntity).isEqualTo(user1);
    }

    @Test
    void isMailTaken() throws BadRequestException {

        //given
        UserEntity user = new UserEntity();
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
        given(userRepository.selectExistsEmail(user.getEmail())).willReturn(true);
        //when
        assertThatThrownBy(()-> underTest.addUser(user))
            .isInstanceOf(BadRequestException.class)
            .hasMessageContaining("Email "+ user.getEmail()+" is assigned");
        //then
        verify(userRepository, never()).save(any()); // should never save any users
    }
    @Test
    void testUpdateUser() {
          //given
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
        given(userRepository.existsById(user.getId())).willReturn(true);
        underTest.updateUser(user);
        //when
       var userArg =ArgumentCaptor.forClass(UserEntity.class);
       verify(userRepository).save(userArg.capture());
       UserEntity capturedUser = userArg.getValue();
       assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void updateUserWhenUserDoesNotExist()  {
          //given
        UserEntity user = new UserEntity();
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
        given(userRepository.existsById(user.getId())).willReturn(false);

        //when
        assertThatThrownBy(()-> underTest.updateUser(user))
            .isInstanceOf(UserEntityNotFoundException.class)
            .hasMessageContaining("User with id "+ user.getId()+" does not exsists => Update failed");
       verify(userRepository,never()).save(any());
    }
}
