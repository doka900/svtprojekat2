package redditclone.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import lombok.AllArgsConstructor;
import redditclone.model.dto.UpdateUserDTO;
import redditclone.model.dto.UserDTO;
import redditclone.model.entity.Roles;
import redditclone.model.entity.User;
import redditclone.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService{

@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;

public List<User> findAllUsers() {
    return userRepository.findAll();
}

public User findByUsername(String username) {
    return userRepository.findByUsername(username);
}

public User findById(Long id) {
    return userRepository.findUserById(id);
}

public void deleteUser(String username) {
    userRepository.deleteByUsername(username);
}


public User createUser(UserDTO userDTO) {
    User newUser = new User();
    newUser.setUsername(userDTO.getUsername());
    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    newUser.setEmail(userDTO.getEmail());
    newUser.setAvatarUrl("avatar");
    newUser.setRegistrationDate(LocalDate.now());
    newUser.setDescription("Opis");
    newUser.setDisplayName(userDTO.getUsername());
    newUser.setRole(Roles.USER);
    userRepository.save(newUser);
    return newUser;
}

public User updateUser(UpdateUserDTO updateUserDTO, String username) {

    User newUser = userRepository.findByUsername(username);


    if(!updateUserDTO.getDescription().equals(null)){
    	newUser.setDescription(updateUserDTO.getDescription());
    }

    if(!updateUserDTO.getDisplayName().equals(null)){
    	newUser.setDisplayName(updateUserDTO.getDisplayName());
    }
    
    userRepository.updateUser(newUser.getDisplayName(), newUser.getDescription(),newUser.getId());
    return newUser;
}

public User changePassword(String newPassword, String username) {
	
	 User newUser = userRepository.findByUsername(username);
	 System.out.println("NEW PASSWORD" + newUser.getPassword());
	 newUser.setPassword(passwordEncoder.encode(newPassword));
	 System.out.println("NEW PASSWORD" + newUser.getPassword());
	 userRepository.changePassord(newUser.getPassword(),newUser.getId());
	 return newUser;
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = findByUsername(username);

    if(user == null){
        throw new UsernameNotFoundException("There is no user with username " + username);
    }else{
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = "ROLE_" + user.getRole().toString();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername().trim(),
                user.getPassword().trim(),
                grantedAuthorities);
    }
}

public Boolean checkOldPassword(String oldPassword, String username) {

	 User newUser = userRepository.findByUsername(username);
	 
	 String encode = passwordEncoder.encode(oldPassword);
	 
	 System.out.println("sifre: " + newUser.getPassword() + " " + encode);
	 if(passwordEncoder.matches( oldPassword,newUser.getPassword())) {
		 
	 return true;
	 }
	 else return false;
}




}
