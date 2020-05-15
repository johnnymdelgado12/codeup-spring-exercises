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


    //******************** method for view all the blogs on the index page*************************
    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "post/index";
    }


//******************** method for grabbing individual post by id and view it on the post/{id} page**************
    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        Post aPost = postRepo.getOne(id);
        model.addAttribute("posts", aPost);
        return "post/show";
    }


//******************** method for taking user to create post page *******************************
    @GetMapping("/posts/create")
    public String createPost() {
        return "post/create";
    }



//******************* method for creating a new post and rerouting user to post/{id} page *******************
    @PostMapping("/posts/create")
    public String submitCreatePost(@RequestParam(name = "blog-title") String title, @RequestParam(name = "blog-body") String body) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost = this.postRepo.save(newPost);
        return "redirect:/posts";
    }


//****************** method for getting edit page **************************
    @GetMapping("/posts/{id}/edit")
    public String getEditPostForm(@PathVariable long id, Model model){
        Post aPost = postRepo.getOne(id);
        model.addAttribute("post", aPost);
        return "post/edit";
    }



//****************** method for editing post and rerouting user to post page ******************
    @PostMapping("/posts/{id}/edit")
    public String savePostEdit(@ModelAttribute Post post, @RequestParam(name = "blog-title") String title, @RequestParam(name = "blog-body") String body, Model model){
        Post editPost = postRepo.getOne(post.getId());
        editPost.setTitle(title);
        editPost.setBody(body);
        postRepo.save(editPost);
//        model.addAttribute("post", editPost);
        return "redirect:/posts/" + post.getId();
    }



    @GetMapping("/posts/{id}/delete")
    public String getDeletePostForm(@PathVariable long id, Model model){
        Post singlePost = postRepo.getOne(id);
        model.addAttribute("post", singlePost);
        return "posts/delete";
    }



    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        Post singlePost = postRepo.getOne(id);
        postRepo.delete(singlePost);
        return "redirect:/posts";
    }


}
