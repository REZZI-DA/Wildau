package wildau.web.live.chat.Service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.repository.UserRepository;
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
    


    public UserEntity updateUser(UserEntity user) {
       
        return userRepository.save(user);
    }
    public void addUser(UserEntity newUser) throws BadRequestException {

        Boolean existsEmail = userRepository.selectExistsEmail(newUser.getEmail());
        if(existsEmail){
            throw new BadRequestException("Email" + newUser.getEmail()+"exsitiert bereits");
        }
        
        userRepository.save(newUser);
       
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
