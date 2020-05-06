package com.trelloiii;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@RunWith(SpringRunner.class)
public class ControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setup(){
        this.mockMvc=MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void test(){
        try {
            ResultActions actions=mockMvc.perform(get("/get/implementer/all")
                    .with(user("admin").password("password").roles("ADMIN")));
            System.out.println(actions.andReturn().getResponse().getContentAsString());
//                    .param("id","1"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
