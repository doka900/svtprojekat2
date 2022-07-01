package redditclone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redditclone.model.dto.CommunityDTO;
import redditclone.model.dto.CreateCommunityDTO;
import redditclone.model.entity.Community;
import redditclone.model.entity.User;
import redditclone.repository.CommunityRepository;
import redditclone.repository.UserRepository;

@Service
public class CommunityService {
	
	@Autowired
	private CommunityRepository communityRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	public List<Community> findAllCommunities() {
	    return communityRepository.findAll();
	}

	public CommunityDTO findByCommunityName(String name) {
		CommunityDTO com = new CommunityDTO();
		Community com1 = communityRepository.findByName(name);
		com.setName(com1.getName());
		com.setDescription(com1.getDescription());
		
		com.setModeratorUsername(com1.getModerator().getUsername());
		
	    return com;
	}

	public Community findById(Long id) {
	    return communityRepository.findCommunityById(id);
	}

	public void deleteCommunity(String name) {
	    communityRepository.deleteByName(name);
	}


	public Community createCommunity(CreateCommunityDTO communityDTO) {
		
		Community com = new Community();			
		com.setModerator(userRepository.findByUsername(communityDTO.getModeratorUsername()));
		com.setName(communityDTO.getName());
		com.setCreationDate(LocalDate.now());
		com.setDescription(communityDTO.getDescription()); 
		com.setSuspended(false);
		com.setSuspendedReason("");
		
		//com.setBanned(null);
	
	    communityRepository.save(com);
	    return com;
	}

	public Community updateCommunity(CommunityDTO comDTO, String oldname) {

		Community newCom = communityRepository.findByName(oldname);

	    if (!comDTO.getName().equals(null)){
	    	newCom.setName(comDTO.getName());
	    }
	    if (!comDTO.getDescription().equals(null)){
	    	newCom.setDescription(comDTO.getDescription());
	    }
	    

	    communityRepository.updateCommunity(comDTO.getName(), comDTO.getDescription(),newCom.getId());
	    return newCom;
	}
	
	
}
