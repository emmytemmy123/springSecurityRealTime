package fcmb.com.good.dto;


import fcmb.com.good.model.User;
import fcmb.com.good.repo.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Data
@RequiredArgsConstructor
public class TestData {

    //private  final UserService userService;

    private static UserRepository userRepository;
    

    public static List<User> addUser() {

        User user = new User();

        user.setUsername("Adeniyi");
        user.setPassword("emmy@gmail.com");


        user = new User();
        user.setUsername("Adeniyi");
        user.setPassword("emmy@gmail.com");

        return List.of(user);

    }



    public static List<User> getListOfUsers() {

        User user = new User();

        user.setUsername("Adeniyi");
        user.setPassword("emmy@gmail.com");

        user = new User();
        user.setUsername("Adeniyi");
        user.setPassword("emmy@gmail.com");

        user = new User();
        user.setUsername("Adeniyi");
        user.setPassword("emmy@gmail.com");

        return List.of(user);
    }


    public static String getContentType() {
        return "application/json";

    }

   /* public static  UserRequest getUseRequest () {
        UserRequest user = new UserRequest();
        user.setId(1);
        user.setName("Abiodun");
        user.setEmail("addy@gmail.com");
        user.setGender("Male");
        user.setPhone("09089786756");

        return user;
    }
*/

}
