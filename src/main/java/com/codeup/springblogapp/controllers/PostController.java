package com.codeup.springblogapp.controllers;


import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }



    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "post/index";
    }


    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        Post aPost = postRepo.getOne(id);
        model.addAttribute("posts", aPost);
        return "post/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "post/create";
    }

    @PostMapping("/posts/create")
    public String submitCreatePost(@RequestParam(name = "blog-title") String title, @RequestParam(name = "blog-body") String body) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost = this.postRepo.save(newPost);
        return "redirect:/posts";
    }


}
