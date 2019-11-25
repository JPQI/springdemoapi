package pe.spring.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.spring.demo.entities.Empleado;
import pe.spring.demo.services.IEmpleadoService;

/*@RestController
@RequestMapping("/empleados")*/
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping
	public List<Empleado> listar(){
		return empleadoService.listar();
	}
	
	@GetMapping("/{id}")
	public Empleado listarPorId(@PathVariable("id") Integer idEmpleado){
		return empleadoService.listarPorId(idEmpleado);
	}
	
	@PostMapping
	public Empleado registrar(@RequestBody Empleado obj) {
		return empleadoService.registrar(obj);
	}
	
	@PutMapping
	public Empleado modificar(@RequestBody Empleado obj) {
		return empleadoService.modificar(obj);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") Integer idEmpleado){
		empleadoService.eliminar(idEmpleado);
	}

}
