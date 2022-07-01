package redditclone.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redditclone.model.dto.CommunityDTO;
import redditclone.model.dto.JwtAuthRequestDTO;
import redditclone.model.dto.UpdateUserDTO;
import redditclone.model.dto.UserDTO;
import redditclone.model.dto.UserTokenDTO;
import redditclone.model.entity.Community;
import redditclone.model.entity.User;
import redditclone.repository.UserRepository;
import redditclone.security.TokenUtils;
import redditclone.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    TokenUtils tokenUtils;

    
    @GetMapping(value = "/")
    public ResponseEntity<List<User>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }
    
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO){
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<User>(newUser,HttpStatus.OK);
    }
    
    @PostMapping(value = "/signup",consumes = "application/json")
    public ResponseEntity<User> signup(@RequestBody UserDTO userDTO){
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<User>(newUser,HttpStatus.OK);
    }
    
    @GetMapping(value = "/{username}")
    public ResponseEntity<User> findUser(@PathVariable("username") String username){
        User foundUser = userService.findByUsername(username);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }
    
    @GetMapping(value = "id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") long id){
        User foundUser = userService.findById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> createAuthenticationToken(
            @RequestBody JwtAuthRequestDTO authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenDTO(jwt, expiresIn));
    }
    
    
    
    
    @PutMapping(value = "changepassword/{username}")
    public ResponseEntity<User> updateUser(@RequestBody String newPassword, @PathVariable("username") String username){
    	System.out.println("KONTROLER NEW PASSOWRD" + newPassword);
     return new ResponseEntity<>(userService.changePassword(newPassword,username), HttpStatus.OK);
    }
    
    @PostMapping(value = "checkOldPassword/{username}")
    public ResponseEntity<Boolean> checkOldPassword(@RequestBody String newPassword, @PathVariable("username") String username){
    	System.out.println("KONTROLER NEW PASSOWRD" + newPassword);
     return new ResponseEntity<Boolean>(userService.checkOldPassword(newPassword,username), HttpStatus.OK);
    }
    
    @PutMapping(value = "/{username}", consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable("username") String username){
     return new ResponseEntity<User>(userService.updateUser(updateUserDTO,username), HttpStatus.OK);
    }
    
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAllUsers();
    }

    @GetMapping("/whoami")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }
    

}
