package com.desafio.prevent.log.services;

import com.desafio.prevent.log.dao.LogDao;
import com.desafio.prevent.log.domain.Log;
import com.desafio.prevent.log.dto.LogReportDTO;
import com.desafio.prevent.log.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogDao repo;

    public LogService() {
    }

    public Log find(Integer id) {
        Log log = repo.findById(id);
        if (log != null) return log;
        else throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Log.class.getName());
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



    public BigInteger findPageInfo() {
        BigInteger numReg = repo.findPageInfo();
        return numReg;
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

    public List<Log> findInterval(Integer begin, Integer end) {
        return repo.findInterval(begin, end);
    }

    public List<Log> search(String searchText) {
        return repo.search(searchText);
    }

    public List<LogReportDTO> searchByAggregation(String option) {
        return repo.searchByAggregation(option);
    }
}
