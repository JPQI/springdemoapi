package pe.spring.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.spring.demo.entities.Empleado;

public interface IEmpleadoRepo extends JpaRepository<Empleado, Integer> {

}
