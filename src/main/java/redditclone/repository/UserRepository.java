package redditclone.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import redditclone.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	User findByUsername(String username);
	
	User deleteByUsername(String username);
	User findUserById(long id);
	
	@Modifying
	@Transactional
	@Query(value = "update users u set u.display_name = ?1, u.description = ?2 where u.id=?3 ", nativeQuery = true)
	
    void updateUser(String displayName, String description, long id );
	@Modifying
	@Transactional
	@Query(value = "update users u set u.password = ?1 where u.id=?2 ", nativeQuery = true)
	void changePassord(String password, Long id);

}

