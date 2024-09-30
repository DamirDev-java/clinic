package com.super_clinic.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.super_clinic.security.jwt.JwtConfigurer;
import com.super_clinic.security.jwt.LoginUrlAuthenticationEntryPoint;

import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtConfigurer jwtConfigurer;

	@Autowired
	private LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;

	public SecurityConfig(JwtConfigurer jwtConfigurer) {
		this.jwtConfigurer = jwtConfigurer;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
		http.oauth2Login(Customizer.withDefaults());
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/", "api/login", "api/v1/clinic/home", "/doctors", "/services", "api/home", "/patient/new", "/register/save", "/service/doctor/**", "/health", "/api/storage/**", "/api/storage/download/**").permitAll()
						.requestMatchers("/patient/home").authenticated()
						.requestMatchers("/assets/**", "/forms/**", "/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**").permitAll()
						.anyRequest().authenticated()).build();
//				)
//				.sessionManagement(sessionManagement ->
//						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				)
//				.exceptionHandling(exceptionHandling ->
//						exceptionHandling.authenticationEntryPoint(loginUrlAuthenticationEntryPoint)
//				)
//				.with(jwtConfigurer, Customizer.withDefaults());

	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		var converter = new JwtAuthenticationConverter();
		var jwtGrantedAuthoritiesContainer = new JwtGrantedAuthoritiesConverter();
		converter.setPrincipalClaimName("preferred_username");
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			var authorities = jwtGrantedAuthoritiesContainer.convert(jwt);
			var roles = jwt.getClaimAsStringList("spring_sec_roles");

			return Stream.concat(authorities.stream(),
							roles.stream()
									.filter(role -> role.startsWith("ROLE_"))
									.map(SimpleGrantedAuthority::new)
									.map(GrantedAuthority.class::cast))
					.toList();
		});
		return converter;
    }

	@Bean
	public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
		var oidcUserService = new OidcUserService();
		return userRequest -> {
			var oidcUser = oidcUserService.loadUser(userRequest);
			var roles = oidcUser.getClaimAsStringList("spring_sec_roles");
			var authorities = Stream.concat(oidcUser.getAuthorities().stream(),
							roles.stream()
									.filter(role -> role.startsWith("ROLE_"))
									.map(SimpleGrantedAuthority::new)
									.map(GrantedAuthority.class::cast))
					.toList();

			return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
		};
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
