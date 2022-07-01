package redditclone.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redditclone.model.entity.Community;
import redditclone.model.entity.Post;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long>{

	
	Community findByName(String name);
	Community findCommunityById(long id);
	Community deleteByName(String name);
	
	@Modifying
	@Transactional
	@Query(value = "update communities c set c.name = ?1, c.description = ?2 where c.id_community=?3 ", nativeQuery = true)
    void updateCommunity(String name, String description, long id );
}
