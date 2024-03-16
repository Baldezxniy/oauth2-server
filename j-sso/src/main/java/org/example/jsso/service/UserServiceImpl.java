package org.example.jsso.service;

import lombok.RequiredArgsConstructor;
import org.example.jsso.dao.entity.UserEntity;
import org.example.jsso.dao.repository.UserRepository;
import org.example.jsso.dto.AuthorizedUser;
import org.example.jsso.exception.AuthException;
import org.example.jsso.mapper.AuthorizedUserMapper;
import org.example.jsso.type.AuthErrorCode;
import org.example.jsso.type.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public UserEntity save(OAuth2User userDto, AuthProvider provider) {
		return switch (provider) {
			case GITHUB -> saveUserFromGithab(userDto);
			case GOOGLE -> saveUserFromGoogle(userDto);
		};
	}


	@Override
	public AuthorizedUser saveAndMap(OAuth2User userDto, AuthProvider provider) {
		UserEntity entity = this.save(userDto, provider);
		return AuthorizedUserMapper.map(entity);
	}

	private UserEntity saveUserFromGithab(OAuth2User userDto) {
		String email = userDto.getAttribute("email");
//		if (email == null) {
//			throw new AuthException(AuthErrorCode.EMAIL_IS_EMPTY);
//		}
		if(email == null){
			email = "user@user.com";
		}

		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			user = new UserEntity();
			user.setEmail(email);
			user.setActive(true);
		}

		if (userDto.getAttribute("name") != null) {
			String[] splitted = ((String) userDto.getAttribute("name")).split(" ");
			user.setFirstName(splitted[0]);
			if (splitted.length > 1) {
				user.setSecondName(splitted[1]);
			}
			if (splitted.length > 2) {
				user.setMiddleName(splitted[2]);
			}
		} else {
			user.setFirstName(userDto.getAttribute("login"));
			user.setSecondName(userDto.getAttribute("login"));
		}
		if (userDto.getAttribute("avatar_url") != null) {
			user.setAvatar(userDto.getAttribute("avatar_url"));
		}

		return userRepository.save(user);
	}

	private UserEntity saveUserFromGoogle(OAuth2User userDto) {
		String email = userDto.getAttribute("email");
		if (email == null) {
			throw new AuthException(AuthErrorCode.EMAIL_IS_EMPTY);
		}

		UserEntity user = this.userRepository.findByEmail(email);
		if (user == null) {
			user = new UserEntity();
			user.setEmail(email);
			user.setActive(true);
		}

		if (userDto.getAttribute("given_name") != null) {
			user.setFirstName(userDto.getAttribute("given_name"));
		}

		if (userDto.getAttribute("family_name") != null) {
			user.setSecondName(userDto.getAttribute("family_name"));
		}

		if (userDto.getAttribute("picture") != null) {
			user.setAvatar(userDto.getAttribute("picture"));
		}

		return userRepository.save(user);
	}
}
