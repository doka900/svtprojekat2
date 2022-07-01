package redditclone.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;


public class Comment {
 
	private Long id;
	private String text;
	private LocalDate timestamp;
	private boolean isDeleted;
	private Comment comment;
	private Post post;
	private User user;
	private ArrayList<Reaction>reactions;
	private ArrayList<Report>reports;
	
}
