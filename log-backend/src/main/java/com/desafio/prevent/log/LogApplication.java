package com.desafio.prevent.log;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication implements CommandLineRunner {
    @Autowired
    private LogDao logRepo;

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Log log1 = new Log(1, "2021-01-01 00:00:11.763", "192.168.234.82", "GET / HTTP/1.1", "200", "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
        logRepo.insert(log1);
        Log log2 = new Log(2, "2021-01-01 00:00:21.164", "192.168.234.82", "GET / HTTP/1.1", "200", "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
        logRepo.insert(log2);
        Log log3 = new Log(3, "2021-01-01 00:00:23.003", "192.168.169.194", "GET / HTTP/1.1", "200", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393");
        logRepo.insert(log3);
        Log log4 = new Log(4, "2021-01-01 00:00:40.554", "192.168.234.82", "GET / HTTP/1.1", "200", "swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
        logRepo.insert(log4);
    }

}
