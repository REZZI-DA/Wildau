package wildau.web.live.chat.repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
	 void itShouldAddUser() {
		//given
        UserEntity user = new UserEntity();
        user.setUsername("aliDa");
        user.setEmail("ali@gmail.com");
        user.setPassword("123tests");
        user.setPhoneNumber("17420499212");
        underTest.save(user);
        // when
        boolean exists = underTest.selectExistsEmail("ali@gmail.com");
   
        assertThat(exists).isTrue();
        
        // then
	 }


} 
