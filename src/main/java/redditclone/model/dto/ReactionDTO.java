package redditclone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redditclone.model.entity.Reaction;
import redditclone.model.entity.ReactionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDTO {

	private ReactionType type;
	private long voterId;
	private long postId;
	
}
