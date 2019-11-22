package pe.spring.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.spring.demo.entities.Usuario;

public interface IUserRepo extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
}
