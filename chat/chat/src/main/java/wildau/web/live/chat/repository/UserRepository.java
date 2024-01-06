package wildau.web.live.chat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import wildau.web.live.chat.api.model.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.email = :email")
  Boolean selectExistsEmail(String email);
}


