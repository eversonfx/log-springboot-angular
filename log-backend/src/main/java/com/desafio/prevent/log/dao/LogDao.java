package com.desafio.prevent.log.dao;

import com.desafio.prevent.log.domain.Log;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
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

        //EntityManager#remove() works only on entities which are managed in the current transaction/context
        //You need to check if the entity is managed by EntityManager#contains() and if not, then make it managed it EntityManager#merge().
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
}