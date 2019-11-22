package pe.spring.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public final class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(
		      HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
		      throws IOException {
		
		response.addHeader("WWW-Authenticate", "Basic realm='" + getRealmName() + "'");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		final PrintWriter writer = response.getWriter();
		writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authEx.getMessage() + " - [Comunicarse con el administrador]");
	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("Denegado");
		super.afterPropertiesSet();
	}

}
