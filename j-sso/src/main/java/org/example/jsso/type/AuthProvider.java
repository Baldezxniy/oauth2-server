package org.example.jsso.type;

import java.util.stream.Stream;

public enum AuthProvider {
	GOOGLE("google"),
	GITHUB("github");

	private final String providerName;

	AuthProvider(String providerName) {
		this.providerName = providerName;
	}

	public static AuthProvider findByName(String providerName) {
		return Stream.of(values())
				.filter(item -> item.getProviderName().equals(providerName))
				.findFirst()
				.orElse(null);
	}

	public String getProviderName() {
		return providerName;
	}
}