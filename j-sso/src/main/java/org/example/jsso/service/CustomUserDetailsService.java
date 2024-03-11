package org.example.jsso.service;

import lombok.RequiredArgsConstructor;
import org.example.jsso.dao.entity.UserEntity;
import org.example.jsso.dao.repository.UserRepository;
import org.example.jsso.mapper.AuthorizedUserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(username);
		if(entity == null){
			throw new UsernameNotFoundException("User with username = " + username + " not found");
		}

		return AuthorizedUserMapper.map(entity);
	}
}
