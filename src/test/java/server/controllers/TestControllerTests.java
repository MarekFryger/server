package server.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllAccess() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/test/all")).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("To use Shopping List app you have to login.", content);
    }
}
