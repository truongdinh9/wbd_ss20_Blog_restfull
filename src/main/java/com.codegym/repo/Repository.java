package com.codegym.repo;

import com.codegym.model.Blog;

import java.util.List;

public interface Repository {
    List<Blog> findAll();

    Blog findById(int id);

    void save(Blog blog);

    void remove(int id);
}
