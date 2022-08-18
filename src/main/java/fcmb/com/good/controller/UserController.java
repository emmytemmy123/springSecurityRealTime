package fcmb.com.good.controller;


import fcmb.com.good.common.UserConstant;
import fcmb.com.good.model.User;
import fcmb.com.good.repo.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    @ApiOperation(value = "Endpoint for adding new user to database", response = String.class)
    public String UserRegister(@RequestBody User user){

        user.setRoles(UserConstant.DEFAULT_ROLE);// by default register will be under user
        String encryptedPwd =  passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);

        userRepository.save(user);
        return "Hi"+user.getUsername()+"Welcome to Group";
    }


    //if loggedIn User is ADMIN => it can give Admin and Moderator access
    @GetMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal){
    User user = userRepository.getById(userId);
    List<String> activeRoles = getRolesByLoggedInUser(principal);

    String newRole="";
    if(activeRoles.contains(userRole)){
        newRole = user.getRoles()+ "," + userRole;
        user.setRoles(newRole);
    }
    userRepository.save(user);
    return "Hi"+user.getUsername()+"new Role assign to you"+ principal.getName();
    }



    @GetMapping
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> loadUsers(){
      return userRepository.findAll();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testUserAccess(){
        return "User can only access this !!!!!!";
    }




    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        return new ResponseEntity(userRepository.getUserById(id), HttpStatus.OK);

    }



    //if loggedIn User is Moderator => it can only give Moderator access


    //How to assign roles by UserLoggedIn
    private List<String> getRolesByLoggedInUser(Principal principal){
        String roles = getUserLoggedIn(principal).getRoles();
        List <String> assignRoles= Arrays.stream(roles.split(",")).collect(Collectors.toList());

         if (assignRoles.contains("ROLE_ADMIN")){
            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
         }
        if (assignRoles.contains("ROLE_MODERATOR")){
            return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }



    //how to get user that loggedIn
    private User getUserLoggedIn(Principal principal){
        return userRepository.findByUsername(principal.getName()).get();
    }


}
