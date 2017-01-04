package com.fatlamb.fattt.test;

import com.fatlamb.fattt.controller.IndexController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by 58 on 2017/1/3.
 */
public class IndexControllerTest {
    @Test
    public void testHomePage() throws Exception{
        IndexController controller = new IndexController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
}
