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
@Table(name="bans")
public class Banned {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate timestamp;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId" , referencedColumnName = "id", nullable = false)
	private User moderator;
	
}
