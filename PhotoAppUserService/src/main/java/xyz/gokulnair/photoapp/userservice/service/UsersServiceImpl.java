package xyz.gokulnair.photoapp.userservice.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.gokulnair.photoapp.userservice.data.UserEntity;
import xyz.gokulnair.photoapp.userservice.data.UserRepository;
import xyz.gokulnair.photoapp.userservice.shared.UserDto;


@Service
public class UsersServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UsersServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	

	@Override
	public UserDto CreateUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity=modelMapper.map(userDetails, UserEntity.class);
		userEntity.setEncryptedPassword("test");
		
		userRepository.save(userEntity);
		
		UserDto returnValue=modelMapper.map(userEntity, UserDto.class);
		
		
		
		return returnValue;
	}

	

}
