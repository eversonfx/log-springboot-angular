package com.desafio.prevent.log.services;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import com.desafio.prevent.log.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    private LogDao repo;

    public Log find(Integer id) {
        Log log = repo.findById(id);
        if (log != null) return log;
        else throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Log.class.getName());
    }

    public List<Log> findAll() {
        return repo.findAll();
    }


    public Log insert(Log obj) {
        obj.setId(null);
        obj = repo.insert(obj);
        return obj;
    }

    public void delete(int id) {
        repo.delete(id);
    }

    public void update(Log obj) {
        Log log = updateData(obj);
        repo.update(log);
    }

    public Log updateData(Log newLog) {
        Log orLog = repo.findById(newLog.getId());

        orLog.setData(newLog.getData());
        orLog.setIp(newLog.getIp());
        orLog.setRequest(newLog.getRequest());
        orLog.setStatus(newLog.getStatus());
        orLog.setRequest(newLog.getRequest());
        orLog.setAgent(newLog.getAgent());

        return orLog;
    }
}
