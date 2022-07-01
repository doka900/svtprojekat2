package redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redditclone.model.dto.ReactionDTO;
import redditclone.model.entity.Reaction;
import redditclone.model.entity.ReactionType;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{
	
	
    @Query (value = "select * from reaction v where v.post_id = ?1", nativeQuery = true)
	List<Reaction> findAllByPost(long postId);
    @Query (value = "select * from reaction v where v.voter_id = ?1", nativeQuery = true)
	List<Reaction> findAllByVoter(long voterId);
    
    
    
    @Query (value = "select * from reaction v where v.post_id = ?1 and v.reaction_type like 'UPVOTE'", nativeQuery = true)
	List<Reaction> findUpvotesOfPost(long postId);
	
    @Query (value = "select * from reaction v where v.post_id = ?1 and v.reaction_type like 'DOWNVOTE'", nativeQuery = true)
	List<Reaction> findDownVotesOfPost(long postId);

    @Query (value = "select * from reaction v where v.voter_id = ?1 and v.reaction_type like 'UPVOTE'", nativeQuery = true)
	List<Reaction> findDownVotesOfUser(long voterId);
    
    @Query (value = "select * from reaction v where v.voter_id = ?1 and v.reaction_type like 'DOWNVOTE'", nativeQuery = true)
	List<Reaction> findUpVotesOfUser(long voterId);
    
    @Query(value = "SELECT * from reaction v where v.post_id = ?1 and v.voter_id = ?2", nativeQuery = true)
    List<Reaction> alreadyVoted(long postId, long voterId);
      
    @Query(value = "SELECT * from reaction v where v.post_id = ?1 and v.voter_id = ?2", nativeQuery = true)
    Reaction vote(long postId, long voterId);
    
	@Modifying
	@Transactional
	@Query(value = "update reaction v set v.reaction_type = ?1 where v.post_id = ?2 and v.voter_id = ?3", nativeQuery = true)
	void changeVote(String string, long postId, long voterId);
    
}
