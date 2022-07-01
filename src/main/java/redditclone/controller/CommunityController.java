package redditclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redditclone.model.dto.CommunityDTO;
import redditclone.model.dto.CreateCommunityDTO;
import redditclone.model.dto.UpdateUserDTO;
import redditclone.model.dto.UserDTO;
import redditclone.model.entity.Community;
import redditclone.model.entity.User;
import redditclone.service.CommunityService;
import redditclone.service.UserService;
@CrossOrigin
@RestController
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	CommunityService comService;
    
    @GetMapping(value = "/")
    public ResponseEntity<List<Community>> findAllCommunities(){
        return new ResponseEntity<>(comService.findAllCommunities(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/",consumes = "application/json")
    public ResponseEntity<Community> saveCommunity(@RequestBody CreateCommunityDTO comDTO){
    	Community newCom = comService.createCommunity(comDTO);
        return new ResponseEntity<Community>(newCom,HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/{name}")
    public ResponseEntity<CommunityDTO> findName(@PathVariable("name") String name){
    	CommunityDTO foundCom = comService.findByCommunityName(name);
        return new ResponseEntity<CommunityDTO>(foundCom, HttpStatus.OK);
    }

    
    @PutMapping(value = "/{name}", consumes = "application/json")
    public ResponseEntity<Community> updateCommunity(@RequestBody CommunityDTO updateComDTO, @PathVariable("name") String oldname){
     return new ResponseEntity<Community>(comService.updateCommunity(updateComDTO,oldname), HttpStatus.OK);
    }

}