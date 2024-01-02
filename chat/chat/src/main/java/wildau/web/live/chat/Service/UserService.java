package wildau.web.live.chat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.repositoriy.UserRepository;
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserEntity createUser(UserEntity user) {
    
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
       
        return userRepository.save(user);
    }
    public boolean registerNewUser(UserEntity newUser) {
       try {
        UserEntity createdUser = createUser(newUser);
        return true ;
       } catch (Exception e) {
        return false ;
       }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
