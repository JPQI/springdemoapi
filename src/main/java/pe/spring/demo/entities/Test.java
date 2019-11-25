package pe.spring.demo.entities;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		
		String encoded = new BCryptPasswordEncoder().encode("1234");
		System.out.println(encoded);
		
	}

}
