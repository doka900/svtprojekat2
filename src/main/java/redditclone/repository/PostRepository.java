package redditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redditclone.model.entity.Post;
import redditclone.model.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>  {

	
	Post findPostById(long id);
	
    @Query (value = "select * from posts p where p.authorid = ?1", nativeQuery = true)
    public List<Post> getPostOfUser(long user_id);
    
    @Query (value = "select * from posts p where p.community_id = ?1", nativeQuery = true)
    public List<Post> getPost(long community_id);

    
    @Transactional
    void deleteByTitle(String title);

}
