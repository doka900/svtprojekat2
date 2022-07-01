package redditclone.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reaction")
public class Reaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "`reaction_type`")
	private ReactionType type;
	@Column
	private LocalDate timestamp;
	@Column
	private long voterId;
	@Column
	private long postId;
	
	
}
