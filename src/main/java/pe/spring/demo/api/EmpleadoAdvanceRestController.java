package pe.spring.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.spring.demo.entities.Empleado;
import pe.spring.demo.error.ModeloNotFoundException;
import pe.spring.demo.services.IEmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoAdvanceRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping
	public ResponseEntity<List<Empleado>> listar(){
		//return empleadoService.listar();
		return new ResponseEntity<List<Empleado>>(empleadoService.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> listarPorId(@PathVariable("id") Integer idEmpleado){
		//return empleadoService.listarPorId(idEmpleado);
		
		Empleado obj = empleadoService.listarPorId(idEmpleado);
		if(obj.getId() == null) {
			throw new ModeloNotFoundException("ID NO EXISTE: " + idEmpleado);
		}
		return new ResponseEntity<Empleado>(obj, HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado obj) {
		//return empleadoService.registrar(obj);
		return new ResponseEntity<Empleado>(empleadoService.registrar(obj), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Empleado> modificar(@RequestBody Empleado obj) {
		//return empleadoService.modificar(obj);
		return new ResponseEntity<Empleado>(empleadoService.modificar(obj), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer idEmpleado){
		//empleadoService.eliminar(idEmpleado);
		
		Empleado obj = empleadoService.listarPorId(idEmpleado);
		if(obj.getId() == null) {
			throw new ModeloNotFoundException("ID NO EXISTE: " + idEmpleado);
		}
		empleadoService.eliminar(idEmpleado);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
