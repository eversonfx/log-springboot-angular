package com.desafio.prevent.log.services;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LogServiceUnitTest {
    @MockBean
    private LogDao logDao;

    @MockBean
    private LogService logService;

    @Before
    public void setUp() {
        Log log1 = new Log(8, "2021-01-01 00:00:11.763", "192.168.234.82", "GET / HTTP/1.1", "200", "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
        logService.insert(log1);
    }

    @Test
    public void find() {
        logService.find(8);
    }

    @Test
    public void delete() {
        logService.delete(8);
    }
}