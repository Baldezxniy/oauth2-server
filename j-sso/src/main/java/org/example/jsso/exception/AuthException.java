package org.example.jsso.exception;

import lombok.Getter;
import org.example.jsso.type.AuthErrorCode;

import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthException extends AuthenticationException {

	private final AuthErrorCode errorCode;

	public AuthException(AuthErrorCode errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	public AuthException(String msg, AuthErrorCode errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	public AuthException(AuthErrorCode errorCode) {
		super(null);
		this.errorCode = errorCode;
	}
}