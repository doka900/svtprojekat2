package redditclone.model.dto;

import lombok.*;
import redditclone.model.entity.User;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{

    
	private String username;
    private String password;
    private String email;

    public UserDTO(User user){
        this(user.getUsername(), user.getPassword(), user.getEmail());

    }
}


