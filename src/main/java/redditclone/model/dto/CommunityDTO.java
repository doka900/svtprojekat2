package redditclone.model.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redditclone.model.entity.Banned;
import redditclone.model.entity.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDTO {

	private String name;

	private String description;

	private LocalDate creationDate;

	private boolean isSuspended;

	private String suspendedReason;

	private Banned banned;
	
	private String moderatorUsername;


}
