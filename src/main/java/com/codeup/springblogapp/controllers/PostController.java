package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private PostRepository postRepo;
    private UserRepository userRepo;

    public PostController(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
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
        Post post = postRepo.getOne(id);
        model.addAttribute("posts", post);
        return "/post/show";
    }


//******************** method for taking user to create post page *******************************
    @GetMapping("/posts/create")
    public String createPost(Model model) {
        User user = userRepo.getOne(1L);
        Post post = new Post();
        post.setUser(user);
        model.addAttribute("post", post);
        return "/post/create";
    }



//******************* method for creating a new post and rerouting user to post/{id} page *******************
    @PostMapping("/posts/create")
    public String submitCreatePost(@ModelAttribute Post post) {
        postRepo.save(post);
        return "redirect:/posts";
    }


//****************** method for getting edit page **************************
    @GetMapping("/posts/{id}/edit")
    public String getEditPostForm(@PathVariable long id, Model model){
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "post/edit";
    }



//****************** method for editing post and rerouting user to post page ******************
    @PostMapping("/posts/{id}/edit")
    public String savePostEdit(@ModelAttribute Post post){
        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }

//    @GetMapping("/posts/editcreate")
//    public String editCreatePost(Model model) {
//        User user = userRepo.getOne(1L);
//        Post post = new Post();
//        post.setUser(user);
//        model.addAttribute("post", post);
//        return "/post/edit";
//    }


//********************* method for taking user to delete post page **********************
    @GetMapping("/posts/{id}/delete")
    public String getDeletePostForm(@PathVariable long id, Model model){
        Post deletePost = postRepo.getOne(id);
        model.addAttribute("post", deletePost);
        return "post/delete";
    }


//******************** method for deleting post and rerouting user to post page ****************
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@ModelAttribute Post post){
        Post deletePost = postRepo.getOne(post.getId());
        postRepo.delete(deletePost);
        return "redirect:/posts";
    }


}
