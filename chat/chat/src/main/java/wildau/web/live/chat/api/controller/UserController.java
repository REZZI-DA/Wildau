package wildau.web.live.chat.api.controller;
import wildau.web.live.chat.api.model.UserEntity;
import wildau.web.live.chat.Service.UserService;
import wildau.web.live.chat.api.model.ListOfEntitys;
import wildau.web.live.chat.api.model.SearchListOfEntitys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService userService;

   
    @GetMapping("/all")
    public ListOfEntitys getAllUsers(){
         ListOfEntitys payload = new ListOfEntitys();
        try {
        payload.status="ok";
        payload.data=userService.getAllUsers();
        return payload;    
        } catch (Exception e) {
            // Handle exceptions (e.g., validation errors, database errors) and return an appropriate response
            payload.status ="ERROR: " + e.getMessage();
            return payload;
            
        }
    }
    @GetMapping("/user")
    public UserEntity showUserEntity(@RequestParam long id){
        return userService.getUserById(id).orElse(new UserEntity());        
    }



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


   @PostMapping("/")
    public ResponseEntity<String> registerNewUser(@RequestBody UserEntity newUser) {
        try {
            userService.addUser(newUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            // Handle exceptions (e.g., validation errors, database errors) and return an appropriate response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user: " + e.getMessage());
        }
    }

     @PutMapping("/{id}")
     public ResponseEntity<UserEntity> updateEmployee(@RequestBody UserEntity currentUser,@PathVariable(value = "id") Long id) 
     throws ResponseStatusException {

        UserEntity user = userService.getUserById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found for this id :: " + id));

        user.setEmail(currentUser.getEmail());
        user.setUsername(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        user.setPhoneNumber(currentUser.getPhoneNumber());
        final UserEntity updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
   
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
    throws ResponseStatusException {
        // check if exsit 
         userService.getUserById(id)  //deleted User Entity
         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found for this id : " + id));
        
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/search")
    public SearchListOfEntitys searchUsername (@RequestParam String name)
    {   
        SearchListOfEntitys search = new SearchListOfEntitys();
        try{
        search.results = "" + userService.getUserByNames(name).size();
        search.data = userService.getUserByNames(name);
        return search;
        }catch(Exception e){
            search.results = "Error: " + e.getMessage();
            return search;
        }
    }
}
