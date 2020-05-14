package com.codeup.springblogapp.controllers;


import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts/index")
    public String getPost(Model model) {
        model.addAttribute("post", PostRepository.findall());
        return "post/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostById(@PathVariable long id) {
        return "Showing individual post: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "View the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitCreatePost() {
        return "create a new post";
    }


}
