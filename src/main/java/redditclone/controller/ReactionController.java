package redditclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redditclone.model.dto.CommunityDTO;
import redditclone.model.dto.CreateCommunityDTO;
import redditclone.model.dto.ReactionDTO;
import redditclone.model.entity.Community;
import redditclone.model.entity.Reaction;
import redditclone.service.ReactionService;

@CrossOrigin
@RestController
@RequestMapping("/reaction")
public class ReactionController {
	@Autowired
	ReactionService reactionService;

    @GetMapping(value = "/{postId}")
    public ResponseEntity<Integer> findKarmaOfPost(@PathVariable("postId") long postId){
        return new ResponseEntity<Integer>(reactionService.getKarmaOfPost(postId), HttpStatus.OK);
    }
    
    @GetMapping(value = "/voter/{voterId}/")
    public ResponseEntity<Integer> findKarmaOfUser(@PathVariable("voterId") long voterId){
        return new ResponseEntity<>(reactionService.getKarmaOfUser(voterId), HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Boolean> hasVoted(@RequestBody ReactionDTO reactionDTO){
        return new ResponseEntity<Boolean>(reactionService.alreadyVoted(reactionDTO),HttpStatus.OK);
    }
    
    @PutMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Boolean> changeVote(@RequestBody ReactionDTO reactionDTO){
        return new ResponseEntity<Boolean>(reactionService.changeVote(reactionDTO),HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Reaction> saveReaction(@RequestBody ReactionDTO reactionDTO){
    	Reaction newReaction = reactionService.createReaction(reactionDTO);
        return new ResponseEntity<Reaction>(newReaction,HttpStatus.CREATED);
    }
    
}
