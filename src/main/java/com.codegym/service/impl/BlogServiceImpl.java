package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.repo.Repository;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BlogServiceImpl implements BlogService {

    @Autowired
    private Repository repository;
    @Override
    public List<Blog> findAll() {
        return repository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        repository.save(blog);
    }

    @Override
    public void remove( int id) {
        repository.remove(id);

    }
}
