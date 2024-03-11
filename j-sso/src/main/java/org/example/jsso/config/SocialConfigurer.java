package org.example.jsso.config;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Setter
@Accessors(chain = true, fluent = true)
public class SocialConfigurer extends AbstractHttpConfigurer<SocialConfigurer, HttpSecurity> {

	private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;
	private AuthenticationFailureHandler failureHandler;
	private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();

	@Override
	public void init(HttpSecurity http) throws Exception {
		http.oauth2Login(oAuth2Login -> {
			if (this.oAuth2UserService != null) {
				oAuth2Login.userInfoEndpoint(configure ->
						configure.userService(this.oAuth2UserService));
			}

			if (this.successHandler != null) {
				oAuth2Login.successHandler(this.successHandler);
			}

			if (this.failureHandler != null) {
				oAuth2Login.failureHandler(failureHandler);
			}
		});
	}
}
