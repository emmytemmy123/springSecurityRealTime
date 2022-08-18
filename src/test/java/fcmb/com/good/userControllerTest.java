package fcmb.com.good;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fcmb.com.good.controller.UserController;
import fcmb.com.good.dto.TestData;
import fcmb.com.good.model.User;
import fcmb.com.good.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class userControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

  /*  @MockBean
    private UserService userService;
*/
    @Autowired
    private ObjectMapper objectMapper;

 /*  @Test
    public void getUserByIdTest() throws Exception {
        when(userRepository.getUserById(anyInt())).thenReturn(Optional.ofNullable(TestData.getListOfUsers().get(anyInt())));
         mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                 .andDo(print())
                 .andExpect(status().isOk());

    }*/



/*    @Test
    public void saveUserTest() throws Exception {

        User user = new User();
        user.setId(1);
        user.setName("Adeniyi");
        user.setEmail("emmy@gmail.com");
        user.setGender("Male");
        user.setPhone("09089786756");

        when(userService.saveUser(any(User.class))).thenReturn((user));
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(user))
                .contentType(TestData.getContentType()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }*/




   /* @Test
    public void saveUserTest2() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn((User) TestData.addUser());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(objectMapper.writeValueAsString(TestData.getUseRequest()))
                        .contentType(TestData.getContentType()))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andDo(print());

    }
*/





    private ObjectMapper objectMapper() {
        return new ObjectMapper();
    }


}
