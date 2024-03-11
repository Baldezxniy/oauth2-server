package org.example.jsso.dao.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	private UUID id;
	private String email;
	private String passwordHash;
	private String firstName;
	private String secondName;
	private String middleName;
	private LocalDate birthday;
	private String avatar;
	private Boolean active;

}
