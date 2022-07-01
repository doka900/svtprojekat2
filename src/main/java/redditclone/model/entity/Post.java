package redditclone.model.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPost",unique = true,nullable = false)
	private Long id;
	@Column
	private String title;
	@Column
	private String text;
	@Column
	private LocalDate creationDate;
	@Column
	private String imagePath;
	
	@JsonBackReference
    @ManyToOne
    @JoinColumn(name="community_id", nullable=false)
    private Community community;
    
    
    @JoinColumn(name = "authorid", referencedColumnName = "id" , nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
	private User author;
		

}
