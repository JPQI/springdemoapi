package pe.spring.demo.services;

import pe.spring.demo.entities.Empleado;

public interface IEmpleadoService extends ICRUD<Empleado> {
	
	Empleado guardarEmpleado(Empleado obj);

}
