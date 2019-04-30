package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView=new ModelAndView("index","blogs",blogService.findAll());
//        modelAndView=new ModelAndView("index");
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }
    @PostMapping("/save")
    public ModelAndView save(Blog blog ){
        blogService.save(blog);
        ModelAndView modelAndView =new ModelAndView("redirect:/blog");
        modelAndView.addObject("message","Saved blog");
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id){
        Blog blog=blogService.findById(id);
        ModelAndView modelAndView=new ModelAndView("edit");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView=new ModelAndView("redirect:/blog");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message","Edited blog successfully");
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        Blog blog=blogService.findById(id);
        model.addAttribute("blog",blog);
        return "delete";
    }
    @PostMapping("/remove")
    public String remove(Blog blog, RedirectAttributes redirect){
        blogService.remove(blog.getId());
        redirect.addFlashAttribute("message","Deleted blog successfully");
        return "redirect:/blog";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        Blog blog=blogService.findById(id);
        model.addAttribute("blog",blog);
        return "view";
    }
}


//
//    @GetMapping("/{id}/edit")
//    public ModelAndView edit(@PathVariable long id){
//        blog blog=customerService.findById(id);
//        ModelAndView modelAndView=new ModelAndView("edit");
//        modelAndView.addObject("blog",blog);
//        return modelAndView;
//    }
//    @PostMapping("/edit")
//    public ModelAndView edit(blog blog){
//        customerService.save(blog);
//        ModelAndView modelAndView=new ModelAndView("redirect:/blog");
//        modelAndView.addObject("blog", blog);
//        modelAndView.addObject("message","Edited blog successfully");
//        return modelAndView;
//    }
//    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable long id, Model model){
//        blog blog=customerService.findById(id);
//        model.addAttribute("blog",blog);
//        return "delete";
//    }
//    @PostMapping("/remove")
//    public String remove(blog blog, RedirectAttributes redirect){
//        customerService.remove(blog.getId());
//        redirect.addFlashAttribute("message","Deleted blog successfully");
//        return "redirect:/blog";
//    }
//    @GetMapping("/{id}/view")
//    public String view(@PathVariable long id, Model model){
//        blog blog=customerService.findById(id);
//        model.addAttribute("blog",blog);
//        return "view";


