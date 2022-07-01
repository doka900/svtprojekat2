package redditclone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostsDTO2 {
	
	private long id;
	
	private String title;

	private String text;

	
	private String authorName;
	
	private String communityName;
	

}

