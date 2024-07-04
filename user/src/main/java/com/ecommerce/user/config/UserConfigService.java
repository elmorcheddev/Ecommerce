package com.ecommerce.user.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecommerce.library.model.CustomUser;
import com.ecommerce.library.repository.UserRepository;

public class UserConfigService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			CustomUser user= userRepository.findUserByEmail(username);
			if(user == null) {
				throw new UsernameNotFoundException("Email dos'nt exist");
			}
		return new User(user.getEmail(),
				user.getPassword(),
				user.getRole() .stream()
                .map(role ->
                new SimpleGrantedAuthority(role.getName()))
                  .collect(Collectors
                     .toList()));
	}

}
