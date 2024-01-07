package wildau.web.live.chat.api.controller;
import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.exceptions.UserEntityNotFoundException;
import wildau.web.live.chat.Service.UserService;

import org.apache.coyote.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wildau.web.live.chat.api.model.SearchListOfEntitys;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public UserEntity showUserEntity(@RequestParam long id){
        return userService.getUserById(id).orElse(new UserEntity());        
    }

    @GetMapping("/search")
    public SearchListOfEntitys searchUsername (@RequestParam String name){  
        SearchListOfEntitys search = new SearchListOfEntitys();
        search.results = userService.getUserByNames(name).size();
        search.data = userService.getUserByNames(name);
        return search;
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
  
         try {
            List<UserEntity> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
         } catch (Exception e) {
             // Handle exceptions (e.g., validation errors, database errors) and return an appropriate response
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<UserEntity>());
         }
    }


 

   @PostMapping("/")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody UserEntity newUser) {

        try {

            userService.addUser(newUser);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message", "User registered successfully");
            }});
            
        } catch (BadRequestException ex) {
             return ResponseEntity.ok(new HashMap<String, String>() {{
                put("message",  ex.getMessage());
            }});
        }
    }

     @PutMapping("/{id}")
     public ResponseEntity<Map<String, String>> updateUserEntity(@RequestBody UserEntity currentUser,@PathVariable(value = "id") Long id) {

          Map<String, String> response = new HashMap<>();
        try {
            UserEntity user = new UserEntity();
            user.setId(id);
            user.setEmail(currentUser.getEmail());
            user.setUsername(currentUser.getUsername());
            user.setPassword(currentUser.getPassword());
            user.setPhoneNumber(currentUser.getPhoneNumber());
            userService.updateUser(user);
            response.put("message", "User updated successfully");
            return ResponseEntity.ok(response);

        } catch (UserEntityNotFoundException ex) {
            response.put("message", ex.getMessage()); 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(value = "id") Long id) {
        Map<String, String> response = new HashMap<>();

        try {
            userService.deleteUser(id);
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (UserEntityNotFoundException ex) {
            response.put("message", ex.getMessage()); 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
