package com.desafio.prevent.log.dto;

import com.desafio.prevent.log.domain.Log;

public class LogDTO {
    private Integer id;
    private String data;
    private String ip;

    public LogDTO() {
    }

    public LogDTO(Log obj) {
        this.id = obj.getId();
        this.data = obj.getData();
        this.ip = obj.getIp();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
