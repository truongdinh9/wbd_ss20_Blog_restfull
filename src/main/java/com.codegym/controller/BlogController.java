package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping
    public ResponseEntity<List<Blog>> listAllBlog(){
        List<Blog> blogs =blogService.findAll();

        if (blogs.isEmpty()){
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);

    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> showById(@PathVariable("id") int id ) {
        Blog blog=blogService.findById(id);
        if (blog==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<Blog>(blog,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestBody Blog blog ){
        blogService.save(blog);
//        ModelAndView modelAndView =new ModelAndView("redirect:/blog");
//        modelAndView.addObject("message","Saved blog");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> edit(@PathVariable("id")int id, @RequestBody Blog newBlog){
        Blog currentBlog=blogService.findById(id);
        if (currentBlog==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentBlog.setTitle(newBlog.getTitle());
        currentBlog.setDescription(newBlog.getDescription());
        blogService.save(currentBlog);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
       if (blogService.findById(id)==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;}
        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
