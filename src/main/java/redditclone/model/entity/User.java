package redditclone.model.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import redditclone.repository.UserRepository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true,nullable = false)
	private Long id;
	 
	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private String avatarUrl;
	

	@Column
	private LocalDate registrationDate;
	
	@Column
	private String description;
	
	@Column
	private String displayName;
	
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;


}

