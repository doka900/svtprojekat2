package redditclone.model.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;


public class Report {

	private Long id;
	private ReportReason reason;
	private LocalDate timestamp;
	private User byUser;
	private boolean accepted;
	
	
}
