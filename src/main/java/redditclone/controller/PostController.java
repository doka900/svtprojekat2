package redditclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redditclone.model.dto.CreatePostDTO;
import redditclone.model.dto.PostDTO;
import redditclone.model.dto.PostsDTO2;
import redditclone.model.entity.Post;
import redditclone.repository.PostRepository;
import redditclone.service.PostService;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	PostService postService;
	
	
    @GetMapping(value = "/")
    public ResponseEntity<List<PostsDTO2>> findAllPosts(){
        return new ResponseEntity<>(postService.findAllPosts(),HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/{name}/")
    public ResponseEntity<List<PostsDTO2>> findPost(@PathVariable("name") String name){
        return new ResponseEntity<>(postService.findPosts(name), HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Post> savePost(@RequestBody CreatePostDTO postDTO){
    	System.out.println(postDTO.getAuthorName() + " " + postDTO.getCommunityName());
        Post newPost = postService.createPost(postDTO);
        return new ResponseEntity<Post>(newPost,HttpStatus.OK);
    }
    

    
    @DeleteMapping(value ="/{name}")
    void deletePost(@PathVariable String name) {
        postService.deleteByTitle(name);
      }
}
