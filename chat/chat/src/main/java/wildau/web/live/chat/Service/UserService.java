package wildau.web.live.chat.Service;

import org.apache.coyote.BadRequestException ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.exceptions.UserEntityNotFoundException;
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

         if(!userRepository.existsById(user.getId())){
            throw new UserEntityNotFoundException("User with id "+ user.getId()+" does not exsists => Update failed");
        }
        return userRepository.save(user);
    }

    public void addUser(UserEntity newUser) throws BadRequestException {

        Boolean excepted = userRepository.selectExistsEmail(newUser.getEmail());
        if(excepted){
            throw new BadRequestException("Email " + newUser.getEmail()+" is assigned");
        }
        userRepository.save(newUser); 
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId))
        {
            throw new UserEntityNotFoundException("User with id "+ userId+" does not exsists => Delete failed");
        }
        userRepository.deleteById(userId);
    }
    public List<UserEntity> getUserByNames(String keyword){
        if(keyword !=null) {
         return userRepository.search(keyword);
        }
        return userRepository.findAll();
     }
}
