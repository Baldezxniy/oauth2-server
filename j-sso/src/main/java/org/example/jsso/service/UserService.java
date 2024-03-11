package org.example.jsso.service;


import org.example.jsso.dao.entity.UserEntity;
import org.example.jsso.dto.AuthorizedUser;
import org.example.jsso.type.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
	UserEntity save(OAuth2User userDto, AuthProvider provider);

	AuthorizedUser saveAndMap(OAuth2User userDto, AuthProvider provider);
}
