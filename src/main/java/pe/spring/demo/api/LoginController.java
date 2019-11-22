package pe.spring.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.spring.demo.config.DemoAuthenticationProvider;
import pe.spring.demo.dto.JwtRequest;
import pe.spring.demo.dto.JwtResponse;
import pe.spring.demo.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class LoginController {

	/*@Autowired
	private AuthenticationManager authenticationManager;*/
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	/*@Autowired
	private UserServiceImpl userDetailsService;*/
	@Autowired
	private DemoAuthenticationProvider userDetailsService;
	
	@RequestMapping(value = "/demoAuthenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		//final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		userDetailsService.authenticate(authReq);
		
		final String token = jwtTokenUtil.generateToken( (String) authReq.getPrincipal());
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	/*private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}*/
	
}