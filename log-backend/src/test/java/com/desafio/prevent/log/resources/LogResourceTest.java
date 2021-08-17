package com.desafio.prevent.log.resources;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import com.desafio.prevent.log.services.FileService;
import com.desafio.prevent.log.services.LogService;
import org.apache.catalina.connector.Response;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LogResource.class)
class LogResourceTest {
    private LogResource logResource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogService logService;

    @MockBean
    private LogDao logDao;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(logResource).build();
        Log log1 = new Log(1, "2021-01-01 00:00:11.763", "192.168.234.82", "GET / HTTP/1.1", "200", "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
        logService.insert(log1);
    }

    @Test
    void checkInsert() throws Exception {
        mockMvc.perform(
                get("/logs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void checkDelete() throws Exception {
        mockMvc.perform(
                delete("/logs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }
}
