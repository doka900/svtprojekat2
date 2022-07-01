package redditclone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redditclone.model.dto.CreatePostDTO;
import redditclone.model.dto.PostDTO;
import redditclone.model.dto.PostsDTO2;
import redditclone.model.dto.ReactionDTO;
import redditclone.model.dto.UpdateUserDTO;
import redditclone.model.dto.UserDTO;
import redditclone.model.entity.Post;
import redditclone.model.entity.Reaction;
import redditclone.model.entity.ReactionType;
import redditclone.model.entity.User;
import redditclone.repository.CommunityRepository;
import redditclone.repository.PostRepository;
import redditclone.repository.ReactionRepository;
import redditclone.repository.UserRepository;
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommunityRepository comRepository;
	@Autowired
	ReactionService reactionService;
	
	public List<PostsDTO2> findAllPosts() {
		
		
		List<PostsDTO2> posts =  new ArrayList<>();	
		for(Post p : postRepository.findAll())
		{
			PostsDTO2 post = new PostsDTO2();
			post.setId(p.getId());
			System.out.println("ID POSTA" + post.getId());
			post.setText(p.getText());
			post.setTitle(p.getTitle());
			post.setCommunityName(comRepository.findCommunityById(p.getCommunity().getId()).getName());
			post.setAuthorName(userRepository.findUserById(p.getAuthor().getId()).getUsername());
			posts.add(post);
		}
		
	    return posts;
	}


	public Post findById(Long id) {
	    return postRepository.findPostById(id);
	}
	
	public List<PostsDTO2> findPosts(String name) {
		
		List<PostsDTO2> posts =  new ArrayList<>();	
		for(Post p : postRepository.getPost(comRepository.findByName(name).getId()))
		{
			PostsDTO2 post = new PostsDTO2();
			
			post.setId(p.getId());
			System.out.println("ID POSTA" + post.getId());
			post.setText(p.getText());
			post.setTitle(p.getTitle());
			post.setCommunityName(comRepository.findCommunityById(p.getCommunity().getId()).getName());
			post.setAuthorName(userRepository.findUserById(p.getAuthor().getId()).getUsername());
			posts.add(post);
		}
		
	    return posts;
	}
	

	public Post createPost(CreatePostDTO postDTO) {

		
	    Post newPost = new Post();
	    System.out.println(postDTO.getAuthorName());
	    
	    newPost.setTitle(postDTO.getTitle());
	    newPost.setText(postDTO.getText());
	    newPost.setCommunity(comRepository.findByName((postDTO.getCommunityName())));
	    newPost.setAuthor(userRepository.findByUsername(postDTO.getAuthorName()));
	    
	    System.out.println(postDTO.getAuthorName());
	    newPost.setImagePath("");
	    
	    ReactionDTO reaction = new ReactionDTO();
	    reaction.setPostId(postRepository.findAll().size()+1);
	    reaction.setVoterId(newPost.getAuthor().getId());
	    reaction.setType(ReactionType.UPVOTE);
	    
	    reactionService.createReaction(reaction);
	    
	    postRepository.save(newPost);

	    return newPost;
	}

	public Post updatePost(PostDTO postDTO, long id) {
		 Post newPost = postRepository.findById(id).orElse(null);
		 
		 if(!postDTO.getTitle().equals(null)) {
			 newPost.setTitle(postDTO.getTitle());
		 }
		 
		 return newPost;
		 
	}


	public void deleteByTitle(String name) {
		postRepository.deleteByTitle(name);
		
	}
	

}
