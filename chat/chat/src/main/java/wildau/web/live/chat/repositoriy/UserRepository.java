package wildau.web.live.chat.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wildau.web.live.chat.api.model.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   
}



