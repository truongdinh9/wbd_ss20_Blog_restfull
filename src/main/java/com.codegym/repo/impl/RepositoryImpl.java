package com.codegym.repo.impl;

import com.codegym.model.Blog;
import com.codegym.repo.Repository;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RepositoryImpl implements Repository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where  b.id=:id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Blog blog) {
        if(blog.getId() != null){
            em.merge(blog);
        } else {
            em.persist(blog);
        }

    }

    @Override
    public void remove(int id) {
        Blog blog = findById(id);
        if(blog != null){
            em.remove(blog);
        }

    }
}
