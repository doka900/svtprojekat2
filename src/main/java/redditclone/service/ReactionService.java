package redditclone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import redditclone.model.dto.CreateCommunityDTO;
import redditclone.model.dto.ReactionDTO;
import redditclone.model.entity.Community;
import redditclone.model.entity.Post;
import redditclone.model.entity.Reaction;
import redditclone.model.entity.ReactionType;
import redditclone.repository.PostRepository;
import redditclone.repository.ReactionRepository;
import redditclone.repository.UserRepository;

@AllArgsConstructor
@Service
public class ReactionService {

	@Autowired
	private ReactionRepository reactionRepository;
	@Autowired
	private PostRepository postRepository;
	
	public List<Reaction> findAllByPost(long postId){
		return reactionRepository.findAllByPost(postId);
	}
	
	public List<Reaction> findUpvotesOfPost(long postId){
		return reactionRepository.findUpvotesOfPost(postId);
	}
	
	public List<Reaction> findDownVotesOfPost(long postId){
		return reactionRepository.findDownVotesOfPost(postId);
	}
	
	public List<Reaction> findAllByVoter(long voterId){
		return reactionRepository.findAllByVoter(voterId);
	}
	public List<Reaction> findUpVotesOfUser(long voterId){
		return reactionRepository.findUpVotesOfUser(voterId);
	}
	public List<Reaction> findDownVotesOfUser(long voterId){
		return reactionRepository.findDownVotesOfUser(voterId);
	}
	
	public int getKarmaOfPost(long postId) {
		return findUpvotesOfPost(postId).size() - findDownVotesOfPost(postId).size();
	}
	
	public int getKarmaOfUser(long userId) {
		int karma= 0;
		List<Post> posts = postRepository.getPostOfUser(userId);
		for(Post post: posts) {
				karma += getKarmaOfPost(post.getId());
		}
		
		return karma;	
	}
	
	public boolean alreadyVoted(ReactionDTO reactionDTO) {
		if(((List<Reaction>) reactionRepository.alreadyVoted(reactionDTO.getPostId(), reactionDTO.getVoterId())).isEmpty())return false;
		else return true;
	}
	
	public Boolean changeVote(ReactionDTO reactionDTO) {

		Reaction re = reactionRepository.vote(reactionDTO.getPostId(), reactionDTO.getVoterId());
		
		if(re.getType() != reactionDTO.getType())
		{
		if(reactionDTO.getType().toString().equals("DOWNVOTE"))
		reactionRepository.changeVote("DOWNVOTE", reactionDTO.getPostId(), reactionDTO.getVoterId()) ;
		
		else if(reactionDTO.getType().toString().equals("UPVOTE"))
		reactionRepository.changeVote("UPVOTE",reactionDTO.getPostId(), reactionDTO.getVoterId()) ;
		}
		return true;
		
	}
	
	public Reaction createReaction(ReactionDTO reactionDTO) {
		
		if(!alreadyVoted(reactionDTO))
		{
			
		Reaction re = new Reaction();
		re.setPostId(reactionDTO.getPostId());
		re.setVoterId(reactionDTO.getVoterId());
		re.setTimestamp(LocalDate.now());
		if(reactionDTO.getType() == ReactionType.UPVOTE)
		re.setType(ReactionType.UPVOTE);
		if(reactionDTO.getType() == ReactionType.DOWNVOTE)
		re.setType(ReactionType.DOWNVOTE);
		
		System.out.println(re.getType());
	    reactionRepository.save(re);
	    return re;
	    }
		else {
			changeVote(reactionDTO);
			return null;
		}
	}
}
