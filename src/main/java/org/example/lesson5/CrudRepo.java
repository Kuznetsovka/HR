package org.example.lesson5;

import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CrudRepo<T, ID>  {

    public void createEntity(EntityManager em, T entity) throws DataAccessException {
        System.out.println("Creating entity");
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        System.out.println("Creating finished");
    }

    public void createEntities(EntityManager em, List<T> entities) throws DataAccessException{
        System.out.println("Creating entities");
        em.getTransaction().begin();
        for (T entity : entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
        System.out.println("Creating finished");
    }

    public T readEntity(EntityManager em, Class<T> thisClass, long id) throws DataAccessException{
        System.out.println("Start reading");
        em.getTransaction().begin();
        T person = em.find(thisClass, id);
        em.getTransaction().commit();
        System.out.println("Reading completed->" + person);
        return person;
    }

    public T saveEntity(EntityManager em, T entity) throws DataAccessException{
        System.out.println("Start saving");
        em.getTransaction().begin();
        T savedEntity = em.merge(entity);
        em.getTransaction().commit();
        System.out.println("Saving completed->" + savedEntity);
        return savedEntity;
    }
}
