package org.example.lesson5;

import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import java.util.List;

public class CrudRepo<T, ID>  {
    public final Class<T> thisClass;

    public CrudRepo(Class<T> thisClass) {
        this.thisClass = thisClass;
    }

    public void create(EntityManager em, T entity) throws DataAccessException {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void create(EntityManager em, List<T> entities) throws DataAccessException{
        em.getTransaction().begin();
        for (T entity : entities)
            em.persist(entity);
        em.getTransaction().commit();
    }

    public T read(EntityManager em, long id) throws DataAccessException{
        em.getTransaction().begin();
        T person = em.find(thisClass, id);
        em.getTransaction().commit();
        return person;
    }

    public T save(EntityManager em, T entity) throws DataAccessException{
        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();
        return savedEntity;
    }
}
