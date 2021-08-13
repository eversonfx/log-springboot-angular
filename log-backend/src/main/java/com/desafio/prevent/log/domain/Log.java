package com.desafio.prevent.log.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "logs_table")
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String data;
    private String ip;
    private String request;
    private String status;
    private String agent;

    public Log() {
    }

    public Log(Integer id, String data, String ip, String request, String status, String agent) {
        this.id = id;
        this.data = data;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.agent = agent;
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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
