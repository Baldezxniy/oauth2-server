package org.example.jsso.mapper;

import lombok.experimental.UtilityClass;
import org.example.jsso.dao.entity.UserEntity;
import org.example.jsso.dto.AuthorizedUser;

import java.util.Collections;

@UtilityClass
public class AuthorizedUserMapper {
	public static AuthorizedUser map(UserEntity entity) {
		return AuthorizedUser.builder(entity.getEmail(), entity.getPasswordHash(), Collections.emptyList())
				.id(entity.getId())
				.firstName(entity.getFirstName())
				.secondName(entity.getSecondName())
				.middleName(entity.getMiddleName())
				.birthday(entity.getBirthday())
				.avatarUrl(entity.getAvatar())
				.build();
	}
}
