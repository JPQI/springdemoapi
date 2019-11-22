package pe.spring.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pe.spring.demo.dto.MyUserPrincipal;
import pe.spring.demo.services.impl.UserServiceImpl;


@Component
public class DemoAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    private UserServiceImpl userService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		try {

			// Quiza necesite una validación previa en BD
			if (username == null || username.equals("unauthorized"))
				throw new BadCredentialsException("El usuario esta incorrecto o no se ha ingresado.");

			if (password == null)
				throw new BadCredentialsException("La constraseña es incorrecta o es inválida.");
			
			MyUserPrincipal user = (MyUserPrincipal) userService.loadUserByUsername(username);
			
			if(user == null || StringUtils.isEmpty( user.getUsername() )) {
				throw new UsernameNotFoundException("No se encuentra el usuario: " + username);
			}
	        
	        if (!passwordEncoder().matches(password, user.getPassword())) {
	            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
	        }

			
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			
			return new PreAuthenticatedAuthenticationToken(username, password, grantedAuths);
			

		} catch (Exception e) {
			
			throw new BadCredentialsException("El sistema de autenticacón falló: " + e.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
