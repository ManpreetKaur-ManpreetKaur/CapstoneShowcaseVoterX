package ca.sheridancollege.kau13223.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.kau13223.beans.CapstoneProject;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoadingIndex() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    
    @Test
    public void testVoteUp() throws Exception {
        Long id = 1L; // Replace with a valid project ID from your database
        this.mockMvc.perform(get("/voteup/" + id))
                .andExpect(status().is3xxRedirection()); // Assuming successful redirect
    }

    @Test
    public void testVoteDown() throws Exception {
        Long id = 1L; // Replace with a valid project ID from your database
        this.mockMvc.perform(get("/votedown/" + id))
                .andExpect(status().is3xxRedirection()); // Assuming successful redirect
    }

    
}
