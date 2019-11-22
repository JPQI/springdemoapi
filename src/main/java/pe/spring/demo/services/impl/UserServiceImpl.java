package pe.spring.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.spring.demo.dto.MyUserPrincipal;
import pe.spring.demo.entities.Usuario;
import pe.spring.demo.repo.IUserRepo;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
    private IUserRepo userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
