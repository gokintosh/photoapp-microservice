package xyz.gokulnair.photoapp.userservice.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gokulnair.photoapp.userservice.service.UserService;
import xyz.gokulnair.photoapp.userservice.shared.UserDto;
import xyz.gokulnair.photoapp.userservice.ui.model.CreateUserRequestModel;
import xyz.gokulnair.photoapp.userservice.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {


	@Autowired
	UserService userService;
	
	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto=modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser=userService.CreateUser(userDto);
		
		CreateUserResponseModel returnValue=modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	
}
