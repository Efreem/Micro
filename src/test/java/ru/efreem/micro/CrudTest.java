package ru.efreem.micro;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.efreem.micro.service.user.DefaultUserService;


@SpringBootTest
@AutoConfigureMockMvc
public class CrudTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DefaultUserService userService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userService).isNotNull();
    }

    @Test
    public void findAllShouldReturnTestUser() throws Exception {
        this.mockMvc.perform(post("/api/v1/user/findAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }
}
