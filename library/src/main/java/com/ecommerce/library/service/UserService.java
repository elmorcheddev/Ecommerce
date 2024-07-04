package com.ecommerce.library.service;

import java.util.List;

import com.ecommerce.library.dto.CustomUserDto;
import com.ecommerce.library.model.CustomUser;

public interface UserService {

	CustomUser findByEmail(String email);
	CustomUserDto saveUser(CustomUserDto customUserDto);
	CustomUser finduserById(Long id_user);
	CustomUser saveCustomUser(CustomUser customUser);
}
