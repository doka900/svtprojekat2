package redditclone.model.entity;
import java.lang.reflect.Array;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="communities")
public class Community {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCommunity",unique = true,nullable = false)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private LocalDate creationDate;
	@Column
	private boolean isSuspended;
	@Column
	private String suspendedReason;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banId" , referencedColumnName = "id", nullable = true)
	private Banned banned;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moderatorId", referencedColumnName = "id")
	private User moderator;

    @JsonManagedReference
    @OneToMany(mappedBy = "community")
    private Set<Post> posts;
}
