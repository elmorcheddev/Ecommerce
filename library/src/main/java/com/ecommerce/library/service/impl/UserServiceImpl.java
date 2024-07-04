package com.ecommerce.library.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.library.dto.CustomUserDto;
import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.repository.RoleRepository;
import com.ecommerce.library.repository.UserRepository;
import com.ecommerce.library.service.UserService;


@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	
	public CustomUserDto saveUser(CustomUserDto customUserDto) {
		CustomUser customUser= new CustomUser();
		customUser.setFirstname(customUserDto.getFirstname());
		customUser.setLastname(customUserDto.getLastname());
		customUser.setEmail(customUserDto.getEmail());
		customUser.setPassword(customUserDto.getPassword());
		customUser.setRole(Arrays.asList(roleRepository.findByName("USER")));
		CustomUser customUsersave = userRepository.save(customUser);
	return mapperDTO(customUsersave);
	
	}
	public CustomUserDto mapperDTO(CustomUser customUser) {
		CustomUserDto customUserDto= new CustomUserDto();
		customUserDto.setFirstname(customUser.getFirstname());
		customUserDto.setLastname(customUser.getLastname());
		customUserDto.setEmail(customUser.getEmail());
		customUserDto.setPassword(customUser.getPassword());
		return customUserDto;
	}

	@Override
	public CustomUser finduserById(Long id_user) {
		 
		return userRepository.findById(id_user).get();
	}

	@Override
	public CustomUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}
	@Override
	public CustomUser saveCustomUser(CustomUser customUser) {
			CustomUser customUsersave = userRepository.findUserByEmail(customUser.getEmail());
			customUsersave.setFirstname(customUser.getFirstname());
			customUsersave.setLastname(customUser.getLastname());
			customUsersave.setAdresse(customUser.getAdresse());
			customUsersave.setPhone(customUser.getPhone());
			customUsersave.setEmail(customUser.getEmail());
			customUsersave.setDatenaissance(customUser.getDatenaissance());
		return userRepository.save(customUsersave);
	}

}
