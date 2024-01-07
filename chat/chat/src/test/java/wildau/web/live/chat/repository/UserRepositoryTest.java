package wildau.web.live.chat.repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.ArrayList;
//import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import wildau.web.live.chat.api.model.UserEntity;


@DataJpaTest
public class UserRepositoryTest {
    
    @Autowired
	private UserRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

	 @Test
	 void itShouldCheckWhenUserMailExists() {
		//given
        UserEntity user = new UserEntity();
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
        underTest.save(user);
        // when
        boolean excepted = underTest.selectExistsEmail("ali@gmail.com");
        assertThat(excepted).isTrue();
    
	 }
     @Test
	 void itShouldCheckWhenUserMailNotExists() {
		//given
       String email = "notExists@gmail.com";
        // when
        boolean excepted = underTest.selectExistsEmail(email);
        assertThat(excepted).isFalse();
    
	 }
     @Test 
     void itShouldSearchUsers(){
        //given
        UserEntity user1 = new UserEntity();
        user1.setUsername("gerHo");
        user1.setEmail("gerHo@gmail.com");
        user1.setPassword("123tests");
        user1.setPhoneNumber("0548561235");
        underTest.save(user1);
        List<UserEntity> expectedOutput = new ArrayList<UserEntity>();
        expectedOutput.add(user1);
          UserEntity user2 = new UserEntity();
        user2.setUsername("aliDa");
        user2.setEmail("alitests@gmail.com");
        user2.setPassword("123tests");
        user2.setPhoneNumber("17420499212");
        underTest.save(user2);
        // when
        List<UserEntity> result = underTest.search("ger");
   
        assertThat(result).isEqualTo(expectedOutput);
        
        // then


     }


} 
