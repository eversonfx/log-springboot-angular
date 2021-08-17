package com.desafio.prevent.log.dao;

import com.desafio.prevent.log.domain.Log;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public Log insert(Log log) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        if (!ObjectUtils.isEmpty(log) && !entityManager.contains(log)) {
            entityManager.persist(entityManager.contains(log) ? log : entityManager.merge(log));
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return log;
    }

    public Log findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Log log = entityManager.find(Log.class, id);
        entityManager.close();
        return log;
    }

    public List<Log> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Log> logList = entityManager.createQuery("from Log", Log.class).getResultList();
        entityManager.close();
        return logList;
    }

    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Log log = findById(id);

        entityManager.remove(entityManager.contains(log) ? log : entityManager.merge(log));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Log newLog) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        if (!ObjectUtils.isEmpty(newLog) && !entityManager.contains(newLog)) {
            entityManager.merge(newLog);
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public BigInteger findPageInfo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("SELECT COUNT(id) FROM logs_table a");
        BigInteger count = (BigInteger) query.getSingleResult();
        entityManager.close();
        return count;
    }

    public List<Log> findInterval(Integer begin, Integer end) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("SELECT a.id, a.data, a.ip, a.request, a.status,a.agent FROM logs_table a WHERE a.id BETWEEN ? AND ? ORDER BY a.id ASC");
        query.setParameter(1, begin);
        query.setParameter(2, end);
        List<Object[]> rows = query.getResultList();

        List<Log> logList = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            logList.add(new Log((int) row[0], (String) row[1], (String) row[2],
                    (String) row[3], (String) row[4], (String) row[5]));
        }

        entityManager.close();
        return logList;
    }

    public List<Log> search(String searchText) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(
                "FROM Log WHERE ip LIKE :searchText order by ip,data asc"
        ).setMaxResults(10000);
        query.setParameter("searchText", "%" + searchText + "%");
        List<Log> logList = query.getResultList();

        entityManager.close();
        return logList;
    }
}