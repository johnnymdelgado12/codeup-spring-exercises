package com.codeup.springblogapp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getPost() {
        return "<h1>Posts Index Page</h1>";
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
