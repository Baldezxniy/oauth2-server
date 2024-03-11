package org.example.jsso.dao.repository;

import org.example.jsso.dao.entity.UserEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository implements InitializingBean {

	private final Map<UUID, UserEntity> store = new HashMap<>();

	public UserEntity save(UserEntity entity) {
		if (entity.getId() == null) {
			entity.setId(UUID.randomUUID());
		}

		this.store.put(entity.getId(), entity);
		return entity;
	}

	public UserEntity findById(UUID id) {
		return store.getOrDefault(id, null);
	}

	public UserEntity findByEmail(String email) {
		return this.store.values().stream()
				.filter(u -> u.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}

	// @formatter:off
	@Override
	public void afterPropertiesSet() throws Exception {
		this.save(
				UserEntity.builder()
						.passwordHash("{noop}admin@example.com")
						.email("email@google.com")
						.active(true)
						.firstName("Admin")
						.secondName("Admin")
						.birthday(LocalDate.of(1998, 7, 14))
						.build()
		);
	}
}