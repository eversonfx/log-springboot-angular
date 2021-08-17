package com.desafio.prevent.log.dto;

import java.math.BigInteger;

public class LogReportDTO {
    private String identifier;
    private BigInteger num;

    public LogReportDTO(String identifier, BigInteger num) {
        this.identifier = identifier;
        this.num = num;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }
}
